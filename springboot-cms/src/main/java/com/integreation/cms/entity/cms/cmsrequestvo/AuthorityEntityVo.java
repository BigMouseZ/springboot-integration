package com.integreation.cms.entity.cms.cmsrequestvo;

import com.integreation.cms.entity.BaseEntity;
import lombok.Data;
/*
 * web端数据请求实体转换
 * */

@Data
public class AuthorityEntityVo extends BaseEntity {
    private String authId;//	是	String	权限id
    private String authKey;//	是	String	权限名称
    private String authType;//是	String	类型
    private String authRoute;//	否	String	前端路由
    private String authCommand;//	否	String	后端命令字
    private String authName;//否	String	显示名
    private String authParent;//	否	String	父级
    private String authSort;//	否	String	排序
    private String authIcon;//	否	String	图标
    private String searchAuthKey;//	否	String	权限名称
    private String searchAuthName;//	否	String	显示名


}