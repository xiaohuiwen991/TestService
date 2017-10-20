package com.hisign.code.model.database;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.support.odps.udf.CodecCheck;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hisign.code.constant.GegentypusEnum;
import com.hisign.code.constant.LableEnum;
import com.hisign.code.util.CamelNamingUtil;
import com.hisign.code.util.StringUtil;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * sql创建
 * @author jiangpeng
 * @since 2017/6/6 16:25
 */
public class CreateSql implements Serializable {

    /**
     *
     */
    private List<TableColumn> tableColumnList;

    private List<TableColumn> tableList;

    private List<TableConnection> tableConnectList;

    private String templateSql;

    private static String regex = "(.*?)";

    public List<String> excludeColumn = new ArrayList<>();

    public List<String> cludeColumn = new ArrayList<>();

    private Map<String, Gegentypus> gegentypusMap = new HashMap<>();

    public void setExcludeColumn(List<String> excludeColumn) {
        if (!CollectionUtils.isEmpty(excludeColumn))
            this.excludeColumn.addAll(excludeColumn);
    }

    public void setCludeColumn(List<String> cludeColumn) {
        this.cludeColumn = cludeColumn;
    }

    public void setGegentypusMap(String str) {
        if (StringUtils.isEmpty(str)) return;
        List<Gegentypus> gegentypusList = JSON.parseArray(str, Gegentypus.class);
        for (Gegentypus gegentypus : gegentypusList) {
            gegentypusMap.put(gegentypus.getOracleType(), gegentypus);
        }
    }

    public List<TableColumn> getTableColumnList() {
        return tableColumnList;
    }

    public void setTableColumnList(List<TableColumn> tableColumnList) {
        this.tableColumnList = tableColumnList;
    }

    public List<TableColumn> getTableList() {
        return tableList;
    }

    public void setTableList(List<TableColumn> tableList) {
        this.tableList = tableList;
    }

    public List<TableConnection> getTableConnectList() {
        return tableConnectList;
    }

    public void setTableConnectList(List<TableConnection> tableConnectList) {
        this.tableConnectList = tableConnectList;
    }

    public String getTemplateSql() {
        return templateSql;
    }

    public void setTemplateSql(String templateSql) {
        this.templateSql = templateSql;
    }

    /**
     * 获得标签内容
     *
     * @param lableEnum
     * @return
     */
    public static String getLableContent(String str, LableEnum lableEnum) {
        String content = "";
        Pattern pattern = Pattern.compile(lableEnum.getLable(regex));
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            content = matcher.group(1);
        }
        return content;
    }

    /**
     * 获得移除表字段信息
     *
     * @return
     */
    public static String getExcludeTable(String str) {
        if (StringUtils.isEmpty(str)) return str;
        //配置
        if (str.trim().startsWith(LableEnum.CONFIG.getStart())) {
            String group = getLableContent(str.replace("\n", "$n"), LableEnum.CONFIG);
            str = getLableContent(group, LableEnum.EXCLUDEBYTABLE);
        }
        return str;
    }

    @JsonIgnore
    @JSONField(serialize = false)
    public String getSubmeterSql(List<String> excludeColumn) throws Exception {
        List<String> sql = new ArrayList<>();
        if (!CollectionUtils.isEmpty(tableList) && !CollectionUtils.isEmpty(tableColumnList)) {
            Map<String, List<TableColumn>> map = new HashMap<>();
            Set<String> strings = map.keySet();
            //将列分表存入集合
            for (TableColumn tableColumn : tableColumnList) {
                if (strings.contains(tableColumn.getTableName())) {
                    map.get(tableColumn.getTableName()).add(tableColumn);
                } else {
                    List<TableColumn> tableColumns = new ArrayList<>();
                    tableColumns.add(tableColumn);
                    map.put(tableColumn.getTableName(), tableColumns);
                }
            }
            //分表创建语句
            for (TableColumn table : tableList) {
                CreateSql createSql = new CreateSql();
                createSql.setTemplateSql(templateSql);
                createSql.setTableList(new ArrayList<>(Arrays.asList(table)));
                createSql.setTableColumnList(map.get(table.getTableName()));
                sql.add(createSql.getCreateSql(excludeColumn));
            }
        }
        return StringUtils.join(sql, "$n$n");
    }

    @JsonIgnore
    @JSONField(serialize = false)
    public String getCreateSql(List<String> excludeColumn) throws Exception {
        //特殊字符替换
        Map replaceMap = ArrayUtils.toMap(new String[][]{
                {"$arrStart", "["},
                {"$arrEnd", "]"}
        });
        templateSql = templateSql.replace("\n", "$n");
        //配置
        if (templateSql.trim().startsWith(LableEnum.CONFIG.getStart())) {
            //获得配置信息
            String group = getLableContent(templateSql, LableEnum.CONFIG);
            //获得类型配置
            String str = getLableContent(group, LableEnum.GEGENTYPUS);
            setGegentypusMap(str);
            //获得移除列
            setExcludeColumn(excludeColumn);
            str = getLableContent(group, LableEnum.EXCLUDECOLUMN);
            if (StringUtils.isNotEmpty(str)) {
                setExcludeColumn(Arrays.asList(str.split(",")));
            }
            //获得包含列
            str = getLableContent(group, LableEnum.CLUDECOLUMN);
            if (StringUtils.isNotEmpty(str)) {
                setCludeColumn(Arrays.asList(str.split(",")));
            }
            excludeColumn();
            //字符替换信息
            str = getLableContent(group, LableEnum.REPLACESTR);
            if (StringUtils.isNotEmpty(str)) {
                replaceMap.putAll(JSON.parseObject(str));
            }
            //去除配置信息
            templateSql = templateSql.replace(LableEnum.CONFIG.getLable(group), "");
            if (templateSql.indexOf("$n") == 0) {
                templateSql = templateSql.substring(2);
            }
        }
        String replaceStr = templateSql.replace("[", LableEnum.ARR.getStart()).replace("]", LableEnum.ARR.getEnd()).replace("\n", "$n");
        String sql = replaceStr;
        //字段去重
        if (replaceStr.contains("$noDuplicate")) {
            distinctColumn();
        }
        //匹配集合
        Pattern p = Pattern.compile(LableEnum.ARR.getLable(regex));
        Matcher m = p.matcher(replaceStr);
        while (m.find()) {
            String group = m.group(1);
            String replaceGroup = String.format("[%s]", group);
            if (!CollectionUtils.isEmpty(tableList)) {
                //分割符
                String spiltStr = replaceGroup.contains("$noSplit") ? "" : ",";
                int start = replaceGroup.contains("$noFirstColumn") ? 1 : 0;
                //列拼接
                if (group.contains("$column") && !CollectionUtils.isEmpty(tableColumnList)) {
                    String[] strs = new String[tableColumnList.size()];
                    for (int i = start; i < strs.length; i++) {
                        TableColumn tableColumn = tableColumnList.get(i);
                        String str = this.findString(tableColumnList.size(),group,i);
                        strs[i] = getColumnStr(str, tableColumn) ;
                    }
                    replaceGroup = StringUtils.join(Arrays.asList(strs), spiltStr);
                    //表连接拼接
                } else if (group.contains("$connect") && !CollectionUtils.isEmpty(tableConnectList)) {
                    String[] strs = new String[tableConnectList.size()];
                    for (int i = 0; i < strs.length; i++) {
                        TableConnection tableConnection = tableConnectList.get(i);
                        String[] split = tableConnection.getName().replace(".", "=").split("=");
                        strs[i] = group.replace("$connect",
                                String.format("%s.%s=%s.%s", getTableAlias(split[0]), split[1], getTableAlias(split[2]), split[3]));
                    }
                    replaceGroup = StringUtils.join(Arrays.asList(strs), "$n  and ");
                    //表拼接
                } else if (group.contains("$tableName")) {
                    String[] strs = new String[tableList.size()];
                    for (int i = 0; i < strs.length; i++) {
                        TableColumn tableColumn = tableList.get(i);
                        String str = this.findString(tableList.size(),group,i);
                        strs[i] = getTableStr(str, tableColumn);
                    }
                    replaceGroup = StringUtils.join(Arrays.asList(strs
 ), spiltStr);
                }
            }
            sql = sql.replace(LableEnum.ARR.getLable(group), replaceGroup);
        }
        if (!CollectionUtils.isEmpty(tableList)) {
            TableColumn table = tableList.get(0);
            sql = getTableStr(sql, table);
            if (!CollectionUtils.isEmpty(tableColumnList)) {
                TableColumn tableColumn = tableColumnList.get(0);
                sql = getColumnStr(sql, tableColumn);
            }
        }
        //字符替换
        for (Map.Entry<String, String> entry : ((Map<String, String>) replaceMap).entrySet()) {
            sql = sql.replace(entry.getKey(), entry.getValue());
        }
        return sql;
    }

    /**
     * 移除列
     */
    private void excludeColumn() {
        if (CollectionUtils.isEmpty(tableColumnList)) return;
        List<TableColumn> list = new ArrayList<>();
        for (TableColumn tableColumn : tableColumnList) {
            if (!excludeColumn.contains(tableColumn.getColumnName()) && (cludeColumn.isEmpty() || cludeColumn.contains(tableColumn.getColumnName()))) {
                list.add(tableColumn);
            }
        }
        tableColumnList = list;
    }

    /**
     * 字段去重
     */
    private void distinctColumn() {
        if (CollectionUtils.isEmpty(tableColumnList)) return;
        List<TableColumn> list = new ArrayList<>();
        List<String> keys = new ArrayList<>();
        for (TableColumn tableColumn : tableColumnList) {
            if (!keys.contains(tableColumn.getColumnName())) {
                list.add(tableColumn);
            }
            keys.add(tableColumn.getColumnName());
        }
        tableColumnList = list;
    }

    @JsonIgnore
    @JSONField(serialize = false)
    public String getTableAlias(String tableName) {
        if (CollectionUtils.isEmpty(tableList)) return "";
        String tableAlias = "";
        for (int i = 0; i < tableList.size(); i++) {
            TableColumn tableColumn = tableList.get(i);
            if (tableColumn.getTableName().equals(tableName)) {
                tableAlias = "T" + i;
            }
        }
        return tableAlias;
    }

    /**
     * 返回列字段
     *
     * @param str
     * @param tableColumn
     * @return
     */
    @JsonIgnore
    @JSONField(serialize = false)
    public String getColumnStr(String str, TableColumn tableColumn) {
        if (StringUtils.isEmpty(str)) return str;
        //获得类型比对
        Gegentypus gegentypus = gegentypusMap.get(tableColumn.getDataType());
        if (gegentypus == null) gegentypus = GegentypusEnum.getGegentypus(tableColumn.getDataType());
        String jdbcType = gegentypus == null ? "" : gegentypus.getJdbcType();
        String javaType = gegentypus == null ? "" : gegentypus.getJavaType();
        String columnName = tableColumn.getColumnName();
        String camel = CamelNamingUtil.fromUnderlineToCamel(str.contains("$noType") ?
                StringUtil.replaceFirst(columnName, tableColumn.getDataTypeSub() + "_", "") : columnName);
        String trimName = "String".equals(javaType) ? String.format("%s == null ? null : %s.trim()", camel, camel) : camel;
        //字符替换
        str = str.replace("$columnAlias", camel)
                .replace("$tableAlias", getTableAlias(tableColumn.getTableName()))
                .replace("$columnCn", tableColumn.getComments())
                .replace("$column", columnName)
                .replace("$jdbcType", jdbcType)
                .replace("$javaType", javaType)
                .replace("$typeSub", tableColumn.getDataTypeSub())
                .replace("$type", tableColumn.getDataType())
                .replace("$noFirstColumn", "")
                .replace("$firstUpperColumn", StringUtil.firstChar2Upper(camel))
                .replace("$trimColumnAlias", trimName)
                .replace("$noType", "")
                .replace("$noDuplicate", "")
        ;
        return getCommonStr(str);
    }

    /**
     * 返回列字段
     *
     * @param str
     * @param tableColumn
     * @return
     */
    @JsonIgnore
    @JSONField(serialize = false)
    public String getTableStr(String str, TableColumn tableColumn) {
        if (StringUtils.isEmpty(str)) return str;
        String camel = CamelNamingUtil.fromUnderlineToCamel(
                str.contains("$noTableType") ? StringUtil.replaceFirstAll(tableColumn.getTableName(), "T_", "P_", "A_") : tableColumn.getTableName());
        str = str.replace("$tableAlias", getTableAlias(tableColumn.getTableName()))
                .replace("$tableCamelName", camel)
                .replace("$tableNameCn", tableColumn.getName().replace("(", "").replace(")", "").replace(tableColumn.getTableName(), ""))
                .replace("$tableName", tableColumn.getTableName())
                .replace("$noTableType", "")
        ;
        return getCommonStr(str);
    }

    /**
     * 返回通用字段
     *
     * @param str
     * @return
     */
    @JsonIgnore
    @JSONField(serialize = false)
    public String getCommonStr(String str) {
        if (StringUtils.isEmpty(str)) return str;
        return str.replace("$noSplit", "")
                .replace("$lastNo", "")
                .replace("$submeter", "")
                ;
    }

    public  String findString(int size,String group,int i) {
            String str = null;
            if (i == size - 1 && group.contains("$lastNo")) {
                str = group.substring(0, group.indexOf("$lastNo"));
            } else {
                str = group;
            }
            return str;
    }

}
