package com.integreation.cms.cmsdao;


import com.integreation.cms.entity.cms.cmsentity.SysParameterConfigEntity;

import java.util.List;

public interface SysParameterConfigMapper {

    int deleteByPrimaryKey(String guid);

    int insertSelective(SysParameterConfigEntity record);

    List<SysParameterConfigEntity> listAllByCondition(SysParameterConfigEntity example);

    SysParameterConfigEntity selectByPrimaryKey(String guid);

    int updateByPrimaryKeySelective(SysParameterConfigEntity record);

    int updateByPrimaryKey(SysParameterConfigEntity record);
}