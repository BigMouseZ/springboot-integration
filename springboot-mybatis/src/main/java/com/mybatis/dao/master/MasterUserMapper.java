package com.mybatis.dao.master;


import com.mybatis.entity.UserEntity;

import java.util.List;

/**
 * Created by ZhangGang on 2018/5/11.
 */
public interface MasterUserMapper {

    List<UserEntity> getAll();

    UserEntity getOne(String id);

    void insert(UserEntity user);

    void update(UserEntity user);

    void delete(String id);
}
