package com.integration.test;



import java.io.Serializable;
import java.util.Date;

/**
 * Created by Admin on 2017/9/9.
 * 警用平台数据库，映射违法数据表peccancycar的实体
 *
 */
public class PeccancyCarPojo implements Serializable {
    private String id;

    private Long policecarid;

    private String policecarcode;

    private String devicecode;

    private Long policeid;

    private String policename;

    private String carno;

    private String carnocolor;

    private String carcolor;

    private Double longitude;

    private Double latitude;

    private String address;

    private String big1;

    private String big2;

    private String big3;

    private String big4;

    private String big5;

    private String small;

    private String bin;

    private Integer filenum;

    private String fileurl;

    private Date collectiontime;

    private String departmentselfid;

    private String cartype;

    private Date addtime;

    private String memo;

    private String illegalcode;

    private String addresscode;

    private String cameraid;

    private Integer isconfirm;

    private Long confirmuserid;

    private Date confirmtime;

    private Integer isupload;

    private Integer haveparking;

    private Integer havestopsign;

    private Integer havenoparkingarea;

    private String addressReference;

    private String addresscodeReference;

    private String parkingsignpicture;

    private String carnoConfirm;

    private String carnocolorConfirm;

    private String mergeUrl;

    private String addressConfirm;

    private String addresscodeConfirm;

    private String illegalcodeConfirm;

    private String platepicture;

    private String otherpicture1;

    private String otherpicture2;

    private String otherpicture3;

    private Integer isidentify;

    private Integer datasourcetype;

    private Integer iswhitelist;

    private Date uploadtime;

    private Date updatetime;

    private String policeaddresscodeConfirm;

    private Date databaseTime;

    private Integer msgSend;

    private String msgSendId;

    private String msgSendStatus;

    private String msgSendFailCode;

    private String msgSendCarowner;

    private String msgSendTel;

    private String msgSendPhone;

    private String msgSendFzjg;

    private String hpzl;

    private String msgSendErrorCode;


    public String getMsgSendErrorCode() {
        return msgSendErrorCode;
    }

    public void setMsgSendErrorCode(String msgSendErrorCode) {
        this.msgSendErrorCode = msgSendErrorCode;
    }

    public String getHpzl() {
        return hpzl;
    }

    public void setHpzl(String hpzl) {
        this.hpzl = hpzl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Long getPolicecarid() {
        return policecarid;
    }

    public void setPolicecarid(Long policecarid) {
        this.policecarid = policecarid;
    }

    public String getPolicecarcode() {
        return policecarcode;
    }

    public void setPolicecarcode(String policecarcode) {
        this.policecarcode = policecarcode == null ? null : policecarcode.trim();
    }

    public String getDevicecode() {
        return devicecode;
    }

    public void setDevicecode(String devicecode) {
        this.devicecode = devicecode == null ? null : devicecode.trim();
    }

    public Long getPoliceid() {
        return policeid;
    }

    public void setPoliceid(Long policeid) {
        this.policeid = policeid;
    }

    public String getPolicename() {
        return policename;
    }

    public void setPolicename(String policename) {
        this.policename = policename == null ? null : policename.trim();
    }

    public String getCarno() {
        return carno;
    }

    public void setCarno(String carno) {
        this.carno = carno == null ? null : carno.trim();
    }

    public String getCarnocolor() {
        return carnocolor;
    }

    public void setCarnocolor(String carnocolor) {
        this.carnocolor = carnocolor == null ? null : carnocolor.trim();
    }

    public String getCarcolor() {
        return carcolor;
    }

    public void setCarcolor(String carcolor) {
        this.carcolor = carcolor == null ? null : carcolor.trim();
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getBig1() {
        return big1;
    }

    public void setBig1(String big1) {
        this.big1 = big1 == null ? null : big1.trim();
    }

    public String getBig2() {
        return big2;
    }

    public void setBig2(String big2) {
        this.big2 = big2 == null ? null : big2.trim();
    }

    public String getBig3() {
        return big3;
    }

    public void setBig3(String big3) {
        this.big3 = big3 == null ? null : big3.trim();
    }

    public String getBig4() {
        return big4;
    }

    public void setBig4(String big4) {
        this.big4 = big4 == null ? null : big4.trim();
    }

    public String getBig5() {
        return big5;
    }

    public void setBig5(String big5) {
        this.big5 = big5 == null ? null : big5.trim();
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small == null ? null : small.trim();
    }

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin == null ? null : bin.trim();
    }

    public Integer getFilenum() {
        return filenum;
    }

    public void setFilenum(Integer filenum) {
        this.filenum = filenum;
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl == null ? null : fileurl.trim();
    }

    public Date getCollectiontime() {
        return collectiontime;
    }

    public void setCollectiontime(Date collectiontime) {
        this.collectiontime = collectiontime;
    }

    public String getDepartmentselfid() {
        return departmentselfid;
    }

    public void setDepartmentselfid(String departmentselfid) {
        this.departmentselfid = departmentselfid == null ? null : departmentselfid.trim();
    }

    public String getCartype() {
        return cartype;
    }

    public void setCartype(String cartype) {
        this.cartype = cartype == null ? null : cartype.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getIllegalcode() {
        return illegalcode;
    }

    public void setIllegalcode(String illegalcode) {
        this.illegalcode = illegalcode == null ? null : illegalcode.trim();
    }

    public String getAddresscode() {
        return addresscode;
    }

    public void setAddresscode(String addresscode) {
        this.addresscode = addresscode == null ? null : addresscode.trim();
    }

    public String getCameraid() {
        return cameraid;
    }

    public void setCameraid(String cameraid) {
        this.cameraid = cameraid == null ? null : cameraid.trim();
    }

    public Integer getIsconfirm() {
        return isconfirm;
    }

    public void setIsconfirm(Integer isconfirm) {
        this.isconfirm = isconfirm;
    }

    public Long getConfirmuserid() {
        return confirmuserid;
    }

    public void setConfirmuserid(Long confirmuserid) {
        this.confirmuserid = confirmuserid;
    }

    public Date getConfirmtime() {
        return confirmtime;
    }

    public void setConfirmtime(Date confirmtime) {
        this.confirmtime = confirmtime;
    }

    public Integer getIsupload() {
        return isupload;
    }

    public void setIsupload(Integer isupload) {
        this.isupload = isupload;
    }

    public Integer getHaveparking() {
        return haveparking;
    }

    public void setHaveparking(Integer haveparking) {
        this.haveparking = haveparking;
    }

    public Integer getHavestopsign() {
        return havestopsign;
    }

    public void setHavestopsign(Integer havestopsign) {
        this.havestopsign = havestopsign;
    }

    public Integer getHavenoparkingarea() {
        return havenoparkingarea;
    }

    public void setHavenoparkingarea(Integer havenoparkingarea) {
        this.havenoparkingarea = havenoparkingarea;
    }

    public String getAddressReference() {
        return addressReference;
    }

    public void setAddressReference(String addressReference) {
        this.addressReference = addressReference == null ? null : addressReference.trim();
    }

    public String getAddresscodeReference() {
        return addresscodeReference;
    }

    public void setAddresscodeReference(String addresscodeReference) {
        this.addresscodeReference = addresscodeReference == null ? null : addresscodeReference.trim();
    }

    public String getParkingsignpicture() {
        return parkingsignpicture;
    }

    public void setParkingsignpicture(String parkingsignpicture) {
        this.parkingsignpicture = parkingsignpicture == null ? null : parkingsignpicture.trim();
    }

    public String getCarnoConfirm() {
        return carnoConfirm;
    }

    public void setCarnoConfirm(String carnoConfirm) {
        this.carnoConfirm = carnoConfirm == null ? null : carnoConfirm.trim();
    }

    public String getCarnocolorConfirm() {
        return carnocolorConfirm;
    }

    public void setCarnocolorConfirm(String carnocolorConfirm) {
        this.carnocolorConfirm = carnocolorConfirm == null ? null : carnocolorConfirm.trim();
    }

    public String getMergeUrl() {
        return mergeUrl;
    }

    public void setMergeUrl(String mergeUrl) {
        this.mergeUrl = mergeUrl == null ? null : mergeUrl.trim();
    }

    public String getAddressConfirm() {
        return addressConfirm;
    }

    public void setAddressConfirm(String addressConfirm) {
        this.addressConfirm = addressConfirm == null ? null : addressConfirm.trim();
    }

    public String getAddresscodeConfirm() {
        return addresscodeConfirm;
    }

    public void setAddresscodeConfirm(String addresscodeConfirm) {
        this.addresscodeConfirm = addresscodeConfirm == null ? null : addresscodeConfirm.trim();
    }

    public String getIllegalcodeConfirm() {
        return illegalcodeConfirm;
    }

    public void setIllegalcodeConfirm(String illegalcodeConfirm) {
        this.illegalcodeConfirm = illegalcodeConfirm == null ? null : illegalcodeConfirm.trim();
    }

    public String getPlatepicture() {
        return platepicture;
    }

    public void setPlatepicture(String platepicture) {
        this.platepicture = platepicture == null ? null : platepicture.trim();
    }

    public String getOtherpicture1() {
        return otherpicture1;
    }

    public void setOtherpicture1(String otherpicture1) {
        this.otherpicture1 = otherpicture1 == null ? null : otherpicture1.trim();
    }

    public String getOtherpicture2() {
        return otherpicture2;
    }

    public void setOtherpicture2(String otherpicture2) {
        this.otherpicture2 = otherpicture2 == null ? null : otherpicture2.trim();
    }

    public String getOtherpicture3() {
        return otherpicture3;
    }

    public void setOtherpicture3(String otherpicture3) {
        this.otherpicture3 = otherpicture3 == null ? null : otherpicture3.trim();
    }

    public Integer getIsidentify() {
        return isidentify;
    }

    public void setIsidentify(Integer isidentify) {
        this.isidentify = isidentify;
    }

    public Integer getDatasourcetype() {
        return datasourcetype;
    }

    public void setDatasourcetype(Integer datasourcetype) {
        this.datasourcetype = datasourcetype;
    }

    public Integer getIswhitelist() {
        return iswhitelist;
    }

    public void setIswhitelist(Integer iswhitelist) {
        this.iswhitelist = iswhitelist;
    }

    public Date getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(Date uploadtime) {
        this.uploadtime = uploadtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getPoliceaddresscodeConfirm() {
        return policeaddresscodeConfirm;
    }

    public void setPoliceaddresscodeConfirm(String policeaddresscodeConfirm) {
        this.policeaddresscodeConfirm = policeaddresscodeConfirm == null ? null : policeaddresscodeConfirm.trim();
    }

    public Date getDatabaseTime() {
        return databaseTime;
    }

    public void setDatabaseTime(Date databaseTime) {
        this.databaseTime = databaseTime;
    }

    public Integer getMsgSend() {
        return msgSend;
    }

    public void setMsgSend(Integer msgSend) {
        this.msgSend = msgSend;
    }

    public String getMsgSendId() {
        return msgSendId;
    }

    public void setMsgSendId(String msgSendId) {
        this.msgSendId = msgSendId == null ? null : msgSendId.trim();
    }

    public String getMsgSendStatus() {
        return msgSendStatus;
    }

    public void setMsgSendStatus(String msgSendStatus) {
        this.msgSendStatus = msgSendStatus;
    }

    public String getMsgSendFailCode() {
        return msgSendFailCode;
    }

    public void setMsgSendFailCode(String msgSendFailCode) {
        this.msgSendFailCode = msgSendFailCode == null ? null : msgSendFailCode.trim();
    }

    public String getMsgSendCarowner() {
        return msgSendCarowner;
    }

    public void setMsgSendCarowner(String msgSendCarowner) {
        this.msgSendCarowner = msgSendCarowner == null ? null : msgSendCarowner.trim();
    }

    public String getMsgSendTel() {
        return msgSendTel;
    }

    public void setMsgSendTel(String msgSendTel) {
        this.msgSendTel = msgSendTel == null ? null : msgSendTel.trim();
    }

    public String getMsgSendPhone() {
        return msgSendPhone;
    }

    public void setMsgSendPhone(String msgSendPhone) {
        this.msgSendPhone = msgSendPhone == null ? null : msgSendPhone.trim();
    }

    public String getMsgSendFzjg() {
        return msgSendFzjg;
    }

    public void setMsgSendFzjg(String msgSendFzjg) {
        this.msgSendFzjg = msgSendFzjg == null ? null : msgSendFzjg.trim();
    }
}

