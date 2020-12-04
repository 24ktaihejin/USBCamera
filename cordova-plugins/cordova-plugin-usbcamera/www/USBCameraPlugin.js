var exec = require('cordova/exec');

exports.faceVerify = function (params, success, error) {
    exec(success, error, 'USBCameraPlugin', 'faceVerify', [params]);
};
