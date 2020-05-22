package com.integreation.cms.entity.cms.cmsentity;

import lombok.Data;

import java.util.Date;

@Data
public class UserRoleEntity {
    private String guid;
    private String userId;
    private String roleId;
    private Date modifyTime;
    private Date addTime;
    private String roleName;
    /**
     * null表示用户为授权该角色，不是null则表示用户已授权该角色
     */
    private String roleState;


}