package com.integreation.cms.cmsdao;


import com.integreation.cms.entity.cms.cmsentity.RoleAuthorityEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoleAuthorityMapper {

    int deleteById(RoleAuthorityEntity record);

    int deleteByPrimaryKey(String roleAuthorityNo);

    int insertList(List<RoleAuthorityEntity> list);

    int insertSelective(RoleAuthorityEntity record);

    RoleAuthorityEntity selectByPrimaryKey(String roleAuthorityNo);

    int updateByPrimaryKeySelective(RoleAuthorityEntity record);

    int updateByPrimaryKey(RoleAuthorityEntity record);

    int updateByRoleNoAndAuthorityNo(RoleAuthorityEntity entity);

    int delRecord(RoleAuthorityEntity entity);

    List<RoleAuthorityEntity> getForList(RoleAuthorityEntity entity);
}