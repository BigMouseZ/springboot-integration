package com.integreation.cms.entity.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class RequestVo implements Serializable {
    private String sessionId;
    private String command;
    private Object params;

}
