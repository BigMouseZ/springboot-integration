package com.integration.springbootjpa.repository;

import com.integration.springbootjpa.entity.SysAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SysAuthorityRepository extends JpaRepository<SysAuthority, String>, JpaSpecificationExecutor<SysAuthority> {

}
