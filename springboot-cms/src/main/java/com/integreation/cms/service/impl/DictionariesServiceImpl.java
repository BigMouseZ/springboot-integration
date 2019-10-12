package com.integreation.cms.service.impl;

import com.integreation.cms.cmsdao.SysDictionariesMapper;
import com.integreation.cms.entity.cms.cmsentity.DictionariesEntity;
import com.integreation.cms.entity.cms.cmsentity.cmsdictionaries.CmsDictUtil;
import com.integreation.cms.entity.cms.cmsentity.enumrntity.DictionariesEnum;
import com.integreation.cms.entity.cms.cmsresponsevo.DictListGroupResponseVo;
import com.integreation.cms.entity.cms.cmsresponsevo.DictListResponseVo;
import com.integreation.cms.entity.cms.cmsresponsevo.DictionariesEntityItemResponseVo;
import com.integreation.cms.entity.cms.cmsresponsevo.DictionariesEntityResponseVo;
import com.integreation.cms.entity.response.ResponseVo;
import com.integreation.cms.service.DictionariesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by ZhangGang on 2019/7/23.
 */
@Slf4j
@Service
public class DictionariesServiceImpl implements DictionariesService {
    @Autowired
    private CmsDictUtil cmsDictUtil;
    @Autowired
    private SysDictionariesMapper sysDictionariesMapper;

    @Override
    public ResponseVo addDictGroup(DictionariesEntity dictionariesEntity) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseVo.ResponseErrCode.AuthErr);
        try {
            String group = cmsDictUtil.getDicString(DictionariesEnum.dictionaryTypeValue.group);
            DictionariesEntity insert = new DictionariesEntity();
            insert.setGuid(dictionariesEntity.getGuid());
            insert.setEdit(true);
            insert.setDictionariesType(group);
            insert.setDictionariesCode(dictionariesEntity.getGuid());
            insert.setDictionariesName(dictionariesEntity.getDictionariesName());
            insert.setDictionariesValue(dictionariesEntity.getDictionariesName());
            sysDictionariesMapper.insertSelective(insert);
            cmsDictUtil.addDictionaries(insert);
            responseVo.setCode(ResponseVo.ResponseErrCode.OK);
            responseVo.setMessage("执行成功");
        } catch (Exception e) {
            log.error("添加字典组异常：", e);
        }
        return responseVo;
    }

    @Override
    public ResponseVo editDictGroup(DictionariesEntity dictionariesEntity) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseVo.ResponseErrCode.AuthErr);
        try {
            DictionariesEntity edit = new DictionariesEntity();
            edit.setGuid(dictionariesEntity.getGuid());
            edit.setDictionariesName(dictionariesEntity.getDictionariesName());
            edit.setDictionariesValue(dictionariesEntity.getDictionariesName());
            sysDictionariesMapper.updateByPrimaryKeySelective(edit);
            DictionariesEntity update = sysDictionariesMapper.selectById(edit);
            cmsDictUtil.addDictionaries(update);
            responseVo.setCode(ResponseVo.ResponseErrCode.OK);
            responseVo.setMessage("执行成功");
        } catch (Exception e) {
            log.error("编辑字典组异常：", e);
        }
        return responseVo;
    }

    @Override
    public ResponseVo deleteDictGroup(DictionariesEntity dictionariesEntity) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseVo.ResponseErrCode.AuthErr);
        try {
            DictionariesEntity one = sysDictionariesMapper.selectById(dictionariesEntity);
            if (one == null) {
                responseVo.setCode(ResponseVo.ResponseErrCode.ParamErr);
                responseVo.setMessage("字典数据不存在");
                return responseVo;
            }
            sysDictionariesMapper.deleteBySelective(dictionariesEntity);
            DictionariesEntity queryItem = new DictionariesEntity();
            queryItem.setParentCode(one.getDictionariesCode());
            List<DictionariesEntity>  dictitemList =  sysDictionariesMapper.listAllByCondition(queryItem);
            for (DictionariesEntity entity : dictitemList) {
                cmsDictUtil.delDictionaries(entity);
            }
            DictionariesEntity deleteitem = new DictionariesEntity();
            deleteitem.setParentCode(one.getDictionariesCode());
            sysDictionariesMapper.deleteBySelective(deleteitem);
            cmsDictUtil.delDictionaries(one);
            responseVo.setCode(ResponseVo.ResponseErrCode.OK);
            responseVo.setMessage("执行成功");
        } catch (Exception e) {
            log.error("删除字典组异常：", e);
        }
        return responseVo;
    }

    @Override
    public ResponseVo listDictGroup() {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseVo.ResponseErrCode.AuthErr);
        try {
            DictionariesEntity query = new DictionariesEntity();
            query.setParentCode("group");
            List<DictionariesEntity> list = sysDictionariesMapper.listAllByCondition(query);
            List<DictionariesEntityResponseVo> dicList = null;
            if (list == null || list.isEmpty()) {
                dicList = new ArrayList<>();
            } else {
                dicList = list.stream().map(s -> {
                    DictionariesEntityResponseVo config = new DictionariesEntityResponseVo();
                    config.setDictGroupId(s.getGuid());
                    config.setDictGroupKey(s.getDictionariesCode());
                    config.setDictGroupName(s.getDictionariesValue());
                    return config;
                }).collect(Collectors.toList());
            }
            responseVo.setCode(ResponseVo.ResponseErrCode.OK);
            responseVo.setMessage("执行成功");
            responseVo.setData(dicList);
        } catch (Exception e) {
            log.error("查询字典组列表异常：", e);
        }
        return responseVo;
    }

    @Override
    public ResponseVo addDictItem(DictionariesEntity dictionariesEntity) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseVo.ResponseErrCode.AuthErr);
        try {
            String enums = cmsDictUtil.getDicString(DictionariesEnum.dictionaryTypeValue.enums);
            DictionariesEntity query = new DictionariesEntity();
            query.setGuid(dictionariesEntity.getDictGroupId());
            DictionariesEntity parent = sysDictionariesMapper.selectById(query);
            if (parent == null) {
                log.info("父级字典不存在，父节点主键:" + dictionariesEntity.getDictGroupId());
                responseVo.setCode(ResponseVo.ResponseErrCode.ParamErr);
                responseVo.setMessage("父级字典不存在");
                return responseVo;
            }
            DictionariesEntity query2 = new DictionariesEntity();
            query2.setDictionariesCode(dictionariesEntity.getDictionariesCode());

            DictionariesEntity old = sysDictionariesMapper.selectByDictionariesCode(query2);
            if (old != null) {
                log.info("字典编码已存在:" + dictionariesEntity.getDictionariesDescribe());
                responseVo.setCode(ResponseVo.ResponseErrCode.ParamErr);
                responseVo.setMessage("字典编码已存在");
                return responseVo;
            }
            DictionariesEntity insert = new DictionariesEntity();
            insert.setGuid(dictionariesEntity.getDictionariesCode());
            insert.setDictionariesType(enums);
            insert.setEdit(true);
            insert.setDictionariesCode(insert.getGuid());
            insert.setParentCode(parent.getDictionariesCode());
            insert.setDictionariesName(dictionariesEntity.getDictionariesName());
            insert.setDictionariesValue(dictionariesEntity.getDictionariesValue());
            insert.setAdditional(dictionariesEntity.getAdditional());
            insert.setDictionariesDescribe(dictionariesEntity.getDictionariesDescribe());
            insert.setSort(dictionariesEntity.getSort());
            sysDictionariesMapper.insertSelective(insert);
            cmsDictUtil.addDictionaries(insert);
            responseVo.setCode(ResponseVo.ResponseErrCode.OK);
            responseVo.setMessage("执行成功");
        } catch (Exception e) {
            log.error("添加字典项异常：", e);
        }
        return responseVo;
    }

    @Override
    public ResponseVo editDictItem(DictionariesEntity dictionariesEntity) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseVo.ResponseErrCode.AuthErr);
        try {
            DictionariesEntity query2 = new DictionariesEntity();
            query2.setDictionariesCode(dictionariesEntity.getDictionariesCode());
            DictionariesEntity old = sysDictionariesMapper.selectByDictionariesCode(query2);
            if (old != null) {
                //根据识别码找到了字典数据，并且该字典与修改目标不是同一个字典，返回错误
                if (!old.getGuid().equals(dictionariesEntity.getGuid())) {
                    log.info("字典编码已存在:" + dictionariesEntity.getDictionariesCode());
                    return responseVo;
                }
            }
            DictionariesEntity update = new DictionariesEntity();
            update.setGuid(dictionariesEntity.getGuid());
            update.setDictionariesCode(dictionariesEntity.getDictionariesCode());
            update.setDictionariesName(dictionariesEntity.getDictionariesName());
            update.setDictionariesValue(dictionariesEntity.getDictionariesValue());
            update.setAdditional(dictionariesEntity.getAdditional());
            update.setDictionariesDescribe(dictionariesEntity.getDictionariesDescribe());
            update.setSort(dictionariesEntity.getSort());
            DictionariesEntity olddict = sysDictionariesMapper.selectById(update);
            sysDictionariesMapper.updateByPrimaryKeySelective(update);
            DictionariesEntity newdict = sysDictionariesMapper.selectById(update);
            cmsDictUtil.delDictionaries(olddict);
            cmsDictUtil.addDictionaries(newdict);
            responseVo.setCode(ResponseVo.ResponseErrCode.OK);
            responseVo.setMessage("执行成功");
        } catch (Exception e) {
            log.error("编辑字典项异常：", e);
        }
        return responseVo;
    }

    @Override
    public ResponseVo deleteDictItem(DictionariesEntity dictionariesEntity) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseVo.ResponseErrCode.AuthErr);
        try {
            DictionariesEntity one = sysDictionariesMapper.selectById(dictionariesEntity);
            if (one == null) {
                responseVo.setCode(ResponseVo.ResponseErrCode.ParamErr);
                responseVo.setMessage("字典项数据不存在");
                return responseVo;
            }
            sysDictionariesMapper.deleteBySelective(dictionariesEntity);
            cmsDictUtil.delDictionaries(one);
            responseVo.setCode(ResponseVo.ResponseErrCode.OK);
            responseVo.setMessage("执行成功");
        } catch (Exception e) {
            log.error("删除字典项异常：", e);
        }
        return responseVo;
    }

    @Override
    public ResponseVo listDictItem(DictionariesEntity dictionariesEntity) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseVo.ResponseErrCode.AuthErr);
        try {
            DictionariesEntity query = new DictionariesEntity();
            query.setGuid(dictionariesEntity.getDictGroupId());
            DictionariesEntity parent = sysDictionariesMapper.selectById(query);
            if (parent == null) {
                responseVo.setCode(ResponseVo.ResponseErrCode.ParamErr);
                responseVo.setMessage("字典项父类数据不存在");
                return responseVo;
            }
            DictionariesEntity queryItem = new DictionariesEntity();
            queryItem.setParentCode(parent.getDictionariesCode());
            List<DictionariesEntity> list = sysDictionariesMapper.listAllByCondition(queryItem);
            List<DictionariesEntityItemResponseVo> dicList = null;
            if (list == null || list.isEmpty()) {
                dicList = new ArrayList<>();
            } else {
                dicList = list.stream().map(s -> {
                    DictionariesEntityItemResponseVo config = new DictionariesEntityItemResponseVo();
                    config.setDictId(s.getGuid());
                    config.setDictKey(s.getDictionariesCode());
                    config.setDictName(s.getDictionariesName());
                    config.setDictValue(s.getDictionariesValue());
                    config.setDictSecondValue(s.getAdditional());
                    config.setDictDescription(s.getDictionariesDescribe());
                    config.setDictEnabled(s.getEdit());
                    config.setDicSort(String.valueOf(s.getSort()));
                    return config;
                }).collect(Collectors.toList());
            }
            responseVo.setCode(ResponseVo.ResponseErrCode.OK);
            responseVo.setMessage("执行成功");
            responseVo.setData(dicList);
        } catch (Exception e) {
            log.error("查询字典项列表异常：", e);
        }
        return responseVo;
    }

    @Override
    public ResponseVo editDictEnabled(DictionariesEntity dictionariesEntity) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseVo.ResponseErrCode.AuthErr);
        try {
            DictionariesEntity one = sysDictionariesMapper.selectById(dictionariesEntity);
            if (one == null) {
                responseVo.setCode(ResponseVo.ResponseErrCode.ParamErr);
                responseVo.setMessage("字典项数据不存在");
                return responseVo;
            }
            sysDictionariesMapper.updateByPrimaryKeySelective(dictionariesEntity);
            DictionariesEntity newdict = sysDictionariesMapper.selectById(dictionariesEntity);
            cmsDictUtil.addDictionaries(newdict);
            responseVo.setCode(ResponseVo.ResponseErrCode.OK);
            responseVo.setMessage("执行成功");
        } catch (Exception e) {
            log.error("字典全表查询异常：", e);
        }
        return responseVo;
    }

    @Override
    public ResponseVo listDict() {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseVo.ResponseErrCode.AuthErr);
        try {
            List<DictListGroupResponseVo> list = new ArrayList<>();
            //先查询分组
            DictionariesEntity query = new DictionariesEntity();
            query.setParentCode("group");
            List<DictionariesEntity> dicList = sysDictionariesMapper.listAllByCondition(query);
            if (dicList != null) {
                for (DictionariesEntity dic : dicList) {
                    DictListGroupResponseVo dr = new DictListGroupResponseVo();
                    dr.setDictKey(dic.getDictionariesCode());
                    //查询分组下的子集合
                    query.setParentCode(dic.getDictionariesCode());
                    List<DictListResponseVo> cList = new ArrayList<>();
                    List<DictionariesEntity> cdList = sysDictionariesMapper.listAllByCondition(query);
                    if (cdList != null) {
                        for (DictionariesEntity d : cdList) {
                            DictListResponseVo drc = new DictListResponseVo();
                            drc.setDictDescription(d.getDictionariesDescribe());
                            drc.setDictId(d.getGuid());
                            drc.setDictKey(d.getDictionariesCode());
                            drc.setDictName(d.getDictionariesName());
                            drc.setDictValue(d.getDictionariesValue());
                            drc.setDictSecondValue(d.getAdditional());
                            drc.setDicSort(d.getSort());
                            cList.add(drc);
                        }
                        if (cList.size() > 1) {
                            cList.sort((d1, d2) -> Optional.ofNullable(d1.getDicSort()).map(o -> o.intValue()).orElse(0)
                                    - Optional.ofNullable(d2.getDicSort()).map(o -> o.intValue()).orElse(0));
                        }
                    }
                    dr.setDictList(cList);
                    list.add(dr);
                }
            }
            responseVo.setCode(ResponseVo.ResponseErrCode.OK);
            responseVo.setMessage("执行成功");
            responseVo.setData(list);
        } catch (Exception e) {
            log.error("更新字典项状态异常：", e);
        }
        return responseVo;
    }
}
