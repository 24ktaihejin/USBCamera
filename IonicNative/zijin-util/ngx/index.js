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
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
import { Injectable } from '@angular/core';
import { IonicNativePlugin, cordova } from '@ionic-native/core';
import { Observable } from 'rxjs';
var ZijinUtil = /** @class */ (function (_super) {
    __extends(ZijinUtil, _super);
    function ZijinUtil() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    ZijinUtil.prototype.openUhf = function () { return cordova(this, "openUhf", {}, arguments); };
    ZijinUtil.prototype.startInventoryReal = function () { return cordova(this, "startInventoryReal", {"observable": true }, arguments); };
    ZijinUtil.prototype.stopInventoryReal = function () { return cordova(this, "stopInventoryReal", {}, arguments); };
    ZijinUtil.prototype.closeUhf = function () { return cordova(this, "closeUhf", {}, arguments); };
    ZijinUtil.prototype.setOutputPower = function (outputPower) { return cordova(this, "setOutputPower", {}, arguments); };
    ZijinUtil.prototype.killTag = function (params) { return cordova(this, "killTag", {}, arguments); };
    ZijinUtil.prototype.lockTag = function (params) { return cordova(this, "lockTag", {}, arguments); };
    ZijinUtil.prototype.readTag = function (params) { return cordova(this, "readTag", {}, arguments); };
    ZijinUtil.prototype.writeTag = function (params) { return cordova(this, "writeTag", {}, arguments); };
    ZijinUtil.prototype.reset = function () { return cordova(this, "reset", {}, arguments); };
    ZijinUtil.prototype.openScanReceiver = function () { return cordova(this, "openScanReceiver", {"observable": true }, arguments); };
    ZijinUtil.prototype.closeScanReceiver = function () { return cordova(this, "closeScanReceiver", {}, arguments); };
    ZijinUtil.prototype.scan = function () { return cordova(this, "scan", {}, arguments); };
    ZijinUtil.prototype.continueScanning = function () { return cordova(this, "continueScanning", {"observable": true }, arguments); };
    ZijinUtil.prototype.stopContinueScanning = function () { return cordova(this, "stopContinueScanning", {}, arguments); };
    ZijinUtil.prototype.setScanInterval = function (intervalTime) { return cordova(this, "setScanInterval", {}, arguments); };
    ZijinUtil.prototype.openFingerprint = function () { return cordova(this, "openFingerprint", {}, arguments); };
    ZijinUtil.prototype.closeFingerprint = function () { return cordova(this, "closeFingerprint", {}, arguments); };
    ZijinUtil.prototype.scanFingerprint = function () { return cordova(this, "scanFingerprint", {}, arguments); };
    ZijinUtil.prototype.verifyFingerprint = function () { return cordova(this, "verifyFingerprint", {}, arguments); };
    ZijinUtil.prototype.loadFpData = function (fpDataList) { return cordova(this, "loadFpData", {}, arguments); };
    ZijinUtil.pluginName = "ZijinUtil";
    ZijinUtil.plugin = "cordova-plugin-x-zijinutil";
    ZijinUtil.pluginRef = "cordova.plugins.ZijinUtil";
    ZijinUtil.platforms = ["Android"];
    ZijinUtil = __decorate([
        Injectable()
    ], ZijinUtil);
    return ZijinUtil;
}(IonicNativePlugin));
export { ZijinUtil };
export var MemBank;
(function (MemBank) {
    MemBank["passwordArea"] = "0";
    MemBank["epcArea"] = "1";
    MemBank["tidArea"] = "2";
    MemBank["userArea"] = "3";
})(MemBank || (MemBank = {}));
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiaW5kZXguanMiLCJzb3VyY2VSb290IjoiIiwic291cmNlcyI6WyIuLi8uLi8uLi8uLi8uLi9zcmMvQGlvbmljLW5hdGl2ZS9wbHVnaW5zL3ppamluLXV0aWwvbmd4L2luZGV4LnRzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiI7Ozs7Ozs7Ozs7Ozs7Ozs7Ozs7QUFBQSxPQUFPLEVBQUUsVUFBVSxFQUFFLE1BQU0sZUFBZSxDQUFDO0FBQzNDLE9BQU8sOEJBQTBGLE1BQU0sb0JBQW9CLENBQUM7QUFDNUgsT0FBTyxFQUFFLFVBQVUsRUFBRSxNQUFNLE1BQU0sQ0FBQzs7SUFTSCw2QkFBaUI7Ozs7SUFNOUMsMkJBQU87SUFRUCxzQ0FBa0I7SUFRbEIscUNBQWlCO0lBUWpCLDRCQUFRO0lBU1Isa0NBQWMsYUFBQyxXQUFtQjtJQVNsQywyQkFBTyxhQUFDLE1BQWlDO0lBU3pDLDJCQUFPLGFBQUMsTUFBc0U7SUFTOUUsMkJBQU8sYUFBQyxNQUEyRjtJQVNuRyw0QkFBUSxhQUFDLE1BQXlHO0lBU2xILHlCQUFLO0lBUUwsb0NBQWdCO0lBUWhCLHFDQUFpQjtJQVFqQix3QkFBSTtJQVFKLG9DQUFnQjtJQVFoQix3Q0FBb0I7SUFTcEIsbUNBQWUsYUFBQyxZQUFvQjtJQVVwQyxtQ0FBZTtJQVdmLG9DQUFnQjtJQVVoQixtQ0FBZTtJQVVmLHFDQUFpQjtJQVdqQiw4QkFBVSxhQUFDLFVBQXlCOzs7OztJQXpMekIsU0FBUztRQURyQixVQUFVLEVBQUU7T0FDQSxTQUFTO29CQVh0QjtFQVcrQixpQkFBaUI7U0FBbkMsU0FBUztBQStMdEIsTUFBTSxDQUFOLElBQVksT0FLWDtBQUxELFdBQVksT0FBTztJQUNqQiw2QkFBa0IsQ0FBQTtJQUNsQix3QkFBYSxDQUFBO0lBQ2Isd0JBQWEsQ0FBQTtJQUNiLHlCQUFjLENBQUE7QUFDaEIsQ0FBQyxFQUxXLE9BQU8sS0FBUCxPQUFPLFFBS2xCIiwic291cmNlc0NvbnRlbnQiOlsiaW1wb3J0IHsgSW5qZWN0YWJsZSB9IGZyb20gJ0Bhbmd1bGFyL2NvcmUnO1xuaW1wb3J0IHsgUGx1Z2luLCBDb3Jkb3ZhLCBDb3Jkb3ZhUHJvcGVydHksIENvcmRvdmFJbnN0YW5jZSwgSW5zdGFuY2VQcm9wZXJ0eSwgSW9uaWNOYXRpdmVQbHVnaW4gfSBmcm9tICdAaW9uaWMtbmF0aXZlL2NvcmUnO1xuaW1wb3J0IHsgT2JzZXJ2YWJsZSB9IGZyb20gJ3J4anMnO1xuXG5AUGx1Z2luKHtcbiAgcGx1Z2luTmFtZTogJ1ppamluVXRpbCcsXG4gIHBsdWdpbjogJ2NvcmRvdmEtcGx1Z2luLXgtemlqaW51dGlsJywgLy8gbnBtIHBhY2thZ2UgbmFtZSwgZXhhbXBsZTogY29yZG92YS1wbHVnaW4tY2FtZXJhXG4gIHBsdWdpblJlZjogJ2NvcmRvdmEucGx1Z2lucy5aaWppblV0aWwnLCAvLyB0aGUgdmFyaWFibGUgcmVmZXJlbmNlIHRvIGNhbGwgdGhlIHBsdWdpbiwgZXhhbXBsZTogbmF2aWdhdG9yLmdlb2xvY2F0aW9uXG4gIHBsYXRmb3JtczogWydBbmRyb2lkJ10gLy8gQXJyYXkgb2YgcGxhdGZvcm1zIHN1cHBvcnRlZCwgZXhhbXBsZTogWydBbmRyb2lkJywgJ2lPUyddXG59KVxuQEluamVjdGFibGUoKVxuZXhwb3J0IGNsYXNzIFppamluVXRpbCBleHRlbmRzIElvbmljTmF0aXZlUGx1Z2luIHtcblxuICAvKipcbiAgICog5byA5ZCvVUhG5qih5Z2XXG4gICAqL1xuICBAQ29yZG92YSgpXG4gIG9wZW5VaGYoKTogUHJvbWlzZTxzdHJpbmc+IHtcbiAgICByZXR1cm47XG4gIH1cblxuICAvKipcbiAgICog5byA5ZCv55uY54K5XG4gICAqL1xuICBAQ29yZG92YSgpXG4gIHN0YXJ0SW52ZW50b3J5UmVhbCgpOiBPYnNlcnZhYmxlPEludmVudG9yeVRhZ01hcFtdPiB7XG4gICAgcmV0dXJuO1xuICB9XG5cbiAgLyoqXG4gICAqIOWBnOatouebmOeCuVxuICAgKi9cbiAgQENvcmRvdmEoKVxuICBzdG9wSW52ZW50b3J5UmVhbCgpOiBQcm9taXNlPHN0cmluZz4ge1xuICAgIHJldHVybjtcbiAgfVxuXG4gIC8qKlxuICAgKiDlhbPpl61VSEbmqKHlnZdcbiAgICovXG4gIEBDb3Jkb3ZhKClcbiAgY2xvc2VVaGYoKTogUHJvbWlzZTxzdHJpbmc+IHtcbiAgICByZXR1cm47XG4gIH1cblxuICAvKipcbiAgICog6K6+572u5aSp57q/5Yqf546HXG4gICAqIEBwYXJhbSBvdXRwdXRQb3dlciDlip/njofvvIgx772eMzNkYu+8iVxuICAgKi9cbiAgQENvcmRvdmEoKVxuICBzZXRPdXRwdXRQb3dlcihvdXRwdXRQb3dlcjogbnVtYmVyKTogUHJvbWlzZTxzdHJpbmc+IHtcbiAgICByZXR1cm47XG4gIH1cblxuICAvKipcbiAgICog5qCH562+54Gt5rS7XG4gICAqIEBwYXJhbSBwYXJhbXNcbiAgICovXG4gIEBDb3Jkb3ZhKClcbiAga2lsbFRhZyhwYXJhbXM6IHsgYnRBcnlQYXNzV29yZDogc3RyaW5nIH0pOiBQcm9taXNlPHN0cmluZz4ge1xuICAgIHJldHVybjtcbiAgfVxuXG4gIC8qKlxuICAgKiDplIHlrprmoIfnrb5cbiAgICogQHBhcmFtIHBhcmFtc1xuICAgKi9cbiAgQENvcmRvdmEoKVxuICBsb2NrVGFnKHBhcmFtczogeyBidEFyeVBhc3NXb3JkOiBzdHJpbmcsIGJ0TWVtQmFuazogTWVtQmFuaywgYnRMb2NrVHlwZTogYW55IH0pOiBQcm9taXNlPHN0cmluZz4ge1xuICAgIHJldHVybjtcbiAgfVxuXG4gIC8qKlxuICAgKiDor7vlj5bmoIfnrb5cbiAgICogQHBhcmFtIHBhcmFtc1xuICAgKi9cbiAgQENvcmRvdmEoKVxuICByZWFkVGFnKHBhcmFtczogeyBidE1lbUJhbms6IE1lbUJhbmssIGJ0V29yZEFkZDogc3RyaW5nLCBidFdvcmRDbnQ6IHN0cmluZywgYnRBcnlQYXNzV29yZDogc3RyaW5nIH0pOiBQcm9taXNlPHN0cmluZz4ge1xuICAgIHJldHVybjtcbiAgfVxuXG4gIC8qKlxuICAgKiDlhpnmlbDmja7liLDmoIfnrb5cbiAgICogQHBhcmFtIHBhcmFtc1xuICAgKi9cbiAgQENvcmRvdmEoKVxuICB3cml0ZVRhZyhwYXJhbXM6IHsgYnRNZW1CYW5rOiBNZW1CYW5rLCBidFdvcmRBZGQ6IHN0cmluZywgYnRXb3JkQ250OiBzdHJpbmcsIGJ0QXJ5UGFzc1dvcmQ6IHN0cmluZywgZGF0YTogc3RyaW5nIH0pOiBQcm9taXNlPHN0cmluZz4ge1xuICAgIHJldHVybjtcbiAgfVxuXG4gIC8qKlxuICAgKiDlpI3kvY1cbiAgICogQHBhcmFtIHBhcmFtc1xuICAgKi9cbiAgQENvcmRvdmEoKVxuICByZXNldCgpOiB2b2lkIHtcblxuICB9XG5cbiAgLyoqXG4gICAqIOW8gOWQr+ebkeWQrOadoeeggeaJq+aPj+eahOe7k+aenFxuICAgKi9cbiAgQENvcmRvdmEoKVxuICBvcGVuU2NhblJlY2VpdmVyKCk6IE9ic2VydmFibGU8c3RyaW5nPiB7XG4gICAgcmV0dXJuO1xuICB9XG5cbiAgLyoqXG4gICAqIOWFs+mXreWvueadoeeggeaJq+aPj+e7k+aenOeahOebkeWQrFxuICAgKi9cbiAgQENvcmRvdmEoKVxuICBjbG9zZVNjYW5SZWNlaXZlcigpOiB2b2lkIHtcblxuICB9XG5cbiAgLyoqXG4gICAqIOWNleasoeadoeeggeaJq+aPj1xuICAgKi9cbiAgQENvcmRvdmEoKVxuICBzY2FuKCk6IFByb21pc2U8c3RyaW5nPiB7XG4gICAgcmV0dXJuO1xuICB9XG5cbiAgLyoqXG4gICAqIOi/nue7readoeeggeaJq+aPj1xuICAgKi9cbiAgQENvcmRvdmEoKVxuICBjb250aW51ZVNjYW5uaW5nKCk6IE9ic2VydmFibGU8c3RyaW5nPiB7XG4gICAgcmV0dXJuO1xuICB9XG5cbiAgLyoqXG4gICAqIOWBnOatoui/nue7readoeeggeaJq+aPj1xuICAgKi9cbiAgQENvcmRvdmEoKVxuICBzdG9wQ29udGludWVTY2FubmluZygpOiB2b2lkIHtcblxuICB9XG5cbiAgLyoqXG4gICAqIOiuvue9rui/nue7reaJq+aPj+aXtuS4remXtOeahOmXtOmalFxuICAgKiBAcGFyYW0gaW50ZXJ2YWxUaW1lXG4gICAqL1xuICBAQ29yZG92YSgpXG4gIHNldFNjYW5JbnRlcnZhbChpbnRlcnZhbFRpbWU6IG51bWJlcik6IHZvaWQge1xuXG4gIH1cblxuICAvKipcbiAgICogQGRlc2NyaXB0aW9uIOaJk+W8gOaMh+e6ueaJq+aPj+S7qlxuICAgKiBAcmV0dXJucyB7dm9pZH1cbiAgICogQG1lbWJlcm9mIFppamluVXRpbFxuICAgKi9cbiAgQENvcmRvdmEoKVxuICBvcGVuRmluZ2VycHJpbnQoKTogUHJvbWlzZTxzdHJpbmc+IHtcbiAgICByZXR1cm47XG4gIH1cblxuICAvKipcbiAgICogQGRlc2NyaXB0aW9uIOWFs+mXreaMh+e6ueaJq+aPj+S7qlxuICAgKlxuICAgKiBAcmV0dXJucyB7dm9pZH1cbiAgICogQG1lbWJlcm9mIFppamluVXRpbFxuICAgKi9cbiAgQENvcmRvdmEoKVxuICBjbG9zZUZpbmdlcnByaW50KCk6IHZvaWQge1xuXG4gIH1cblxuICAvKipcbiAgICogQGRlc2NyaXB0aW9uIOiOt+WPluaMh+e6ueeJueW+geWAvFxuICAgKiBAcmV0dXJucyB7UHJvbWlzZTxhbnk+fVxuICAgKiBAbWVtYmVyb2YgWmlqaW5VdGlsXG4gICAqL1xuICBAQ29yZG92YSgpXG4gIHNjYW5GaW5nZXJwcmludCgpOiBQcm9taXNlPHN0cmluZz4ge1xuICAgIHJldHVybjtcbiAgfVxuXG4gIC8qKlxuICAgKiBAZGVzY3JpcHRpb24g5q+U5a+55oyH57q554m55b6B5YC8XG4gICAqIEByZXR1cm5zIHtQcm9taXNlPGFueT59XG4gICAqIEBtZW1iZXJvZiBaaWppblV0aWxcbiAgICovXG4gIEBDb3Jkb3ZhKClcbiAgdmVyaWZ5RmluZ2VycHJpbnQoKTogUHJvbWlzZTxzdHJpbmc+IHtcbiAgICByZXR1cm47XG4gIH1cblxuICAvKipcbiAgICogQGRlc2NyaXB0aW9uIOWQjOatpeaMh+e6ueeJueW+geWAvFxuICAgKiBAcGFyYW0ge3N0cmluZ1tdfSBjaGFyQXJyYXkg5oyH57q554m55b6B5pWw57uEXG4gICAqIEByZXR1cm5zIHtQcm9taXNlPGFueT59XG4gICAqIEBtZW1iZXJvZiBaaWppblV0aWxcbiAgICovXG4gIEBDb3Jkb3ZhKClcbiAgbG9hZEZwRGF0YShmcERhdGFMaXN0OiBBcnJheTxzdHJpbmc+KTogUHJvbWlzZTxhbnk+IHtcbiAgICByZXR1cm47XG4gIH1cblxufVxuXG5leHBvcnQgZW51bSBNZW1CYW5rIHtcbiAgcGFzc3dvcmRBcmVhID0gJzAnLFxuICBlcGNBcmVhID0gJzEnLFxuICB0aWRBcmVhID0gJzInLFxuICB1c2VyQXJlYSA9ICczJ1xufVxuXG5leHBvcnQgaW50ZXJmYWNlIEludmVudG9yeVRhZ01hcCB7XG4gIHN0clBDOiBzdHJpbmc7XG4gIHN0ckNSQzogc3RyaW5nO1xuICBzdHJFUEM6IHN0cmluZztcbiAgYnRBbnRJZDogbnVtYmVyO1xuICBzdHJSU1NJOiBzdHJpbmc7XG4gIG5SZWFkQ291bnQ6IG51bWJlcjtcbiAgc3RyRnJlcTogc3RyaW5nO1xuICBuQW50MTogbnVtYmVyO1xuICBuQW50MjogbnVtYmVyO1xuICBuQW50MzogbnVtYmVyO1xuICBuQW50NDogbnVtYmVyO1xufVxuXG4iXX0=