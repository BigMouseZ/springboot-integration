package com.integration.springbootjpa.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 系统用户绑定角色数据表
 */
@Data
@Entity
@Table(name = "sys_users_role")
public class SysUsersRole {

	/**
	 * guid主键
	 * default value: null
	 */
	@Id
	@Column(name = "guid", nullable = false, length = 50)
	private String guid;

	/**
	 * 用户guid
	 * default value: null
	 */
	@Column(name = "user_id", nullable = true, length = 50)
	private String userId;

	/**
	 * 角色guid
	 * default value: null
	 */
	@Column(name = "role_id", nullable = true, length = 50)
	private String roleId;

	/**
	 * 修改时间
	 * default value: null
	 */
	@Column(name = "modify_time", nullable = true, length = 6)
	private java.util.Date modifyTime;

	/**
	 * 添加时间
	 * default value: null
	 */
	@Column(name = "add_time", nullable = true, length = 6)
	private java.util.Date addTime;


	private String roleName;


	private String roleState;




}
