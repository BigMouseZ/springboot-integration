package com.integreation.cms.cmscontroller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.integreation.cms.aop.AuthCheck;
import com.integreation.cms.entity.cms.cmsentity.UsersEntity;
import com.integreation.cms.entity.cms.cmsrequestvo.UsersEntityVo;
import com.integreation.cms.entity.response.RequestVo;
import com.integreation.cms.entity.response.ResponseVo;
import com.integreation.cms.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ZhangGang on 2019/7/18.
 * 用户相关接口:
 * 用户登录
 * 用户注销
 * 添加用户
 * 编辑用户信息
 * 删除用户信息
 * 分页查询用户数据
 *获取用户已授权的角色数据
 *用户角色编辑
 */
@Slf4j
@RestController
@RequestMapping("/cms/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    //用户登录
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseVo login(@RequestBody RequestVo requestVo) {
        UsersEntityVo usersEntityVo =  JSONObject.parseObject(JSON.toJSONString(requestVo.getParams()), new TypeReference<UsersEntityVo>() {});
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setLoginName(usersEntityVo.getUserLoginName());
        usersEntity.setLoginPwd(usersEntityVo.getUserPassword());
        return userService.login(usersEntity);
    }

    //用户注销
    @AuthCheck(checkAuth=false)
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseVo logout(@RequestBody RequestVo requestVo) {
        return userService.logout(requestVo.getSessionId());
    }

    //添加用户
    @AuthCheck(checkAuth=false)
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ResponseVo adduser(@RequestBody RequestVo requestVo) {
        UsersEntityVo usersEntityVo =  JSONObject.parseObject(JSON.toJSONString(requestVo.getParams()), new TypeReference<UsersEntityVo>() {});
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setLoginName(usersEntityVo.getUserLoginName());
        usersEntity.setLoginPwd(usersEntityVo.getUserPassword());
        usersEntity.setRealName(usersEntityVo.getUserRealName());
        usersEntity.setPhone(usersEntityVo.getUserPhone());
        usersEntity.setHeadIcon(usersEntityVo.getUserProfilePhoto());
        usersEntity.setRoleIds(usersEntityVo.getUserRoleIds());
        usersEntity.setUserPosition(usersEntityVo.getUserPosition());
        usersEntity.setJobNumber(usersEntityVo.getJobNumber());
        return userService.adduser(usersEntity);
    }

    //编辑用户信息
    @AuthCheck(checkAuth=false)
    @RequestMapping(value = "/editUser", method = RequestMethod.POST)
    public ResponseVo edituser(@RequestBody RequestVo requestVo) {
        UsersEntityVo usersEntityVo =  JSONObject.parseObject(JSON.toJSONString(requestVo.getParams()), new TypeReference<UsersEntityVo>() {});
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setGuid(usersEntityVo.getUserId());
        usersEntity.setLoginPwd(usersEntityVo.getUserPassword());
        usersEntity.setRealName(usersEntityVo.getUserRealName());
        usersEntity.setHeadIcon(usersEntityVo.getUserProfilePhoto());
        usersEntity.setPhone(usersEntityVo.getUserPhone());
        usersEntity.setUserPosition(usersEntityVo.getUserPosition());
        usersEntity.setJobNumber(usersEntityVo.getJobNumber());
        return userService.edituser(usersEntity);
    }

    //删除用户信息
    @AuthCheck(checkAuth=false)
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public ResponseVo deleteuser(@RequestBody RequestVo requestVo) {
        UsersEntityVo usersEntityVo =  JSONObject.parseObject(JSON.toJSONString(requestVo.getParams()), new TypeReference<UsersEntityVo>() {});
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setGuid(usersEntityVo.getUserId());
        return userService.deleteuser(usersEntity);
    }

    //分页查询用户数据
    @AuthCheck(checkAuth=false)
    @RequestMapping(value = "/pageListUser", method = RequestMethod.POST)
    public ResponseVo pageListUser(@RequestBody RequestVo requestVo) {
        UsersEntityVo usersEntityVo =  JSONObject.parseObject(JSON.toJSONString(requestVo.getParams()), new TypeReference<UsersEntityVo>() {});
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setPage(usersEntityVo.getPage());
        usersEntity.setPageSize(usersEntityVo.getPageSize());
        usersEntity.setLoginName(usersEntityVo.getSearchUserLoginName());
        usersEntity.setRealName(usersEntityVo.getSearchUserRealName());
        usersEntity.setRoleId(usersEntityVo.getSearchUserRoleId());
        return userService.pageListUser(usersEntity);
    }
    //获取用户已授权的角色数据
    @AuthCheck(checkAuth=false)
    @RequestMapping(value = "/listUserRoles", method = RequestMethod.POST)
    public ResponseVo listUserRoles(@RequestBody RequestVo requestVo) {
        UsersEntityVo usersEntityVo =  JSONObject.parseObject(JSON.toJSONString(requestVo.getParams()), new TypeReference<UsersEntityVo>() {});
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setGuid(usersEntityVo.getUserId());
        return userService.listUserRoles(usersEntity);
    }
    //用户角色编辑
    @AuthCheck(checkAuth=false)
    @RequestMapping(value = "/editUserRoles", method = RequestMethod.POST)
    public ResponseVo editUserRoles(@RequestBody RequestVo requestVo) {
        UsersEntityVo usersEntityVo =  JSONObject.parseObject(JSON.toJSONString(requestVo.getParams()), new TypeReference<UsersEntityVo>() {});
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setGuid(usersEntityVo.getUserId());
        usersEntity.setRoleIds(usersEntityVo.getUserRoleIds());
        return userService.editUserRoles(usersEntity);
    }
    //获取用户授权状态
    @AuthCheck(checkAuth=false)
    @RequestMapping(value = "/listUserAuth", method = RequestMethod.POST)
    public ResponseVo listUserAuth(@RequestBody RequestVo requestVo) {
        return userService.listUserAuth(requestVo.getSessionId());
    }

}
