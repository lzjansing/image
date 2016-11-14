//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.us.common.persistence;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.us.common.modules.sys.utils.DictUtil;
import com.us.common.utils.IdGen;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.xml.bind.annotation.XmlTransient;
import java.util.Date;

public abstract class DataEntity<T> extends BaseEntity<T> {
    protected String remarks;
    protected Date createDate;
    protected Date updateDate;
    protected Integer valid;
    public static final Integer VALID_DELETE = 0;
    public static final Integer VALID_ENABLE = Integer.valueOf(DictUtil.getDictValue("正常", "valid", null));
    public static final Integer VALID_DISABLE = Integer.valueOf(DictUtil.getDictValue("禁用", "valid", null));

    public DataEntity() {
        this.valid = VALID_ENABLE;
    }

    public DataEntity(String id) {
        super(id);
    }

    public void preInsert() {
        if (!this.isNew) {
            this.setId(IdGen.uuid());
        }
        this.updateDate = new Date();
        this.createDate = this.updateDate;
    }

    public void preUpdate() {
        this.updateDate = new Date();
    }

    @Length(max = 255)
    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @JsonIgnore
    @XmlTransient
    @Range(min = 0, max = 2)
    public Integer getValid() {
        return this.valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }
}
