package com.zijin.plugin.base;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * Description: 定义插件通用方法接口
 * Date: 10/14/20
 *
 * @author wangke
 */
public interface ICordovaPlugin {
    /**
     * 初始化插件
     *
     * @param cordova
     * @param webView
     */
    public void initialize(CordovaInterface cordova, CordovaWebView webView);

    /**
     * Activitiy 重新可见时回调
     *
     * @param multitasking
     */
    public void onResume(boolean multitasking);

    /**
     * 执行插件功能
     *
     * @param action
     * @param args
     * @param callbackContext
     * @return
     * @throws JSONException
     */
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext);

    /**
     * 当 Activity 被遮挡或不可见时回调
     *
     * @param multitasking
     */
    public void onPause(boolean multitasking);

    /**
     * 当 Activity 被销毁时回调
     */
    public void onDestroy();

}
