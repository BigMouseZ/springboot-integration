package com.integreation.cms.entity.cms.cmsresponsevo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserAuthStateResponseBean implements Serializable{
	private String authCommand;//;// ""
    private Boolean authHas;// false;//
    private String authIcon;// "/icon/audit.png";//
    private String authId;// "450bd90b-5156-4f06-8636-5a07d744c826";//
    private String authKey;// "menu.order.list";//
    private String authName;// "待审核数据列表";//
    private String authRoute;// "/menu/order/list";//
    private String authType;// "menu";//
    private String authSort;
    private List<UserAuthStateResponseBean> children;
}
