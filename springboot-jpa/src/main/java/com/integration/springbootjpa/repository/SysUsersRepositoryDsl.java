package com.integration.springbootjpa.repository;

import com.integration.springbootjpa.entity.SysUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
public interface SysUsersRepositoryDsl extends JpaRepository<SysUsers, String>, QuerydslPredicateExecutor<SysUsers> {

}
