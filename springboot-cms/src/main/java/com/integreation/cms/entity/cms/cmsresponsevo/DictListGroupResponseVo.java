package com.integreation.cms.entity.cms.cmsresponsevo;

import lombok.Data;

import java.util.List;

@Data
public class DictListGroupResponseVo {
    private String  dictKey;
    private List<DictListResponseVo> dictList;
}
