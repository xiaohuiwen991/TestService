package com.hisign.code.model.system;

import com.hisign.code.model.common.BaseModel;

/**
 *
 * @author jiangpeng
 * @since 2017/6/12 11:26
 */
public class SysDictModel extends BaseModel {

    private String root;

    private String key;

    private String[] keys;

    private String value;

    private String parentKey;

    private String queryString;

    private String queryType;

    private String py;

    /**
     * 启用标志
     */
    private String openFlag;

    /**
     * 默认rootKey
     */
    private String defaultRoot;

    /**
     * 备注
     */
    private String remark;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 是否父节点
     */
    private String isParent;

    /**
     * 字典级别
     */
    private String dictLevel;

    /**
     * 是否叶节点
     */
    private String leafFlag;

    /**
     * 当前id
     */
    private String localId;

    public String getPy() {
        return py;
    }

    public void setPy(String py) {
        this.py = py;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getParentKey() {
        return parentKey;
    }

    public void setParentKey(String parentKey) {
        this.parentKey = parentKey;
    }

    public String[] getKeys() {
        return keys;
    }

    public void setKeys(String[] keys) {
        this.keys = keys;
    }

    public String getOpenFlag() {
        return openFlag;
    }

    public void setOpenFlag(String openFlag) {
        this.openFlag = openFlag;
    }

    public String getDefaultRoot() {
        return defaultRoot;
    }

    public void setDefaultRoot(String defaultRoot) {
        this.defaultRoot = defaultRoot;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDictKey() {
        return key;
    }

    public void setDictKey(String dictKey) {
        this.key = dictKey;
    }

    public String getDictValue() {
        return value;
    }

    public void setDictValue(String dictValue) {
        this.value = dictValue;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getIsParent() {
        return isParent;
    }

    public void setIsParent(String isParent) {
        this.isParent = isParent;
    }

    public String getDictLevel() {
        return dictLevel;
    }

    public void setDictLevel(String dictLevel) {
        this.dictLevel = dictLevel;
    }

    public String getLeafFlag() {
        return leafFlag;
    }

    public void setLeafFlag(String leafFlag) {
        this.leafFlag = leafFlag;
    }

    @Override
    public String getLocalId() {
        return localId;
    }

    @Override
    public void setLocalId(String localId) {
        this.localId = localId;
    }

    public SysDictModel(String key, String value, String py, String parentKey) {
        this.key = key;
        this.value = value;
        this.py = py;
        this.parentKey = parentKey;
    }

    public SysDictModel(){};
}
