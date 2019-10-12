package com.integreation.cms.cmsdao;


import com.integreation.cms.entity.cms.cmsentity.RoleEntity;

import java.util.List;

public interface RoleMapper {

    int insertSelective(RoleEntity record);

    RoleEntity selectByRoleName(RoleEntity record);

    RoleEntity selectByPrimaryKey(String roleId);

    int saveByExample(RoleEntity example);

    int updateByPrimaryKeySelective(RoleEntity example);

    int deleteByPrimaryKey(String guid);

    int countByRoleName(RoleEntity example);

    List<RoleEntity> selectAll(RoleEntity record);
}