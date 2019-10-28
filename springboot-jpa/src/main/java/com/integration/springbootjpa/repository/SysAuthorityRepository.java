package com.integration.springbootjpa.repository;

import com.integration.springbootjpa.entity.SysAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SysAuthorityRepository extends JpaRepository<SysAuthority, String>, JpaSpecificationExecutor<SysAuthority> {
    @Query(value = "select tba.guid ,tba.authority_name,tba.authority_type,tba.start_path,tba.display_name,tba.icon,tba.command,tbb.authority_id as authority_state," +
            "tba.parent_id,tba.sort from sys_authority tba LEFT JOIN  ( select distinct authority_id from sys_role_authority where role_id in(" +
            "select role_id from sys_users_role where user_id =?1  )) tbb on tba.guid = tbb.authority_id where 1=1 and tbb.authority_id is not null ",nativeQuery = true)
    List<SysAuthority> findUserInfo(String useId);
}
