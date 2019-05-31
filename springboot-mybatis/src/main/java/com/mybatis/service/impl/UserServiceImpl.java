package com.mybatis.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mybatis.dao.cluster.ClusterUserMapper;
import com.mybatis.dao.master.MasterUserMapper;
import com.mybatis.entity.UserEntity;
import com.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ZhangGang on 2018/11/29.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private MasterUserMapper masterUserMapper;
    @Autowired
    private ClusterUserMapper clusterUserMapper;
    @Override
    public UserEntity getuserinfo(String id) {
        UserEntity userEntity = masterUserMapper.getOne(id);
        return userEntity;
    }

    @Override
    public void setuserinfo(UserEntity userEntity) {
        masterUserMapper.insert(userEntity);
    }
    @Override
    public UserEntity getclusteruserinfo(String id) {
        UserEntity userEntity = clusterUserMapper.getOne(id);
        return userEntity;
    }

    @Override
    public void setclusteruserinfo(UserEntity userEntity) {
        clusterUserMapper.insert(userEntity);
    }

    public PageInfo<UserEntity> getUserBySearch(int pageNum, int pageSize) {
        // TODO Auto-generated method stub
        PageHelper.startPage(pageNum,pageSize);
        List<UserEntity> list=clusterUserMapper.getAll();
        PageInfo<UserEntity> pageInfo = new PageInfo<UserEntity>(list);
        return pageInfo;
    }
}
