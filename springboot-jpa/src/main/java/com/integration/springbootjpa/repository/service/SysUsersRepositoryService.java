package com.integration.springbootjpa.repository.service;

import com.integration.springbootjpa.entity.SysUsers;
import com.integration.springbootjpa.entity.SysUsersRole;
import com.integration.springbootjpa.entity.UserDTO;
import com.querydsl.core.QueryResults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface SysUsersRepositoryService {

     Page<SysUsers> findPageListByCondition(SysUsers sysUsers, Pageable pageable);

     SysUsers findByUsernameAndPassword(String loginName, String password);

     List<SysUsers> findAll();

     QueryResults<SysUsers> findAllPage(Pageable pageable);

     List<SysUsers> findByBirthdayBetween(Date start, Date end);

     List<UserDTO> findAllUserDto(Pageable pageable);

     List<SysUsers> findByNicknameAndUsername(String loginName, String loginName2);


     long countByNickNameLike(String likeName);

     Page<SysUsers> findByUserProperties(Pageable pageable, String loginName, String password, String loginName2, Date addTime, Date addTime2);


     List<SysUsers> findByUserPropertiesGroupByUIndex(String loginName, String password, String loginName2, Date addTime, Date addTime2);

     List<SysUsersRole> getFileList();














}
