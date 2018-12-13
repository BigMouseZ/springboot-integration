package com.utils.xlsx;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DataOutOndlyHandIngControl {
	// 线程安全的map
	public static Map<String, Object> DATA_OUT_HAND_CONTROL_MAP = new ConcurrentHashMap<>();

	//疑似逃费数据导出
	public static final String CARANTICIPATION_DATA_OUT = "CARANTICIPATION_DATA_OUT";
	//ETC过车数据导出
	public static final String ETCCARDATA_DATA_OUT = "ETCCARDATA_DATA_OUT";
	//历史数据导出
	public static final String HISTORYCARDATA_DATA_OUT = "HISTORYCARDATA_DATA_OUT";
	//异常数据
	public static final String EXCARDATA_DATA_OUT = "EXCARDATA_DATA_OUT";
	//服务区滞留数据导出
	public static final String SARETENTIONDATA_DATA_OUT = "SARETENTIONDATA_DATA_OUT";
	//车辆轨迹数据导出
	public static final String CARPATHDATA_DATA_OUT = "CARPATHDATA_DATA_OUT";
	//黑名单库数据导出
	public static final String CARBLACKDATA_DATA_OUT = "CARBLACKDATA_DATA_OUT";
	//进出比对数据导出
	public static final String CARINOUTDATA_DATA_OUT = "CARINOUTDATA_DATA_OUT";
	
	
	
	
	
	
	
	

}
