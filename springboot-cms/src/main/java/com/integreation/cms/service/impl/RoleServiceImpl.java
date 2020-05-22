package com.integreation.cms.service.impl;

import com.integreation.cms.cmsdao.RoleAuthorityMapper;
import com.integreation.cms.cmsdao.RoleMapper;
import com.integreation.cms.cmsdao.UserRoleMapper;
import com.integreation.cms.entity.cms.cmsentity.RoleAuthorityEntity;
import com.integreation.cms.entity.cms.cmsentity.RoleEntity;
import com.integreation.cms.entity.cms.cmsentity.UserRoleEntity;
import com.integreation.cms.entity.cms.cmsresponsevo.RoleEntityResponseVo;
import com.integreation.cms.entity.response.ResponseVo;
import com.integreation.cms.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by ZhangGang on 2019/7/19.
 */
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Resource
    private RoleAuthorityMapper roleAuthorityMapper;
    @Value("${cms.superAdmin.open:false}")
    private boolean open;

    @Override
    public ResponseVo addRole(RoleEntity roleEntity) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseVo.ResponseErrCode.AuthErr);
        // 判断是否存在该用角色
        try {
            RoleEntity roleReturn = roleMapper.selectByRoleName(roleEntity);
            if (roleReturn != null) {
                responseVo.setMessage("该角色名[" + roleEntity.getRoleName() + "]已存在！");
                return responseVo;
            }
            RoleEntity insert = new RoleEntity();
            insert.setGuid(UUID.randomUUID().toString());//临时生成guid
            insert.setRoleName(roleEntity.getRoleName());
            insert.setRoleDescribe(roleEntity.getRoleDescribe());
            roleMapper.insertSelective(insert);
            responseVo.setCode(ResponseVo.ResponseErrCode.OK);
            responseVo.setMessage("执行成功");
        } catch (Exception e) {
            log.error("添加角色异常：", e);
        }
        return responseVo;
    }

    @Override
    public ResponseVo editRole(RoleEntity roleEntity) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseVo.ResponseErrCode.AuthErr);
        try {
            // 判断是否存在该用角色
            RoleEntity roleReturn = roleMapper.selectByRoleName(roleEntity);
            if (roleReturn != null) {
                responseVo.setMessage("该角色名[" + roleEntity.getRoleName() + "]已存在！");
                return responseVo;
            }
            RoleEntity update = new RoleEntity();
            //保存角色到数据库
            update.setGuid(roleEntity.getGuid().trim());//临时生成guid
            update.setRoleName(roleEntity.getRoleName());
            update.setRoleDescribe(roleEntity.getRoleDescribe());
            roleMapper.updateByPrimaryKeySelective(update);
            responseVo.setCode(ResponseVo.ResponseErrCode.OK);
            responseVo.setMessage("执行成功");
        } catch (Exception e) {
            log.error("修改角色异常：", e);
        }
        return responseVo;
    }

    @Override
    public ResponseVo deleteRole(RoleEntity roleEntity) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseVo.ResponseErrCode.AuthErr);
        try {
            //查询角色
            RoleEntity role = roleMapper.selectByPrimaryKey(roleEntity.getGuid());
            if (role != null) {
                //删除角色捆绑的权限
                RoleAuthorityEntity delete = new RoleAuthorityEntity();
                delete.setRoleId(roleEntity.getGuid());
                roleAuthorityMapper.deleteById(delete);
                //删除用户捆绑的角色
                UserRoleEntity userRoleEntity = new UserRoleEntity();
                userRoleEntity.setRoleId(roleEntity.getGuid());
                userRoleMapper.deleteById(userRoleEntity);
                //删除角色
                roleMapper.deleteByPrimaryKey(role.getGuid());
            } else {
                log.error("根据角色id :{}找不到角色数据", roleEntity.getGuid());
            }
            responseVo.setCode(ResponseVo.ResponseErrCode.OK);
            responseVo.setMessage("执行成功");
        } catch (Exception e) {
            log.error("删除角色异常：", e);
        }
        return responseVo;
    }

    @Override
    public ResponseVo ListAllRole() {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseVo.ResponseErrCode.AuthErr);
        try {
            RoleEntity query = new RoleEntity();
            if(!open){
                query.setUiview("20180116134425_2F876C2D9B8D4381A7C9C0AFCD028CCC0");
            }
            List<RoleEntity> list = roleMapper.selectAll(query);
            List<RoleEntityResponseVo> voList = new ArrayList<>();
            for (RoleEntity tr : list) {
                RoleEntityResponseVo roleEntityResponseVo = new RoleEntityResponseVo();
                roleEntityResponseVo.setRoleId(tr.getGuid());
                roleEntityResponseVo.setRoleName(tr.getRoleName());
                roleEntityResponseVo.setRoleDescription(tr.getRoleDescribe());
                voList.add(roleEntityResponseVo);
            }
            responseVo.setCode(ResponseVo.ResponseErrCode.OK);
            responseVo.setMessage("执行成功");
            responseVo.setData(voList);
        } catch (Exception e) {
            log.error("查询角色异常：", e);
        }
        return responseVo;
    }

    @Override
    public ResponseVo editRoleAuth(RoleEntity roleEntity) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseVo.ResponseErrCode.AuthErr);
        try {
            //删除角色捆绑的权限
            RoleAuthorityEntity delete = new RoleAuthorityEntity();
            delete.setRoleId(roleEntity.getGuid());
            roleAuthorityMapper.deleteById(delete);
            //重新捆绑角色的权限
            if (roleEntity.getRoleAuthIds() != null) {
                List<RoleAuthorityEntity> list = new ArrayList<RoleAuthorityEntity>();
                for (String authid : roleEntity.getRoleAuthIds()) {
                    RoleAuthorityEntity roa = new RoleAuthorityEntity();
                    roa.setGuid(UUID.randomUUID().toString());
                    roa.setRoleId(roleEntity.getGuid());
                    roa.setAuthorityId(authid);
                    list.add(roa);
                }
                //批量插入
                roleAuthorityMapper.insertList(list);
            }
            responseVo.setCode(ResponseVo.ResponseErrCode.OK);
            responseVo.setMessage("执行成功");
        } catch (Exception e) {
            log.error("编辑角色权限异常：", e);
        }
        return responseVo;
    }

    @Override
    public ResponseVo listRoleAuth(RoleEntity roleEntity) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseVo.ResponseErrCode.AuthErr);
        try {
            List<String> list = new ArrayList<String>();
            RoleAuthorityEntity query = new RoleAuthorityEntity();
            query.setRoleId(roleEntity.getGuid());
            query.setAuthorityState("all");
            List<RoleAuthorityEntity> listData = roleAuthorityMapper.getForList(query);
            if (listData != null) {
                for (RoleAuthorityEntity r : listData) {
                    list.add(r.getAuthorityId());
                }
            }
            responseVo.setCode(ResponseVo.ResponseErrCode.OK);
            responseVo.setMessage("执行成功");
            responseVo.setData(list);
        } catch (Exception e) {
            log.error("编辑角色权限异常：", e);
        }
        return responseVo;
    }


}
