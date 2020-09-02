package com.integration.springboottime.manualtime.timing;

/**
 * 定义定时任务基本西信息
 * 项目名称：稽查平台基础依赖
 * 类名称：TimingJobInformation
 * 创建人: lzl
 * 创建时间:2020年4月1日 上午10:55:13
 * 修改时间:2020年4月1日 上午10:55:13
 */
public enum TimingJobInformation {
    TIMING_SPLIT_TRACK_CREATE_DATA_SEND("TIMING_SPLIT_TRACK_CREATE_DATA_SEND", "用于汇总生成清分流水数据"),


    TIMING_MERGE_TRACK_TIMEOUT_DATA_SEND("TIMING_MERGE_TRACK_TIMEOUT_DATA_SEND", "用于轨迹合并超时未完整强行输出"),
    TIMING_MERGE_TRACK_SECOND_DATA_SEND("TIMING_MERGE_TRACK_SECOND_DATA_SEND", "用于轨迹二次合并"),
    TIMING_TRACK_CREATE_DATA_SEND("TIMING_TRACK_CREATE_DATA_SEND", "用于将图像通行数据发送给轨迹还原生成系统"),

    TIMING_TOLL_TRACK_CREATE_DATA_SEND("TIMING_TOLL_TRACK_CREATE_DATA_SEND", "用于将收费数据发送给收费轨迹还原生成系统"),
    TIMING_TOLL_TRACK_TIMEOUT_DATA_SEND("TIMING_TOLL_TRACK_TIMEOUT_DATA_SEND", "用于将超时未出的收费轨迹强行输出"),

    TIMING_SYNC_TEMP_TRACK_TO_VIDEO_TRACK("TIMING_SYNC_TEMP_TRACK_TO_VIDEO_TRACK", "用于同步超时临时轨迹到主表"),

    TIMING_DVIMAGE_TEMP_TO_NORMAL("TIMING_DVIMAGE_TEMP_TO_NORMAL", "用于把设备二次识别修正后的数据送到待还原轨迹表"),

    TIMING_DVIMAGE_TEMP_TO_MQ("TIMING_DVIMAGE_TEMP_TO_MQ", "用于把设备识别数据推送到二次识别队列"),

    TIMING_UPLOAD_STATIONENTER("TIMING_UPLOAD_STATIONENTER", "定时上传收费站入口通行数据"),

    TIMING_UPLOAD_STATIONEXIT("TIMING_UPLOAD_STATIONEXIT", "定时上传收费站出口交易数据"),

    TIMING_UPLOAD_GANTRYTRAVELIMAGE("TIMING_UPLOAD_GANTRYTRAVELIMAGE", "定时上传中间门架牌识数据"),

    TIMING_UPLOAD_DTGANTRYTRANSACTION("TIMING_UPLOAD_DTGANTRYTRANSACTION", "定时上传边界门架交易数据"),

    TIMING_UPLOAD_DFSGANTRYTRANSACTION("TIMING_UPLOAD_DFSGANTRYTRANSACTION", "定时上传中间门架交易数据"),

    TIMING_MONITORDATA_CHECKVALID("TIMING_MONITORDATA_CHECKVALID", "定时检测昨天接入的数据有效性");


    private String key;
    private String describe;

    TimingJobInformation(String key, String describe) {
        this.key = key;
        this.describe = describe;
    }

    public String getKey() {
        return key;
    }

    public String getDescribe() {
        return describe;
    }


}
