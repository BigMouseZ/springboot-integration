package com.integration.springbootjpa.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 系统角色捆绑权限表
 */
@Data
@Entity
@Table(name = "sys_role_authority")
public class SysRoleAuthority {

	/**
	 * null
	 * default value: null
	 */
	@Id
	@Column(name = "guid", nullable = false, length = 50)
	private String guid;

	/**
	 * 角色id
	 * default value: null
	 */
	@Column(name = "role_id", nullable = true, length = 50)
	private String roleId;

	/**
	 * 权限id
	 * default value: null
	 */
	@Column(name = "authority_id", nullable = true, length = 50)
	private String authorityId;

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
}
