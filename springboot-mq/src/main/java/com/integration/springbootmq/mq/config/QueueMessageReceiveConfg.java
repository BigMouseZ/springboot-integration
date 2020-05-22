package com.integration.springbootmq.mq.config;

import lombok.Data;

@Data
public class QueueMessageReceiveConfg {
	private int consumersCount;
	private int consumersMaxPrefetchCount;
	

}
