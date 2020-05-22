package com.integration.springbootjpa.repository;

import com.integration.springbootjpa.entity.SysUsersRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SysUsersRoleRepository extends JpaRepository<SysUsersRole, String>, JpaSpecificationExecutor<SysUsersRole> {

}
