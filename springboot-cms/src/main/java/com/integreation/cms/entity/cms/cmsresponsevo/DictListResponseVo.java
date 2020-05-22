package com.integreation.cms.entity.cms.cmsresponsevo;

import lombok.Data;

@Data
public class DictListResponseVo {
    private String dictId;//	String	字典id
    private String dictKey;//	String	识别码
    private String dictName	;//String	显示值
    private String dictValue;//	String	字典值
    private String  dictSecondValue;// 	String	附加值
    private String  dictDescription	;//String	描述
    private Integer dicSort;
}
