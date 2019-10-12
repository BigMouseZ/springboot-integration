package com.integreation.cms.entity.cms.cmsrequestvo;

import lombok.Data;

/**
 * Created by ZhangGang on 2019/7/23.
 *
 */
/*
* web端数据请求实体转换
* */
@Data
public class DictionariesEntityVo {
    //查询
    private String dictGroupId; //是	string	字典分组id
    private String dictGroupName; //是	string	字典分组名称
    //字典分组编辑
    private String settingGroupId;
    private String settingGroupName;
    //字典添加、编辑、删除
    private String dictSecondValue;
    private String dictDescription;
    private String dictId;
    private String dictKey;
    private String dictName;
    private String dictValue;

    private Integer dicSort;

    private boolean dictEnabled;

}
