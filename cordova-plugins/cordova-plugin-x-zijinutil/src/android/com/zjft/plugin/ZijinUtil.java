package com.zjft.plugin;

import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import com.zjft.plugin.base.ICordovaPlugin;
import com.zjft.plugin.plugins.FingerprintPlugin;
import com.zjft.plugin.plugins.ScanPlugin;
import com.zjft.plugin.plugins.UHFPlugin;

/**
 * 插件功能调用入口
 */
public class ZijinUtil extends CordovaPlugin {
    List<ICordovaPlugin> plugins = new ArrayList<>();

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        initPlugins(plugins);
        callPluginsInitialize(plugins, cordova, webView);
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        return callPluginsExecute(plugins, action, args, callbackContext);
    }

    /**
     * 初始化插件
     *
     * @param plugins
     */
    private void initPlugins(List<ICordovaPlugin> plugins) {
        if (plugins.size() != 0) {
            for (ICordovaPlugin plugin : plugins) {
                plugin.onDestroy();
            }
        }
        plugins.clear();
        plugins.add(new FingerprintPlugin());
        plugins.add(new ScanPlugin());
        plugins.add(new UHFPlugin());
    }

    /**
     * 调用插件的初始化方法
     *
     * @param plugins
     * @param cordova
     * @param webView
     */
    private void callPluginsInitialize(List<ICordovaPlugin> plugins, CordovaInterface cordova, CordovaWebView webView) {
        for (ICordovaPlugin plugin : plugins) {
            plugin.initialize(cordova, webView);
        }
    }

    /**
     * 根据 action 调用与之匹配的插件功能
     *
     * @param plugins
     * @param action
     * @param args
     * @param callbackContext
     * @return
     */
    private boolean callPluginsExecute(List<ICordovaPlugin> plugins, String action, JSONArray args, CallbackContext callbackContext) {
        for (ICordovaPlugin plugin : plugins) {
            if (plugin.execute(action, args, callbackContext)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onPause(boolean multitasking) {
        super.onPause(multitasking);
        for (ICordovaPlugin plugin : plugins) {
            plugin.onPause(multitasking);
        }
    }

    @Override
    public void onResume(boolean multitasking) {
        super.onResume(multitasking);
        for (ICordovaPlugin plugin : plugins){
            plugin.onResume(multitasking);
        }
    }

    @Override
    public void onDestroy() {
        for (ICordovaPlugin plugin : plugins) {
            plugin.onDestroy();
        }
    }
}
