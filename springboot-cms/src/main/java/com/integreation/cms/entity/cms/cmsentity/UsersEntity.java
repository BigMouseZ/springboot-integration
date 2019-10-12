package com.integreation.cms.entity.cms.cmsentity;


import com.integreation.cms.entity.BaseEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UsersEntity  extends BaseEntity {
    private String guid;
    private String loginName;
    private String realName;
    private String phone;
    private String sex;
    private String address;
    private String headIcon;
    private String loginPwd;
    private Date modifyTime;
    private Date addTime;
    private String roleId;
    private String organizationId;
    private String userState;  //用户状态(0：启用；1：冻结)
    private Long onLineState;
    private Integer userPosition;//用户职位（0：普通用户，1：客服）
    private String jobNumber;  //用户工号


    //查询辅助字段
    private Date lastLoginTime;
    private Date loginLimitTime;
    private String organizationStyle; //所属组织机构类别
    private Integer organizationDeep; //所属组织机构在树结构中的深度
    private List<String> roleIds;  //用户角色id集合
    private String searchUserRoleId;
    /**
     * 用于标记是否过滤掉内置的角色
     * 如果不空则将用此值过滤掉guid不等于此项的角色
     */
    private String uiview;
}