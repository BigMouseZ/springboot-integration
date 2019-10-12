package com.integreation.cms.entity.cms.cmsresponsevo;

import lombok.Data;
/*
* 返回web端数据请求实体转换
* */

@Data
public class RoleEntityResponseVo {
    private String roleId;//	是	string	角色id
    private String roleName	;//是	string	角色名称
    private String roleDescription;//	否	string	角色描述
}