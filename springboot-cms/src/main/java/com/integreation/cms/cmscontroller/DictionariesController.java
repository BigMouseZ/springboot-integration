package com.integreation.cms.cmscontroller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.integreation.cms.aop.AuthCheck;
import com.integreation.cms.entity.cms.cmsentity.DictionariesEntity;
import com.integreation.cms.entity.cms.cmsrequestvo.DictionariesEntityVo;
import com.integreation.cms.entity.response.RequestVo;
import com.integreation.cms.entity.response.ResponseVo;
import com.integreation.cms.service.DictionariesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ZhangGang on 2019/7/23.
 * 字典相关接口:
 * 添加分组
 * 修改字典分组
 * 删除字典分组
 * 查询分组数据列表
 * 添加字典项
 * 修改字典项
 * 删除字典项
 * 字典项数据列表
 * 启用/禁用字典项
 *
 */
@Slf4j
@RestController
@RequestMapping("/cms/dictionaries")
@CrossOrigin
public class DictionariesController {
    @Autowired
    private DictionariesService dictionariesService;
    //添加分组
     @AuthCheck(checkAuth=false)
    @RequestMapping(value = "/addDictGroup", method = RequestMethod.POST)
    public ResponseVo addDictGroup(@RequestBody RequestVo requestVo) {
        DictionariesEntityVo dictionariesEntityVo = JSONObject.parseObject(JSON.toJSONString(requestVo.getParams()), new TypeReference<DictionariesEntityVo>() {});
        DictionariesEntity dictionariesEntity = new DictionariesEntity();
        dictionariesEntity.setGuid(dictionariesEntityVo.getDictGroupId());
        dictionariesEntity.setDictionariesName(dictionariesEntityVo.getDictGroupName());
        return dictionariesService.addDictGroup(dictionariesEntity);
    }

    //修改字典分组
     @AuthCheck(checkAuth=false)
    @RequestMapping(value = "/editDictGroup", method = RequestMethod.POST)
    public ResponseVo editDictGroup(@RequestBody RequestVo requestVo) {
        DictionariesEntityVo dictionariesEntityVo = JSONObject.parseObject(JSON.toJSONString(requestVo.getParams()), new TypeReference<DictionariesEntityVo>() {});
        DictionariesEntity dictionariesEntity = new DictionariesEntity();
        dictionariesEntity.setGuid(dictionariesEntityVo.getDictGroupId());
        dictionariesEntity.setDictionariesName(dictionariesEntityVo.getDictGroupName());
        return dictionariesService.editDictGroup(dictionariesEntity);
    }

    //删除字典分组
     @AuthCheck(checkAuth=false)
    @RequestMapping(value = "/deleteDictGroup", method = RequestMethod.POST)
    public ResponseVo deleteDictGroup(@RequestBody RequestVo requestVo) {
        DictionariesEntityVo dictionariesEntityVo = JSONObject.parseObject(JSON.toJSONString(requestVo.getParams()), new TypeReference<DictionariesEntityVo>() {});
        DictionariesEntity dictionariesEntity = new DictionariesEntity();
        dictionariesEntity.setGuid(dictionariesEntityVo.getDictGroupId());
        return dictionariesService.deleteDictGroup(dictionariesEntity);
    }

    //查询分组数据列表
     @AuthCheck(checkAuth=false)
    @RequestMapping(value = "/listDictGroup", method = RequestMethod.POST)
    public ResponseVo listDictGroup(@RequestBody RequestVo requestVo) {
        return dictionariesService.listDictGroup();
    }

    //添加字典项
     @AuthCheck(checkAuth=false)
    @RequestMapping(value = "/addDictItem", method = RequestMethod.POST)
    public ResponseVo addDictItem(@RequestBody RequestVo requestVo) {
        DictionariesEntityVo dictionariesEntityVo = JSONObject.parseObject(JSON.toJSONString(requestVo.getParams()), new TypeReference<DictionariesEntityVo>() {});
        DictionariesEntity dictionariesEntity = new DictionariesEntity();
        dictionariesEntity.setDictGroupId(dictionariesEntityVo.getDictGroupId());
        dictionariesEntity.setDictionariesCode(dictionariesEntityVo.getDictKey());
        dictionariesEntity.setDictionariesName(dictionariesEntityVo.getDictName());
        dictionariesEntity.setDictionariesValue(dictionariesEntityVo.getDictValue());
        dictionariesEntity.setAdditional(dictionariesEntityVo.getDictSecondValue());
        dictionariesEntity.setDictionariesDescribe(dictionariesEntityVo.getDictDescription());
        dictionariesEntity.setSort(dictionariesEntityVo.getDicSort());
        return dictionariesService.addDictItem(dictionariesEntity);
    }

    //修改字典项
     @AuthCheck(checkAuth=false)
    @RequestMapping(value = "/editDictItem", method = RequestMethod.POST)
    public ResponseVo editDictItem(@RequestBody RequestVo requestVo) {
        DictionariesEntityVo dictionariesEntityVo = JSONObject.parseObject(JSON.toJSONString(requestVo.getParams()), new TypeReference<DictionariesEntityVo>() {});
        DictionariesEntity dictionariesEntity = new DictionariesEntity();
        dictionariesEntity.setGuid(dictionariesEntityVo.getDictId());
        dictionariesEntity.setDictionariesCode(dictionariesEntityVo.getDictKey());
        dictionariesEntity.setDictionariesName(dictionariesEntityVo.getDictName());
        dictionariesEntity.setDictionariesValue(dictionariesEntityVo.getDictValue());
        dictionariesEntity.setAdditional(dictionariesEntityVo.getDictSecondValue());
        dictionariesEntity.setDictionariesDescribe(dictionariesEntityVo.getDictDescription());
        dictionariesEntity.setSort(dictionariesEntityVo.getDicSort());
        return dictionariesService.editDictItem(dictionariesEntity);
    }

    //删除字典项
     @AuthCheck(checkAuth=false)
    @RequestMapping(value = "/deleteDictItem", method = RequestMethod.POST)
    public ResponseVo deleteDictItem(@RequestBody RequestVo requestVo) {
        DictionariesEntityVo dictionariesEntityVo = JSONObject.parseObject(JSON.toJSONString(requestVo.getParams()), new TypeReference<DictionariesEntityVo>() {});
        DictionariesEntity dictionariesEntity = new DictionariesEntity();
        dictionariesEntity.setGuid(dictionariesEntityVo.getDictId());
        return dictionariesService.deleteDictItem(dictionariesEntity);
    }

    //字典项数据列表
     @AuthCheck(checkAuth=false)
    @RequestMapping(value = "/listDictItem", method = RequestMethod.POST)
    public ResponseVo listDictItem(@RequestBody RequestVo requestVo) {
        DictionariesEntityVo dictionariesEntityVo = JSONObject.parseObject(JSON.toJSONString(requestVo.getParams()), new TypeReference<DictionariesEntityVo>() {});
        DictionariesEntity dictionariesEntity = new DictionariesEntity();
        dictionariesEntity.setDictGroupId(dictionariesEntityVo.getDictGroupId());
        return dictionariesService.listDictItem(dictionariesEntity);
    }
    //启用/禁用字典项
     @AuthCheck(checkAuth=false)
    @RequestMapping(value = "/editDictEnabled", method = RequestMethod.POST)
    public ResponseVo editDictEnabled(@RequestBody RequestVo requestVo) {
        DictionariesEntityVo dictionariesEntityVo = JSONObject.parseObject(JSON.toJSONString(requestVo.getParams()), new TypeReference<DictionariesEntityVo>() {});
        DictionariesEntity dictionariesEntity = new DictionariesEntity();
        dictionariesEntity.setGuid(dictionariesEntityVo.getDictId());
        dictionariesEntity.setState(dictionariesEntityVo.isDictEnabled());
        return dictionariesService.editDictEnabled(dictionariesEntity);
    }
    //获取字典全表数据，按类型分类
     @AuthCheck(checkAuth=false)
    @RequestMapping(value = "/listDict", method = RequestMethod.POST)
    public ResponseVo listDict(@RequestBody RequestVo requestVo) {
        return dictionariesService.listDict();
    }

}
