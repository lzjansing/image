package com.us.image.entities;

import com.us.common.modules.sys.utils.DictUtil;
import com.us.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

/**
 * Created by jansing on 16-11-9.
 */
public class Rule extends DataEntity<Rule> {
    private Integer type;
    private String keyword;
    public static final Integer RULETYPE_NORMAL = Integer.valueOf(DictUtil.getDictValue("精确匹配", "rule_type", null));
    public static final Integer RULETYPE_REGEXP = Integer.valueOf(DictUtil.getDictValue("正则匹配", "rule_type", null));

    public Rule() {
    }

    /**
     * 过滤策略
     * 模糊匹配	1
     * 正则匹配	2
     *
     * @return
     */
    @Range(min = 1, max = 2, message = "系统错误，请重试")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Length(min = 1, max = 50, message = "请输入1～50个字符作为关键字")
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "Rule{" +
                "type=" + type +
                ", keyword='" + keyword + '\'' +
                '}';
    }
}
