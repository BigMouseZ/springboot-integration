package com.integration.springbootjpa.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 系统永用户表
 */
@Data
@Entity
@Table(name = "sys_users")
public class SysUsers {

	/**
	 * guid主键
	 * default value: null
	 */
	@Id
	@Column(name = "guid", nullable = false, length = 50)
	private String guid;

	/**
	 * 登录名
	 * default value: null
	 */
	@Column(name = "login_name", nullable = true, length = 50)
	private String loginName;

	/**
	 * 姓名
	 * default value: null
	 */
	@Column(name = "real_name", nullable = true, length = 50)
	private String realName;

	/**
	 * 电话
	 * default value: null
	 */
	@Column(name = "phone", nullable = true, length = 50)
	private String phone;

	/**
	 * 性别（男/女）
	 * default value: null
	 */
	@Column(name = "sex", nullable = true, length = 50)
	private String sex;

	/**
	 * 住址
	 * default value: null
	 */
	@Column(name = "address", nullable = true, length = 255)
	private String address;

	/**
	 * 头像地址
	 * default value: null
	 */
	@Column(name = "head_icon", nullable = true, length = 255)
	private String headIcon;

	/**
	 * 登录密码
	 * default value: null
	 */
	@Column(name = "login_pwd", nullable = true, length = 255)
	private String loginPwd;

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
	 * 关联的组织机构ID
	 * default value: null
	 */
	@Column(name = "organization_id", nullable = true, length = 50)
	private String organizationId;

	/**
	 * 绑定的cas帐号
	 * default value: null
	 */
	@Column(name = "cas_name", nullable = true, length = 50)
	private String casName;

	/**
	 * 用户状态(0：启用；1：冻结)
	 * default value: 0
	 */
	@Column(name = "user_state", nullable = true, length =32 )
	private Integer userState;

	/**
	 * 用户职位（0：普通用户，1：客服）
	 * default value: 0
	 */
	@Column(name = "user_position", nullable = true, length =32 )
	private Integer userPosition;

	/**
	 * 用户的工号
	 * default value: null
	 */
	@Column(name = "job_number", nullable = true, length = 50)
	private String jobNumber;

	//字段不与数据库表映射
	@Transient
	private Date addTimeStart; //时间开始

	@Transient
	private Date addTimeEnd; //时间结束
}
