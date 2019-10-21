package com.integration.springbootjpa.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 系统权限模块数据表
 */
@Data
@Entity
@Table(name = "sys_authority")
public class SysAuthority {

	/**
	 * guid主键
	 * default value: null
	 */
	@Id
	@Column(name = "guid", nullable = false, length = 50)
	private String guid;

	/**
	 * 权限名称（唯一键）
	 * default value: null
	 */
	@Column(name = "authority_name", nullable = false, length = 255)
	private String authorityName;

	/**
	 * 权限类型（菜单menu，ui、api）
	 * default value: null
	 */
	@Column(name = "authority_type", nullable = false, length = 255)
	private String authorityType;

	/**
	 * 起始地址(一般只有菜单才有）
	 * default value: null
	 */
	@Column(name = "start_path", nullable = true, length = 255)
	private String startPath;

	/**
	 * 显示名称(前端可见的内容）
	 * default value: null
	 */
	@Column(name = "display_name", nullable = true, length = 255)
	private String displayName;

	/**
	 * 排序号
	 * default value: null
	 */
	@Column(name = "sort", nullable = true, length = 16)
	private Integer sort;

	/**
	 * 图标（一般只有菜单和ui上有用）
	 * default value: null
	 */
	@Column(name = "icon", nullable = true, length = 255)
	private String icon;

	/**
	 * 命令值
	 * default value: null
	 */
	@Column(name = "command", nullable = true, length = 255)
	private String command;

	/**
	 * 父级权限id
	 * default value: null
	 */
	@Column(name = "parent_id", nullable = true, length = 50)
	private String parentId;

	/**
	 * null
	 * default value: null
	 */
	@Column(name = "modify_time", nullable = false, length = 6)
	private java.util.Date modifyTime;

	/**
	 * null
	 * default value: null
	 */
	@Column(name = "add_time", nullable = false, length = 6)
	private java.util.Date addTime;
}
