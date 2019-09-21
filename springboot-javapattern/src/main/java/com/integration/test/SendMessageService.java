package com.integration.test;

/**
 * Created by ZhangGang on 2019/7/9.
 */
public interface SendMessageService {
     PeccancycarOperationRecordEntity getJiNanPeccancyTo(PeccancyCarPojo car);

    void sendMessage(PeccancyCarPojo carPojo, PoliceCarConfig carConfig, PeccancycarOperationRecordEntity beforePeccancy);
}
