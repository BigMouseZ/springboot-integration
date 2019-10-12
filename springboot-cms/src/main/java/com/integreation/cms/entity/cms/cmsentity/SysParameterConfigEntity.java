package com.integreation.cms.entity.cms.cmsentity;

import lombok.Data;

import java.util.Date;

@Data
public class SysParameterConfigEntity {
    private String guid;

    private String parameterType;

    private String parentKey;

    private String parameterKey;

    private String parameterName;

    private String parameterValue;

    private Integer sort;

    private Boolean edit;

    private String parameterDescribe;

    private Date modifyTime;

    private Date addTime;

}