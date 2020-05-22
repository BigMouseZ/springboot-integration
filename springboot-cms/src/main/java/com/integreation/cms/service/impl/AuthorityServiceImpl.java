package com.integreation.cms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.integreation.cms.cmsdao.AuthorityMapper;
import com.integreation.cms.cmsdao.RoleAuthorityMapper;
import com.integreation.cms.entity.PageModule;
import com.integreation.cms.entity.cms.cmsentity.AuthorityEntity;
import com.integreation.cms.entity.cms.cmsentity.CmsRedisKey;
import com.integreation.cms.entity.cms.cmsentity.RoleAuthorityEntity;
import com.integreation.cms.entity.cms.cmsentity.UsersEntity;
import com.integreation.cms.entity.cms.cmsresponsevo.AuthorityEntityResponseVo;
import com.integreation.cms.entity.response.ResponseVo;
import com.integreation.cms.service.AuthorityService;
import com.integreation.cms.utils.redis.redisutils.RedisItemUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by ZhangGang on 2019/7/19.
 */
@Slf4j
@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityMapper authorityMapper;
    @Autowired
    private RoleAuthorityMapper roleAuthorityMapper;
    @Autowired
    private RedisItemUtil redisItemUtil;

    @Override
    public ResponseVo addAuthority(AuthorityEntity authorityEntity) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseVo.ResponseErrCode.AuthErr);
        try {
            //检查当前的权限名称key是否存在
            AuthorityEntity query = new AuthorityEntity();
            query.setAuthorityName(authorityEntity.getAuthorityName());
            AuthorityEntity authorityReturn = authorityMapper.selectByAuthorityName(query);
            if (authorityReturn != null) {
                responseVo.setMessage("该权限名[" + authorityEntity.getAuthorityName() + "]已存在！");
                return responseVo;
            }
            //检查是否存在父级权限
            if (StringUtils.isNotBlank(authorityEntity.getAuthParent())) {
                query.setAuthorityName(authorityEntity.getAuthParent());
                AuthorityEntity pAuth = authorityMapper.selectByAuthorityName(query);
                if (pAuth == null) {
                    responseVo.setMessage("输入的父级名[" + authorityEntity.getAuthParent() + "]在系统中不存在！");
                    return responseVo;
                } else {
                    authorityEntity.setParentId(pAuth.getGuid());
                }
            }
            authorityEntity.setGuid(UUID.randomUUID().toString());
            authorityMapper.insertSelective(authorityEntity);
            responseVo.setCode(ResponseVo.ResponseErrCode.OK);
            responseVo.setMessage("执行成功");
        } catch (Exception e) {
            log.error("添加权限异常：", e);
        }
        return responseVo;
    }

    @Override
    public ResponseVo editAuthority(AuthorityEntity authorityEntity) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseVo.ResponseErrCode.AuthErr);
        try {
            //检查是否存在父级权限
            if (authorityEntity.getAuthParent() != null) {
                AuthorityEntity query = new AuthorityEntity();
                query.setAuthorityName(authorityEntity.getAuthParent());
                AuthorityEntity pAuth = authorityMapper.selectByAuthorityName(query);
                if (pAuth == null) {
                    responseVo.setMessage("输入的父级名[" + authorityEntity.getAuthParent() + "]在系统中不存在！");
                    return responseVo;
                } else {
                    authorityEntity.setParentId(pAuth.getGuid());
                }
            }
            authorityMapper.updateByPrimaryKeySelective(authorityEntity);
            responseVo.setCode(ResponseVo.ResponseErrCode.OK);
            responseVo.setMessage("执行成功");
        } catch (Exception e) {
            log.error("修改权限异常：", e);
        }
        return responseVo;
    }

    @Override
    public ResponseVo deleteAuthority(AuthorityEntity authorityEntity) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseVo.ResponseErrCode.AuthErr);
        try {
            //删除权限关联的角色数据
            RoleAuthorityEntity record = new RoleAuthorityEntity();
            record.setAuthorityId(authorityEntity.getGuid());
            roleAuthorityMapper.deleteById(record);
            //删除具体权限数据，由于权限是树形结构，当前暂要求删除当前的该权限，对于当前权限的子集暂时不做删除处理
            AuthorityEntity delete = new AuthorityEntity();
            delete.setGuid(authorityEntity.getGuid());
            authorityMapper.deleteById(delete);
            responseVo.setCode(ResponseVo.ResponseErrCode.OK);
            responseVo.setMessage("执行成功");
        } catch (Exception e) {
            log.error("删除权限异常：", e);
        }
        return responseVo;
    }

    @Override
    public ResponseVo pageListAuthority(AuthorityEntity authorityEntity) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseVo.ResponseErrCode.AuthErr);
        try {
            AuthorityEntity queryUserPage = new AuthorityEntity();
            queryUserPage.setDisplayName(authorityEntity.getDisplayName());
            queryUserPage.setAuthorityName(authorityEntity.getAuthorityName());
            PageHelper.startPage(authorityEntity.getPage(), authorityEntity.getPageSize());
            List<AuthorityEntity> list = authorityMapper.listAllByCondition(queryUserPage);
            PageInfo<AuthorityEntity> pageInfo = new PageInfo<AuthorityEntity>(list);
            //查询分页数据
            PageModule<AuthorityEntityResponseVo> pageModule = new PageModule<>();
            List<AuthorityEntityResponseVo> voList = new ArrayList<>();
            for (AuthorityEntity tr : pageInfo.getList()) {
                AuthorityEntityResponseVo responseVo1 = new AuthorityEntityResponseVo();
                responseVo1.setAuthId(tr.getGuid());
                responseVo1.setAuthKey(tr.getAuthorityName());
                responseVo1.setAuthType(tr.getAuthorityType());
                responseVo1.setAuthRoute(tr.getStartPath());
                responseVo1.setAuthCommand(tr.getCommand());
                responseVo1.setAuthName(tr.getDisplayName());
                responseVo1.setAuthParent(tr.getParentName());
                responseVo1.setAuthSort(tr.getSort());
                responseVo1.setAuthIcon(tr.getIcon());
                voList.add(responseVo1);
            }
            pageModule.setPage(authorityEntity.getPage());
            pageModule.setPageSize(pageInfo.getPageSize());
            pageModule.setTotal(pageInfo.getTotal());
            pageModule.setRows(voList);
            responseVo.setCode(ResponseVo.ResponseErrCode.OK);
            responseVo.setMessage("执行成功");
            responseVo.setData(pageModule);
        } catch (Exception e) {
            log.error("查询权限异常：", e);
        }
        return responseVo;
    }

    @Override
    public ResponseVo roleListAllAuthority(String sessionId) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseVo.ResponseErrCode.AuthErr);
        try {
            AuthorityEntity queryUserPage = new AuthorityEntity();
            UsersEntity usersEntity = redisItemUtil.getByKey(CmsRedisKey.USER_LOGIN_INFO_TABLE + ":" + sessionId);
            queryUserPage.setOnlyUserAuto(usersEntity.getGuid());
            List<AuthorityEntity> list = authorityMapper.listAllByCondition(queryUserPage);
            List<AuthorityEntityResponseVo> voList = new ArrayList<>();
            for (AuthorityEntity tr : list) {
                AuthorityEntityResponseVo responseVo1 = new AuthorityEntityResponseVo();
                responseVo1.setAuthId(tr.getGuid());
                responseVo1.setAuthKey(tr.getAuthorityName());
                responseVo1.setAuthType(tr.getAuthorityType());
                responseVo1.setAuthRoute(tr.getStartPath());
                responseVo1.setAuthCommand(tr.getCommand());
                responseVo1.setAuthName(tr.getDisplayName());
                responseVo1.setAuthParent(tr.getParentName());
                responseVo1.setAuthSort(tr.getSort());
                responseVo1.setAuthIcon(tr.getIcon());
                voList.add(responseVo1);
            }
            responseVo.setCode(ResponseVo.ResponseErrCode.OK);
            responseVo.setMessage("执行成功");
            responseVo.setData(voList);
        } catch (Exception e) {
            log.error("查询用户权限范围异常：", e);
        }
        return responseVo;
    }
}
