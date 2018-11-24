package com.wxpay.err;

public class DangerException extends BaseException {
    public DangerException(int errCode, String errMsg) {
        super(errCode, errMsg);
    }

    public DangerException(String message, int errCode, String errMsg) {
        super(message, errCode, errMsg);
    }

    public DangerException(String message, Throwable cause, int errCode, String errMsg) {
        super(message, cause, errCode, errMsg);
    }

    public DangerException(Throwable cause, int errCode, String errMsg) {
        super(cause, errCode, errMsg);
    }

    public DangerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int errCode, String errMsg) {
        super(message, cause, enableSuppression, writableStackTrace, errCode, errMsg);
    }

    public static class DangerExceptionCode {
        public static final int ApiCC = 30000; //API疑似被CC
        public static final int ApiCCTest = 30001; //API疑似被CC测试
        public static final int ApiFounded = 30010; //不想暴露的接口地址可能已经暴露
    }
}
