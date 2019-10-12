package com.integreation.cms.entity.cms.cmsrequestvo;


import com.integreation.cms.entity.BaseEntity;
import lombok.Data;

import java.util.List;
/*
* web端数据请求实体转换
* */

@Data
public class UsersEntityVo extends BaseEntity {
    private String userId;//	是	string	用户id
    private String userLoginName;//	是	string	登录名
    private String userPassword;//是	string	密码
    private String userRealName;//	是	string	昵称
    private String userPhone;//	否	string	联系电话
    private String userProfilePhoto;//	否	string	用户头像base64格式

    private String searchUserLoginName;//	是	String	登录名
    private String searchUserRealName;//	是	String	姓名
    private String searchUserRoleId	;//是	String	角色id
    private List<String> userRoleIds;	;//是	string	当前选择的角色id
    private Integer userPosition;//用户职位（0：普通用户，1：客服）
    private String jobNumber;  //用户工号

}