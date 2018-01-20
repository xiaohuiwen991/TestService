package com.hisign.code.model.business;

import com.hisign.code.model.common.BaseModel;

import java.util.Date;

/**
 * 模版语句model
 * @author xiaohuiwen
 * @since 2017/5/25 14:00
 */
public class TemplateSql extends BaseModel {
    private String name;

    private String type;

    private String typeZw;

    private String sql;

    private String description;

    private String createPid;

    private Date createDate;

    private String createDateBegin;

    private String createDateEnd;

    private String modifyPid;

    private Date modifyDate;

    private String createPidName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql == null ? null : sql.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getCreatePid() {
        return createPid;
    }

    public void setCreatePid(String createPid) {
        this.createPid = createPid == null ? null : createPid.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getModifyPid() {
        return modifyPid;
    }

    public void setModifyPid(String modifyPid) {
        this.modifyPid = modifyPid == null ? null : modifyPid.trim();
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getTypeZw() {
        return typeZw;
    }

    public void setTypeZw(String typeZw) {
        this.typeZw = typeZw;
    }

    public String getCreateDateBegin() {
        return createDateBegin;
    }

    public void setCreateDateBegin(String createDateBegin) {
        this.createDateBegin = createDateBegin;
    }

    public String getCreateDateEnd() {
        return createDateEnd;
    }

    public void setCreateDateEnd(String createDateEnd) {
        this.createDateEnd = createDateEnd;
    }

    public String getCreatePidName() {
        return createPidName;
    }

    public void setCreatePidName(String createPidName) {
        this.createPidName = createPidName;
    }
}