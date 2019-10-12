package com.integreation.cms.service;

import com.integreation.cms.entity.cms.cmsentity.AuthorityEntity;
import com.integreation.cms.entity.response.ResponseVo;

/**
 * Created by ZhangGang on 2019/7/19.
 * 角色操作相关接口
 */
public interface AuthorityService {

   ResponseVo addAuthority(AuthorityEntity authorityEntity);

   ResponseVo editAuthority(AuthorityEntity authorityEntity);

   ResponseVo deleteAuthority(AuthorityEntity authorityEntity);

   ResponseVo pageListAuthority(AuthorityEntity authorityEntity);

   ResponseVo roleListAllAuthority(String sessionId);
}
