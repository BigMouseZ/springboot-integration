package com.integration.springbootmq.mq.queues;


import com.integration.springbootmq.mq.config.MessageSendRoutingKey;

public enum MqQueue {

	EXPRESSWAY_SUBSET_VIDEO_TRACK_DATA_QUEUE("EXPRESSWAY_SUBSET_VIDEO_TRACK_DATA_QUEUE","子系统视觉轨迹上传中心数据",MessageSendRoutingKey.EXPRESSWAY_SUBSET_VIDEO_TRACK_DATA);
	private String queueName;
	private String queuedescribe;
	private MessageSendRoutingKey routkey;
	
	
	MqQueue(String queueName,String queuedescribe,MessageSendRoutingKey routkey){
		this.queuedescribe=queuedescribe;
		this.queueName = queueName;
		this.routkey = routkey;
	}


	public String getQueueName() {
		return queueName;
	}


	public String getQueuedescribe() {
		return queuedescribe;
	}


	public MessageSendRoutingKey getRoutkey() {
		return routkey;
	}


	
	
}
