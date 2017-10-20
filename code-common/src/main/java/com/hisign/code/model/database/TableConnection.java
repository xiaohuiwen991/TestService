package com.hisign.code.model.database;

import com.hisign.code.model.common.BaseModel;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TableConnection extends BaseModel {

    private String id;

    private String connectionName;

    private String tableName1;

    private String column1;

    private String tableName2;

    private String column2;

    private String connectionType;

    private String createPid;

    private Date createDate;

    private String modifyPid;

    private Date modifyDate;

    private String connectionTypeZw;

    private List<String> tableName;

    private List<String> column;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getConnectionName() {
        return connectionName;
    }

    public void setConnectionName(String connectionName) {
        this.connectionName = connectionName == null ? null : connectionName.trim();
    }

    public String getTableName1() {
        return tableName1;
    }

    public void setTableName1(String tableName1) {
        this.tableName1 = tableName1 == null ? null : tableName1.trim();
    }

    public String getColumn1() {
        return column1;
    }

    public void setColumn1(String column1) {
        this.column1 = column1 == null ? null : column1.trim();
    }

    public String getTableName2() {
        return tableName2;
    }

    public void setTableName2(String tableName2) {
        this.tableName2 = tableName2 == null ? null : tableName2.trim();
    }

    public String getColumn2() {
        return column2;
    }

    public void setColumn2(String column2) {
        this.column2 = column2 == null ? null : column2.trim();
    }

    public String getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType == null ? null : connectionType.trim();
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

    public String getConnectionTypeZw() {
        return connectionTypeZw;
    }

    public void setConnectionTypeZw(String connectionTypeZw) {
        this.connectionTypeZw = connectionTypeZw;
    }

    public List<String> getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = StringUtils.isEmpty(tableName) ? null: Arrays.asList(tableName.split(","));
    }

    public List<String> getColumn() {
        return column;
    }

    public void setColumn(List<String> column) {
        this.column = column;
    }
}