package com.integreation.cms.entity;

import lombok.Data;

import java.util.List;

/**
 * 分页数据模版
 * 2017年12月21日
 *@author 李增禄
 *
 */
@Data
public class PageModule<T> {
	/**
	 * 第几页
	 */
	private int page;
	/** 
     * 每页条数 
     */  
    private int pageSize;
    /** 
     * 总记录数 
     */  
    private long total;  
    /** 
     * 单页数据 
     */  
    private List<T> rows;
    
}
