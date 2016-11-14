package com.us.common.modules.sys.entities;

import com.us.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * Created by jansing on 16-11-13.
 */
public class Dict extends DataEntity<Dict> {
    private String value;
    private String label;
    private String type;
    private String description;
    private Integer sort;

    public Dict() {
    }

    public Dict(String id) {
        super(id);
    }

    public Dict(String value, String label) {
        this.value = value;
        this.label = label;
    }

    @Length(min = 1, max = 100)
    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Length(min = 1, max = 100)
    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Length(min = 1, max = 100)
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Length(min = 0, max = 100)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull
    public Integer getSort() {
        return this.sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String toString() {
        return this.label;
    }
}
