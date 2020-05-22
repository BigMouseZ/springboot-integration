package com.integration.springbootmq.mqlistener;

import com.integration.springbootmq.mq.message.BaseMqMessage;
import lombok.Data;

import java.util.Date;

/**
 * 视觉轨迹数据
 * 发送到MQ用于后续轨迹拟合并生成行驶档案
 * 项目名称：稽查平台MQ组件  
 * 类名称：VideoTrackMessage  
 * 创建人: lzl
 * 创建时间:2020年4月2日 上午10:00:26  
 * 修改时间:2020年4月2日 上午10:00:26
 */
@Data
public class VideoTrackMessage extends BaseMqMessage {


    private String videoTrackId;
	
	private String carType;
    private String guid;

    private String plateCode;

    private String plateColor;

    private String plateType;

    private String identifyType;

    private String vehicleSerial;

    private String vehicleColor;

    private String vehicleBrand;

    private String vehicleType;

    private String calcStatus;

    private Integer travelDistance;

    private Integer travelDuration;

    private Integer travelSpeed;

    private Long tollsAmount;

    private Long discountAmount;

    private Date firstTime;

    private String firstRoadId;

    private String firstRoadCode;

    private String firstRoadName;

    private String firstSectionId;

    private String firstSectionCode;

    private String firstSectionName;

    private String firstStationId;

    private String firstStationCode;

    private String firstStationName;

    private String firstGantryId;

    private String firstGantryCode;

    private String firstGantryName;

    private String firstLaneCode;

    private String firstMilestone;

    private String firstDriveType;

    private String firstRecordId;

    private Date lastTime;

    private String lastRoadId;

    private String lastRoadCode;

    private String lastRoadName;

    private String lastSectionId;

    private String lastSectionCode;

    private String lastSectionName;

    private String lastGantryCode;

    private String lastStationId;

    private String lastStationCode;

    private String lastStationName;

    private String lastGantryId;

    private String lastGantryName;

    private String lastGantryMilestone;

    private String lastLaneCode;

    private String lastDriveType;

    private String lastRecordId;

    private Object nextPoints;

    private String memo;

    private Date createTime;

    private Date modifyTime;

    private String lastUser;
    //新增字段
    private String trackType;

    //辅助字段
    private Double score; // 图像比对分数

    private boolean cango; //标识可达



}
