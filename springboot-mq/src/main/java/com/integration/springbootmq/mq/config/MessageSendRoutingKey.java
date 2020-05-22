package com.integration.springbootmq.mq.config;

public enum MessageSendRoutingKey {
	EXPRESSWAY_CAPTURE_TOLL_ALL_DATA("EXPRESSWAY.CAPTURE.TOLL.*.DATA","全部的收费通行数据"),
	EXPRESSWAY_SUSPECT_VIDEO_TRACK_DATA_STATISTIC("EXPRESSWAY.SUSPECT.VIDEO.TRACK.DATA.STATISTIC","疑似视觉轨迹数据统计"),
	EXPRESSWAY_SUBSET_VIDEO_TRACK_MERGE_DATA("EXPRESSWAY.SUBSET.VIDEO.TRACK.MERGE.DATA","子系统视觉轨迹片段合并数据"),
	EXPRESSWAY_SUBSET_VIDEO_TRACK_DATA("EXPRESSWAY.SUBSET.VIDEO.TRACK.DATA","子系统视觉轨迹上传中心数据"),
	EXPRESSWAY_SUBSET_TOLL_TRACK_DATA("EXPRESSWAY.SUBSET.TOLL.TRACK.DATA","子系统收费轨迹上传中心数据"),
	EXPRESSWAY_SUBSET_TOLL_TRACK_MERGE_DATA("EXPRESSWAY.SUBSET.TOLL.TRACK.MERGE.DATA","子系统收费轨迹片段合并数据"),
	EXPRESSWAY_OVERTIME_VIDEO_TRACK_DATA("EXPRESSWAY.OVERTIME.VIDEO.TRACK.DATA","视觉轨迹超时数据"),
	EXPRESSWAY_OVERTIME_TOLL_TRACK_DATA("EXPRESSWAY.OVERTIME.TOLL.TRACK.DATA","收费轨迹超时数据"),
	EXPRESSWAY_CAPTURE_VIDEO_ALL_DATA("EXPRESSWAY.CAPTURE.VIDEO.*.DATA","全部的视觉通行数据"),
	EXPRESSWAY_CAPTURE_VIDEO_IN_DATA("EXPRESSWAY.CAPTURE.VIDEO.IN.DATA","入口的视觉通行数据"),
	EXPRESSWAY_CAPTURE_VIDEO_OUT_DATA("EXPRESSWAY.CAPTURE.VIDEO.OUT.DATA","出口的视觉通行数据"),
	EXPRESSWAY_CAPTURE_VIDEO_PASS_DATA("EXPRESSWAY.CAPTURE.VIDEO.PASS.DATA","经过的视觉通行数据"),
	EXPRESSWAY_TRAVELARCHIVES_ALL_DATA("EXPRESSWAY.TRAVEL.ARCHIVES.*.DATA","行驶档案全部数据"),
	EXPRESSWAY_VIDEO_TRACK_ALL_DATA("EXPRESSWAY.VIDEO.TRACK.*.DATA","视觉轨迹全部数据"),
	EXPRESSWAY_TRAVEL_ASSERTMODEL_ALL_DATA("EXPRESSWAY.TRAVEL.ASSERTMODEL.*.DATA","包含特征结果数据的行驶档案全部数据");

	private String routingKey;
	private String name;
	
	 MessageSendRoutingKey(String routingKey, String name){
		this.routingKey = routingKey;
		this.name = name;
	}

	public String getRoutingKey() {
		return routingKey;
	}

	public String getName() {
		return name;
	}
	
	
	

}
