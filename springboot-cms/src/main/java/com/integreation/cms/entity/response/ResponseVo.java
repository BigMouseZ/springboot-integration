package com.integreation.cms.entity.response;


import lombok.Data;

@Data
public class ResponseVo {
    private String code = ResponseErrCode.OK;
    private String message="执行失败";
    private String memo;
    private Object data;
    public static class ResponseErrCode {
        public static final String OK = "0";
        public static final String LoginFail = "1";
        public static final String ParamErr = "300";
        public static final String AuthErr = "401";
        public static final String ServerErr = "500";
        public static final String BusinessErr = "600";
        public static final String BusinessErr_NotIn = "601";
    }
}
