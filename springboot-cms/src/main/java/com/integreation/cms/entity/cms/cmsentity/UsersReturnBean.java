package com.integreation.cms.entity.cms.cmsentity;


import com.integreation.cms.entity.BaseEntity;

import java.util.Date;

/**
 * Created by Admin on 2017/3/25.
 */
public class UsersReturnBean extends BaseEntity {
    private String userNo;

    private String userToken;

    private String loginName;

    private String loginPassword;

    private String resourcesNo;

    private Long resourceType;
    
    private String resourceName;

    private String realname;

    private Long sex;

    private String phone;

    private String address;

    private Long userState;

    private Long onLineState;

    private Date lastLoginTime;

    private Date addTime;

    private Date updateTime;

    private Date loginLimitTime;

    private String userImg;

    private String roleName;

    private String roleNo;

    public String getRoleNo() {
        return roleNo;
    }

    public void setRoleNo(String roleNo) {
        this.roleNo = roleNo;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo == null ? null : userNo.trim();
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken == null ? null : userToken.trim();
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword == null ? null : loginPassword.trim();
    }

    public String getResourcesNo() {
        return resourcesNo;
    }

    public void setResourcesNo(String resourcesNo) {
        this.resourcesNo = resourcesNo == null ? null : resourcesNo.trim();
    }

    public Long getResourceType() {
        return resourceType;
    }

    public void setResourceType(Long resourceType) {
        this.resourceType = resourceType;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public Long getSex() {
        return sex;
    }

    public void setSex(Long sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Long getUserState() {
        return userState;
    }

    public void setUserState(Long userState) {
        this.userState = userState;
    }

    public Long getOnLineState() {
        return onLineState;
    }

    public void setOnLineState(Long onLineState) {
        this.onLineState = onLineState;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getLoginLimitTime() {
        return loginLimitTime;
    }

    public void setLoginLimitTime(Date loginLimitTime) {
        this.loginLimitTime = loginLimitTime;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg == null ? null : userImg.trim();
    }

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
    
}
