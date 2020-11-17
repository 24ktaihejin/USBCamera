var exec = require('cordova/exec');
// region UHF超高频扫描
exports.openUhf = function (successCallback, errorCallback) {
    exec(successCallback, errorCallback, 'ZijinUtil', 'openUhf', [])
}

exports.startInventoryReal = function (successCallback, errorCallback) {
    exec(successCallback, errorCallback, 'ZijinUtil', 'startInventoryReal', [])
}

exports.stopInventoryReal = function (successCallback, errorCallback) {
    exec(successCallback, errorCallback, 'ZijinUtil', 'stopInventoryReal', [])
}

exports.closeUhf = function (successCallback, errorCallback) {
    exec(successCallback, errorCallback, 'ZijinUtil', 'closeUhf', [])
}

exports.setOutputPower = function (outputPower, successCallback, errorCallback) {
    exec(successCallback, errorCallback, 'ZijinUtil', 'setOutputPower', [outputPower])
}

exports.killTag = function (params, successCallback, errorCallback) {
    exec(successCallback, errorCallback, 'ZijinUtil', 'killTag', [params])
}

exports.lockTag = function (params, successCallback, errorCallback) {
    exec(successCallback, errorCallback, 'ZijinUtil', 'lockTag', [params])
}

exports.readTag = function (params, successCallback, errorCallback) {
    exec(successCallback, errorCallback, 'ZijinUtil', 'readTag', [params])
}

exports.writeTag = function (params, successCallback, errorCallback, params) {
    exec(successCallback, errorCallback, 'ZijinUtil', 'writeTag', [params])
}

exports.reset = function () {
    exec(null, null, 'ZijinUtil', 'reset', [])
}
// end region

// region 条码扫描
exports.openScanReceiver = function (successCallback, errorCallback) {
    exec(successCallback, errorCallback, 'ZijinUtil', 'openScanReceiver', [])
}

exports.closeScanReceiver = function () {
    exec(null, null, 'ZijinUtil', 'closeScanReceiver', [])
}

exports.scan = function (successCallback, errorCallback) {
    exec(successCallback, errorCallback, 'ZijinUtil', 'scan', [])
}

exports.continueScanning = function (successCallback, errorCallback) {
    exec(successCallback, errorCallback, 'ZijinUtil', 'continueScanning', [])
}

exports.stopContinueScanning = function () {
    exec(null, null, 'ZijinUtil', 'stopContinueScanning', [])
}

exports.setScanInterval = function (intervalTime) {
    exec(null, null, 'ZijinUtil', 'setScanInterval', [{
        "intervalTime": intervalTime
    }])
}
// end region 

// region 指纹
exports.openFingerprint = function (successCallback, errorCallback) {
    exec(successCallback, errorCallback, 'ZijinUtil', 'openFingerprint', [])
}

exports.closeFingerprint = function () {
    exec(null, null, 'ZijinUtil', 'closeFingerprint', [])
}

exports.scanFingerprint = function (successCallback, errorCallback) {
    exec(successCallback, errorCallback, 'ZijinUtil', 'scanFingerprint', [])
}

exports.verifyFingerprint = function (successCallback, errorCallback) {
    exec(successCallback, errorCallback, 'ZijinUtil', 'verifyFingerprint', [])
}

exports.loadFpData = function (fpDataList, successCallback, errorCallback) {
    exec(successCallback, errorCallback, 'ZijinUtil', 'loadFpData', [...fpDataList])
}
// end region
