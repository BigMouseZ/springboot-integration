package com.integreation.cms.entity.cms.cmsrequestvo;

import lombok.Data;

import java.util.List;
/*
* web端数据请求实体转换
* */

@Data
public class RoleEntityVo {
    private String roleId;//	是	string	角色id
    private String roleName	;//是	string	角色名称
    private String roleDescription;//	否	string	角色描述
    private List<String>  roleAuthIds;//	是	Array	当前选择的权限id数组
}