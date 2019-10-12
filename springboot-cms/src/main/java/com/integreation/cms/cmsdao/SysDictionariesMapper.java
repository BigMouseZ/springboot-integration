package com.integreation.cms.cmsdao;


import com.integreation.cms.entity.cms.cmsentity.DictionariesEntity;

import java.util.List;


public interface SysDictionariesMapper {

    int deleteBySelective(DictionariesEntity record);

    int insertSelective(DictionariesEntity record);

    DictionariesEntity selectById(DictionariesEntity example);

    DictionariesEntity selectByDictionariesCode(DictionariesEntity example);

    int updateByPrimaryKeySelective(DictionariesEntity record);

    List<DictionariesEntity> listAllByCondition(DictionariesEntity record);
}