package com.hisign.code.persist.mapper.database;

import com.hisign.code.model.database.TemplateSql;
import java.util.List;

/**
 * 模版语句mapper
 * @author jiangpeng
 * @since 2017/05/25 13:55
 */
public interface TemplateSqlMapper {

    /**
     * 获取模版语句列表信息
     * @param templateSql 模版语句查询条件
     * @return 模版语句列表信息
     */
    List<TemplateSql> findTemplateSqlList(TemplateSql templateSql);

    /**
     * 获取模版语句列表信息数量
     * @param templateSql 模版语句查询条件
     * @return 模版语句列表信息数量
     */
    int findTemplateSqlListForCount(TemplateSql templateSql);

    /**
     * 删除模版语句
     * @param templateSql 模版语句信息
     */
    void deleteTemplateSql(TemplateSql templateSql);

    /**
     * 修改模版语句信息
     * @param templateSql 模版语句信息
     */
    void updateTemplateSql(TemplateSql templateSql);

    /**
     * 新增模版语句
     * @param templateSql 模版语句信息
     */
    void insertTemplateSql(TemplateSql templateSql);

    /**
     * 获得模版语句信息
     * @param templateSql 模版语句信息
     * @return 模版语句信息
     */
    TemplateSql findTemplateSqlInfo(TemplateSql templateSql);

    List<TemplateSql> findAllUser();
}
