package com.integreation.cms.entity.cms.cmsresponsevo;


import lombok.Data;
/*
* 返回web端数据请求实体转换
* */

@Data
public class UsersEntityResponseVo  {
    private String userId;//	string	用户id
    private String userProfilePhoto;//	string	头像完整路径
    private String userRealName;//	string	姓名
    private String userLoginName;//	string	登录名
    private String userPhone;//	string	联系电话
    private String userRoleIds;//	Array	所属角色id数组
    private Integer userPosition;//用户职位（0：普通用户，1：客服）
    private String jobNumber;  //用户工号
}