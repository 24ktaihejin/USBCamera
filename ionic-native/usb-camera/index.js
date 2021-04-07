var __extends = (this && this.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
import { IonicNativePlugin, cordova } from '@ionic-native/core';
var USBCameraOriginal = /** @class */ (function (_super) {
    __extends(USBCameraOriginal, _super);
    function USBCameraOriginal() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    USBCameraOriginal.prototype.faceVerifyByUsbCamera = function (args) { return cordova(this, "faceVerifyByUsbCamera", {}, arguments); };
    USBCameraOriginal.prototype.getUserInfoByUsbCamera = function (args) { return cordova(this, "getUserInfoByUsbCamera", {}, arguments); };
    USBCameraOriginal.prototype.faceVerifyByCamera = function (args) { return cordova(this, "faceVerifyByCamera", {}, arguments); };
    USBCameraOriginal.pluginName = "USBCameraPlugin";
    USBCameraOriginal.plugin = "cordova-plugin-usbcamera";
    USBCameraOriginal.pluginRef = "cordova.plugins.USBCameraPlugin";
    USBCameraOriginal.platforms = ["Android"];
    return USBCameraOriginal;
}(IonicNativePlugin));
var USBCamera = new USBCameraOriginal();
export { USBCamera };
export var PreviewSupportedSize;
(function (PreviewSupportedSize) {
    PreviewSupportedSize["S_1920_1080"] = "1920_1080";
    PreviewSupportedSize["S_1280_720"] = "1280_720";
    PreviewSupportedSize["S_640_480"] = "640_480";
    PreviewSupportedSize["S_320_240"] = "320_240";
})(PreviewSupportedSize || (PreviewSupportedSize = {}));
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiaW5kZXguanMiLCJzb3VyY2VSb290IjoiIiwic291cmNlcyI6WyIuLi8uLi8uLi8uLi9zcmMvQGlvbmljLW5hdGl2ZS9wbHVnaW5zL3VzYi1jYW1lcmEvaW5kZXgudHMiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6Ijs7Ozs7Ozs7Ozs7OztBQVlBLE9BQU8sOEJBQTBGLE1BQU0sb0JBQW9CLENBQUM7O0lBK0I3Riw2QkFBaUI7Ozs7SUFROUMseUNBQXFCLGFBQUMsSUFBcUQ7SUFVM0UsMENBQXNCLGFBQUMsSUFBMEU7SUFTakcsc0NBQWtCLGFBQUMsSUFBd0I7Ozs7O29CQXRFN0M7RUEyQytCLGlCQUFpQjtTQUFuQyxTQUFTO0FBZ0N0QixNQUFNLENBQU4sSUFBWSxvQkFLWDtBQUxELFdBQVksb0JBQW9CO0lBQzlCLGlEQUF5QixDQUFBO0lBQ3pCLCtDQUF1QixDQUFBO0lBQ3ZCLDZDQUFxQixDQUFBO0lBQ3JCLDZDQUFxQixDQUFBO0FBQ3ZCLENBQUMsRUFMVyxvQkFBb0IsS0FBcEIsb0JBQW9CLFFBSy9CIiwic291cmNlc0NvbnRlbnQiOlsiLyoqXG4gKiBUaGlzIGlzIGEgdGVtcGxhdGUgZm9yIG5ldyBwbHVnaW4gd3JhcHBlcnNcbiAqXG4gKiBUT0RPOlxuICogLSBBZGQvQ2hhbmdlIGluZm9ybWF0aW9uIGJlbG93XG4gKiAtIERvY3VtZW50IHVzYWdlIChpbXBvcnRpbmcsIGV4ZWN1dGluZyBtYWluIGZ1bmN0aW9uYWxpdHkpXG4gKiAtIFJlbW92ZSBhbnkgaW1wb3J0cyB0aGF0IHlvdSBhcmUgbm90IHVzaW5nXG4gKiAtIFJlbW92ZSBhbGwgdGhlIGNvbW1lbnRzIGluY2x1ZGVkIGluIHRoaXMgdGVtcGxhdGUsIEVYQ0VQVCB0aGUgQFBsdWdpbiB3cmFwcGVyIGRvY3MgYW5kIGFueSBvdGhlciBkb2NzIHlvdSBhZGRlZFxuICogLSBSZW1vdmUgdGhpcyBub3RlXG4gKlxuICovXG5pbXBvcnQgeyBJbmplY3RhYmxlIH0gZnJvbSAnQGFuZ3VsYXIvY29yZSc7XG5pbXBvcnQgeyBQbHVnaW4sIENvcmRvdmEsIENvcmRvdmFQcm9wZXJ0eSwgQ29yZG92YUluc3RhbmNlLCBJbnN0YW5jZVByb3BlcnR5LCBJb25pY05hdGl2ZVBsdWdpbiB9IGZyb20gJ0Bpb25pYy1uYXRpdmUvY29yZSc7XG5pbXBvcnQgeyBPYnNlcnZhYmxlIH0gZnJvbSAncnhqcyc7XG5cbi8qKlxuICogQG5hbWUgVVNCQ2FtZXJhXG4gKiBAZGVzY3JpcHRpb25cbiAqIFRoaXMgcGx1Z2luIGRvZXMgc29tZXRoaW5nXG4gKlxuICogQHVzYWdlXG4gKiBgYGB0eXBlc2NyaXB0XG4gKiBpbXBvcnQgeyBVU0JDYW1lcmEgfSBmcm9tICdAaW9uaWMtbmF0aXZlL3VzYi1jYW1lcmEnO1xuICpcbiAqXG4gKiBjb25zdHJ1Y3Rvcihwcml2YXRlIHVTQkNhbWVyYTogVVNCQ2FtZXJhKSB7IH1cbiAqXG4gKiAuLi5cbiAqXG4gKlxuICogdGhpcy51U0JDYW1lcmEuZnVuY3Rpb25OYW1lKCdIZWxsbycsIDEyMylcbiAqICAgLnRoZW4oKHJlczogYW55KSA9PiBjb25zb2xlLmxvZyhyZXMpKVxuICogICAuY2F0Y2goKGVycm9yOiBhbnkpID0+IGNvbnNvbGUuZXJyb3IoZXJyb3IpKTtcbiAqXG4gKiBgYGBcbiAqL1xuQFBsdWdpbih7XG4gIHBsdWdpbk5hbWU6ICdVU0JDYW1lcmFQbHVnaW4nLFxuICBwbHVnaW46ICdjb3Jkb3ZhLXBsdWdpbi11c2JjYW1lcmEnLCAvLyBucG0gcGFja2FnZSBuYW1lLCBleGFtcGxlOiBjb3Jkb3ZhLXBsdWdpbi1jYW1lcmFcbiAgcGx1Z2luUmVmOiAnY29yZG92YS5wbHVnaW5zLlVTQkNhbWVyYVBsdWdpbicsIC8vIHRoZSB2YXJpYWJsZSByZWZlcmVuY2UgdG8gY2FsbCB0aGUgcGx1Z2luLCBleGFtcGxlOiBuYXZpZ2F0b3IuZ2VvbG9jYXRpb24gdGhlIGdpdGh1YiByZXBvc2l0b3J5IFVSTCBmb3IgdGhlIHBsdWdpblxuICBwbGF0Zm9ybXM6IFsnQW5kcm9pZCddIC8vIEFycmF5IG9mIHBsYXRmb3JtcyBzdXBwb3J0ZWQsIGV4YW1wbGU6IFsnQW5kcm9pZCcsICdpT1MnXVxufSlcbkBJbmplY3RhYmxlKClcbmV4cG9ydCBjbGFzcyBVU0JDYW1lcmEgZXh0ZW5kcyBJb25pY05hdGl2ZVBsdWdpbiB7XG5cbiAgLyoqXG4gICAqIOWunuaXtuS6uuiEuOmqjOivgVxuICAgKiBAcGFyYW0gc2l6ZSDorr7nva7pooTop4jnlLvpnaLnmoTlsLrlr7jvvIjmi43mkYTlm77lg4/nmoTlsLrlr7jvvIlcbiAgICogQHBhcmFtIGJhc2VVcmwg6aG555uu5Z+656GA55qEVVJM5Zyw5Z2AIGh0dHBzOi8veC54LngueDp4XG4gICAqL1xuICBAQ29yZG92YSgpXG4gIGZhY2VWZXJpZnlCeVVzYkNhbWVyYShhcmdzOiB7IHNpemU6IFByZXZpZXdTdXBwb3J0ZWRTaXplLCBiYXNlVXJsOiBzdHJpbmcgfSk6IFByb21pc2U8c3RyaW5nPiB7XG4gICAgcmV0dXJuO1xuICB9XG4gIC8qKlxuICAgKiDlrp7ml7bmo4DmtYvkurrohLjojrflj5blr7nlupTnmoTnlKjmiLfkv6Hmga9cbiAgICogQHBhcmFtIGF1dGhvcml6YXRpb24gdG9rZW7kv6Hmga9cbiAgICogQHBhcmFtIHNpemUg6K6+572u6aKE6KeI55S76Z2i55qE5bC65a+477yI5ouN5pGE5Zu+5YOP55qE5bC65a+477yJXG4gICAqIEBwYXJhbSBiYXNlVXJsIOmhueebruWfuuehgOeahFVSTOWcsOWdgCBodHRwczovL3gueC54Lng6eFxuICAgKi9cbiAgQENvcmRvdmEoKVxuICBnZXRVc2VySW5mb0J5VXNiQ2FtZXJhKGFyZ3M6IHthdXRob3JpemF0aW9uOiBzdHJpbmcsIHNpemU6IFByZXZpZXdTdXBwb3J0ZWRTaXplLCBiYXNlVXJsOiBzdHJpbmd9KTogUHJvbWlzZTxzdHJpbmc+IHtcbiAgICByZXR1cm47XG4gIH1cblxuICAvKipcbiAgICog5a6e5pe25Lq66IS46aqM6K+B77yI5Z+65LqOIEFuZHJvaWQgQ2FtZXJhIGFwaSDlvIDlj5HvvIlcbiAgICogQHBhcmFtIGJhc2VVcmwg6aG555uu5Z+656GA55qEVVJM5Zyw5Z2AIGh0dHBzOi8veC54LngueDp4XG4gICAqL1xuICBAQ29yZG92YSgpXG4gIGZhY2VWZXJpZnlCeUNhbWVyYShhcmdzOiB7YmFzZVVybDogc3RyaW5nIH0pOiBQcm9taXNlPHN0cmluZz4ge1xuICAgIHJldHVybjtcbiAgfVxufVxuXG5leHBvcnQgZW51bSBQcmV2aWV3U3VwcG9ydGVkU2l6ZSB7XG4gIFNfMTkyMF8xMDgwID0gJzE5MjBfMTA4MCcsXG4gIFNfMTI4MF83MjAgPSAnMTI4MF83MjAnLFxuICBTXzY0MF80ODAgPSAnNjQwXzQ4MCcsXG4gIFNfMzIwXzI0MCA9ICczMjBfMjQwJyxcbn1cbiJdfQ==