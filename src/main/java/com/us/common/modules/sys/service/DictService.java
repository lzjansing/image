package com.us.common.modules.sys.service;

import com.us.common.modules.sys.dao.DictDao;
import com.us.common.modules.sys.entities.Dict;
import com.us.common.modules.sys.utils.DictUtil;
import com.us.common.service.CrudService;
import com.us.spring.utils.CacheUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jansing on 16-11-13.
 */
@Service
@Transactional(readOnly = true)
public class DictService extends CrudService<DictDao, Dict> {
    public List<String> findTypeList() {
        return this.dao.findTypeList(new Dict());
    }

    @Transactional(readOnly = false)
    public void save(Dict dict) {
        super.save(dict);
        CacheUtil.remove(DictUtil.CACHE_DICT_MAP);
    }

    @Transactional(readOnly = false)
    public void delete(Dict dict) {
        super.delete(dict);
        CacheUtil.remove(DictUtil.CACHE_DICT_MAP);
    }
}
