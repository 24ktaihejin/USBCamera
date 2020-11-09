import {IonicNativePlugin} from '@ionic-native/core';
import {Observable} from 'rxjs';

export interface UhfOptions {
    strRepeat?: string;
    mPos1?: number;
    mPos2?: number;
    lAntenna?: number;
    mCbRealSession?: boolean;
}

export interface ScannerOptions {
    PowerOnOff: number;
    OutputMode: number;
    TerminalChar: number;
    Prefix: string;
    Suffix: string;
    Volume: number;
    PlayoneMode: number;
}

/**
 * @name Zijin Util
 * @description
 * This plugin does something
 *
 * @usage
 * ```typescript
 * import { ZijinUtil } from '@ionic-native/zijin-util';
 *
 *
 * constructor(private zijinUtil: ZijinUtil) { }
 *
 * ...
 *
 *
 * this.zijinUtil.functionName('Hello', 123)
 *   .then((res: any) => console.log(res))
 *   .catch((error: any) => console.error(error));
 *
 * ```
 */
export declare class ZijinUtilOriginal extends IonicNativePlugin {
    /**
     * 开启接收条码扫描结果
     */
    openScanReceiver(): Observable<any>;

    /**
     * @description 单次扫码
     * @returns {Promise<any>}
     * @memberof ZijinUtil
     */
    scan(): Promise<any>;

    /**
     * @description 连续扫码
     * @returns {Observable<any>}
     * @memberof ZijinUtil
     */
    continueScanning(): Observable<any>;

    /**
     * @description 关闭连续扫码
     * @returns {void}
     * @memberof ZijinUtil
     */
    closeScanning(): void;

    /**
     * @description 设置扫码机参数
     * @param {ScannerOptions} options
     * @returns {void}
     * @memberof ZijinUtil
     */
    setScanner(options: ScannerOptions): void;

    /**
     * @description 设置连续扫码间隔
     * @param {number} time
     * @returns {void}
     * @memberof ZijinUtil
     */
    setScanInterval(time: number): void;

    /**
     * @description 打开超高频设备
     * @returns {Promise<any>}
     * @memberof ZijinUtil
     */
    openUHF(): Promise<any>;

    /**
     * @description 关闭超高频设备
     * @returns {Promise<any>}
     * @memberof ZijinUtil
     */
    closeUHF(): Promise<any>;

    /**
     * @description 盘存标签（实时模式）
     * @param {UhfOptions} options
     * @returns {Observable<any>}
     * @memberof ZijinUtil
     */
    startInventoryReal(options: UhfOptions): Observable<any>;

    /**
     * @description 停止实时盘点
     * @returns {Promise<any>}
     * @memberof ZijinUtil
     */
    stopInventoryReal(): Promise<any>;

    /**
     * @description 获取模块温度
     * @returns {Promise<any>}
     * @memberof ZijinUtil
     */
    getReaderTemperature(): Promise<any>;

    /**
     * @description 销毁标签,灭活标签操作 灭活标签必须提供灭活口令，并且灭活口令不能为00 00 00 00, 因此要销毁一张标签，首先要通过写标签命令，修改密码区灭活口令的内容。
     * @param {String} btAryPassWord
     * @returns {Promise<any>}
     * @memberof ZijinUtil
     */
    killTag(btAryPassWord: String): Promise<any>;

    /**
     * @description 锁定标签 锁定标签必须提供访问密码才能进行
     * @param {{ btAryPassWord: string; btMemBank: string, btLockType: string }} options
     * @returns {Promise<any>}
     * @memberof ZijinUtil
     */
    lockTag(options: {
        btAryPassWord: string;
        btMemBank: string;
        btLockType: string;
    }): Promise<any>;

    /**
     * @description 读标签 读标签需要输入三个参数：要读取的标签区域，起始地址和数据长度
     * @param {{ btMemBank: string, btWordAdd: string, btWordCnt: string, btAryPassWord: string }} options
     * @returns {Promise<any>}
     * @memberof ZijinUtil
     */
    readTag(options: {
        btMemBank: string;
        btWordAdd: string;
        btWordCnt: string;
        btAryPassWord: string;
    }): Promise<any>;

    /**
     * @description 写标签
     * @param {{btMemBank: string, btWordAdd: string, btWordCnt: string, btAryPassWord: string, btAryData: string}} options
     * @returns {Promise<any>}
     * @memberof ZijinUtil
     */
    writeTag(options: {
        btMemBank: string;
        btWordAdd: string;
        btWordCnt: string;
        btAryPassWord: string;
        btAryData: string;
    }): Promise<any>;

    /**
     * @description 复位
     * @returns {void}
     * @memberof ZijinUtil
     */
    reset(): void;

    /**
     * @description 设置盘点时间间隔，默认2000ms
     * @param {number} delayMillis
     * @returns {void}
     * @memberof ZijinUtil
     */
    setInventoryDelayMillis(delayMillis: number): void;

    /**
     * @description 设置输出功率，0~33
     * @param {number} mOutPower
     * @returns {void}
     * @memberof ZijinUtil
     */
    setOutputPower(mOutPower: number): void;

    /**
     * @description 打开指纹扫描仪
     * @returns {void}
     * @memberof ZijinUtil
     */
    openFingerprint(): Promise<any>;

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
    scanFingerprint(): Promise<any>;

    /**
     * @description 比对指纹特征值
     * @param {string[]} charArray 指纹特征数组
     * @returns {Promise<any>}
     * @memberof ZijinUtil
     */
    verifyFingerprint(charArray: string[]): Promise<any>;

    /**
     * @description 同步指纹特征值
     * @param {string[]} charArray 指纹特征数组
     * @returns {Promise<any>}
     * @memberof ZijinUtil
     */
    loadFpData(charArray: string[]): Promise<any>;
}

export declare const ZijinUtil: ZijinUtilOriginal;
