import { IonicNativePlugin } from '@ionic-native/core';
/**
 * @name USBCamera
 * @description
 * This plugin does something
 *
 * @usage
 * ```typescript
 * import { USBCamera } from '@ionic-native/usb-camera';
 *
 *
 * constructor(private uSBCamera: USBCamera) { }
 *
 * ...
 *
 *
 * this.uSBCamera.functionName('Hello', 123)
 *   .then((res: any) => console.log(res))
 *   .catch((error: any) => console.error(error));
 *
 * ```
 */
export declare class USBCamera extends IonicNativePlugin {
    /**
     * 实时人脸验证
     * @param size 设置预览画面的尺寸（拍摄图像的尺寸）
     * @param baseUrl 项目基础的URL地址 https://x.x.x.x:x
     */
    faceVerifyByUsbCamera(args: {
        size: PreviewSupportedSize;
        baseUrl: string;
    }): Promise<string>;
    /**
     * 实时检测人脸获取对应的用户信息
     * @param authorization token信息
     * @param size 设置预览画面的尺寸（拍摄图像的尺寸）
     * @param baseUrl 项目基础的URL地址 https://x.x.x.x:x
     */
    getUserInfoByUsbCamera(args: {
        authorization: string;
        size: PreviewSupportedSize;
        baseUrl: string;
    }): Promise<string>;
    /**
     * 实时人脸验证（基于 Android Camera api 开发）
     * @param baseUrl 项目基础的URL地址 https://x.x.x.x:x
     */
    faceVerifyByCamera(args: {
        baseUrl: string;
    }): Promise<string>;
}
export declare enum PreviewSupportedSize {
    S_1920_1080 = "1920_1080",
    S_1280_720 = "1280_720",
    S_640_480 = "640_480",
    S_320_240 = "320_240"
}
