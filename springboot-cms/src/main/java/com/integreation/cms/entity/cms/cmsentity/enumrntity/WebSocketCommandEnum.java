package com.integreation.cms.entity.cms.cmsentity.enumrntity;

/**
 * Created by ZhangGang on 2019/6/9 0009.
 */
public enum WebSocketCommandEnum {
    WEB_APP_CHAT(0, "web.app.chat"),
    WEB_APP_ERROR(1, "web.app.error"),
    WEB_APP_NEW_ORDER(2, "web.app.newOrder"),
    WEB_APP_NEW_HEARTBEAT(3, "web.app.heartbeat"),
    WEB_APP_CHAT_RESPONSE(4,"web.app.chat.response");

    private Integer code;
    private String message;

    WebSocketCommandEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
