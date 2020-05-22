package com.integration.springbootmq.mq.config;

import com.integration.springbootmq.mq.queues.MqQueue;
import com.integration.springbootmq.mq.receive.MqMessagfeReceive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MqConfigManager {
	
	private static Map<String, MqQueue> queuesMap = new HashMap<String, MqQueue>();
	
	private static Map<String, QueueMessageReceiveConfg> queueListenerMap = new HashMap<String, QueueMessageReceiveConfg>();
	
	private static Map<String, MqMessagfeReceive> queueReceiveServiceHandleMap = new HashMap<String, MqMessagfeReceive>();
	
	public static void putQueue(MqQueue queue){
		queuesMap.put(queue.getQueueName(), queue);
	}
	
	public static void putQueueReceive(String queueName,QueueMessageReceiveConfg queueconfig){
		queueListenerMap.put(queueName, queueconfig);
	}
	
	public static List<MqQueue> allqueue(){
		List<MqQueue> queues = new ArrayList<MqQueue>();
		queues.addAll(queuesMap.values());
		return queues;
	}
	
	public static Map<String, QueueMessageReceiveConfg> allQueueConfig(){
		return queueListenerMap;
	}
	
	public static void putQueueReceivceService(String queueName,MqMessagfeReceive receiveService){
		queueReceiveServiceHandleMap.put(queueName, receiveService);
	}
	
	public static List<MqMessagfeReceive> getAllQueueReceivceService(){
		List<MqMessagfeReceive> list = new ArrayList<MqMessagfeReceive>();
		list.addAll(queueReceiveServiceHandleMap.values());
		return list;
	}
	public static  MqMessagfeReceive getReceiveService(String queuename){
		return queueReceiveServiceHandleMap.get(queuename);
	}
	

}
