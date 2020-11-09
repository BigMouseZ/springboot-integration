package com.signalway.mannager;

import com.signalway.base.MqQueue;
import com.signalway.base.MqQueueConfig;
import com.signalway.base.QueueConfg;
import com.signalway.recive.MqMessagfeReceive;
import com.signalway.recive.RocketMqListener;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MqManager {
	
	private static Map<String, MqQueueConfig> queuesMap = new HashMap<String, MqQueueConfig>();
	
	private static Map<String, QueueConfg> queueListenerMap = new HashMap<String, QueueConfg>();
	
	private static Map<String, MqMessagfeReceive> queueReceiveServiceHandleMap = new HashMap<String, MqMessagfeReceive>();
	private  static  Map<String, RocketMqListener> rocketMqListenerMap=new HashMap<>(  );
	
	public static void putQueue(MqQueueConfig queue){
		queuesMap.put(queue.getQueueName(), queue);
	}
	
	public static void putQueueReceive(String queueName,QueueConfg queueconfig){
		queueListenerMap.put(queueName, queueconfig);
	}
	
	public static List<MqQueueConfig> allqueue(){
		List<MqQueueConfig> queues = new ArrayList<MqQueueConfig>();
		queues.addAll(queuesMap.values());
		return queues;
	}
	
	public static Map<String, QueueConfg> allQueueConfig(){
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
	 public  static void  putRocketMqListenerMap(RocketMqListener rocketMqListener){
		 rocketMqListenerMap.put(rocketMqListener.receiveConfig().getTopic()+":"+rocketMqListener.receiveConfig().getTag(),rocketMqListener );
	 }
	public  static List<RocketMqListener>  allRocketMqListener(){
		List<RocketMqListener> list = new ArrayList<RocketMqListener>();
		list.addAll(rocketMqListenerMap.values());
		return list;
	}
}
