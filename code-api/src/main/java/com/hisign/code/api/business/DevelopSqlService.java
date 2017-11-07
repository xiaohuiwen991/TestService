package com.hisign.code.api.business;

import com.hisign.code.model.database.DevelopSql;
import java.util.List;

/**
 * 开发语句接口
 * @author jiangpeng
 * @since 2017/05/26 09:41
 */
public interface DevelopSqlService {

    /**
     * 获取开发语句列表信息
     * @param developSql 开发语句查询条件
     * @return 开发语句列表信息
     * @throws Exception
     */
    List<DevelopSql> findDevelopSqlList(DevelopSql developSql) throws Exception;

    /**
     * 获取开发语句列表信息数量
     * @param developSql 开发语句查询条件
     * @return 开发语句列表信息数量
     * @throws Exception
     */
    int findDevelopSqlListForCount(DevelopSql developSql) throws Exception;

    /**
     * 删除开发语句
     * @param developSql 开发语句信息
     * @throws Exception
     */
    void deleteDevelopSql(DevelopSql developSql) throws Exception;

    /**
     * 修改开发语句信息
     * @param developSql 开发语句信息
     * @throws Exception
     */
    void updateDevelopSql(DevelopSql developSql) throws Exception;

    /**
     * 新增开发语句
     * @param developSql 开发语句信息
     * @return 开发语句编号
     * @throws Exception
     */
    String insertDevelopSql(DevelopSql developSql) throws Exception;

    /**
     * 获得开发语句信息
     * @param developSql 开发语句信息
     * @return 开发语句信息
     * @throws Exception
     */
    DevelopSql findDevelopSqlInfo(DevelopSql developSql) throws Exception;

}
