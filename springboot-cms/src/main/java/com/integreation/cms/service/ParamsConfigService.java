package com.integreation.cms.service;


import com.integreation.cms.entity.cms.cmsentity.SysParameterConfigEntity;
import com.integreation.cms.entity.cms.cmsrequestvo.ParameterConfigEntityVo;
import com.integreation.cms.entity.response.ResponseVo;

/**
 * Created by ZhangGang on 2019/7/23.
 */
public interface ParamsConfigService {
    ResponseVo addConfigGroup(SysParameterConfigEntity configEntity);

    ResponseVo editConfigGroup(SysParameterConfigEntity configEntity);

    ResponseVo deleteConfigGroup(SysParameterConfigEntity configEntity);

    ResponseVo listConfigGroup();

    ResponseVo listConfig(SysParameterConfigEntity configEntity);

    ResponseVo editConfig(ParameterConfigEntityVo configEntityVo);
}
