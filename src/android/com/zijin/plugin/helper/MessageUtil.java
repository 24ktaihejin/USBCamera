package com.zijin.plugin.helper;

import android.os.Message;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;

/**
 * Description:
 * Date: 10/14/20
 *
 * @author wangke
 */
public class MessageUtil {
    private MessageUtil() {

    }

    public static void sendByPromise(CallbackContext callbackContext, PluginResult.Status status, String msgData) {
        PluginResult pr = new PluginResult(status, msgData);
        pr.setKeepCallback(false);
        callbackContext.sendPluginResult(pr);
    }

    public static void sendByObservable(CallbackContext callbackContext, PluginResult.Status status, String msgData) {
        PluginResult pr = new PluginResult(status, msgData);
        pr.setKeepCallback(true);
        callbackContext.sendPluginResult(pr);
    }

    public static void sendNoResult(CallbackContext callbackContext) {
        PluginResult pr = new PluginResult(PluginResult.Status.NO_RESULT);
        pr.setKeepCallback(true);
        callbackContext.sendPluginResult(pr);
    }
}
