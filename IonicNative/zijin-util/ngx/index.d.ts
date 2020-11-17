import { IonicNativePlugin } from '@ionic-native/core';
import { Observable } from 'rxjs';
export declare class ZijinUtil extends IonicNativePlugin {
    /**
     * 开启UHF模块
     */
    openUhf(): Promise<string>;
    /**
     * 开启盘点
     */
    startInventoryReal(): Observable<InventoryTagMap[]>;
    /**
     * 停止盘点
     */
    stopInventoryReal(): Promise<string>;
    /**
     * 关闭UHF模块
     */
    closeUhf(): Promise<string>;
    /**
     * 设置天线功率
     * @param outputPower 功率（1～33db）
     */
    setOutputPower(outputPower: number): Promise<string>;
    /**
     * 标签灭活
     * @param params
     */
    killTag(params: {
        btAryPassWord: string;
    }): Promise<string>;
    /**
     * 锁定标签
     * @param params
     */
    lockTag(params: {
        btAryPassWord: string;
        btMemBank: MemBank;
        btLockType: any;
    }): Promise<string>;
    /**
     * 读取标签
     * @param params
     */
    readTag(params: {
        btMemBank: MemBank;
        btWordAdd: string;
        btWordCnt: string;
        btAryPassWord: string;
    }): Promise<string>;
    /**
     * 写数据到标签
     * @param params
     */
    writeTag(params: {
        btMemBank: MemBank;
        btWordAdd: string;
        btWordCnt: string;
        btAryPassWord: string;
        data: string;
    }): Promise<string>;
    /**
     * 复位
     * @param params
     */
    reset(): void;
    /**
     * 开启监听条码扫描的结果
     */
    openScanReceiver(): Observable<string>;
    /**
     * 关闭对条码扫描结果的监听
     */
    closeScanReceiver(): void;
    /**
     * 单次条码扫描
     */
    scan(): Promise<string>;
    /**
     * 连续条码扫描
     */
    continueScanning(): Observable<string>;
    /**
     * 停止连续条码扫描
     */
    stopContinueScanning(): void;
    /**
     * 设置连续扫描时中间的间隔
     * @param intervalTime
     */
    setScanInterval(intervalTime: number): void;
    /**
     * @description 打开指纹扫描仪
     * @returns {void}
     * @memberof ZijinUtil
     */
    openFingerprint(): Promise<string>;
    /**
     * @description 关闭指纹扫描仪
     *
     * @returns {void}
     * @memberof ZijinUtil
     */
    closeFingerprint(): void;
    /**
     * @description 获取指纹特征值
     * @returns {Promise<any>}
     * @memberof ZijinUtil
     */
    scanFingerprint(): Promise<string>;
    /**
     * @description 比对指纹特征值
     * @returns {Promise<any>}
     * @memberof ZijinUtil
     */
    verifyFingerprint(): Promise<string>;
    /**
     * @description 同步指纹特征值
     * @param {string[]} charArray 指纹特征数组
     * @returns {Promise<any>}
     * @memberof ZijinUtil
     */
    loadFpData(fpDataList: Array<string>): Promise<any>;
}
export declare enum MemBank {
    passwordArea = "0",
    epcArea = "1",
    tidArea = "2",
    userArea = "3"
}
export interface InventoryTagMap {
    strPC: string;
    strCRC: string;
    strEPC: string;
    btAntId: number;
    strRSSI: string;
    nReadCount: number;
    strFreq: string;
    nAnt1: number;
    nAnt2: number;
    nAnt3: number;
    nAnt4: number;
}
