package com.integration.springbootjpa.repository;

import com.integration.springbootjpa.entity.SysRoleAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SysRoleAuthorityRepository extends JpaRepository<SysRoleAuthority, String>, JpaSpecificationExecutor<SysRoleAuthority> {

}
