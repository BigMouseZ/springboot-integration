package com.integration.springbootjpa.repository;

import com.integration.springbootjpa.entity.AuthorityEntity;
import com.integration.springbootjpa.entity.SysUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface SysUsersRepository extends JpaRepository<SysUsers, String>, QuerydslPredicateExecutor<SysUsers> {



}
