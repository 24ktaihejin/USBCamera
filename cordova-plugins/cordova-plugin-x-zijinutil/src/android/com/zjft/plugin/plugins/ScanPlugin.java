package com.zjft.plugin.plugins;

import android.app.Activity;
import android.util.Log;

import com.cw.barcodesdk.SoftDecodingAPI;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

import com.zjft.plugin.base.AbstractBarcodeData;
import com.zjft.plugin.base.ICordovaPlugin;

/**
 * Description: 条码扫描插件功能
 * Date: 11/12/20
 *
 * @author wangke
 */
public class ScanPlugin extends AbstractBarcodeData implements ICordovaPlugin {
    private SoftDecodingAPI softDecodingAPI;
    private Activity context;
    private CallbackContext callbackContext;
    private String action;
    private boolean isContinueScanning = false;
    private boolean scanReceiverIsOpen = false;
    private final static String TAG = ScanPlugin.class.getSimpleName();

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        this.context = cordova.getActivity();
        this.softDecodingAPI = new SoftDecodingAPI(context, this);
        this.softDecodingAPI.setGlobalSwicth(true);
        openScanReceiver();
    }

    @Override
    public void onResume(boolean multitasking) {
        openScanReceiver();
        if (isContinueScanning) {
            startContinueScanning();
        }
    }


    @Override
    public void onPause(boolean multitasking) {
        closeScanReceiver();
        if (isContinueScanning) {
            stopContinueScanning();
        }
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
        this.action = action;
        if ("openScanReceiver".equals(action)) {
            this.callbackContext = callbackContext;
            if (!this.scanReceiverIsOpen) {
                openScanReceiver();
            }
            return true;
        } else if ("closeScanReceiver".equals(action)) {
            if (this.scanReceiverIsOpen) {
                closeScanReceiver();
            }
            return true;
        } else if ("scan".equals(action)) {
            if (!this.scanReceiverIsOpen){
                openScanReceiver();
            }
            this.callbackContext = callbackContext;
            scanBarcode();
            return true;
        } else if ("continueScanning".equals(action)) {
            this.callbackContext = callbackContext;
            if (!this.scanReceiverIsOpen){
                openScanReceiver();
            }
            startContinueScanning();
            isContinueScanning = true;
            return true;
        } else if ("stopContinueScanning".equals(action)) {
            this.callbackContext = callbackContext;
            stopContinueScanning();
            isContinueScanning = false;
            return true;
        } else if ("setScanInterval".equals(action)) {
            try {
                softDecodingAPI.setTime(args.getJSONObject(0).getInt("intervalTime"));
            } catch (JSONException e) {
                Log.e(TAG, "set scan interval failed.");
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    private void startContinueScanning() {
        softDecodingAPI.ContinuousScanning();
    }

    private void stopContinueScanning() {
        softDecodingAPI.CloseScanning();
    }

    private void scanBarcode() {
        if (softDecodingAPI == null) {
            return;
        }
        softDecodingAPI.scan();
    }

    private void openScanReceiver() {
        this.scanReceiverIsOpen = true;
        this.softDecodingAPI.openBarCodeReceiver();
    }

    private void closeScanReceiver() {
        if (this.softDecodingAPI != null) {
            this.softDecodingAPI.closeBarCodeReceiver();
            this.scanReceiverIsOpen = false;
        }
    }

    private void close() {
        closeScanReceiver();
        this.softDecodingAPI.setGlobalSwicth(false);
    }

    @Override
    public void onDestroy() {
        close();
    }

    // region 条码扫描结果回调
    @Override
    public void onBarCodeData(String s) {
        // observable
        if ("continueScanning".equals(action)) {
            PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, s);
            pluginResult.setKeepCallback(true);
            callbackContext.sendPluginResult(pluginResult);
            // observable
        } else if ("openScanReceiver".equals(action)) {
            PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, s);
            pluginResult.setKeepCallback(true);
            callbackContext.sendPluginResult(pluginResult);
            // promise
        } else if ("scan".equals(action)) {
            callbackContext.success(s);
        }
    }
    // end region
}
