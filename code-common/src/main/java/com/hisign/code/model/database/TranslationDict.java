package com.hisign.code.model.database;

import com.hisign.code.model.common.BaseModel;

import java.util.Date;

/**
 * 本地词库实体类
 * Created by likangbo on 2017/5/31.
 */
public class TranslationDict extends BaseModel{
    /**
     * 词典原文
     */
    private String originalText;
    /**
     * 译文
     */
    private String translation;
    /**
     * 描述
     */
    private String description;
    /**
     * 创建人
     */
    private String createPid;
    /**
     * 创建时间
     */
    private Date createDate;

    private String createDateBegin;

    private String createDateEnd;

    private String createPidName;

    public void setTranslationDict(String originalText, String translation) {
        if(originalText != null) originalText = originalText.toUpperCase();
        this.originalText = originalText;
        this.translation = translation;
    }

    public String getOriginalText() {
        return originalText;
    }

    public void setOriginalText(String originalText) {
        this.originalText = originalText == null ? null : originalText.trim().toUpperCase();
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation == null ? null : translation.trim();
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

    public String getCreateDateBegin() {
        return createDateBegin;
    }

    public void setCreateDateBegin(String createDateBegin) {
        this.createDateBegin = createDateBegin == null?null:createDateBegin.trim();
    }

    public String getCreateDateEnd() {
        return createDateEnd;
    }

    public void setCreateDateEnd(String createDateEnd) {
        this.createDateEnd = createDateEnd == null?null:createDateEnd.trim();
    }

    public String getCreatePidName() {
        return createPidName;
    }

    public void setCreatePidName(String createPidName) {
        this.createPidName = createPidName == null?null:createPidName.trim();
    }
}
