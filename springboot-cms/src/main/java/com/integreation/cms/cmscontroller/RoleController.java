package com.integreation.cms.cmscontroller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.integreation.cms.aop.AuthCheck;
import com.integreation.cms.entity.cms.cmsentity.RoleEntity;
import com.integreation.cms.entity.cms.cmsrequestvo.RoleEntityVo;
import com.integreation.cms.entity.response.RequestVo;
import com.integreation.cms.entity.response.ResponseVo;
import com.integreation.cms.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ZhangGang on 2019/7/18.
 * 角色相关接口:
 * 添加角色
 * 编辑角色信息
 * 删除角色信息
 * 角色数据列表
 * 编辑角色权限
 * 获取角色已授权的系统权限id
 */
@Slf4j
@RestController
@RequestMapping("/cms/role")
@CrossOrigin
public class RoleController {

    @Autowired
    private RoleService roleService;


    //添加角色
     @AuthCheck(checkAuth=false)
    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    public ResponseVo addRole(@RequestBody RequestVo requestVo) {
        RoleEntityVo roleEntityVo = JSONObject.parseObject(JSON.toJSONString(requestVo.getParams()), new TypeReference<RoleEntityVo>() {});
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleName(roleEntityVo.getRoleName());
        roleEntity.setRoleDescribe(roleEntityVo.getRoleDescription());
        return roleService.addRole(roleEntity);
    }

    //编辑角色信息
     @AuthCheck(checkAuth=false)
    @RequestMapping(value = "/editRole", method = RequestMethod.POST)
    public ResponseVo editRole(@RequestBody RequestVo requestVo) {
        RoleEntityVo roleEntityVo = JSONObject.parseObject(JSON.toJSONString(requestVo.getParams()), new TypeReference<RoleEntityVo>() {});
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setGuid(roleEntityVo.getRoleId());
        roleEntity.setRoleName(roleEntityVo.getRoleName());
        roleEntity.setRoleDescribe(roleEntityVo.getRoleDescription());
        roleEntity.setNotself(roleEntityVo.getRoleId());
        return roleService.editRole(roleEntity);
    }

    //删除角色信息
     @AuthCheck(checkAuth=false)
    @RequestMapping(value = "/deleteRole", method = RequestMethod.POST)
    public ResponseVo deleteRole(@RequestBody RequestVo requestVo) {
        RoleEntityVo roleEntityVo = JSONObject.parseObject(JSON.toJSONString(requestVo.getParams()), new TypeReference<RoleEntityVo>() {});
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setGuid(roleEntityVo.getRoleId());
        return roleService.deleteRole(roleEntity);
    }

    //角色数据列表
     @AuthCheck(checkAuth=false)
    @RequestMapping(value = "/ListAllRole", method = RequestMethod.POST)
    public ResponseVo ListAllRole(@RequestBody RequestVo requestVo) {
        return roleService.ListAllRole();
    }

    //编辑角色权限
     @AuthCheck(checkAuth=false)
    @RequestMapping(value = "/editRoleAuth", method = RequestMethod.POST)
    public ResponseVo editRoleAuth(@RequestBody RequestVo requestVo) {
        RoleEntityVo roleEntityVo = JSONObject.parseObject(JSON.toJSONString(requestVo.getParams()), new TypeReference<RoleEntityVo>() {});
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setGuid(roleEntityVo.getRoleId());
        roleEntity.setRoleAuthIds(roleEntityVo.getRoleAuthIds());
        return roleService.editRoleAuth(roleEntity);
    }

    //获取角色已授权的系统权限id
     @AuthCheck(checkAuth=false)
    @RequestMapping(value = "/listRoleAuth", method = RequestMethod.POST)
    public ResponseVo listRoleAuth(@RequestBody RequestVo requestVo) {
        RoleEntityVo roleEntityVo = JSONObject.parseObject(JSON.toJSONString(requestVo.getParams()), new TypeReference<RoleEntityVo>() {});
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setGuid(roleEntityVo.getRoleId());
        return roleService.listRoleAuth(roleEntity);
    }
}
