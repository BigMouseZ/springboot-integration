package com.integration.springbootjpa.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 系统角色数据表
 */
@Data
@Entity
@Table(name = "sys_role")
public class SysRole {

	/**
	 * guid主键
	 * default value: null
	 */
	@Id
	@Column(name = "guid", nullable = false, length = 50)
	private String guid;

	/**
	 * 角色名称
	 * default value: null
	 */
	@Column(name = "role_name", nullable = true, length = 50)
	private String roleName;

	/**
	 * 角色描述
	 * default value: null
	 */
	@Column(name = "role_describe", nullable = true, length = 255)
	private String roleDescribe;

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

	/**
	 * 角色起始页
	 * default value: null
	 */
	@Column(name = "start_page", nullable = true, length = 255)
	private String startPage;
}
