package com.integreation.cms.entity.cms.cmsresponsevo;

import lombok.Data;

/**
 * Created by ZhangGang on 2019/7/23.
 *
 */
/*
* web端数据请求实体转换
* */
@Data
public class DictionariesEntityItemResponseVo {
    private String dictId;//	String	字典id
    private String dictKey;//	String	识别码
    private String dictName	;//String	显示值
    private String dictValue;//	String	字典值
    private String  dictSecondValue;// 	String	附加值
    private String  dictDescription	;//String	描述
    private boolean  dictEnabled	;//String	启用
    private String dicSort;

}
