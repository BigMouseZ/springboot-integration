package com.wxpay.err;

public class BusinessException extends BaseException {
    public BusinessException(int errCode, String errMsg) {
        super(errMsg, errCode, errMsg);
    }

    public BusinessException(String message, int errCode, String errMsg) {
        super(message, errCode, errMsg);
    }

    public BusinessException(String message, Throwable cause, int errCode, String errMsg) {
        super(message, cause, errCode, errMsg);
    }

    public BusinessException(Throwable cause, int errCode, String errMsg) {
        super(cause, errCode, errMsg);
    }

    public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int errCode, String errMsg) {
        super(message, cause, enableSuppression, writableStackTrace, errCode, errMsg);
    }

    public static class BusinessExceptionCode {
        public static final int OK = 0;
        public static final int RemoteServerException = 10001; //远程服务器返回失败
        public static final int DataNotFound = 10002; //数据未找到
        public static final int DataNotFound_NotIn = 100021; //无入场数据
        public static final int DataNotFound_Berth = 100022; //数据未找到
        public static final int SessionNotFound = 10003; //登陆数据未找到
        public static final int PayOrderErr = 10004; //支付预下单失败
        public static final int ParamCheck = 10005;
        public static final int BindNotFound = 10006; //绑定关系不存在
        public static final int ThreatErr = 10007; //绑定关系不存在
        public static final int DateExist = 10008; //数据已经存在
        public static final int DateExist_EvtNo = 100081; //事件Id重复
        public static final int PlateNumber_Binded = 10009; //车牌已被绑定
        public static final int PlateNumber_HaveIn = 10010; //该车已自报入场
    }
}
