package com.integration.springbootsqlite.service;

import com.integration.springbootsqlite.entity.User;

import java.util.List;

/**
 * Created by ZhangGang on 2019/12/25
 */
public interface JdbcService {

    /**
     * 添加用户
     * @param user
     */
    public void insertUser(User user);

    /**
     * 更新用户
     * @param user
     */
    public void updateUser(User user);

    /**
     * 查询用户
     * @param user
     * @return
     */
    public List<User> selectUser(User user);

    /**
     * 删除用户
     * @param id
     */
    public void deleteUser(Integer id);
}
