package com.integreation.cms.entity.cms.cmsentity;


import com.integreation.cms.entity.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class AuthorityEntity extends BaseEntity {

    private String guid;
    private String authorityName;
    private String authorityType;
    private String startPath;
    private String displayName;
    private Integer sort;
    private String icon;
    private String command;
    private String parentId;
    private Date modifyTime;
    private Date addTime;
    //辅助字段
    private String authParent;//	否	String	父级
    private String parentName;
    private String onlyUserAuto;
    private String userId;
    /**
     * null表示未授权权限，不是null表示已授权权限
     */
    private String authorityState;
}