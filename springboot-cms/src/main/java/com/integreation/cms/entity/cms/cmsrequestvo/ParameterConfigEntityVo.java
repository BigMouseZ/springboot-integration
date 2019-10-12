package com.integreation.cms.entity.cms.cmsrequestvo;

import lombok.Data;

import java.util.List;

@Data
public class ParameterConfigEntityVo {
    private String configId;
    private String configValue;
    private String configName;
    private String configDescribe;
    private List<ParameterConfigItemEntityVo> configParams;
    private String configGroupName;

    private String configGroupId;
}
