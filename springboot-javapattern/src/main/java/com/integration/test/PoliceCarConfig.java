package com.integration.test;
/**
 * @author: 林嘉宝
 *  
 * @Date: 2018/7/19
 *  
 * @name: 
 *
 * @Description: 
 */
public class PoliceCarConfig {
    // 警车ID
    private Long policecarid;
    // 警车编号
    private String policeCarCode;
    // 警车车牌号
    private String policeCarNo;
    // 警车设备编号
    private String devicecode;

    // 目标平台
    private String target;
    // 海康 - 卡口密钥
    private String hikvisionKKMY;
    // 海康 - 卡口编号
    private String hikvisionKKBH;
    // 海康 - 车道号
    private String hikvisionCDBH;

    // 集指 - 卡口 - 卡口编号
    private String rminfCardataKKBH;
    // 集指 - 卡口 - 方向类型
    private String rminfCardataFXLX;
    // 集指 - 卡口 - 车道号
    private String rminfCardataCDH;
    // 集指 - 违法 - 设备编号
    private String rminfPeccancycarSBBH;
    // 集指 - 违法 - 车辆方向
    private String rminfPeccancycarCLFX;
    // 集指 - GPS - gps设备编号
    private String rminfGPSBH;

    // 警车名称
    private String policeCarNameTop;

    // 对接用 警员编号
    private String postPoliceCode;
    // 对接用 采集机关代码
    private String postDepartmentCode;
    // 配置json字符串
    private String json;
    //执法设备编号
    private String checkpuid;

    public String getPoliceCarNameTop() {
        return policeCarNameTop;
    }

    public void setPoliceCarNameTop(String policeCarNameTop) {
        this.policeCarNameTop = policeCarNameTop;
    }

    public String getRminfGPSBH() {
        return rminfGPSBH;
    }

    public void setRminfGPSBH(String rminfGPSBH) {
        this.rminfGPSBH = rminfGPSBH;
    }

    public String getPoliceCarCode() {
        return policeCarCode;
    }

    public void setPoliceCarCode(String policeCarCode) {
        this.policeCarCode = policeCarCode;
    }

    public String getPoliceCarNo() {
        return policeCarNo;
    }

    public void setPoliceCarNo(String policeCarNo) {
        this.policeCarNo = policeCarNo;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getPostPoliceCode() {
        return postPoliceCode;
    }

    public void setPostPoliceCode(String postPoliceCode) {
        this.postPoliceCode = postPoliceCode;
    }

    public String getPostDepartmentCode() {
        return postDepartmentCode;
    }

    public void setPostDepartmentCode(String postDepartmentCode) {
        this.postDepartmentCode = postDepartmentCode;
    }

    public String getDevicecode() {
        return devicecode;
    }

    public void setDevicecode(String devicecode) {
        this.devicecode = devicecode;
    }

    public Long getPolicecarid() {
        return policecarid;
    }

    public void setPolicecarid(Long policecarid) {
        this.policecarid = policecarid;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getHikvisionKKMY() {
        return hikvisionKKMY;
    }

    public void setHikvisionKKMY(String hikvisionKKMY) {
        this.hikvisionKKMY = hikvisionKKMY;
    }

    public String getHikvisionKKBH() {
        return hikvisionKKBH;
    }

    public void setHikvisionKKBH(String hikvisionKKBH) {
        this.hikvisionKKBH = hikvisionKKBH;
    }

    public String getHikvisionCDBH() {
        return hikvisionCDBH;
    }

    public void setHikvisionCDBH(String hikvisionCDBH) {
        this.hikvisionCDBH = hikvisionCDBH;
    }

    public String getRminfCardataKKBH() {
        return rminfCardataKKBH;
    }

    public void setRminfCardataKKBH(String rminfCardataKKBH) {
        this.rminfCardataKKBH = rminfCardataKKBH;
    }

    public String getRminfCardataFXLX() {
        return rminfCardataFXLX;
    }

    public void setRminfCardataFXLX(String rminfCardataFXLX) {
        this.rminfCardataFXLX = rminfCardataFXLX;
    }

    public String getRminfCardataCDH() {
        return rminfCardataCDH;
    }

    public void setRminfCardataCDH(String rminfCardataCDH) {
        this.rminfCardataCDH = rminfCardataCDH;
    }

    public String getRminfPeccancycarSBBH() {
        return rminfPeccancycarSBBH;
    }

    public void setRminfPeccancycarSBBH(String rminfPeccancycarSBBH) {
        this.rminfPeccancycarSBBH = rminfPeccancycarSBBH;
    }

    public String getRminfPeccancycarCLFX() {
        return rminfPeccancycarCLFX;
    }

    public void setRminfPeccancycarCLFX(String rminfPeccancycarCLFX) {
        this.rminfPeccancycarCLFX = rminfPeccancycarCLFX;
    }

    public String getCheckpuid() {
        return checkpuid;
    }

    public void setCheckpuid(String checkpuid) {
        this.checkpuid = checkpuid;
    }

    @Override
    public String toString() {
        return "PoliceCarConfig{" +
                "policecarid=" + policecarid +
                ", policeCarCode='" + policeCarCode + '\'' +
                ", policeCarNo='" + policeCarNo + '\'' +
                ", devicecode='" + devicecode + '\'' +
                ", target='" + target + '\'' +
                ", hikvisionKKMY='" + hikvisionKKMY + '\'' +
                ", hikvisionKKBH='" + hikvisionKKBH + '\'' +
                ", hikvisionCDBH='" + hikvisionCDBH + '\'' +
                ", rminfCardataKKBH='" + rminfCardataKKBH + '\'' +
                ", rminfCardataFXLX='" + rminfCardataFXLX + '\'' +
                ", rminfCardataCDH='" + rminfCardataCDH + '\'' +
                ", rminfPeccancycarSBBH='" + rminfPeccancycarSBBH + '\'' +
                ", rminfPeccancycarCLFX='" + rminfPeccancycarCLFX + '\'' +
                ", postPoliceCode='" + postPoliceCode + '\'' +
                ", postDepartmentCode='" + postDepartmentCode + '\'' +
                ", json='" + json + '\'' +
                ", checkpuid='" + checkpuid + '\'' +
                '}';
    }
}
