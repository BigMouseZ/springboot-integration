package com.integreation.cms.cmscontroller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.integreation.cms.aop.AuthCheck;
import com.integreation.cms.entity.cms.cmsentity.SysParameterConfigEntity;
import com.integreation.cms.entity.cms.cmsrequestvo.ParameterConfigEntityVo;
import com.integreation.cms.entity.response.RequestVo;
import com.integreation.cms.entity.response.ResponseVo;
import com.integreation.cms.service.ParamsConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/*
* 系统参数相关接口
* */
@Slf4j
@RestController
@RequestMapping("/cms/systemconfig")
@CrossOrigin
public class ParamsConfigController {
    @Autowired
    private ParamsConfigService paramsConfigService;
    //添加分组
     @AuthCheck(checkAuth=false)
    @RequestMapping(value = "/addConfigGroup", method = RequestMethod.POST)
    public ResponseVo addConfigGroup(@RequestBody RequestVo requestVo) {
        ParameterConfigEntityVo configEntityVo = JSONObject.parseObject(JSON.toJSONString(requestVo.getParams()), new TypeReference<ParameterConfigEntityVo>() {});
        SysParameterConfigEntity configEntity = new SysParameterConfigEntity();
        configEntity.setParameterKey(configEntityVo.getConfigGroupId());
        configEntity.setParameterName(configEntityVo.getConfigGroupName());
        return paramsConfigService.addConfigGroup(configEntity);
    }

    //修改分组
     @AuthCheck(checkAuth=false)
    @RequestMapping(value = "/editConfigGroup", method = RequestMethod.POST)
    public ResponseVo editConfigGroup(@RequestBody RequestVo requestVo) {
        ParameterConfigEntityVo configEntityVo = JSONObject.parseObject(JSON.toJSONString(requestVo.getParams()), new TypeReference<ParameterConfigEntityVo>() {});
        SysParameterConfigEntity configEntity = new SysParameterConfigEntity();
        configEntity.setParameterKey(configEntityVo.getConfigGroupId());
        configEntity.setParameterName(configEntityVo.getConfigGroupName());
        return paramsConfigService.editConfigGroup(configEntity);
    }

    //删除分组
     @AuthCheck(checkAuth=false)
    @RequestMapping(value = "/deleteConfigGroup", method = RequestMethod.POST)
    public ResponseVo deleteConfigGroup(@RequestBody RequestVo requestVo) {
        ParameterConfigEntityVo configEntityVo = JSONObject.parseObject(JSON.toJSONString(requestVo.getParams()), new TypeReference<ParameterConfigEntityVo>() {});
        SysParameterConfigEntity configEntity = new SysParameterConfigEntity();
        configEntity.setGuid(configEntityVo.getConfigGroupId());
        return paramsConfigService.deleteConfigGroup(configEntity);
    }

    //查询分组数据列表
     @AuthCheck(checkAuth=false)
    @RequestMapping(value = "/listConfigGroup", method = RequestMethod.POST)
    public ResponseVo listConfigGroup(@RequestBody RequestVo requestVo) {
        return paramsConfigService.listConfigGroup();
    }

    //项数据列表
     @AuthCheck(checkAuth=false)
    @RequestMapping(value = "/listConfig", method = RequestMethod.POST)
    public ResponseVo listConfig(@RequestBody RequestVo requestVo) {
        ParameterConfigEntityVo configEntityVo = JSONObject.parseObject(JSON.toJSONString(requestVo.getParams()), new TypeReference<ParameterConfigEntityVo>() {});
        SysParameterConfigEntity configEntity = new SysParameterConfigEntity();
        configEntity.setGuid(configEntityVo.getConfigGroupId());
        return paramsConfigService.listConfig(configEntity);
    }

    //项数据修改
     @AuthCheck(checkAuth=false)
    @RequestMapping(value = "/editConfig", method = RequestMethod.POST)
    public ResponseVo editConfig(@RequestBody RequestVo requestVo) {
        ParameterConfigEntityVo configEntityVo = JSONObject.parseObject(JSON.toJSONString(requestVo.getParams()), new TypeReference<ParameterConfigEntityVo>() {});
        return paramsConfigService.editConfig(configEntityVo);
    }
}
