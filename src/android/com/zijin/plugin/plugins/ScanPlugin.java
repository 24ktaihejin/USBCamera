package com.zijin.plugin.plugins;

import android.text.TextUtils;

import com.cw.barcodesdk.SoftDecodingAPI;
import com.zijin.plugin.base.ICordovaPlugin;
import com.zijin.plugin.helper.MessageUtil;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * Description: 条码扫描
 * Date: 10/14/20
 *
 * @author wangke
 */
public class ScanPlugin implements ICordovaPlugin, SoftDecodingAPI.IBarCodeData {
    private static final String TAG = ScanPlugin.class.getSimpleName();
    private CallbackContext callbackContext;
    private SoftDecodingAPI softDecodingAPI;
    private CordovaInterface cordova;
    private boolean isCloseScan = false;
    private boolean isScanning = false;

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        this.cordova = cordova;
        this.softDecodingAPI = new SoftDecodingAPI(cordova.getContext(), this);
    }

    @Override
    public void onResume(boolean multitasking) {
        if (softDecodingAPI == null || isCloseScan) {
            return;
        }
        if (isScanning) {
            softDecodingAPI.setTime(800);
            softDecodingAPI.ContinuousScanning();
        }
        softDecodingAPI.openBarCodeReceiver();
    }

    @Override
    public void onPause(boolean multitasking) {
        if (softDecodingAPI == null || isCloseScan) {
            return;
        }
        if (isScanning) {
            softDecodingAPI.CloseScanning();
        }
        softDecodingAPI.closeBarCodeReceiver();
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
        if ("scan".equals(action)) {
            this.callbackContext = callbackContext;
            MessageUtil.sendNoResult(callbackContext);
            // 单次扫描
            softDecodingAPI.scan();
            return true;
        } else if ("continueScanning".equals(action)) {
            this.callbackContext = callbackContext;
            MessageUtil.sendNoResult(callbackContext);
            // 多次扫描
            softDecodingAPI.setTime(800);
            softDecodingAPI.ContinuousScanning();
            this.isScanning = true;
            return true;
        } else if ("closeScanning".equals(action)) {
            this.callbackContext = callbackContext;
            // 关闭扫描
            softDecodingAPI.CloseScanning();
            softDecodingAPI.closeBarCodeReceiver();
            this.isScanning = false;
            return true;
        } else if ("setScanInterval".equals(action)) {
            this.callbackContext = callbackContext;
            // 设置连续扫描的时间间隔
            try {
                softDecodingAPI.setTime(args.getJSONObject(0).getInt("time"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    @Override
    public void onDestroy() {
        if (softDecodingAPI == null) {
            return;
        }
        softDecodingAPI.closeBarCodeReceiver();
    }

    /**
     * 当触发扫码动作时回调
     */
    @Override
    public void sendScan() {

    }

    /**
     * 当扫描到结果时回调
     *
     * @param barcode 条码值
     */
    @Override
    public void onBarCodeData(String barcode) {
        if (TextUtils.isEmpty(barcode) || barcode.contains("No decoded message available.")) {
            return;
        }
        MessageUtil.sendByObservable(callbackContext, PluginResult.Status.OK, barcode);
    }

    /**
     * 获取当前扫描机的设置
     *
     * @param PowerOnOff
     * @param OutputMode
     * @param TerminalChar
     * @param Prefix
     * @param Suffix
     * @param Volume
     * @param PlayoneMode
     */
    @Override
    public void getSettings(int PowerOnOff, int OutputMode, int TerminalChar, String Prefix, String Suffix, int Volume, int PlayoneMode) {

    }

    /**
     * 设置扫码机设置成功回调
     */
    @Override
    public void setSettingsSuccess() {

    }
}
