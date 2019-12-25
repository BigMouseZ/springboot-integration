package com.integration.springbootsqlite.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by ZhangGang on 2019/12/25
 */
@RestController
@RequestMapping("/quartz")
public class uploadcontroller {

    @Resource
    private JdbcTemplate jdbcTemplate;
    //添加一个job
    @RequestMapping(value = "/addJob", method = RequestMethod.POST)
    public String addjob(String json) throws Exception {


        return "执行成功";
    }

}
