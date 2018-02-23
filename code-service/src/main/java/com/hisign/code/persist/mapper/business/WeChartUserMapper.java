package com.hisign.code.persist.mapper.business;

import com.hisign.code.model.business.DevelopSql;
import com.hisign.code.model.business.WeChartUserInfo;

import java.util.List;

/**
 * 开发语句mapper
 * @author xiaohuiwen
 * @since 2017/05/26 09:41
 */
public interface WeChartUserMapper {

    /**
     * 获取开发语句列表信息
     * @param weChartUserInfo 开发语句查询条件
     * @return 开发语句列表信息
     */
    List<DevelopSql> findDevelopSqlList(WeChartUserInfo weChartUserInfo);

    /**
     * 获取开发语句列表信息数量
     * @param weChartUserInfo 开发语句查询条件
     * @return 开发语句列表信息数量
     */
    int findDevelopSqlListForCount(WeChartUserInfo weChartUserInfo);

    /**
     * 删除开发语句
     * @param weChartUserInfo 开发语句信息
     */
    void deleteDevelopSql(WeChartUserInfo weChartUserInfo);

    /**
     * 修改开发语句信息
     * @param weChartUserInfo 开发语句信息
     */
    void updateDevelopSql(WeChartUserInfo weChartUserInfo);

    /**
     * 新增开发语句
     * @param weChartUserInfo 开发语句信息
     */
    void insertDevelopSql(WeChartUserInfo weChartUserInfo);

    /**
     * 获得开发语句信息
     * @param weChartUserInfo 开发语句信息
     * @return 开发语句信息
     */
    WeChartUserInfo findDevelopSqlInfo(WeChartUserInfo weChartUserInfo);

    /**
     * 启用用户信息
     * @param weChartUserInfo
     * @throws Exception
     */
    void enableUserStatus(WeChartUserInfo weChartUserInfo);

}
