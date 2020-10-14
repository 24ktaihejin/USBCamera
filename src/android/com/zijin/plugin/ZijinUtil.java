package com.zijin.plugin;

import com.zijin.plugin.base.ICordovaPlugin;
import com.zijin.plugin.plugins.FingerprintPlugin;
import com.zijin.plugin.plugins.ScanPlugin;
import com.zijin.plugin.plugins.UHFPlugin;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 插件功能入口
 * Date: 10/14/20
 *
 * @author wangke
 */
public class ZijinUtil extends CordovaPlugin {

    private List<ICordovaPlugin> plugins = new ArrayList<>();

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


    @Override
    public void onPause(boolean multitasking) {
        super.onPause(multitasking);
        callPluginsPause(plugins, multitasking);
    }

    @Override
    public void onResume(boolean multitasking) {
        super.onResume(multitasking);
        callPluginsResume(plugins, multitasking);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        callPluginsDestory(plugins);
    }

    private void initPlugins(List<ICordovaPlugin> plugins) {
        cordova.getThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                if (plugins.size() != 0) {
                    for (ICordovaPlugin plugin : plugins) {
                        plugin.onDestroy();
                    }
                }
            }
        });
        plugins.clear();
        plugins.add(new FingerprintPlugin());
        plugins.add(new ScanPlugin());
        plugins.add(new UHFPlugin());
    }


    private void callPluginsInitialize(List<ICordovaPlugin> plugins, CordovaInterface cordova, CordovaWebView webView) {
        for (ICordovaPlugin plugin : plugins) {
            plugin.initialize(cordova, webView);
        }
    }

    private boolean callPluginsExecute(List<ICordovaPlugin> plugins, String action, JSONArray args, CallbackContext callbackContext) {
        for (ICordovaPlugin plugin : plugins) {
            if (plugin.execute(action, args, callbackContext)) {
                return true;
            }
        }
        return false;
    }

    private void callPluginsPause(List<ICordovaPlugin> plugins, boolean multitasking) {
        for (ICordovaPlugin plugin : plugins) {
            plugin.onResume(multitasking);
        }
    }

    private void callPluginsResume(List<ICordovaPlugin> plugins, boolean multitasking) {
        for (ICordovaPlugin plugin : plugins) {
            plugin.onResume(multitasking);
        }
    }

    private void callPluginsDestory(List<ICordovaPlugin> plugins) {
        for (ICordovaPlugin plugin : plugins) {
            plugin.onDestroy();
        }
    }

}
