package com.zjft.plugin.plugins;

import android.app.Activity;
import android.util.Log;
import android.widget.TabHost;

import com.cw.r2000uhfsdk.R2000UHFAPI;
import com.cw.r2000uhfsdk.helper.InventoryBuffer;
import com.cw.r2000uhfsdk.helper.OperateTagBuffer;
import com.cw.serialportsdk.utils.DataUtils;
import com.google.gson.Gson;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.zjft.plugin.base.AbstractOnInventoryRealReceiver;
import com.zjft.plugin.base.AbstractOnTagOperation;
import com.zjft.plugin.base.ICordovaPlugin;

/**
 * Description: UHF条码扫描插件相关功能
 * Date: 11/12/20
 *
 * @author wangke
 */
public class UHFPlugin implements ICordovaPlugin {
    private Activity context;
    private CordovaInterface cordva;
    private R2000UHFAPI uhfApi;
    private CallbackContext callbackContext;
    private boolean isStartInventory = false;
    private final static String TAG = UHFPlugin.class.getSimpleName();

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        this.cordva = cordova;
        this.context = cordova.getActivity();
        this.uhfApi = R2000UHFAPI.getInstance();
    }

    @Override
    public void onResume(boolean multitasking) {
        if (isStartInventory) {
            startInventoryReal();
        }
    }


    @Override
    public void onPause(boolean multitasking) {
        if (isStartInventory) {
            stopInventoryReal();
        }
    }


    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
        if ("openUhf".equals(action)) {
            this.callbackContext = callbackContext;
            openUhf();
            return true;
        } else if ("startInventoryReal".equals(action)) {
            this.callbackContext = callbackContext;
            startInventoryReal();
            isStartInventory = true;
            return true;
        } else if ("stopInventoryReal".equals(action)) {
            this.callbackContext = callbackContext;
            isStartInventory = false;
            stopInventoryReal();
            return true;
        } else if ("closeUhf".equals(action)) {
            this.callbackContext = callbackContext;
            closeUhf();
            return true;
        } else if ("setOutputPower".equals(action)) {
            this.callbackContext = callbackContext;
            setOutputPower(args);
            return true;
        } else if ("killTag".equals(action)) {
            this.callbackContext = callbackContext;
            killTag(args);
            return true;
        } else if ("lockTag".equals(action)) {
            this.callbackContext = callbackContext;
            lockTag(args);
            return true;
        } else if ("readTag".equals(action)) {
            this.callbackContext = callbackContext;
            readTag(args);
            return true;
        } else if ("writeTag".equals(action)) {
            this.callbackContext = callbackContext;
            writeTag(args);
            return true;
        } else if ("reset".equals(action)) {
            this.callbackContext = callbackContext;
            reset();
            return true;
        }
        return false;
    }

    private void reset() {
        this.uhfApi.reset();
    }

    private void writeTag(JSONArray args) {
        uhfApi.setOnTagOperation(new AbstractOnTagOperation() {
            @Override
            public void writeTagResult(String s) {
                super.writeTagResult(s);
                callbackContext.success(s);
            }
        });
        try {
            JSONObject params = args.getJSONObject(0);
            Byte btMemBank = Byte.valueOf(params.getString("btMemBank"));
            String btWordAdd = params.getString("btWordAdd");
            String btWordCnt = params.getString("btWordCnt");
            String btAryPassWord = params.getString("btAryPassWord");
            String data = params.getString("data");
            int resultCode = uhfApi.writeTag(btMemBank, btWordAdd, btWordCnt, btAryPassWord, data);
            if (resultCode != 0) {
                callbackContext.error("write tag failed, result code: " + resultCode);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            callbackContext.error("write tag failed, cause: " + e.getMessage());
        }
    }

    private void readTag(JSONArray args) {
        uhfApi.setOnTagOperation(new AbstractOnTagOperation() {
            @Override
            public void readTagResult(OperateTagBuffer operateTagBuffer) {
                super.readTagResult(operateTagBuffer);
                callbackContext.success(new Gson().toJson(operateTagBuffer));
            }
        });
        try {
            JSONObject params = args.getJSONObject(0);
            Byte btMemBank = Byte.valueOf(params.getString("btMemBank"));
            String btWordAdd = params.getString("btWordAdd");
            String btWordCnt = params.getString("btWordCnt");
            String btAryPassWord = params.getString("btAryPassWord");
            int resultCode = uhfApi.readTag(btMemBank, btWordAdd, btWordCnt, btAryPassWord);
            if (resultCode != 0) {
                callbackContext.error("read tag failed, result code: " + resultCode);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            callbackContext.error("read tag failed, cause: " + e.getMessage());
        }
    }

    private void lockTag(JSONArray args) {
        uhfApi.setOnTagOperation(new AbstractOnTagOperation() {
            @Override
            public void lockTagResult() {
                super.lockTagResult();
                callbackContext.success("lock tag success.");
            }
        });
        try {
            JSONObject params = args.getJSONObject(0);
            String btAryPassWord = params.getString("btAryPassWord");
            byte btMemBank = Byte.parseByte(params.getString("btMemBank")); // "0" "1" "2" "3"
            byte btLockType = Byte.parseByte(params.getString("btLockType")); // "0" "1" "2" "3"
            int resultCode = uhfApi.lockTag(btAryPassWord, btMemBank, btLockType);
            if (resultCode != 0) {
                callbackContext.error("lock tag failed, result code: " + resultCode);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            callbackContext.error("lock tag failed, cause: " + e.getMessage());
        }

    }

    private void killTag(JSONArray args) {
        uhfApi.setOnTagOperation(new AbstractOnTagOperation() {
            @Override
            public void killTagResult() {
                super.killTagResult();
                callbackContext.success("kill tag success.");
            }
        });
        try {
            JSONObject params = args.getJSONObject(0);
            String btAryPassWord = params.getString("btAryPassWord");
            int resultCode = uhfApi.killTag(btAryPassWord);
            if (resultCode != 0) {
                callbackContext.error("kill tag failed, result code: " + resultCode);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            callbackContext.error("kill tag failed, cause: " + e.getMessage());
        }
    }

    private void stopInventoryReal() {
        cordva.getThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                if (uhfApi.getReaderHelper() != null && uhfApi.getReaderHelper().getInventoryFlag()) {
                    uhfApi.stopInventoryReal();
                    Log.i(TAG, "停止盘点");
                    callbackContext.success("stop inventory success.");
                }
            }
        });
    }

    private void startInventoryReal() {
        try {
            uhfApi.startInventoryReal("1");
            uhfApi.setOnInventoryRealReceiver(new AbstractOnInventoryRealReceiver() {
                @Override
                public void inventoryEnd(InventoryBuffer inventoryBuffer) {
                    super.inventoryEnd(inventoryBuffer);
                    Log.i(TAG, "扫描到的结果：" + inventoryBuffer.toString());
                    PluginResult pr = new PluginResult(PluginResult.Status.OK, new Gson().toJson(inventoryBuffer.lsTagList));
                    pr.setKeepCallback(true);
                    callbackContext.sendPluginResult(pr);
                }
            });
        } catch (Exception e) {
            PluginResult pr = new PluginResult(PluginResult.Status.ERROR, "start inventory failed: " + e.getMessage());
            pr.setKeepCallback(true);
            callbackContext.sendPluginResult(pr);
        }
    }

    private void openUhf() {
        cordva.getThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                uhfApi.open(context);
                Log.i(TAG, "uhf开启成功");
                callbackContext.success("uhf module open success.");
            }
        });
    }

    private void closeUhf() {
        this.cordva.getThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                uhfApi.close();
                Log.i(TAG, "uhf开启失败");
                if (callbackContext != null) {
                    callbackContext.success("close uhf success.");
                }
            }
        });
    }

    private void setOutputPower(JSONArray args) {
        try {
            int power = args.getInt(0);
            if (power <= 0 || power > 33) {
                PluginResult pluginResult = new PluginResult(PluginResult.Status.ERROR);
                callbackContext.error("please input output power in（1 ~ 33dBm)");
                return;
            }
            uhfApi.setOutputPower(power);
            callbackContext.success("set output power success");
        } catch (JSONException e) {
            e.printStackTrace();
            callbackContext.error("set output power failed.");
        }
    }

    @Override
    public void onDestroy() {
        closeUhf();
    }
}
