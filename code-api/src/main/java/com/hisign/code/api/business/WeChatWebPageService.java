package com.hisign.code.api.business;

import com.hisign.code.model.business.TableColumn;
import com.hisign.code.model.business.WeChartUserInfo;

import java.util.List;

/**
 * 字段信息接口
 * @author xiaohuiwen
 * @since 2017/05/24 17:27
 */
public interface WeChatWebPageService {

    /**
     * 获取手机端登录信息
     * @param weChartUserInfo
     * @return
     * @throws Exception
     */
    public WeChartUserInfo queryLoginInfo(WeChartUserInfo weChartUserInfo) throws Exception;

    /**
     * web端用户注册
     * @param weChartUserInfo
     * @return
     * @throws Exception
     */
    public void registerWebPageUser(WeChartUserInfo weChartUserInfo) throws Exception;

    /**
     * 检查账号是否已经存在
     * @param weChartUserInfo
     * @return
     * @throws Exception
     */
    int checkUserName(WeChartUserInfo weChartUserInfo) throws Exception;

    /**
     * 获取字段信息列表信息
     * @param tableColumn 字段信息查询条件
     * @return 字段信息列表信息
     * @throws Exception
     */
    List<TableColumn> findTableColumnList(TableColumn tableColumn) throws Exception;

    /**
     * 获取字段信息列表信息数量
     * @param tableColumn 字段信息查询条件
     * @return 字段信息列表信息数量
     * @throws Exception
     */
    int findTableColumnListForCount(TableColumn tableColumn) throws Exception;

    /**
     *
     * @param tableColumn
     * @return
     * @throws Exception
     */
    List<TableColumn> findTableList(TableColumn tableColumn) throws Exception;

    /**
     *
     * @param tableColumn
     * @return
     * @throws Exception
     */
    int findTableListForCount(TableColumn tableColumn) throws Exception;

    /**
     *
     * @param tableColumn
     * @return
     * @throws Exception
     */
    List<TableColumn> findColumnList(TableColumn tableColumn) throws Exception;
}
