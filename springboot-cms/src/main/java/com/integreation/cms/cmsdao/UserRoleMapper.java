package com.integreation.cms.cmsdao;


import com.integreation.cms.entity.cms.cmsentity.UserRoleEntity;

import java.util.List;

public interface UserRoleMapper {

    int insertSelective(UserRoleEntity record);

    int deleteById(UserRoleEntity userRoleEntity);


   List<UserRoleEntity> getForList(UserRoleEntity userRoleEntity);



}