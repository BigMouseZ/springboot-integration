package com.mybatis.service;

import com.github.pagehelper.PageInfo;
import com.mybatis.entity.UserEntity;

/**
 * Created by ZhangGang on 2018/11/29.
 */
public interface UserService {
    UserEntity getuserinfo(String id);
    void setuserinfo(UserEntity userEntity);
    UserEntity getclusteruserinfo(String id);
    void setclusteruserinfo(UserEntity userEntity);
    PageInfo<UserEntity> getUserBySearch(int pageNum, int pageSize);
}
