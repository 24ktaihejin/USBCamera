package com.zijin.plugin.usbcamera;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.zijin.camera_lib.CameraActivity;
import com.zijin.camera_lib.UsbFaceVerifyActivity;
import com.zijin.camera_lib.hepler.DataPersistenceHelper;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class USBCameraPlugin extends CordovaPlugin {
    private CallbackContext callbackContext;
    private int doWhat;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        this.callbackContext = callbackContext;
        if ("faceVerifyByUsbCamera".equals(action)) {
            JSONObject jsonObject = args.getJSONObject(0);
            String size = jsonObject.getString("size");
            String baseUrl = jsonObject.getString("baseUrl");

            Intent intent = new Intent(cordova.getContext(), UsbFaceVerifyActivity.class);
            intent.putExtra("doWhat", UsbFaceVerifyActivity.FOR_LOGIN);
            intent.putExtra("size", size);
            intent.putExtra("base_url", baseUrl);
            doWhat = UsbFaceVerifyActivity.FOR_LOGIN;
            cordova.startActivityForResult(this, intent, UsbFaceVerifyActivity.REQ_START_USB_CAMERA);
            return true;
        } else if ("getUserInfoByUsbCamera".equals(action)) {
            JSONObject jsonObject = args.getJSONObject(0);
            String size = jsonObject.getString("size");
            String baseUrl = jsonObject.getString("baseUrl");
            String authorization = "Bearer " + jsonObject.getString("authorization");

            Intent intent = new Intent(cordova.getContext(), UsbFaceVerifyActivity.class);
            intent.putExtra("doWhat", UsbFaceVerifyActivity.FOR_USER_INFO);
            intent.putExtra("size", size);
            intent.putExtra("base_url", baseUrl);
            intent.putExtra("authorization", authorization);
            doWhat = UsbFaceVerifyActivity.FOR_USER_INFO;
            cordova.startActivityForResult(this, intent, UsbFaceVerifyActivity.REQ_START_USB_CAMERA);
            return true;
        } else if ("faceVerifyByCamera".equals(action)) {
            JSONObject jsonObject = args.getJSONObject(0);
            String baseUrl = jsonObject.getString("baseUrl");
            Intent intent = new Intent(cordova.getContext(), CameraActivity.class);
            intent.putExtra("base_url", baseUrl);
            cordova.startActivityForResult(this, intent, CameraActivity.REQ_START_CAMERA);
            return true;
        }
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == UsbFaceVerifyActivity.REQ_START_USB_CAMERA && resultCode == Activity.RESULT_OK) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                String response = extras.getString("response", "");
                if (doWhat == UsbFaceVerifyActivity.FOR_USER_INFO) {
                    String base64Picture = DataPersistenceHelper.getBase64Picture(cordova.getContext());
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        jsonObject.put("faceBase64", base64Picture);
                        response = jsonObject.toString();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        callbackContext.error(e.getMessage());
                    }
                }
                callbackContext.success(response);
            } else {
                callbackContext.error("response is empty");
            }
        } else if (requestCode == CameraActivity.REQ_START_CAMERA && resultCode == Activity.RESULT_OK) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                String response = extras.getString("response", "");
                callbackContext.success(response);
            } else {
                callbackContext.error("response is empty");
            }
        } else if ((requestCode == CameraActivity.REQ_START_CAMERA || requestCode == UsbFaceVerifyActivity.REQ_START_USB_CAMERA) && resultCode != Activity.RESULT_OK) {
            callbackContext.error("canceled");
        }
    }
}