package com.integration.springbootmq.mq.message;

import lombok.Data;

import java.io.Serializable;
@Data
public class BaseMqMessage implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4821652645183021382L;
	private String mid;

}
