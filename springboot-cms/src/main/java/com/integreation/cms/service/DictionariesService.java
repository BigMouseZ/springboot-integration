package com.integreation.cms.service;


import com.integreation.cms.entity.cms.cmsentity.DictionariesEntity;
import com.integreation.cms.entity.response.ResponseVo;

/**
 * Created by ZhangGang on 2019/7/23.
 */
public interface DictionariesService {
    ResponseVo addDictGroup(DictionariesEntity dictionariesEntity);

    ResponseVo editDictGroup(DictionariesEntity dictionariesEntity);

    ResponseVo deleteDictGroup(DictionariesEntity dictionariesEntity);

    ResponseVo listDictGroup();

    ResponseVo addDictItem(DictionariesEntity dictionariesEntity);

    ResponseVo editDictItem(DictionariesEntity dictionariesEntity);

    ResponseVo deleteDictItem(DictionariesEntity dictionariesEntity);

    ResponseVo listDictItem(DictionariesEntity dictionariesEntity);

    ResponseVo editDictEnabled(DictionariesEntity dictionariesEntity);

    ResponseVo listDict();


}
