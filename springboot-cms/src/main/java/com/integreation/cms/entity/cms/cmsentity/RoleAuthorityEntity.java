package com.integreation.cms.entity.cms.cmsentity;

import lombok.Data;

import java.util.Date;

@Data
public class RoleAuthorityEntity {
    private String guid;
    private String roleId;
    private String authorityId;
    private Date modifyTime;
    private Date addTime;

    //辅助字段
    private String authorityState;
}