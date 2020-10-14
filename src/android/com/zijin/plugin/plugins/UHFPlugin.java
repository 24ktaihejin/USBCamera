package com.zijin.plugin.plugins;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.cw.r2000uhfsdk.IOnInventoryRealReceiver;
import com.cw.r2000uhfsdk.IOnTagOperation;
import com.cw.r2000uhfsdk.R2000UHFAPI;
import com.cw.r2000uhfsdk.helper.InventoryBuffer;
import com.cw.r2000uhfsdk.helper.OperateTagBuffer;
import com.google.gson.Gson;
import com.zijin.plugin.base.ICordovaPlugin;
import com.zijin.plugin.helper.MessageUtil;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Description: UHF 超高频扫描
 * Date: 10/14/20
 *
 * @author wangke
 */
public class UHFPlugin implements ICordovaPlugin {
    private static final String TAG = UHFPlugin.class.getSimpleName();
    private CallbackContext callbackContext;
    private CordovaInterface cordova;
    private R2000UHFAPI uhfApi;
    private static int WHAT_OPEN_UHF_SUCCESS = 0x01;
    private static int WHAT_OPEN_UHF_FAILED = 0x02;

    private Handler messageHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == WHAT_OPEN_UHF_SUCCESS) {
                MessageUtil.sendByPromise(callbackContext, PluginResult.Status.OK, "open uhf module success");
            } else if (msg.what == WHAT_OPEN_UHF_FAILED) {
                MessageUtil.sendByPromise(callbackContext, PluginResult.Status.ERROR, "open uhf module failed");
            }
        }
    };

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        this.cordova = cordova;
        this.uhfApi = R2000UHFAPI.getInstance();
    }

    @Override
    public void onResume(boolean multitasking) {
        if (uhfApi.getReaderHelper() != null && !uhfApi.getReaderHelper().getInventoryFlag()) {
            startInventoryReal();
        }
    }

    @Override
    public void onPause(boolean multitasking) {
        if (uhfApi.getReaderHelper() != null && uhfApi.getReaderHelper().getInventoryFlag()) {
            uhfApi.stopInventoryReal();
        }
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
        if ("openUHF".equals(action)) {
            this.callbackContext = callbackContext;
            // 开启 UHF 模块
            cordova.getThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        uhfApi.open(cordova.getContext());
                        messageHandler.sendEmptyMessage(WHAT_OPEN_UHF_SUCCESS);
                    } catch (Exception e) {
                        messageHandler.sendEmptyMessage(WHAT_OPEN_UHF_FAILED);
                    }
                }
            });
            return true;
        } else if ("startInventoryReal".equals(action)) {
            this.callbackContext = callbackContext;
            // 开始实时盘点
            cordova.getThreadPool().execute(() -> startInventoryReal());
            MessageUtil.sendNoResult(callbackContext);
            return true;
        } else if ("stopInventoryReal".equals(action)) {
            // 停止实时盘点
            this.callbackContext = callbackContext;
            cordova.getThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    if (uhfApi.getReaderHelper() != null && uhfApi.getReaderHelper().getInventoryFlag()) {
                        uhfApi.stopInventoryReal();
                    }
                }
            });
            return true;
        } else if ("closeUHF".equals(action)) {
            // 关闭 UHF 模块
            this.callbackContext = callbackContext;
            cordova.getThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    uhfApi.close();
                }
            });
            return true;
        } else if ("killTag".equals(action)) {
            this.callbackContext = callbackContext;
            // 销毁标签
            cordova.getThreadPool().execute(() -> {
                try {
                    killTag(args);
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            });
            return true;
        } else if ("lockTag".equals(action)) {
            this.callbackContext = callbackContext;
            // 锁定标签
            cordova.getThreadPool().execute(() -> {
                try {
                    lockTag(args);
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            });
            return true;
        } else if ("readTag".equals(action)) {
            this.callbackContext = callbackContext;
            // 读标签
            cordova.getThreadPool().execute(() -> {
                try {
                    readTag(args);
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            });
            return true;
        } else if ("writeTag".equals(action)) {
            this.callbackContext = callbackContext;
            // 写标签
            cordova.getThreadPool().execute(() -> {
                try {
                    writeTag(args);
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            });
            return true;
        } else if ("reset".equals(action)) {
            this.callbackContext = callbackContext;
            // 复位设备
            uhfApi.reset();
            return true;
        } else if ("setInventoryDelayMillis".equals(action)) {
            this.callbackContext = callbackContext;
            // 设置盘点时间间隔
            try {
                uhfApi.setInventoryDelayMillis(args.getJSONObject(0).getInt("delayMillis"));
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e(TAG, "setInventoryDelayMillis failed");
            }
            return true;
        } else if ("setOutputPower".equals(action)) {
            // 设置输出功率
            this.callbackContext = callbackContext;
            try {
                uhfApi.setOutputPower(args.getJSONObject(0).getInt("mOutPower"));
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e(TAG, "setOutputPower failed");
            }
            return true;
        }
        return false;
    }

    private void startInventoryReal() {
        try {
            uhfApi.startInventoryReal("1");
            uhfApi.setOnInventoryRealReceiver(new IOnInventoryRealReceiver() {
                @Override
                public void realTimeInventory() {

                }

                @Override
                public void customized_session_target_inventory(InventoryBuffer inventoryBuffer) {

                }

                @Override
                public void inventoryErr() {

                }

                @Override
                public void inventoryErrEnd() {

                }

                @Override
                public void inventoryEnd(InventoryBuffer inventoryBuffer) {
                    MessageUtil.sendByObservable(callbackContext, PluginResult.Status.OK, new Gson().toJson(inventoryBuffer));
                }

                @Override
                public void inventoryRefresh(InventoryBuffer inventoryBuffer) {

                }

                @Override
                public void onLog(String strLog, int type) {

                }
            });
        } catch (Exception e) {
            MessageUtil.sendByObservable(callbackContext, PluginResult.Status.ERROR, "start inventory failed");
            e.printStackTrace();
        }
    }

    private void writeTag(JSONArray args) throws JSONException {
        uhfApi.setOnTagOperation(new IOnTagOperation() {
            @Override
            public void getAccessEpcMatch(OperateTagBuffer operateTagBuffer) {

            }

            @Override
            public void readTagResult(OperateTagBuffer operateTagBuffer) {

            }

            @Override
            public void writeTagResult(String s) {
                callbackContext.success(s);
            }

            @Override
            public void lockTagResult() {

            }

            @Override
            public void killTagResult() {

            }

            @Override
            public void onLog(String s, int i) {

            }
        });
        JSONObject params = args.getJSONObject(0);
        Byte btMemBank = Byte.valueOf(params.getString("btMemBank"));
        String btWordAdd = params.getString("btWordAdd");
        String btWordCnt = params.getString("btWordCnt");
        String btAryPassWord = params.getString("btAryPassWord");
        String data = params.getString("data");
        int resultCode = uhfApi.writeTag(btMemBank, btWordAdd, btWordCnt, btAryPassWord, data);
        if (resultCode != 0) {
            callbackContext.error(resultCode + "");
        }
    }

    private void readTag(JSONArray args) throws JSONException {
        uhfApi.setOnTagOperation(new IOnTagOperation() {
            @Override
            public void getAccessEpcMatch(OperateTagBuffer operateTagBuffer) {

            }

            @Override
            public void readTagResult(OperateTagBuffer operateTagBuffer) {
                callbackContext.success(new Gson().toJson(operateTagBuffer));
            }

            @Override
            public void writeTagResult(String s) {

            }

            @Override
            public void lockTagResult() {

            }

            @Override
            public void killTagResult() {

            }

            @Override
            public void onLog(String s, int i) {

            }
        });
        JSONObject params = args.getJSONObject(0);
        Byte btMemBank = Byte.valueOf(params.getString("btMemBank"));
        String btWordAdd = params.getString("btWordAdd");
        String btWordCnt = params.getString("btWordCnt");
        String btAryPassWord = params.getString("btAryPassWord");
        int resultCode = uhfApi.readTag(btMemBank, btWordAdd, btWordCnt, btAryPassWord);
        if (resultCode != 0) {
            callbackContext.error(resultCode + "");
        }
    }

    private void killTag(JSONArray args) throws JSONException {
        uhfApi.setOnTagOperation(new IOnTagOperation() {
            @Override
            public void getAccessEpcMatch(OperateTagBuffer operateTagBuffer) {

            }

            @Override
            public void readTagResult(OperateTagBuffer operateTagBuffer) {

            }

            @Override
            public void writeTagResult(String s) {

            }

            @Override
            public void lockTagResult() {

            }

            @Override
            public void killTagResult() {
                callbackContext.success();
            }

            @Override
            public void onLog(String s, int i) {

            }
        });
        JSONObject params = args.getJSONObject(0);
        String btAryPassWord = params.getString("btAryPassWord");
        int resultCode = uhfApi.killTag(btAryPassWord);
        if (resultCode != 0) {
            callbackContext.error(resultCode + "");
        }
    }

    private void lockTag(JSONArray args) throws JSONException {
        uhfApi.setOnTagOperation(new IOnTagOperation() {
            @Override
            public void getAccessEpcMatch(OperateTagBuffer operateTagBuffer) {

            }

            @Override
            public void readTagResult(OperateTagBuffer operateTagBuffer) {

            }

            @Override
            public void writeTagResult(String s) {

            }

            @Override
            public void lockTagResult() {
                callbackContext.success();
            }

            @Override
            public void killTagResult() {

            }

            @Override
            public void onLog(String s, int i) {

            }
        });
        JSONObject params = args.getJSONObject(0);
        String btAryPassWord = params.getString("btAryPassWord");
        Byte btMemBank = Byte.valueOf(params.getString("btMemBank"));
        Byte btLockType = Byte.valueOf(params.getString("btLockType"));
        int resultCode = uhfApi.lockTag(btAryPassWord, btMemBank, btLockType);
        if (resultCode != 0) {
            callbackContext.error(resultCode + "");
        }
    }

    @Override
    public void onDestroy() {
        uhfApi.close();
    }
}
