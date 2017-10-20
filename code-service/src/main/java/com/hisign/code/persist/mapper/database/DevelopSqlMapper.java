package com.hisign.code.persist.mapper.database;

import com.hisign.code.model.database.DevelopSql;
import java.util.List;

/**
 * 开发语句mapper
 * @author jiangpeng
 * @since 2017/05/26 09:41
 */
public interface DevelopSqlMapper {

    /**
     * 获取开发语句列表信息
     * @param developSql 开发语句查询条件
     * @return 开发语句列表信息
     */
    List<DevelopSql> findDevelopSqlList(DevelopSql developSql);

    /**
     * 获取开发语句列表信息数量
     * @param developSql 开发语句查询条件
     * @return 开发语句列表信息数量
     */
    int findDevelopSqlListForCount(DevelopSql developSql);

    /**
     * 删除开发语句
     * @param developSql 开发语句信息
     */
    void deleteDevelopSql(DevelopSql developSql);

    /**
     * 修改开发语句信息
     * @param developSql 开发语句信息
     */
    void updateDevelopSql(DevelopSql developSql);

    /**
     * 新增开发语句
     * @param developSql 开发语句信息
     */
    void insertDevelopSql(DevelopSql developSql);

    /**
     * 获得开发语句信息
     * @param developSql 开发语句信息
     * @return 开发语句信息
     */
    DevelopSql findDevelopSqlInfo(DevelopSql developSql);

}
