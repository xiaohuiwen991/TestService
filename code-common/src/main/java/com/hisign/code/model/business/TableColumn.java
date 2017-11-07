package com.hisign.code.model.business;

import com.hisign.code.model.common.BaseModel;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * 表字段信息
 * @author jiangpeng
 * @since 2017/5/24 16:39
 */
public class TableColumn extends BaseModel {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 字段名
     */
    private String columnName;

    /**
     * 数据类型
     */
    private String dataType;

    /**
     * 数据长度
     */
    private Integer dataLength;

    /**
     * 是否可为空
     */
    private String nullable;

    /**
     * 备注
     */
    private String comments;
    /**
     * 描述来源
     */
    private String commentSource;

    private String tableComments;

    private List<String> tableNames;

    public String getCommentSource() {
        return commentSource;
    }

    public void setCommentSource(String commentSource) {
        this.commentSource = commentSource;
    }

    public String getTableName() {
        if(StringUtils.isEmpty(tableName) && StringUtils.isNotEmpty(getId())) {
            tableName = getId().split("\\.")[0];
        }
        return tableName;
    }

    public void setTableName(String tableName) {
        if(tableName != null) {
            tableName = tableName.toUpperCase();
            if(tableName.contains(",")) {
                setTableNames(tableName);
                return;
            }
        }
        this.tableName = tableName;
    }

    public String getColumnName() {
        if(StringUtils.isEmpty(columnName) && StringUtils.isNotEmpty(getId())) {
            columnName = getId().split("\\.")[1];
        }
        return columnName;
    }

    public void setColumnName(String columnName) {
        if(columnName != null) columnName = columnName.toUpperCase();
        this.columnName = columnName;
    }

    public String getDataType() {
        return dataType;
    }

    public String getDataTypeSub() {
        if(StringUtils.isEmpty(dataType)) return null;
        String type = dataType;
        switch (type) {
            case "VARCHAR2":
                type = "VC";
                break;
            case "CHAR":
                type = "C";
                break;
            case "NUMBER":
                type = "N";
                break;
            case "DATE":
                type = "D";
                break;
            default:
        }
        return type;
    }


    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Integer getDataLength() {
        return dataLength;
    }

    public void setDataLength(Integer dataLength) {
        this.dataLength = dataLength;
    }

    public String getNullable() {
        return nullable;
    }

    public void setNullable(String nullable) {
        this.nullable = nullable;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public List<String> getTableNames() {
        return tableNames;
    }

    public void setTableNames(String tableNames) {
        this.tableNames = StringUtils.isEmpty(tableNames) ? null: Arrays.asList(tableNames.split(","));
    }

    public String getId() {
        return StringUtils.isEmpty(super.getId()) && StringUtils.isNotEmpty(tableName) && StringUtils.isNotEmpty(columnName) ? tableName + "." + columnName : super.getId();
    }

    public String getTableComments() {
        return tableComments;
    }

    public void setTableComments(String tableComments) {
        this.tableComments = tableComments;
    }
}
