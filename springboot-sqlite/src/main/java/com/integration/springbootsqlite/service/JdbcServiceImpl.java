package com.integration.springbootsqlite.service;

import com.integration.springbootsqlite.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by ZhangGang on 2019/12/25
 */
@Service
public class JdbcServiceImpl implements JdbcService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public void insertUser(User user) {
        String sql = "insert into t_user(id, name) values(?,?)";
        jdbcTemplate.update(sql, user.getId(), user.getName());
//		int num = 1/0;  //放开测试事务。预期结果为添加失败，库中无数据。测试结果与预期一致
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        String sql = "update t_user set name = ? where id = ?";

        PreparedStatementSetter pss = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement arg0) throws SQLException {
                arg0.setString(1, user.getName());
                arg0.setString(2, user.getId());
            }
        };

        jdbcTemplate.update(sql, pss);
    }

    @Override
    public List<User> selectUser(User user) {
        String sql = "select id,name from t_user where 1=1 ";

        RowMapper<User> rowMapper = new RowMapper<User>() {

            @Override
            public User mapRow(ResultSet arg0, int arg1) throws SQLException {
                User user = new User();
                if(arg0.wasNull()) {
                    return user;
                }
                user.setId(arg0.getString(1));
                user.setName(arg0.getString(2));
                return user;
            }
        };
        Object[] args = null;
        if(user != null) {
            if(user.getId() != null) {
                sql += "and id = ?";
                args = new Object[1];
                args[0] = user.getId();
            }
        }
        return jdbcTemplate.query(sql, args, rowMapper);
    }

    @Override
    @Transactional
    public void deleteUser(Integer id) {
        String sql = "delete from t_user where id = ?";

        jdbcTemplate.update(sql, String.valueOf(id));
    }
}
