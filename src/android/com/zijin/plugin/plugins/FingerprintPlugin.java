package com.zijin.plugin.plugins;

import android.annotation.SuppressLint;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.cw.fpjrasdk.JRA_API;
import com.cw.fpjrasdk.USBFingerManager;
import com.cw.serialportsdk.utils.DataUtils;
import com.google.gson.Gson;
import com.zijin.plugin.base.ICordovaPlugin;
import com.zijin.plugin.helper.FileWRTool;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


import static com.cw.fpjrasdk.syno_usb.OTG_KEY.PS_OK;

/**
 * Description: 指纹功能插件
 * Date: 10/14/20
 *
 * @author wangke
 */
public class FingerprintPlugin implements ICordovaPlugin {
    private static final String TAG = FingerprintPlugin.class.getSimpleName();
    private static final String FILE_NAME = "fingerData.txt";

    private CordovaInterface cordova;
    private CallbackContext callbackContext;
    private JRA_API jraApi;
    private boolean fpOpened = false;
    private static int fingerCnt = 1;
    private HashMap<String, String> fingerMap;

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        this.cordova = cordova;
        USBFingerManager.getInstance(cordova.getContext()).setDelayMs(500);
    }

    @Override
    public void onResume(boolean multitasking) {

    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
        if ("openFingerprint".equals(action)) {
            this.callbackContext = callbackContext;
            cordova.getThreadPool().execute(() -> {
                openDevice();
            });
            return true;
        } else if ("closeFingerprint".equals(action)) {
            this.callbackContext = callbackContext;
            cordova.getThreadPool().execute(() -> {
                closeDevice();
            });
            return true;
        } else if ("verifyFingerprint".equals(action)) {
            this.callbackContext = callbackContext;
            cordova.getThreadPool().execute(() -> {
                SearchAsyncTask asyncTask_search = new SearchAsyncTask();
                asyncTask_search.execute(1);
            });
            return true;
        } else if ("loadFpData".equals(action)) {
            this.callbackContext = callbackContext;
            cordova.getThreadPool().execute(() -> {
                JSONObject params;
                String[] verifyList;
                try {
                    params = args.getJSONObject(0);
                    verifyList = params.getString("chars").split("\\$");
                    if (fpOpened == false) {
                        callbackContext.error("指纹仪未打开");
                        return;
                    }
                    if (jraApi != null && jraApi.PSEmpty() != PS_OK) {
                        Log.w(TAG, "指纹库清空异常!");
                        callbackContext.error("指纹库初始化异常");
                        return;
                    }
                    String fileData = "";
                    if (fingerMap == null) {
                        fingerMap = new HashMap<>();
                    }
                    for (int i = 0; i < verifyList.length; i++) {
                        if (verifyList[i].length() != 1024) {
                            continue;
                        }
                        int[] id = new int[1];
                        if (jraApi.PSDownCharToJRA(DataUtils.hexStringTobyte(verifyList[i]), id) != PS_OK) {
                            Log.w(TAG, "存储模板失败!");
                        } else {
                            fileData += String.valueOf(id[0]) + "$" + verifyList[i] + "&";
                            fingerMap.put(String.valueOf(id[0]), verifyList[i]);
                        }
                    }
                    for (int i : jraApi.getUserId()) {
                        Log.i(TAG, String.valueOf(i));
                    }
                    FileWRTool.writeFile(this.cordova.getContext(), FILE_NAME, fileData);
                    callbackContext.success();
                } catch (JSONException e) {
                    e.printStackTrace();
                    callbackContext.error("指纹库初始化异常");
                }
            });
            return true;
        } else if ("scanFingerprint".equals(action)) {
            this.callbackContext = callbackContext;
            InputAsyncTask asyncTask = new InputAsyncTask();
            asyncTask.execute(1);
            return true;
        }
        return false;
    }

    public void openDevice() {
        closeDevice();
        USBFingerManager.getInstance(cordova.getContext()).openUSB(new USBFingerManager.OnUSBFingerListener() {
            @Override
            public void onOpenUSBFingerSuccess(String s, UsbManager usbManager, UsbDevice usbDevice) {
                if (s.equals(USBFingerManager.BYD_SMALL_DEVICE)) {
                    Log.i(TAG, "切换USB成功");

                    jraApi = new JRA_API(usbManager, usbDevice);
                    int ret = jraApi.openJRA();
                    if (ret == JRA_API.DEVICE_SUCCESS) {
                        Log.e(TAG, "open device success!");
                        fpOpened = true;
                        callbackContext.success();
                    } else if (ret == JRA_API.PS_DEVICE_NOT_FOUND) {
                        callbackContext.error("can't find this device!");
                    } else if (ret == JRA_API.PS_EXCEPTION) {
                        callbackContext.error("open device fail");
                    }

                }
            }

            @Override
            public void onOpenUSBFingerFailure(String s) {
                callbackContext.error(s);
            }
        });
    }

    public void closeDevice() {
        try {
            if (jraApi != null) {
                jraApi.closeJRA();
            }
            Log.e(TAG, "Device Closed");
            fpOpened = false;
            USBFingerManager.getInstance(cordova.getContext()).closeUSB();
        } catch (Exception e) {
            Log.i(TAG, "Exception: => " + e.toString());
        }
    }

    /**
     * 采集指纹
     */
    @SuppressLint("StaticFieldLeak")
    private class InputAsyncTask extends AsyncTask<Integer, String, Integer> {
        Toast toast;

        @Override
        protected Integer doInBackground(Integer... params) {
            int cnt = 1;
            long startTime = System.currentTimeMillis(), timeout = 2 * 60 * 1000;

            while (true) {
                if (!fpOpened) {
                    return -1;
                }
                if (System.currentTimeMillis() - startTime > timeout) {
                    callbackContext.error(new Gson().toJson(new ErrorResult(ErrorCode.TIMEOUT, "指纹录入超时!")));
                    return -1;
                }
                while (jraApi.PSGetImage() != JRA_API.PS_NO_FINGER) {
                    if (!fpOpened) {
                        return -1;
                    }
                    if (System.currentTimeMillis() - startTime > timeout) {
                        callbackContext.error(new Gson().toJson(new ErrorResult(ErrorCode.TIMEOUT, "指纹录入超时!")));
                        return -1;
                    }
                    sleep(200);
                    publishProgress("请离开手指!");
                }
                while (jraApi.PSGetImage() == JRA_API.PS_NO_FINGER) {
                    if (!fpOpened) {
                        return -1;
                    }
                    if (System.currentTimeMillis() - startTime > timeout) {
                        callbackContext.error(new Gson().toJson(new ErrorResult(ErrorCode.TIMEOUT, "指纹录入超时!")));
                        return -1;
                    }
                    sleep(200);
                    if (cnt == 1) {
                        publishProgress("请按压手指!");
                    } else {
                        publishProgress("请再次按压手指!");
                    }
                }
                Log.i(TAG, "-----开始采集-----");
                if (cnt == 1) {
                    if (jraApi.PSGenChar(JRA_API.CHAR_BUFFER_A) != PS_OK) {
                        cnt--;
                    }
                }
                if (cnt == 2) {
                    if (jraApi.PSGenChar(JRA_API.CHAR_BUFFER_B) != PS_OK) {
                        continue;
                    }
                    if (jraApi.PSRegModule() != PS_OK) {
                        publishProgress("生成模板失败，请重新录入");
                        return -1;
                    }
                    int[] fingerId = new int[1];
                    byte[] g_TempData = new byte[512];

                    if (jraApi.PSStoreChar(fingerId, g_TempData) != PS_OK) {
                        publishProgress("存储特征失败，请重新录入");
                        return -1;
                    }
                    Log.e(TAG, "特征值: " + DataUtils.bytesToHexString(g_TempData));
                    callbackContext.success(DataUtils.bytesToHexString(g_TempData));
                    publishProgress("OK");
                    return 0;
                }
                cnt++;
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            if ("OK".equals(values[0])) {
                toast.cancel();
                return;
            }
            if (toast != null) {
                toast.cancel();
            }
            toast = Toast.makeText(cordova.getContext(), values[0], Toast.LENGTH_LONG);
            toast.show();
            Log.i(TAG, values[0]);
        }

        @Override
        protected void onPostExecute(Integer result) {
            if (0 == result) {
                if (fingerCnt > 256) {
                    Log.i(TAG, "fingerCnt > 256");
                    return;
                }
                fingerCnt++;
                Log.i(TAG, "Enroll Success fingerCnt = " + fingerCnt);
            } else {
                Log.i(TAG, "Enroll Error " + result);
                callbackContext.error("Sy Enroll:" + result);
            }
        }

        @Override
        protected void onPreExecute() {
            Log.i(TAG, "Please press finger...");
        }
    }

    /**
     * 搜索指纹
     */
    private class SearchAsyncTask extends AsyncTask<Integer, String, Integer> {
        int exeCount = 0;

        @Override
        protected Integer doInBackground(Integer... params) {
            int[] fingerId = new int[1];
            long startTime = System.currentTimeMillis(), timeout = 60 * 1000;
            try {
                while (true) {
                    if (System.currentTimeMillis() - startTime > timeout) {
                        callbackContext.error(new Gson().toJson(new ErrorResult(ErrorCode.TIMEOUT, "指纹搜索超时!")));
                        return -1;
                    }
                    if (!fpOpened) {
                        callbackContext.error(new Gson().toJson(new ErrorResult(ErrorCode.UNUSED, "指纹设备未打开!")));
                        return -1;
                    }
                    if (exeCount > 2) {
                        callbackContext.error(new Gson().toJson(new ErrorResult(ErrorCode.UNFIND, "未找到该指纹!")));
                        return -1;
                    }
                    while (jraApi.PSGetImage() == JRA_API.PS_NO_FINGER) {
                        if (System.currentTimeMillis() - startTime > timeout) {
                            callbackContext.error(new Gson().toJson(new ErrorResult(ErrorCode.TIMEOUT, "指纹搜索超时!")));
                            return -1;
                        }
                        if (!fpOpened) {
                            callbackContext.error(new Gson().toJson(new ErrorResult(ErrorCode.UNUSED, "指纹设备未打开!")));
                            return -1;
                        }
                        sleep(20);
                    }
                    if (jraApi.PSGenChar(JRA_API.CHAR_BUFFER_A) != PS_OK) {
                        continue;
                    }
                    if (PS_OK != jraApi.PSSearch(JRA_API.CHAR_BUFFER_A, fingerId)) {
                        exeCount++;
                        publishProgress("没有找到该指纹");
                        continue;
                    }

                    if (fingerMap == null) {
                        String fingerData = FileWRTool.readFile(cordova.getContext(), FILE_NAME);
                        String[] fingerList = fingerData.split("&");
                        fingerMap = new HashMap<>();
                        for (int i = 0; i < fingerList.length; i++) {
                            String[] fingerItem = fingerList[i].split("\\$");
                            fingerMap.put(fingerItem[0], fingerItem[1]);
                        }
                    }
                    Log.i(TAG, "匹配指纹特征:[" + fingerId[0] + "][" + fingerMap.get(String.valueOf(fingerId[0])) + "]");
                    callbackContext.success(fingerMap.get(String.valueOf(fingerId[0])));
                    return 0;
                }
            } catch (Exception e) {
                callbackContext.error(new Gson().toJson(new ErrorResult(ErrorCode.EXCEPTION, e.getMessage())));
                return -1;
            }
        }

        @Override
        protected void onPostExecute(Integer result) {
        }

        @Override
        protected void onPreExecute() {
        }
    }

    /**
     * 延时
     *
     * @param time
     */
    private void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
            e.toString();
        }
    }

    public class ErrorCode {
        public static final int TIMEOUT = 94;
        public static final int UNFIND = 97;
        public static final int UNUSED = 98;
        public static final int EXCEPTION = 99;
    }

    public class ErrorResult {
        private int errorCode;
        private String errorMsg;

        public ErrorResult(int errorCode, String errorMsg) {
            this.errorCode = errorCode;
            this.errorMsg = errorMsg;
        }

        public int getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(int errorCode) {
            this.errorCode = errorCode;
        }

        public String getErrorMsg() {
            return errorMsg;
        }

        public void setErrorMsg(String errorMsg) {
            this.errorMsg = errorMsg;
        }
    }

    @Override
    public void onPause(boolean multitasking) {

    }

    @Override
    public void onDestroy() {
        closeDevice();
    }
}
