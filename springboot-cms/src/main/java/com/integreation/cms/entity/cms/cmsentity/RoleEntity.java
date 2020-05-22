package com.integreation.cms.entity.cms.cmsentity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class RoleEntity {
    private String guid;
    private String roleName;
    private String startPage;
    private String roleDescribe;
    private Date modifyTime;
    private Date addTime;
    /**
     * 用于标记是否过滤掉内置的角色
     * 如果不空则将用此值过滤掉guid不等于此项的角色
     */
    private String uiview;
    //用于查询校验,不是自身
    private String notself;
    private List<String> roleAuthIds;
}