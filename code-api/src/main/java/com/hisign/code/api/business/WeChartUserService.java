package com.hisign.code.api.business;

import com.hisign.code.model.business.DevelopSql;
import com.hisign.code.model.business.WeChartUserInfo;

import java.util.List;

/**
 * 开发语句接口
 * @author xiaohuiwen
 * @since 2017/05/26 09:41
 */
public interface WeChartUserService {

    /**
     * 获取开发语句列表信息
     * @param weChartUserInfo 开发语句查询条件
     * @return 开发语句列表信息
     * @throws Exception
     */
    List<DevelopSql> findDevelopSqlList(WeChartUserInfo weChartUserInfo) throws Exception;

    /**
     * 获取开发语句列表信息数量
     * @param weChartUserInfo 开发语句查询条件
     * @return 开发语句列表信息数量
     * @throws Exception
     */
    int findDevelopSqlListForCount(WeChartUserInfo weChartUserInfo) throws Exception;

    /**
     * 删除开发语句
     * @param weChartUserInfo 开发语句信息
     * @throws Exception
     */
    void deleteDevelopSql(WeChartUserInfo weChartUserInfo) throws Exception;

    /**
     * 修改开发语句信息
     * @param weChartUserInfo 开发语句信息
     * @throws Exception
     */
    void updateDevelopSql(WeChartUserInfo weChartUserInfo) throws Exception;

    /**
     * 新增开发语句
     * @param weChartUserInfo 开发语句信息
     * @return 开发语句编号
     * @throws Exception
     */
    String insertDevelopSql(WeChartUserInfo weChartUserInfo) throws Exception;

    /**
     * 获得开发语句信息
     * @param weChartUserInfo 开发语句信息
     * @return 开发语句信息
     * @throws Exception
     */
    WeChartUserInfo findDevelopSqlInfo(WeChartUserInfo weChartUserInfo) throws Exception;

    /**
     * 启用用户信息
     * @param weChartUserInfo
     * @throws Exception
     */
    void enableUserStatus(WeChartUserInfo weChartUserInfo) throws Exception;

    /**
     * 手机端更新用户信息
     * @param weChartUserInfo
     * @throws Exception
     */
    void registerUpdateWebPageUser(WeChartUserInfo weChartUserInfo) throws Exception;

}
