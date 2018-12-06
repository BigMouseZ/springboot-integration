package com.mybatis.controller;

import com.mybatis.dao.cluster.ClusterUserMapper;
import com.mybatis.dao.master.MasterUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class UserController {

	@Autowired
	private MasterUserMapper masterUserMapper;
	
	@Autowired
	private ClusterUserMapper clusterUserMapper;
	
	@RequestMapping("user/getAllMaster")
	public String getAllMaster() {
		masterUserMapper.getAll();
		clusterUserMapper.getAll();
		return  "测试";
	}
}
