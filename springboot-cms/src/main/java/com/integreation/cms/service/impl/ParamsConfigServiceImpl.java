package com.integreation.cms.service.impl;

import com.integreation.cms.cmsdao.SysParameterConfigMapper;
import com.integreation.cms.entity.cms.cmsentity.SysParameterConfigEntity;
import com.integreation.cms.entity.cms.cmsentity.cmsdictionaries.CmsDictUtil;
import com.integreation.cms.entity.cms.cmsrequestvo.ParameterConfigEntityVo;
import com.integreation.cms.entity.cms.cmsresponsevo.ParameterConfigEntityResponseVo;
import com.integreation.cms.entity.cms.cmsresponsevo.ParameterConfigItemEntityResponseVo;
import com.integreation.cms.entity.response.ResponseVo;
import com.integreation.cms.service.ParamsConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ParamsConfigServiceImpl implements ParamsConfigService {
    @Autowired
    private CmsDictUtil cmsDictUtil;
    @Autowired
    private SysParameterConfigMapper sysParameterConfigMapper;

    @Override
    public ResponseVo addConfigGroup(SysParameterConfigEntity configEntity) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseVo.ResponseErrCode.AuthErr);
        try {
            SysParameterConfigEntity insert = new SysParameterConfigEntity();
            insert.setGuid(configEntity.getParameterKey());
            insert.setEdit(true);
            insert.setParameterKey(configEntity.getParameterKey());
            insert.setParameterName(configEntity.getParameterName());
            insert.setParameterType("分组");
            sysParameterConfigMapper.insertSelective(insert);
            cmsDictUtil.addParameterConfig(insert);
            responseVo.setCode(ResponseVo.ResponseErrCode.OK);
            responseVo.setMessage("执行成功");
        } catch (Exception e) {
            log.error("添加参数组异常：", e);
        }
        return responseVo;
    }

    @Override
    public ResponseVo editConfigGroup(SysParameterConfigEntity configEntity) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseVo.ResponseErrCode.AuthErr);
        try {
            SysParameterConfigEntity update = new SysParameterConfigEntity();
            update.setGuid(configEntity.getParameterKey());
            update.setParameterName(configEntity.getParameterName());
            sysParameterConfigMapper.updateByPrimaryKeySelective(update);
            SysParameterConfigEntity newData = sysParameterConfigMapper.selectByPrimaryKey(update.getGuid());
            cmsDictUtil.addParameterConfig(newData);
            responseVo.setCode(ResponseVo.ResponseErrCode.OK);
            responseVo.setMessage("执行成功");
        } catch (Exception e) {
            log.error("编辑参数组异常：", e);
        }
        return responseVo;
    }

    @Override
    public ResponseVo deleteConfigGroup(SysParameterConfigEntity configEntity) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseVo.ResponseErrCode.AuthErr);
        try {
            SysParameterConfigEntity one = sysParameterConfigMapper.selectByPrimaryKey(configEntity.getGuid());
            if (one == null) {
                responseVo.setCode(ResponseVo.ResponseErrCode.ParamErr);
                responseVo.setMessage("分组数据不存在");
                return responseVo;
            }
            sysParameterConfigMapper.deleteByPrimaryKey(configEntity.getGuid());
            cmsDictUtil.delParameterConfig(one);
            responseVo.setCode(ResponseVo.ResponseErrCode.OK);
            responseVo.setMessage("执行成功");
        } catch (Exception e) {
            log.error("删除参数组异常：", e);
        }
        return responseVo;
    }

    @Override
    public ResponseVo listConfigGroup() {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseVo.ResponseErrCode.AuthErr);
        try {
            SysParameterConfigEntity query = new SysParameterConfigEntity();
            query.setParentKey("group");
            List<SysParameterConfigEntity> list = sysParameterConfigMapper.listAllByCondition(query);
            List<ParameterConfigEntityResponseVo> dicList = null;
            if (list == null || list.isEmpty()) {
                dicList = new ArrayList<>();
            } else {
                dicList = list.stream().map(s -> {
                    ParameterConfigEntityResponseVo config = new ParameterConfigEntityResponseVo();
                    config.setConfigGroupId(s.getGuid());
                    config.setConfigGroupName(s.getParameterName());
                    return config;
                }).collect(Collectors.toList());
            }
            responseVo.setCode(ResponseVo.ResponseErrCode.OK);
            responseVo.setMessage("执行成功");
            responseVo.setData(dicList);
        } catch (Exception e) {
            log.error("查询参数组列表异常：", e);
        }
        return responseVo;
    }

    @Override
    public ResponseVo listConfig(SysParameterConfigEntity configEntity) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseVo.ResponseErrCode.AuthErr);
        try {
            SysParameterConfigEntity query = new SysParameterConfigEntity();
            query.setParentKey(configEntity.getGuid());
            List<SysParameterConfigEntity> list = sysParameterConfigMapper.listAllByCondition(query);
            List<ParameterConfigItemEntityResponseVo> dicList = null;
            if (list == null || list.isEmpty()) {
                dicList = new ArrayList<>();
            } else {
                dicList = list.stream().map(s -> {
                    ParameterConfigItemEntityResponseVo config = new ParameterConfigItemEntityResponseVo();
                    config.setConfigId(s.getGuid());
                    config.setConfigName(s.getParameterName());
                    config.setConfigValue(s.getParameterValue());
                    return config;
                }).collect(Collectors.toList());
            }
            responseVo.setCode(ResponseVo.ResponseErrCode.OK);
            responseVo.setMessage("执行成功");
            responseVo.setData(dicList);
        } catch (Exception e) {
            log.error("查询参数项列表异常：", e);
        }
        return responseVo;
    }

    @Override
    public ResponseVo editConfig(ParameterConfigEntityVo configEntity) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseVo.ResponseErrCode.AuthErr);
        try {
            if (configEntity != null && configEntity.getConfigParams() != null) {
                configEntity.getConfigParams().forEach(one -> {
                    SysParameterConfigEntity update = new SysParameterConfigEntity();
                    update.setGuid(one.getConfigId());
                    update.setParameterValue(one.getConfigValue());
                    sysParameterConfigMapper.updateByPrimaryKeySelective(update);
                    SysParameterConfigEntity newData = sysParameterConfigMapper.selectByPrimaryKey(update.getGuid());
                    cmsDictUtil.addParameterConfig(newData);
                });
            }
            responseVo.setCode(ResponseVo.ResponseErrCode.OK);
            responseVo.setMessage("执行成功");
        } catch (Exception e) {
            log.error("查询参数项列表异常：", e);
        }
        return responseVo;
    }
}
