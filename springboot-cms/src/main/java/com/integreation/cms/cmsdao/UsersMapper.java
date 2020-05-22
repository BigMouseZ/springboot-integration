package com.integreation.cms.cmsdao;


import com.integreation.cms.entity.cms.cmsentity.UsersEntity;

import java.util.List;

public interface UsersMapper {
    int deleteByPrimaryKey(String userId);

    int insertSelective(UsersEntity record);

    UsersEntity selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(UsersEntity record);

    List<UsersEntity> listAllByCondition(UsersEntity record);

    int selectCountByLoginName(String loginName);

    UsersEntity selectByLoginName(UsersEntity user);

}