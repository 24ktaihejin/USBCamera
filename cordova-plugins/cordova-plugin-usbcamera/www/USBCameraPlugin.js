var exec = require('cordova/exec');

exports.faceVerifyByUsbCamera = function (params, success, error) {
    exec(success, error, 'USBCameraPlugin', 'faceVerifyByUsbCamera', [params]);
};
exports.getUserInfoByUsbCamera = function (params, success, error) {
    exec(success, error, 'USBCameraPlugin', 'getUserInfoByUsbCamera', [params]);
};
exports.getUserInfoByCamera = function (params, success, error) {
    exec(success, error, 'USBCameraPlugin', 'getUserInfoByCamera', [params]);
};
exports.faceVerifyByCamera = function (params, success, error) {
    exec(success, error, 'USBCameraPlugin', 'faceVerifyByCamera', [params]);
};
