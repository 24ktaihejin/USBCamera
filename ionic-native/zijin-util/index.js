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
import { Observable } from 'rxjs';
var ZijinUtilOriginal = /** @class */ (function (_super) {
    __extends(ZijinUtilOriginal, _super);
    function ZijinUtilOriginal() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    ZijinUtilOriginal.prototype.openUhf = function () { return cordova(this, "openUhf", {}, arguments); };
    ZijinUtilOriginal.prototype.startInventoryReal = function () { return cordova(this, "startInventoryReal", {"observable": true}, arguments); };
    ZijinUtilOriginal.prototype.stopInventoryReal = function () { return cordova(this, "stopInventoryReal", {}, arguments); };
    ZijinUtilOriginal.prototype.closeUhf = function () { return cordova(this, "closeUhf", {}, arguments); };
    ZijinUtilOriginal.prototype.setOutputPower = function (outputPower) { return cordova(this, "setOutputPower", {}, arguments); };
    ZijinUtilOriginal.prototype.killTag = function (params) { return cordova(this, "killTag", {}, arguments); };
    ZijinUtilOriginal.prototype.lockTag = function (params) { return cordova(this, "lockTag", {}, arguments); };
    ZijinUtilOriginal.prototype.readTag = function (params) { return cordova(this, "readTag", {}, arguments); };
    ZijinUtilOriginal.prototype.writeTag = function (params) { return cordova(this, "writeTag", {}, arguments); };
    ZijinUtilOriginal.prototype.reset = function () { return cordova(this, "reset", {}, arguments); };
    ZijinUtilOriginal.prototype.openScanReceiver = function () { return cordova(this, "openScanReceiver", {"observable": true}, arguments); };
    ZijinUtilOriginal.prototype.closeScanReceiver = function () { return cordova(this, "closeScanReceiver", {}, arguments); };
    ZijinUtilOriginal.prototype.scan = function () { return cordova(this, "scan", {}, arguments); };
    ZijinUtilOriginal.prototype.continueScanning = function () { return cordova(this, "continueScanning", {"observable": true}, arguments); };
    ZijinUtilOriginal.prototype.stopContinueScanning = function () { return cordova(this, "stopContinueScanning", {}, arguments); };
    ZijinUtilOriginal.prototype.setScanInterval = function (intervalTime) { return cordova(this, "setScanInterval", {}, arguments); };
    ZijinUtilOriginal.prototype.openFingerprint = function () { return cordova(this, "openFingerprint", {}, arguments); };
    ZijinUtilOriginal.prototype.closeFingerprint = function () { return cordova(this, "closeFingerprint", {}, arguments); };
    ZijinUtilOriginal.prototype.scanFingerprint = function () { return cordova(this, "scanFingerprint", {}, arguments); };
    ZijinUtilOriginal.prototype.verifyFingerprint = function () { return cordova(this, "verifyFingerprint", {}, arguments); };
    ZijinUtilOriginal.prototype.loadFpData = function (fpDataList) { return cordova(this, "loadFpData", {}, arguments); };
    ZijinUtilOriginal.pluginName = "ZijinUtil";
    ZijinUtilOriginal.plugin = "cordova-plugin-x-zijinutil";
    ZijinUtilOriginal.pluginRef = "cordova.plugins.ZijinUtil";
    ZijinUtilOriginal.platforms = ["Android"];
    return ZijinUtilOriginal;
}(IonicNativePlugin));
var ZijinUtil = new ZijinUtilOriginal();
export { ZijinUtil };
export var MemBank;
(function (MemBank) {
    MemBank["passwordArea"] = "0";
    MemBank["epcArea"] = "1";
    MemBank["tidArea"] = "2";
    MemBank["userArea"] = "3";
})(MemBank || (MemBank = {}));
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiaW5kZXguanMiLCJzb3VyY2VSb290IjoiIiwic291cmNlcyI6WyIuLi8uLi8uLi8uLi9zcmMvQGlvbmljLW5hdGl2ZS9wbHVnaW5zL3ppamluLXV0aWwvaW5kZXgudHMiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6Ijs7Ozs7Ozs7Ozs7OztBQUNBLE9BQU8sOEJBQTBGLE1BQU0sb0JBQW9CLENBQUM7QUFDNUgsT0FBTyxFQUFFLFVBQVUsRUFBRSxNQUFNLE1BQU0sQ0FBQzs7SUFTSCw2QkFBaUI7Ozs7SUFNOUMsMkJBQU87SUFRUCxzQ0FBa0I7SUFRbEIscUNBQWlCO0lBUWpCLDRCQUFRO0lBU1Isa0NBQWMsYUFBQyxXQUFtQjtJQVNsQywyQkFBTyxhQUFDLE1BQWlDO0lBU3pDLDJCQUFPLGFBQUMsTUFBc0U7SUFTOUUsMkJBQU8sYUFBQyxNQUEyRjtJQVNuRyw0QkFBUSxhQUFDLE1BQXlHO0lBU2xILHlCQUFLO0lBUUwsb0NBQWdCO0lBUWhCLHFDQUFpQjtJQVFqQix3QkFBSTtJQVFKLG9DQUFnQjtJQVFoQix3Q0FBb0I7SUFTcEIsbUNBQWUsYUFBQyxZQUFvQjtJQVVwQyxtQ0FBZTtJQVdmLG9DQUFnQjtJQVVoQixtQ0FBZTtJQVVmLHFDQUFpQjtJQVdqQiw4QkFBVSxhQUFDLFVBQXlCOzs7OztvQkFwTXRDO0VBVytCLGlCQUFpQjtTQUFuQyxTQUFTO0FBK0x0QixNQUFNLENBQU4sSUFBWSxPQUtYO0FBTEQsV0FBWSxPQUFPO0lBQ2pCLDZCQUFrQixDQUFBO0lBQ2xCLHdCQUFhLENBQUE7SUFDYix3QkFBYSxDQUFBO0lBQ2IseUJBQWMsQ0FBQTtBQUNoQixDQUFDLEVBTFcsT0FBTyxLQUFQLE9BQU8sUUFLbEIiLCJzb3VyY2VzQ29udGVudCI6WyJpbXBvcnQgeyBJbmplY3RhYmxlIH0gZnJvbSAnQGFuZ3VsYXIvY29yZSc7XG5pbXBvcnQgeyBQbHVnaW4sIENvcmRvdmEsIENvcmRvdmFQcm9wZXJ0eSwgQ29yZG92YUluc3RhbmNlLCBJbnN0YW5jZVByb3BlcnR5LCBJb25pY05hdGl2ZVBsdWdpbiB9IGZyb20gJ0Bpb25pYy1uYXRpdmUvY29yZSc7XG5pbXBvcnQgeyBPYnNlcnZhYmxlIH0gZnJvbSAncnhqcyc7XG5cbkBQbHVnaW4oe1xuICBwbHVnaW5OYW1lOiAnWmlqaW5VdGlsJyxcbiAgcGx1Z2luOiAnY29yZG92YS1wbHVnaW4teC16aWppbnV0aWwnLCAvLyBucG0gcGFja2FnZSBuYW1lLCBleGFtcGxlOiBjb3Jkb3ZhLXBsdWdpbi1jYW1lcmFcbiAgcGx1Z2luUmVmOiAnY29yZG92YS5wbHVnaW5zLlppamluVXRpbCcsIC8vIHRoZSB2YXJpYWJsZSByZWZlcmVuY2UgdG8gY2FsbCB0aGUgcGx1Z2luLCBleGFtcGxlOiBuYXZpZ2F0b3IuZ2VvbG9jYXRpb25cbiAgcGxhdGZvcm1zOiBbJ0FuZHJvaWQnXSAvLyBBcnJheSBvZiBwbGF0Zm9ybXMgc3VwcG9ydGVkLCBleGFtcGxlOiBbJ0FuZHJvaWQnLCAnaU9TJ11cbn0pXG5ASW5qZWN0YWJsZSgpXG5leHBvcnQgY2xhc3MgWmlqaW5VdGlsIGV4dGVuZHMgSW9uaWNOYXRpdmVQbHVnaW4ge1xuXG4gIC8qKlxuICAgKiDlvIDlkK9VSEbmqKHlnZdcbiAgICovXG4gIEBDb3Jkb3ZhKClcbiAgb3BlblVoZigpOiBQcm9taXNlPHN0cmluZz4ge1xuICAgIHJldHVybjtcbiAgfVxuXG4gIC8qKlxuICAgKiDlvIDlkK/nm5jngrlcbiAgICovXG4gIEBDb3Jkb3ZhKClcbiAgc3RhcnRJbnZlbnRvcnlSZWFsKCk6IE9ic2VydmFibGU8SW52ZW50b3J5VGFnTWFwW10+IHtcbiAgICByZXR1cm47XG4gIH1cblxuICAvKipcbiAgICog5YGc5q2i55uY54K5XG4gICAqL1xuICBAQ29yZG92YSgpXG4gIHN0b3BJbnZlbnRvcnlSZWFsKCk6IFByb21pc2U8c3RyaW5nPiB7XG4gICAgcmV0dXJuO1xuICB9XG5cbiAgLyoqXG4gICAqIOWFs+mXrVVIRuaooeWdl1xuICAgKi9cbiAgQENvcmRvdmEoKVxuICBjbG9zZVVoZigpOiBQcm9taXNlPHN0cmluZz4ge1xuICAgIHJldHVybjtcbiAgfVxuXG4gIC8qKlxuICAgKiDorr7nva7lpKnnur/lip/njodcbiAgICogQHBhcmFtIG91dHB1dFBvd2VyIOWKn+eOh++8iDHvvZ4zM2Ri77yJXG4gICAqL1xuICBAQ29yZG92YSgpXG4gIHNldE91dHB1dFBvd2VyKG91dHB1dFBvd2VyOiBudW1iZXIpOiBQcm9taXNlPHN0cmluZz4ge1xuICAgIHJldHVybjtcbiAgfVxuXG4gIC8qKlxuICAgKiDmoIfnrb7nga3mtLtcbiAgICogQHBhcmFtIHBhcmFtc1xuICAgKi9cbiAgQENvcmRvdmEoKVxuICBraWxsVGFnKHBhcmFtczogeyBidEFyeVBhc3NXb3JkOiBzdHJpbmcgfSk6IFByb21pc2U8c3RyaW5nPiB7XG4gICAgcmV0dXJuO1xuICB9XG5cbiAgLyoqXG4gICAqIOmUgeWumuagh+etvlxuICAgKiBAcGFyYW0gcGFyYW1zXG4gICAqL1xuICBAQ29yZG92YSgpXG4gIGxvY2tUYWcocGFyYW1zOiB7IGJ0QXJ5UGFzc1dvcmQ6IHN0cmluZywgYnRNZW1CYW5rOiBNZW1CYW5rLCBidExvY2tUeXBlOiBhbnkgfSk6IFByb21pc2U8c3RyaW5nPiB7XG4gICAgcmV0dXJuO1xuICB9XG5cbiAgLyoqXG4gICAqIOivu+WPluagh+etvlxuICAgKiBAcGFyYW0gcGFyYW1zXG4gICAqL1xuICBAQ29yZG92YSgpXG4gIHJlYWRUYWcocGFyYW1zOiB7IGJ0TWVtQmFuazogTWVtQmFuaywgYnRXb3JkQWRkOiBzdHJpbmcsIGJ0V29yZENudDogc3RyaW5nLCBidEFyeVBhc3NXb3JkOiBzdHJpbmcgfSk6IFByb21pc2U8c3RyaW5nPiB7XG4gICAgcmV0dXJuO1xuICB9XG5cbiAgLyoqXG4gICAqIOWGmeaVsOaNruWIsOagh+etvlxuICAgKiBAcGFyYW0gcGFyYW1zXG4gICAqL1xuICBAQ29yZG92YSgpXG4gIHdyaXRlVGFnKHBhcmFtczogeyBidE1lbUJhbms6IE1lbUJhbmssIGJ0V29yZEFkZDogc3RyaW5nLCBidFdvcmRDbnQ6IHN0cmluZywgYnRBcnlQYXNzV29yZDogc3RyaW5nLCBkYXRhOiBzdHJpbmcgfSk6IFByb21pc2U8c3RyaW5nPiB7XG4gICAgcmV0dXJuO1xuICB9XG5cbiAgLyoqXG4gICAqIOWkjeS9jVxuICAgKiBAcGFyYW0gcGFyYW1zXG4gICAqL1xuICBAQ29yZG92YSgpXG4gIHJlc2V0KCk6IHZvaWQge1xuXG4gIH1cblxuICAvKipcbiAgICog5byA5ZCv55uR5ZCs5p2h56CB5omr5o+P55qE57uT5p6cXG4gICAqL1xuICBAQ29yZG92YSgpXG4gIG9wZW5TY2FuUmVjZWl2ZXIoKTogT2JzZXJ2YWJsZTxzdHJpbmc+IHtcbiAgICByZXR1cm47XG4gIH1cblxuICAvKipcbiAgICog5YWz6Zet5a+55p2h56CB5omr5o+P57uT5p6c55qE55uR5ZCsXG4gICAqL1xuICBAQ29yZG92YSgpXG4gIGNsb3NlU2NhblJlY2VpdmVyKCk6IHZvaWQge1xuXG4gIH1cblxuICAvKipcbiAgICog5Y2V5qyh5p2h56CB5omr5o+PXG4gICAqL1xuICBAQ29yZG92YSgpXG4gIHNjYW4oKTogUHJvbWlzZTxzdHJpbmc+IHtcbiAgICByZXR1cm47XG4gIH1cblxuICAvKipcbiAgICog6L+e57ut5p2h56CB5omr5o+PXG4gICAqL1xuICBAQ29yZG92YSgpXG4gIGNvbnRpbnVlU2Nhbm5pbmcoKTogT2JzZXJ2YWJsZTxzdHJpbmc+IHtcbiAgICByZXR1cm47XG4gIH1cblxuICAvKipcbiAgICog5YGc5q2i6L+e57ut5p2h56CB5omr5o+PXG4gICAqL1xuICBAQ29yZG92YSgpXG4gIHN0b3BDb250aW51ZVNjYW5uaW5nKCk6IHZvaWQge1xuXG4gIH1cblxuICAvKipcbiAgICog6K6+572u6L+e57ut5omr5o+P5pe25Lit6Ze055qE6Ze06ZqUXG4gICAqIEBwYXJhbSBpbnRlcnZhbFRpbWVcbiAgICovXG4gIEBDb3Jkb3ZhKClcbiAgc2V0U2NhbkludGVydmFsKGludGVydmFsVGltZTogbnVtYmVyKTogdm9pZCB7XG5cbiAgfVxuXG4gIC8qKlxuICAgKiBAZGVzY3JpcHRpb24g5omT5byA5oyH57q55omr5o+P5LuqXG4gICAqIEByZXR1cm5zIHt2b2lkfVxuICAgKiBAbWVtYmVyb2YgWmlqaW5VdGlsXG4gICAqL1xuICBAQ29yZG92YSgpXG4gIG9wZW5GaW5nZXJwcmludCgpOiBQcm9taXNlPHN0cmluZz4ge1xuICAgIHJldHVybjtcbiAgfVxuXG4gIC8qKlxuICAgKiBAZGVzY3JpcHRpb24g5YWz6Zet5oyH57q55omr5o+P5LuqXG4gICAqXG4gICAqIEByZXR1cm5zIHt2b2lkfVxuICAgKiBAbWVtYmVyb2YgWmlqaW5VdGlsXG4gICAqL1xuICBAQ29yZG92YSgpXG4gIGNsb3NlRmluZ2VycHJpbnQoKTogdm9pZCB7XG5cbiAgfVxuXG4gIC8qKlxuICAgKiBAZGVzY3JpcHRpb24g6I635Y+W5oyH57q554m55b6B5YC8XG4gICAqIEByZXR1cm5zIHtQcm9taXNlPGFueT59XG4gICAqIEBtZW1iZXJvZiBaaWppblV0aWxcbiAgICovXG4gIEBDb3Jkb3ZhKClcbiAgc2NhbkZpbmdlcnByaW50KCk6IFByb21pc2U8c3RyaW5nPiB7XG4gICAgcmV0dXJuO1xuICB9XG5cbiAgLyoqXG4gICAqIEBkZXNjcmlwdGlvbiDmr5Tlr7nmjIfnurnnibnlvoHlgLxcbiAgICogQHJldHVybnMge1Byb21pc2U8YW55Pn1cbiAgICogQG1lbWJlcm9mIFppamluVXRpbFxuICAgKi9cbiAgQENvcmRvdmEoKVxuICB2ZXJpZnlGaW5nZXJwcmludCgpOiBQcm9taXNlPHN0cmluZz4ge1xuICAgIHJldHVybjtcbiAgfVxuXG4gIC8qKlxuICAgKiBAZGVzY3JpcHRpb24g5ZCM5q2l5oyH57q554m55b6B5YC8XG4gICAqIEBwYXJhbSB7c3RyaW5nW119IGNoYXJBcnJheSDmjIfnurnnibnlvoHmlbDnu4RcbiAgICogQHJldHVybnMge1Byb21pc2U8YW55Pn1cbiAgICogQG1lbWJlcm9mIFppamluVXRpbFxuICAgKi9cbiAgQENvcmRvdmEoKVxuICBsb2FkRnBEYXRhKGZwRGF0YUxpc3Q6IEFycmF5PHN0cmluZz4pOiBQcm9taXNlPGFueT4ge1xuICAgIHJldHVybjtcbiAgfVxuXG59XG5cbmV4cG9ydCBlbnVtIE1lbUJhbmsge1xuICBwYXNzd29yZEFyZWEgPSAnMCcsXG4gIGVwY0FyZWEgPSAnMScsXG4gIHRpZEFyZWEgPSAnMicsXG4gIHVzZXJBcmVhID0gJzMnXG59XG5cbmV4cG9ydCBpbnRlcmZhY2UgSW52ZW50b3J5VGFnTWFwIHtcbiAgc3RyUEM6IHN0cmluZztcbiAgc3RyQ1JDOiBzdHJpbmc7XG4gIHN0ckVQQzogc3RyaW5nO1xuICBidEFudElkOiBudW1iZXI7XG4gIHN0clJTU0k6IHN0cmluZztcbiAgblJlYWRDb3VudDogbnVtYmVyO1xuICBzdHJGcmVxOiBzdHJpbmc7XG4gIG5BbnQxOiBudW1iZXI7XG4gIG5BbnQyOiBudW1iZXI7XG4gIG5BbnQzOiBudW1iZXI7XG4gIG5BbnQ0OiBudW1iZXI7XG59XG5cbiJdfQ==