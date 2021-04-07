package com.zjft.plugin.plugins;

import android.app.Activity;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.AsyncTask;
import android.os.Looper;
import android.util.Log;

import com.cw.fpfbbsdk.FingerPrintAPI;
import com.cw.serialportsdk.USB.USBFingerManager;
import com.cw.serialportsdk.utils.DataUtils;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.com.aratek.fp.Bione;
import cn.com.aratek.fp.FingerprintImage;
import cn.com.aratek.fp.FingerprintScanner;
import cn.com.aratek.util.Result;

import com.zjft.plugin.base.ICordovaPlugin;

/**
 * Description: 指纹相关功能
 * Date: 11/12/20
 *
 * @author wangke
 */
public class FingerprintPlugin implements ICordovaPlugin {
    private CallbackContext callbackContext;
    private Activity context;
    private CordovaInterface cordova;
    private FingerprintScanner scanner;
    private FingerPrintAPI fpApi;
    private boolean fpDeviceOpenStatus = false;
    private static String FP_DB_PATH = "";
    private FingerprintAsyncTask fingerprintTask;
    private boolean taskIsDone = false;
    private static final String TAG = FingerprintPlugin.class.getSimpleName();

    public FingerprintPlugin() {

    }

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        this.cordova = cordova;
        this.context = cordova.getActivity();
        initDBPath();
    }

    @Override
    public void onResume(boolean multitasking) {

    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
        if ("openFingerprint".equals(action)) {
            this.callbackContext = callbackContext;
            switchUsbDeviceAndOpenFp();
            return true;
        } else if ("closeFingerprint".equals(action)) {
            this.callbackContext = callbackContext;
            closeFingerprint();
            return true;
        } else if ("scanFingerprint".equals(action)) {
            this.callbackContext = callbackContext;
            scanFingerprint();
            return true;
        } else if ("verifyFingerprint".equals(action)) {
            this.callbackContext = callbackContext;
            verifyFingerprint();
            return true;
        } else if ("loadFpData".equals(action)) {
            this.callbackContext = callbackContext;
            loadFpData(args);
            return true;
        }
        return false;
    }

    /**
     * 加载外部指纹数据到指纹库
     *
     * @param args
     */
    private void loadFpData(JSONArray args) {
        this.cordova.getThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    clearFpDB();
                    List<String> fpDataList = loadFpDataListFromArgs(args);
                    batchEnrollFp(fpDataList);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(TAG, "load fp data failed: " + e.getMessage());
                    callbackContext.error("load fp data failed: " + e.getMessage());
                }
            }
        });
    }

    /**
     * ["fp1", "fp2", "fp3", "fp4"]
     *
     * @param args
     * @return
     */
    private List<String> loadFpDataListFromArgs(JSONArray args) throws JSONException {
        List<String> fpDataList = new ArrayList<>();
        if (args != null) {
            for (int i = 0; i < args.length(); i++) {
                String fpData = args.getString(i);
                fpDataList.add(fpData);
            }
        }
        return fpDataList;
    }

    /**
     * 批量向库中写入指纹数据
     *
     * @param fpDataList
     */
    private void batchEnrollFp(List<String> fpDataList) {
        if (fpApi == null || scanner == null) {
            Log.e(TAG, "please turn on the fingerprint before performing this operation.");
            this.callbackContext.error("please turn on the fingerprint before performing this operation.");
            return;
        }
        for (String fpData : fpDataList) {
            int freeID = this.fpApi.getFreeID();
            if (freeID < 0) {
                clearFpDB();
                Log.e(TAG, "batch enroll fp failed, cause: cant get usable free fp ID.");
                this.callbackContext.error("batch enroll fp failed, cause: cant get usable free fp ID.");
                break;
            } else {
                int ret = this.fpApi.enroll(freeID, DataUtils.hexStringTobyte(fpData));
                if (ret != Bione.RESULT_OK) {
                    clearFpDB();
                    Log.e(TAG, "batch enroll fp failed");
                    this.callbackContext.error("batch enroll fp failed.");
                    break;
                }
            }
        }
        this.callbackContext.success("batch enroll fp success.");
    }

    /**
     * 从指纹库中匹配当前录入的指纹是否存在
     */
    private void verifyFingerprint() {
        if (fpApi == null || scanner == null) {
            Log.i(TAG, "please turn on the fingerprint before performing this operation.");
            this.callbackContext.error("please turn on the fingerprint before performing this operation.");
            return;
        }
        this.fingerprintTask = new FingerprintAsyncTask();
        this.fingerprintTask.execute("identify");
    }

    /**
     * 录入指纹
     */
    private void scanFingerprint() {
        if (fpApi == null || scanner == null) {
            Log.i(TAG, "please turn on the fingerprint before performing this operation.");
            this.callbackContext.error("please turn on the fingerprint before performing this operation.");
            return;
        }
        this.fingerprintTask = new FingerprintAsyncTask();
        this.fingerprintTask.execute("enroll");
    }


    /**
     * 打开指纹模块
     */
    private void switchUsbDeviceAndOpenFp() {
        USBFingerManager.getInstance(context).openUSB(new USBFingerManager.OnUSBFingerListener() {
            @Override
            public void onOpenUSBFingerSuccess(String s, UsbManager usbManager, UsbDevice usbDevice) {
                scanner = new FingerprintScanner(context);
                fpApi = FingerPrintAPI.getInstance();
                openFingerprint();
            }

            @Override
            public void onOpenUSBFingerFailure(String s, int i) {
                // sdk调用逻辑存在问题，不可通过此回调判定模块开启状态
            }
        });
    }

    /**
     * 开启指纹模块
     */
    private void openFingerprint() {
        if (scanner == null || fpApi == null) {
            return;
        }
        if (scanner.open() != FingerprintScanner.RESULT_OK) {
            Log.e(TAG, "open fingerprint failed");
            this.callbackContext.error("open fingerprint failed");
            return;
        } else {
            fpDeviceOpenStatus = true;
        }
        if (fpApi.initialize(context, FP_DB_PATH) != Bione.RESULT_OK) {
            Log.e(TAG, "open fpApi failed");
            this.callbackContext.error("open fpApi failed");
            return;
        }
        this.callbackContext.success("open fingerprint success, current version: " + fpApi.getVersion());
    }


    @Override
    public void onPause(boolean multitasking) {

    }


    @Override
    public void onDestroy() {
        closeFingerprint();
    }

    /**
     * 关闭指纹模块
     */
    private void closeFingerprint() {
        if (scanner == null) {
            fpDeviceOpenStatus = false;
            return;
        }
        if (fingerprintTask != null && fingerprintTask.getStatus() != AsyncTask.Status.FINISHED) {
            fingerprintTask.cancel(false);
            fingerprintTask.waitForDone();
        }
        if (scanner.close() != FingerprintScanner.RESULT_OK) {
            Log.e(TAG, "FingerprintScanner close failed.");
        }
        if (fpApi.exit() != Bione.RESULT_OK) {
            Log.e(TAG, "fpApi release failed.");
        }
        USBFingerManager.getInstance(context).closeUSB();
        fpDeviceOpenStatus = false;
    }

    /**
     * 初始化指纹库的存储路径
     */
    private void initDBPath() {
        File fpDataDir = new File(context.getFilesDir(), "fingerprint_data");
        if (!fpDataDir.exists()) {
            fpDataDir.mkdir();
        }
        File fpDBFile = new File(fpDataDir, "fingerprint.db");
        FP_DB_PATH = fpDBFile.getPath();
    }

    /**
     * 清空指纹库数据
     */
    private void clearFpDB() {
        fpApi.clear();
    }


    private class FingerprintAsyncTask extends AsyncTask<String, String, FingerprintStatus> {

        @Override
        protected FingerprintStatus doInBackground(String... params) {
            String action = params[0];
            FingerprintImage fi = null;
            byte[] fpFeat = null;
            byte[] fpTemp;
            Result res;
            long startTime;
            if ("enroll".equals(action) || "identify".equals(action)) {
                int fpCaptureRetry = 0;
                scanner.prepare();
                while (true) {
                    // 不管手指有没有按下，指纹模块都会进行采集
                    res = scanner.capture();
                    fi = (FingerprintImage) res.data;
                    int quality = 0;
                    if (fi != null) {
                        quality = fpApi.getFingerprintQuality(fi);
                        if (quality < 50 && fpCaptureRetry < 3 && !isCancelled()) {
                            fpCaptureRetry++;
                            continue;
                        }
                    }
                    if (res.error != FingerprintScanner.NO_FINGER || isCancelled()) {
                        break;
                    }
                }
                scanner.finish();
                if (isCancelled()) {
                    return FingerprintStatus.taskCanceled;
                }
                if (res.error != FingerprintScanner.RESULT_OK) {
                    return FingerprintStatus.captureFingerprintFailed;
                }
            }

            if (action.equals("enroll") || action.equals("identify")) {
                // 抽取指纹特征值
                res = fpApi.extractFeature(fi);
                if (res.error != Bione.RESULT_OK) {
                    return FingerprintStatus.extractFeatureFailed;
                }
                // 指纹特征数据
                fpFeat = (byte[]) res.data;
            }

            if ("enroll".equals(action)) {
                res = fpApi.makeTemplate(fpFeat, fpFeat, fpFeat);
                if (res.error != Bione.RESULT_OK) {
                    return FingerprintStatus.generalizeFingerprintFailed;
                }
                fpTemp = (byte[]) res.data;
                // 从当前指纹库中获取一个未使用的 id 值
                int id = fpApi.getFreeID();
                if (id < 0) {
                    return FingerprintStatus.generalizeFingerprintFailed;
                }
                int ret = fpApi.enroll(id, fpTemp);
                if (ret != Bione.RESULT_OK) {
                    return FingerprintStatus.entryFingerprintFailed;
                }
                callbackContext.success(DataUtils.bytesToHexString(fpTemp));
                return FingerprintStatus.noting;
                // 从当前的指纹库中检索指纹
            } else if ("identify".equals(action)) {
                // 从指纹库搜索是否有匹配的指纹
                int id = fpApi.identify(fpFeat);
                if (id < 0) {
                    return FingerprintStatus.fingerprintIdentifyFailed;
                }
            }
            taskIsDone = true;
            return FingerprintStatus.successed;
        }

        @Override
        protected void onPostExecute(FingerprintStatus status) {
            super.onPostExecute(status);
            if (status == FingerprintStatus.taskCanceled) {
                Log.e(TAG, "fingerprint async task was canceled.");
                callbackContext.error("fingerprint async task was canceled.");
            } else if (status == FingerprintStatus.captureFingerprintFailed) {
                Log.e(TAG, "capture fingerprint failed.");
                callbackContext.error("capture fingerprint failed.");
            } else if (status == FingerprintStatus.extractFeatureFailed) {
                Log.e(TAG, "extract feature failed.");
                callbackContext.error("extract feature failed.");
            } else if (status == FingerprintStatus.generalizeFingerprintFailed) {
                Log.e(TAG, "generalize fingerprint failed.");
                callbackContext.error("generalize fingerprint failed.");
            } else if (status == FingerprintStatus.entryFingerprintFailed) {
                Log.e(TAG, "entry fingerprint failed.");
                callbackContext.error("entry fingerprint failed.");
            } else if (status == FingerprintStatus.fingerprintIdentifyFailed) {
                Log.e(TAG, "fingerprint identify failed.");
                callbackContext.error("fingerprint identify failed.");
            } else {
                Log.i(TAG, "success.");
                callbackContext.success("success.");
            }
        }

        public void waitForDone() {
            while (!taskIsDone) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private enum FingerprintStatus {
        taskCanceled,
        captureFingerprintFailed,
        extractFeatureFailed,
        generalizeFingerprintFailed,
        entryFingerprintFailed,
        fingerprintIdentifyFailed,
        successed,
        noting
    }
}
