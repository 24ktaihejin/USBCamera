package com.zijin.plugin.usbcamera;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.zijin.camera_lib.CameraActivity;
import com.zijin.camera_lib.hepler.DataPersistenceHelper;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class USBCameraPlugin extends CordovaPlugin {
    private CallbackContext callbackContext;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        this.callbackContext = callbackContext;
        if ("faceVerify".equals(action)) {
            JSONObject jsonObject = args.getJSONObject(0);
            String size = jsonObject.getString("size");
            String baseUrl = jsonObject.getString("baseUrl");

            Intent intent = new Intent(cordova.getContext(), CameraActivity.class);
            intent.putExtra("size", size);
            intent.putExtra("base_url", baseUrl);

            cordova.startActivityForResult(this, intent, CameraActivity.REQ_START_CAMERA);
            return true;
        }
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == CameraActivity.REQ_START_CAMERA && resultCode == Activity.RESULT_OK) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                String response = extras.getString("response", "");
                callbackContext.success(response);
            } else {
                callbackContext.error("response is empty");
            }
        } else if (requestCode == CameraActivity.REQ_START_CAMERA && resultCode != Activity.RESULT_OK) {
            callbackContext.error("canceled");
        }
    }
}
