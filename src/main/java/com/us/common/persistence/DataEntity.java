//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.us.common.persistence;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.us.common.modules.sys.entities.User;
import com.us.common.modules.sys.utils.UserUtil;
import com.us.common.utils.IdGen;
import com.us.common.utils.StringUtil;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.xml.bind.annotation.XmlTransient;
import java.util.Date;

public abstract class DataEntity<T> extends BaseEntity<T> {
    protected String remarks;
    protected User createBy;
    protected Date createDate;
    protected User updateBy;
    protected Date updateDate;
    protected Integer valid;
    public static final Integer VALID_DELETE = 0;
    public static final Integer VALID_ENABLE = 1;
    public static final Integer VALID_DISABLE = 2;

    public static final Integer YES = 1;
    public static final Integer NO = 0;


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

        User user = UserUtil.getUser();
        if (StringUtil.isNotBlank(user.getId())) {
            this.createBy = user;
            this.updateBy = user;
        }

        this.updateDate = new Date();
        this.createDate = this.updateDate;
    }

    public void preUpdate() {
        User user = UserUtil.getUser();
        if (StringUtil.isNotBlank(user.getId())) {
            this.updateBy = user;
        }

        this.updateDate = new Date();
    }

    @Length(max = 255)
    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @JsonIgnore
    public User getCreateBy() {
        return createBy;
    }

    public void setCreateBy(User createBy) {
        this.createBy = createBy;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @JsonIgnore
    public User getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(User updateBy) {
        this.updateBy = updateBy;
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
