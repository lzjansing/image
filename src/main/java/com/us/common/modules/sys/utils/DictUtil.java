package com.us.common.modules.sys.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.us.common.mapper.JsonMapper;
import com.us.common.modules.sys.dao.DictDao;
import com.us.common.modules.sys.entities.Dict;
import com.us.spring.utils.CacheUtil;
import com.us.spring.utils.SpringContextHolder;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by jansing on 16-11-13.
 */
public class DictUtil {
    private static DictDao dictDao = (DictDao) SpringContextHolder.getBean(DictDao.class);
    public static final String CACHE_DICT_MAP = "dictMap";

    public static String getDictLabel(String value, String type, String defaultValue) {
        if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(value)) {
            Iterator<Dict> iterator = getDictList(type).iterator();

            while (iterator.hasNext()) {
                Dict dict = iterator.next();
                if (value.equals(dict.getValue())) {
                    return dict.getLabel();
                }
            }
        }
        return defaultValue;
    }

    public static String getDictLabels(String values, String type, String defaultValue) {
        if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(values)) {
            ArrayList valueList = Lists.newArrayList();
            String[] var4 = StringUtils.split(values, ",");
            for (int i = 0; i < var4.length; ++i) {
                valueList.add(getDictLabel(var4[i], type, defaultValue));
            }

            return StringUtils.join(valueList, ",");
        } else {
            return defaultValue;
        }
    }

    public static String getDictValue(String label, String type, String defaultLabel) {
        if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(label)) {
            Iterator<Dict> iterator = getDictList(type).iterator();
            while (iterator.hasNext()) {
                Dict dict = iterator.next();
                if (label.equals(dict.getLabel())) {
                    return dict.getValue();
                }
            }
        }
        return defaultLabel;
    }

    public static List<Dict> getDictList(String type) {
        Map<String, List<Dict>> dictMap = (Map) CacheUtil.get("dictMap");
        if (dictMap == null) {
            dictMap = Maps.newHashMap();
            Iterator<Dict> dictList = dictDao.findList(new Dict()).iterator();

            while (dictList.hasNext()) {
                Dict dict = dictList.next();
                List<Dict> dictList1 = dictMap.get(dict.getType());
                if (dictList1 != null) {
                    dictList1.add(dict);
                } else {
                    dictMap.put(dict.getType(), Lists.newArrayList(new Dict[]{dict}));
                }
            }
            CacheUtil.put("dictMap", dictMap);
        }

        List<Dict> dictList2 = dictMap.get(type);
        if (dictList2 == null) {
            dictList2 = Lists.newArrayList();
        }
        return dictList2;
    }

    public static String getDictListJson(String type) {
        return JsonMapper.toJsonString(getDictList(type));
    }
}
