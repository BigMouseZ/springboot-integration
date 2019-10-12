package com.integreation.cms.cmsdao;


import com.integreation.cms.entity.cms.cmsentity.AuthorityEntity;

import java.util.List;

public interface AuthorityMapper {

    int deleteById(AuthorityEntity record);

    int insertSelective(AuthorityEntity record);

    AuthorityEntity selectByAuthorityName(AuthorityEntity record);

    int updateByPrimaryKeySelective(AuthorityEntity record);

    List<AuthorityEntity> getForList(AuthorityEntity authorityEntity);

    List<AuthorityEntity> listAllByCondition(AuthorityEntity authorityEntity);

}