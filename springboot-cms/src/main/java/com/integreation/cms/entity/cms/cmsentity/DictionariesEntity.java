package com.integreation.cms.entity.cms.cmsentity;


import com.integreation.cms.entity.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class DictionariesEntity extends BaseEntity {
    private String guid;

    private String dictionariesType;

    private String parentCode;

    private String dictionariesCode;

    private String dictionariesName;

    private String dictionariesValue;

    private Integer sort;

    private Boolean state;

    private Boolean edit;

    private Date modifyTime;

    private Date addTime;

    private String additional;

    private String dictionariesDescribe;

    //辅助字典
    private String dictGroupId;

}