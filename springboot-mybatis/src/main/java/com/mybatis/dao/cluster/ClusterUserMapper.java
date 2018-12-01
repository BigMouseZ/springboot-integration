package com.mybatis.dao.cluster;


import com.mybatis.entity.UserEntity;

import java.util.List;

/**
 * Created by ZhangGang on 2018/5/11.
 */
public interface ClusterUserMapper {

    List<UserEntity> getAll();

    UserEntity getOne(String id);

    void insert(UserEntity user);

    void update(UserEntity user);

    void delete(String id);
}
