package com.integreation.cms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.integreation.cms.cmsdao.AuthorityMapper;
import com.integreation.cms.cmsdao.UserRoleMapper;
import com.integreation.cms.cmsdao.UsersMapper;
import com.integreation.cms.entity.PageModule;
import com.integreation.cms.entity.cms.cmsentity.AuthorityEntity;
import com.integreation.cms.entity.cms.cmsentity.CmsRedisKey;
import com.integreation.cms.entity.cms.cmsentity.UserRoleEntity;
import com.integreation.cms.entity.cms.cmsentity.UsersEntity;
import com.integreation.cms.entity.cms.cmsresponsevo.UserAuthStateResponseBean;
import com.integreation.cms.entity.cms.cmsresponsevo.UsersEntityResponseVo;
import com.integreation.cms.entity.response.ResponseVo;
import com.integreation.cms.service.UserService;
import com.integreation.cms.utils.redis.redisutils.RedisItemUtil;
import com.integreation.cms.utils.redis.redisutils.RedisMapUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by ZhangGang on 2019/7/19.
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private AuthorityMapper authorityMapper;
    @Autowired
    private RedisMapUtil redisMapUtil;
    @Autowired
    private RedisItemUtil redisItemUtil;
    @Value("${cms.superAdmin.open:false}")
    private boolean open;
    @Override
    public ResponseVo login(UsersEntity usersEntity) {

        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseVo.ResponseErrCode.AuthErr);
        responseVo.setMessage("登录失败");
        try {
            // 判断是否存在该用户名
            UsersEntity userReturn = usersMapper.selectByLoginName(usersEntity);
            if (userReturn == null) {
                responseVo.setMessage("该用户名[" + usersEntity.getLoginName() + "]不存在！");
                return responseVo;
            }
            if (!usersEntity.getLoginPwd().equals(userReturn.getLoginPwd())) {
                responseVo.setMessage("用户名[" + usersEntity.getLoginName() + "]的登录密码不正确！");
                return responseVo;
            }
            if (!"0".equals(userReturn.getUserState())) {
                responseVo.setMessage("用户[" + usersEntity.getLoginName() + "]处于冻结状态！");
                return responseVo;
            }
            //登录密码正确，开始获取用户权限
            String last_session_id = redisMapUtil.getByKey(CmsRedisKey.USER_SESSIONID_TABLE, userReturn.getLoginName());
            if (last_session_id != null) {
                //删除上次的登录缓存
                redisMapUtil.deleteTable(last_session_id);
//                redisMapUtil.deleteByKey(CmsRedisKey.USER_LOGIN_INFO_TABLE, last_session_id);
                redisItemUtil.deleteByKey(CmsRedisKey.USER_LOGIN_INFO_TABLE+":"+last_session_id);
            }
            String current_session_id = UUID.randomUUID().toString().replace("-", "");
            redisMapUtil.setByKey(CmsRedisKey.USER_SESSIONID_TABLE, userReturn.getLoginName(), current_session_id);
            //查询并装载用户权限到缓存
            AuthorityEntity query_user_auth = new AuthorityEntity();
            query_user_auth.setUserId(userReturn.getGuid());
            query_user_auth.setAuthorityState("all");
            List<AuthorityEntity> user_auth_list = authorityMapper.getForList(query_user_auth);
            for (AuthorityEntity user_auth : user_auth_list) {
                String commands = user_auth.getCommand();
                if (commands != null && !"".equals(commands.trim()) && (user_auth.getAuthorityState() != null && !"".equals(user_auth.getAuthorityState().trim()))) {
                    String[] command_array = commands.split(",");
                    for (String comm : command_array) {
                        redisMapUtil.setByKey(current_session_id, comm, user_auth);
                    }
                }
            }
            //组织数据返回
            Map<String, Object> response_map = new HashMap<String, Object>();
            response_map.put("userLoginName", userReturn.getLoginName());
            response_map.put("userRealName", userReturn.getRealName());
            response_map.put("userProfilePhoto", userReturn.getHeadIcon());
            response_map.put("sessionId", current_session_id);
            response_map.put("userId", userReturn.getGuid());
            response_map.put("userState", userReturn.getUserState());
            response_map.put("userPosition", userReturn.getUserPosition());
            //缓存用户信息和sessionid的捆绑
//            redisItemUtil.setByKey(CmsRedisKey.USER_LOGIN_INFO_TABLE+":"+current_session_id,userReturn,7200);
            redisItemUtil.setByKey(CmsRedisKey.USER_LOGIN_INFO_TABLE+":"+current_session_id, userReturn);
            responseVo.setCode(ResponseVo.ResponseErrCode.OK);
            responseVo.setMessage("登录成功");
            responseVo.setData(response_map);
        } catch (Exception e) {
            log.error("用户登录异常：", e);
        }
        return responseVo;
    }

    @Override
    public ResponseVo logout(String sessoinid) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseVo.ResponseErrCode.AuthErr);
        try {
            redisMapUtil.deleteTable(sessoinid);
            UsersEntity userInfo = redisItemUtil.getByKey(CmsRedisKey.USER_LOGIN_INFO_TABLE+":"+sessoinid);
            if (userInfo != null && userInfo.getLoginName() != null) {
                redisMapUtil.deleteByKey(CmsRedisKey.USER_SESSIONID_TABLE, userInfo.getLoginName());

            }
            redisItemUtil.deleteByKey(CmsRedisKey.USER_LOGIN_INFO_TABLE+":"+ sessoinid);
            redisMapUtil.deleteTable(sessoinid);
            responseVo.setCode(ResponseVo.ResponseErrCode.OK);
            responseVo.setMessage("执行成功");
        } catch (Exception e) {
            log.error("用户注销异常：", e);
        }
        return responseVo;
    }


    @Override
    public ResponseVo adduser(UsersEntity usersEntity) {
        //查询用户是否存在
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseVo.ResponseErrCode.AuthErr);
        // 判断是否存在该用户名
        try {
            UsersEntity query = new UsersEntity();
            query.setLoginName(usersEntity.getLoginName());
            UsersEntity userReturn = usersMapper.selectByLoginName(query);
            if (userReturn != null) {
                responseVo.setMessage("该用户名[" + usersEntity.getLoginName() + "]已存在！");
                return responseVo;
            }
            UsersEntity insert = new UsersEntity();
            insert.setLoginName(usersEntity.getLoginName().trim());
            insert.setGuid(UUID.randomUUID().toString());//临时生成guid
            insert.setLoginPwd(usersEntity.getLoginPwd());
            insert.setPhone(usersEntity.getPhone());
            insert.setHeadIcon(usersEntity.getHeadIcon());
            insert.setRealName(usersEntity.getRealName());
            insert.setUserPosition(usersEntity.getUserPosition());
            insert.setJobNumber(usersEntity.getJobNumber());
            usersMapper.insertSelective(insert);
            //添加用户的角色
            if (usersEntity.getRoleIds() != null) {
                for (String rid : usersEntity.getRoleIds()) {
                    if (!StringUtils.isBlank(rid)) {
                        UserRoleEntity insertUserRole = new UserRoleEntity();
                        insertUserRole.setGuid(UUID.randomUUID().toString());
                        insertUserRole.setRoleId(rid);
                        insertUserRole.setUserId(insert.getGuid());
                        userRoleMapper.insertSelective(insertUserRole);
                    }
                }
            }
            responseVo.setCode(ResponseVo.ResponseErrCode.OK);
            responseVo.setMessage("执行成功");
        } catch (Exception e) {
            log.error("添加用户异常：", e);
        }
        return responseVo;
    }

    @Override
    public ResponseVo edituser(UsersEntity usersEntity) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseVo.ResponseErrCode.AuthErr);
        try {
            UsersEntity update = new UsersEntity();
            //保存用户到数据库
            update.setGuid(usersEntity.getGuid().trim());//临时生成guid
            update.setLoginPwd(usersEntity.getLoginPwd());
            update.setPhone(usersEntity.getPhone());
            update.setHeadIcon(usersEntity.getHeadIcon());
            update.setRealName(usersEntity.getRealName());
            update.setUserPosition(usersEntity.getUserPosition());
            update.setJobNumber(usersEntity.getJobNumber());
            usersMapper.updateByPrimaryKeySelective(update);
            //更新缓存
            UsersEntity newUser = usersMapper.selectByPrimaryKey(update.getGuid());
            String sessionId = redisMapUtil.getByKey(CmsRedisKey.USER_SESSIONID_TABLE, newUser.getLoginName());
            redisItemUtil.setByKey(CmsRedisKey.USER_LOGIN_INFO_TABLE+":"+sessionId, newUser);
            responseVo.setCode(ResponseVo.ResponseErrCode.OK);
            responseVo.setMessage("执行成功");
        } catch (Exception e) {
            log.error("修改用户异常：", e);
        }
        return responseVo;
    }

    @Override
    public ResponseVo deleteuser(UsersEntity usersEntity) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseVo.ResponseErrCode.AuthErr);
        try {
            //查询用户
            UsersEntity user = usersMapper.selectByPrimaryKey(usersEntity.getGuid());
            if (user != null) {
                //删除用户绑定角色
                UserRoleEntity delete = new UserRoleEntity();
                delete.setUserId(usersEntity.getGuid());
                userRoleMapper.deleteById(delete);
                //删除用户信息
                usersMapper.deleteByPrimaryKey(usersEntity.getGuid());
                //删除用户登录缓存数据
                String sessionId = redisMapUtil.getByKey(CmsRedisKey.USER_SESSIONID_TABLE, user.getLoginName());
                if (sessionId != null && !"".equals(sessionId)) {
                    redisMapUtil.deleteByKey(CmsRedisKey.USER_SESSIONID_TABLE, user.getLoginName());
                    redisMapUtil.deleteTable(sessionId);
                    redisItemUtil.deleteByKey(CmsRedisKey.USER_LOGIN_INFO_TABLE+":"+ sessionId);
                }
            } else {
                log.error("根据用户id :{}找不到用户数据", usersEntity.getGuid());
            }
            responseVo.setCode(ResponseVo.ResponseErrCode.OK);
            responseVo.setMessage("执行成功");
        } catch (Exception e) {
            log.error("删除用户异常：", e);
        }
        return responseVo;
    }

    @Override
    public ResponseVo pageListUser(UsersEntity usersEntity) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseVo.ResponseErrCode.AuthErr);
        try {
            UsersEntity queryUserPage = new UsersEntity();
            queryUserPage.setLoginName(usersEntity.getLoginName());
            queryUserPage.setRealName(usersEntity.getRealName());
            queryUserPage.setRoleId(usersEntity.getRoleId());
            //过滤掉超级用户
            if(!open){
                queryUserPage.setUiview("20180116134425_2F876C2D9B8D4381A7C9C0AFCD028FFF0");
            }
            PageHelper.startPage(usersEntity.getPage(), usersEntity.getPageSize());
            List<UsersEntity> list = usersMapper.listAllByCondition(queryUserPage);
            PageInfo<UsersEntity> pageInfo = new PageInfo<UsersEntity>(list);
            //查询分页数据
            PageModule<UsersEntityResponseVo> pageModule = new PageModule<>();
            List<UsersEntityResponseVo> voList = new ArrayList<>();
            for (UsersEntity tr : pageInfo.getList()) {
                UsersEntityResponseVo responseVo1 = new UsersEntityResponseVo();
                responseVo1.setUserId(tr.getGuid());
                responseVo1.setUserLoginName(tr.getLoginName());
                responseVo1.setUserPhone(tr.getPhone());
                responseVo1.setUserProfilePhoto(tr.getHeadIcon());
                responseVo1.setUserRealName(tr.getRealName());
                responseVo1.setUserPosition(tr.getUserPosition());
                responseVo1.setJobNumber(tr.getJobNumber());
                voList.add(responseVo1);
            }
            pageModule.setPage(usersEntity.getPage());
            pageModule.setPageSize(pageInfo.getPageSize());
            pageModule.setTotal(pageInfo.getTotal());
            pageModule.setRows(voList);
            responseVo.setCode(ResponseVo.ResponseErrCode.OK);
            responseVo.setMessage("执行成功");
            responseVo.setData(pageModule);
        } catch (Exception e) {
            log.error("分页查询用户异常：", e);
        }
        return responseVo;
    }

    @Override
    public ResponseVo listUserRoles(UsersEntity usersEntity) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseVo.ResponseErrCode.AuthErr);
        try {
            List<String> roleListId = new ArrayList<String>();
            //查询用户已授权的角色
            UserRoleEntity query = new UserRoleEntity();
            query.setUserId(usersEntity.getGuid());
            query.setRoleState("all");
            List<UserRoleEntity> rolelist = userRoleMapper.getForList(query);
            if (rolelist != null) {
                for (UserRoleEntity r : rolelist) {
                    roleListId.add(r.getRoleId());
                }
            }
            responseVo.setCode(ResponseVo.ResponseErrCode.OK);
            responseVo.setMessage("执行成功");
            responseVo.setData(roleListId);
        } catch (Exception e) {
            log.error("获取用户已授权的角色数据异常：", e);
        }
        return responseVo;
    }

    @Override
    public ResponseVo editUserRoles(UsersEntity usersEntity) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseVo.ResponseErrCode.AuthErr);
        try {
            //删除原有用户绑定角色
            UserRoleEntity delete = new UserRoleEntity();
            delete.setUserId(usersEntity.getGuid());
            userRoleMapper.deleteById(delete);
            //重新添加角色
            if (usersEntity.getRoleIds() != null) {
                for (String roleid : usersEntity.getRoleIds()) {
                    UserRoleEntity insertUserRole = new UserRoleEntity();
                    insertUserRole.setGuid(UUID.randomUUID().toString());
                    insertUserRole.setRoleId(roleid);
                    insertUserRole.setUserId(usersEntity.getGuid());
                    userRoleMapper.insertSelective(insertUserRole);
                }
            }
            responseVo.setCode(ResponseVo.ResponseErrCode.OK);
            responseVo.setMessage("执行成功");
        } catch (Exception e) {
            log.error("用户角色编辑异常：", e);
        }
        return responseVo;
    }
    @Override
    public ResponseVo listUserAuth(String sessionId) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseVo.ResponseErrCode.AuthErr);
        try {
            UsersEntity usersEntity = redisItemUtil.getByKey(CmsRedisKey.USER_LOGIN_INFO_TABLE+":"+sessionId);
            if(usersEntity!=null){
                //查询并装载用户权限到缓存
                List<UserAuthStateResponseBean> list = new ArrayList<UserAuthStateResponseBean>();
                AuthorityEntity query = new AuthorityEntity();
                query.setUserId(usersEntity.getGuid());
                query.setParentId("top");
                List<AuthorityEntity> listrs = authorityMapper.getForList(query);
                List<UserAuthStateResponseBean> temp_list = new ArrayList<>();
                if(listrs != null && listrs.size()>0){
                    UserAuthStateResponseBean uab = null;
                    for(AuthorityEntity ua:listrs){
                        uab = new UserAuthStateResponseBean();
                        uab.setAuthCommand(ua.getCommand());
                        if(ua.getAuthorityState() == null){
                            uab.setAuthHas(false);
                        }else{
                            uab.setAuthHas(true);
                        }
                        uab.setAuthIcon(ua.getIcon());
                        uab.setAuthId(ua.getGuid());
                        uab.setAuthKey(ua.getAuthorityName());
                        uab.setAuthName(ua.getDisplayName());
                        uab.setAuthRoute(ua.getStartPath());
                        uab.setAuthType(ua.getAuthorityType());
                        uab.setAuthSort(String.valueOf(ua.getSort()));
                        //查询子节点
                        query.setParentId(ua.getGuid());
                        List<AuthorityEntity> child_list = authorityMapper.getForList(query);
                        List<UserAuthStateResponseBean> c_list = new ArrayList<UserAuthStateResponseBean>();
                        if(child_list != null && child_list.size() >0){
                            UserAuthStateResponseBean ucb =null;
                            for(AuthorityEntity u:child_list){
                                ucb = new UserAuthStateResponseBean();
                                ucb.setAuthCommand(u.getCommand());
                                if(u.getAuthorityState() == null){
                                    ucb.setAuthHas(false);
                                }else{
                                    ucb.setAuthHas(true);
                                }
                                ucb.setAuthSort(String.valueOf(u.getSort()));
                                ucb.setAuthIcon(u.getIcon());
                                ucb.setAuthId(u.getGuid());
                                ucb.setAuthKey(u.getAuthorityName());
                                ucb.setAuthRoute(u.getStartPath());
                                ucb.setAuthType(u.getAuthorityType());
                                ucb.setAuthName(u.getDisplayName());
                                c_list.add(ucb);
                                temp_list.add(ucb);
                            }
                        }
                        uab.setChildren(c_list);
                        list.add(uab);
                    }
                }
                while(temp_list.size()>0){
                    List<UserAuthStateResponseBean> t_list = new ArrayList<UserAuthStateResponseBean>();
                    for(UserAuthStateResponseBean ua2:temp_list){
                        //查询子节点
                        query.setParentId(ua2.getAuthId());
                        List<UserAuthStateResponseBean> ttlist = new ArrayList<UserAuthStateResponseBean>();
                        List<AuthorityEntity> child_list2 = authorityMapper.getForList(query);;
                        if(child_list2 != null && child_list2.size() >0 ){
                            UserAuthStateResponseBean uu2 = null;
                            for(AuthorityEntity uu:child_list2){
                                uu2 = new UserAuthStateResponseBean();
                                uu2.setAuthCommand(uu.getCommand());
                                if(uu.getAuthorityState() == null){
                                    uu2.setAuthHas(false);
                                }else{
                                    uu2.setAuthHas(true);
                                }
                                uu2.setAuthSort(String.valueOf(uu.getSort()));
                                uu2.setAuthIcon(uu.getIcon());
                                uu2.setAuthId(uu.getGuid());
                                uu2.setAuthKey(uu.getAuthorityName());
                                uu2.setAuthRoute(uu.getStartPath());
                                uu2.setAuthType(uu.getAuthorityType());
                                uu2.setAuthName(uu.getDisplayName());
                                ttlist.add(uu2);
                                t_list.add(uu2);
                            }
                        }
                        ua2.setChildren(ttlist);
                    }
                    temp_list.clear();
                    temp_list.addAll(t_list);
                }
                responseVo.setData(list);
            }
            responseVo.setCode(ResponseVo.ResponseErrCode.OK);
            responseVo.setMessage("执行成功");
        } catch (Exception e) {
            log.error("用户角色编辑异常：", e);
        }
        return responseVo;
    }
}
