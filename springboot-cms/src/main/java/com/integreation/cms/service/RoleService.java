package com.integreation.cms.service;


import com.integreation.cms.entity.cms.cmsentity.RoleEntity;
import com.integreation.cms.entity.response.ResponseVo;

/**
 * Created by ZhangGang on 2019/7/19.
 * 角色操作相关接口
 */
public interface RoleService {

   ResponseVo addRole(RoleEntity roleEntity);

   ResponseVo editRole(RoleEntity roleEntity);

   ResponseVo deleteRole(RoleEntity roleEntity);

   ResponseVo ListAllRole();

   ResponseVo editRoleAuth(RoleEntity roleEntity);

   ResponseVo listRoleAuth(RoleEntity roleEntity);

}
