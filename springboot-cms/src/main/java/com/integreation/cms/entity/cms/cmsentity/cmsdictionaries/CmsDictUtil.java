package com.integreation.cms.entity.cms.cmsentity.cmsdictionaries;

import com.integreation.cms.entity.cms.cmsentity.CmsRedisKey;
import com.integreation.cms.entity.cms.cmsentity.DictionariesEntity;
import com.integreation.cms.entity.cms.cmsentity.SysParameterConfigEntity;
import com.integreation.cms.utils.redis.redisutils.RedisMapUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by ZhangGang on 2019/7/23.
 */
@Slf4j
@Component
public class CmsDictUtil {
    @Autowired
    private RedisMapUtil redisMapUtil;

    public void addDictionaries(DictionariesEntity d) {
        if (d.getParentCode() == null || "".equals(d.getParentCode().trim())) {
            redisMapUtil.setByKey(CmsRedisKey.DICTIONARIES + ":group", d.getDictionariesCode(), d);
        } else {
            redisMapUtil.setByKey(CmsRedisKey.DICTIONARIES + ":" + d.getParentCode(), d.getDictionariesCode(), d);
        }
    }
    public void delDictionaries(DictionariesEntity d) {
        if (d.getParentCode() == null || "".equals(d.getParentCode())) {
            redisMapUtil.deleteByKey(CmsRedisKey.DICTIONARIES + ":group", d.getDictionariesCode());
        } else {
            redisMapUtil.deleteByKey(CmsRedisKey.DICTIONARIES + ":" + d.getParentCode(), d.getDictionariesCode());
        }
    }

    /**
     * 获取具体字典值
     *
     * @param e
     * @return
     */
    public String getDicString(Enum e) {
        if (e.getClass().getSimpleName().endsWith("Value")) {
            String key = CmsRedisKey.DICTIONARIES + ":" + e.getClass().getSimpleName().replace("Value", "");
            DictionariesEntity dic = redisMapUtil.getByKey(key, e.toString());
            if (dic != null) {
                return dic.getDictionariesValue();
            }
        }
        return null;
    }
    public Integer getDicInteger(Enum e) {
        if (e.getClass().getSimpleName().endsWith("Value")) {
            String key = CmsRedisKey.DICTIONARIES + ":" + e.getClass().getSimpleName().replace("Value", "");
            DictionariesEntity dic = redisMapUtil.getByKey(key, e.toString());
            if (dic != null) {
                return Integer.parseInt(dic.getDictionariesValue());
            }
        }
        return null;
    }
    //以下是系统配置项
    public void addParameterConfig(SysParameterConfigEntity par) {
        if (par.getParentKey() == null || "".equals(par.getParentKey())) {
            redisMapUtil.setByKey(CmsRedisKey.PARAMETER_CONFIG + ":group", par.getParameterKey(), par);
        } else {
            redisMapUtil.setByKey(CmsRedisKey.PARAMETER_CONFIG + ":" + par.getParentKey(), par.getParameterKey(), par);
        }
    }

    public void delParameterConfig(SysParameterConfigEntity par) {
        if (par.getParentKey() == null) {
            redisMapUtil.deleteByKey(CmsRedisKey.PARAMETER_CONFIG + ":group", par.getParameterKey());
        } else {
            redisMapUtil.deleteByKey(CmsRedisKey.PARAMETER_CONFIG + ":" + par.getParentKey(), par.getParameterKey());
        }
    }
    public String getPCString(Enum e) {
        if (e.getClass().getSimpleName().endsWith("Value")) {
            String key = CmsRedisKey.PARAMETER_CONFIG + ":" + e.getClass().getSimpleName().replace("Value", "");
            SysParameterConfigEntity pc = redisMapUtil.getByKey(key, e.toString());
            if (pc != null) {
                return pc.getParameterValue();
            }
        }
        return null;
    }
    public Integer getPCInteger(Enum e) {
        if (e.getClass().getSimpleName().endsWith("Value")) {
            String key = CmsRedisKey.PARAMETER_CONFIG + ":" + e.getClass().getSimpleName().replace("Value", "");
            SysParameterConfigEntity pc = redisMapUtil.getByKey(key, e.toString());
            if (pc != null) {
                return Integer.parseInt(pc.getParameterValue());
            }
        }
        return null;
    }

}
