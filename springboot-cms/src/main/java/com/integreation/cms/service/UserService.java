package com.integreation.cms.service;


import com.integreation.cms.entity.cms.cmsentity.UsersEntity;
import com.integreation.cms.entity.response.ResponseVo;

/**
 * Created by ZhangGang on 2019/7/19.
 * 用户操作相关接口
 */
public interface UserService {

   ResponseVo login(UsersEntity usersEntity);

   ResponseVo logout(String sessoinid);

   ResponseVo adduser(UsersEntity usersEntity);

   ResponseVo edituser(UsersEntity usersEntity);

   ResponseVo deleteuser(UsersEntity usersEntity);

   ResponseVo pageListUser(UsersEntity usersEntity);

   ResponseVo listUserRoles(UsersEntity usersEntity);

   ResponseVo editUserRoles(UsersEntity usersEntity);

   ResponseVo listUserAuth(String sessionId);


}
