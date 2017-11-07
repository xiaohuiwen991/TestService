package com.hisign.code.api.business;

import com.hisign.code.model.business.TemplateSql;
import java.util.List;

/**
 * 模版语句接口
 * @author jiangpeng
 * @since 2017/05/25 13:55
 */
public interface TemplateSqlService {

    /**
     * 获取模版语句列表信息
     * @param templateSql 模版语句查询条件
     * @return 模版语句列表信息
     * @throws Exception
     */
    List<TemplateSql> findTemplateSqlList(TemplateSql templateSql) throws Exception;

    /**
     * 获取模版语句列表信息数量
     * @param templateSql 模版语句查询条件
     * @return 模版语句列表信息数量
     * @throws Exception
     */
    int findTemplateSqlListForCount(TemplateSql templateSql) throws Exception;

    /**
     * 删除模版语句
     * @param templateSql 模版语句信息
     * @throws Exception
     */
    void deleteTemplateSql(TemplateSql templateSql) throws Exception;

    /**
     * 修改模版语句信息
     * @param templateSql 模版语句信息
     * @throws Exception
     */
    void updateTemplateSql(TemplateSql templateSql) throws Exception;

    /**
     * 新增模版语句
     * @param templateSql 模版语句信息
     * @return 模版语句编号
     * @throws Exception
     */
    String insertTemplateSql(TemplateSql templateSql) throws Exception;

    /**
     * 获得模版语句信息
     * @param templateSql 模版语句信息
     * @return 模版语句信息
     * @throws Exception
     */
    TemplateSql findTemplateSqlInfo(TemplateSql templateSql) throws Exception;

    List<TemplateSql> findAllUser() throws Exception;
}
