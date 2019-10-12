package com.integreation.cms.cmscontroller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.integreation.cms.aop.AuthCheck;
import com.integreation.cms.entity.cms.cmsentity.AuthorityEntity;
import com.integreation.cms.entity.cms.cmsrequestvo.AuthorityEntityVo;
import com.integreation.cms.entity.response.RequestVo;
import com.integreation.cms.entity.response.ResponseVo;
import com.integreation.cms.service.AuthorityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ZhangGang on 2019/7/18.
 * 权限相关接口:
 * 添加权限
 * 编辑权限信息
 * 删除权限信息
 * 分页查询权限数据(获取所有权限)
 */
@Slf4j
@RestController
@RequestMapping("/cms/authority")
@CrossOrigin
public class AuthorityController {

    @Autowired
    private AuthorityService authorityService;


    //添加权限
    @AuthCheck(checkAuth=false)
    @RequestMapping(value = "/addAuthority", method = RequestMethod.POST)
    public ResponseVo addAuthority(@RequestBody RequestVo requestVo) {
        AuthorityEntityVo authorityEntityVo = JSONObject.parseObject(JSON.toJSONString(requestVo.getParams()), new TypeReference<AuthorityEntityVo>() {});
        AuthorityEntity authorityEntity = new AuthorityEntity();
        authorityEntity.setAuthorityName(authorityEntityVo.getAuthKey());
        authorityEntity.setAuthorityType(authorityEntityVo.getAuthType());
        authorityEntity.setStartPath(authorityEntityVo.getAuthRoute());
        authorityEntity.setDisplayName(authorityEntityVo.getAuthName());
        authorityEntity.setIcon(authorityEntityVo.getAuthIcon());
        authorityEntity.setCommand(authorityEntityVo.getAuthCommand());
        authorityEntity.setSort(authorityEntityVo.getAuthSort()==null ? 0:Integer.valueOf(authorityEntityVo.getAuthSort()));
        authorityEntity.setAuthParent(authorityEntityVo.getAuthParent());
        return authorityService.addAuthority(authorityEntity);
    }

    //编辑权限信息
     @AuthCheck(checkAuth=false)
    @RequestMapping(value = "/editAuthority", method = RequestMethod.POST)
    public ResponseVo editAuthority(@RequestBody RequestVo requestVo) {
        AuthorityEntityVo authorityEntityVo = JSONObject.parseObject(JSON.toJSONString(requestVo.getParams()), new TypeReference<AuthorityEntityVo>() {});
        AuthorityEntity authorityEntity = new AuthorityEntity();
        authorityEntity.setGuid(authorityEntityVo.getAuthId());
        authorityEntity.setAuthorityName(authorityEntityVo.getAuthKey());
        authorityEntity.setAuthorityType(authorityEntityVo.getAuthType());
        authorityEntity.setStartPath(authorityEntityVo.getAuthRoute());
        authorityEntity.setDisplayName(authorityEntityVo.getAuthName());
        authorityEntity.setIcon(authorityEntityVo.getAuthIcon());
        authorityEntity.setCommand(authorityEntityVo.getAuthCommand());
        authorityEntity.setSort(authorityEntityVo.getAuthSort()==null ? 0:Integer.valueOf(authorityEntityVo.getAuthSort()));
        authorityEntity.setAuthParent(authorityEntityVo.getAuthParent());
        return authorityService.editAuthority(authorityEntity);
    }

    //删除权限信息
     @AuthCheck(checkAuth=false)
    @RequestMapping(value = "/deleteAuthority", method = RequestMethod.POST)
    public ResponseVo deleteAuthority(@RequestBody RequestVo requestVo) {
        AuthorityEntityVo authorityEntityVo = JSONObject.parseObject(JSON.toJSONString(requestVo.getParams()), new TypeReference<AuthorityEntityVo>() {});
        AuthorityEntity authorityEntity = new AuthorityEntity();
        authorityEntity.setGuid(authorityEntityVo.getAuthId());
        return authorityService.deleteAuthority(authorityEntity);
    }

    //分页查询权限数据
     @AuthCheck(checkAuth=false)
    @RequestMapping(value = "/pageListAuthority", method = RequestMethod.POST)
    public ResponseVo pageListAuthority(@RequestBody RequestVo requestVo) {
        AuthorityEntityVo authorityEntityVo = JSONObject.parseObject(JSON.toJSONString(requestVo.getParams()), new TypeReference<AuthorityEntityVo>() {});
        AuthorityEntity authorityEntity = new AuthorityEntity();
        authorityEntity.setPage(authorityEntityVo.getPage());
        authorityEntity.setPageSize(authorityEntityVo.getPageSize());
        authorityEntity.setDisplayName(authorityEntityVo.getSearchAuthName());
        authorityEntity.setAuthorityName(authorityEntityVo.getSearchAuthKey());
        return authorityService.pageListAuthority(authorityEntity);
    }

    //获取当前用户角色权限范围
    @AuthCheck(checkAuth=false)
    @RequestMapping(value = "/roleListAllAuthority", method = RequestMethod.POST)
    public ResponseVo roleListAllAuthority(@RequestBody RequestVo requestVo) {

        return authorityService.roleListAllAuthority(requestVo.getSessionId());
    }


}
