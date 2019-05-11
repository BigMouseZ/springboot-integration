package com.integration.basic;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Admin on 2016/12/15.
 */
@Data
public class PoliceCarPojo implements Serializable {
//    public static final String OBJECT_KEY = "POLICE_CAR"; //旧版本的平台使用了此KEY
    public static final String OBJECT_KEY = "PoliceCar";    //1.2版本平台使用的KEY

    private static final long serialVersionUID = 5649745715610947815L;

    private Long id;
    private String policecarcode;
    private String policecarno;
    private String devicecode;
    private String ip;
    private String brand;
    private Integer models;
    private int state;
    private Date nextmot;
    private Date nextmaintenancetime;
    private String contacts;
    private String contactnumber;
    private String intercomnumber;
    private Long departmentid;
    private String departmentselfid;
    private String departmentname;
    private String company;
    private String subcompany;
    private Date addtime;
    private byte isdeleted;
    private Integer devicetype;
    private String json;
    private Date modifytime;

    public PoliceCarPojo(Long id, String policecarcode, String policecarno, String devicecode) {
        this.id = id;
        this.policecarcode = policecarcode;
        this.policecarno = policecarno;
        this.devicecode = devicecode;
    }
}
