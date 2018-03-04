package com.hisign.code.persist.mapper.business;

import com.hisign.code.model.business.TableColumn;
import com.hisign.code.model.business.WeChartUserInfo;

import java.util.List;

/**
 * 字段信息mapper
 * @author xiaohuiwen
 * @since 2017/05/24 17:27
 */
public interface WeChatWebPageMapper {

    /**
     * 查询登录用户信息
     * @param webUser
     * @return
     */
    WeChartUserInfo queryLoginInfo(WeChartUserInfo webUser);

    /**
     * 获取手机端登录信息
     * @param weChartUserInfo
     * @return
     * @throws Exception
     */
    WeChartUserInfo queryLoginByIdWebPage(WeChartUserInfo weChartUserInfo);

    /**
     * web端用户注册
     * @param weChartUserInfo
     * @return
     * @throws Exception
     */
    void registerWebPageUser(WeChartUserInfo weChartUserInfo);

    /**
     * 查询消息写入用户信息
     */
    List<WeChartUserInfo> queryUserList();

    /**
     * 查询报告信息
     * @param id
     * @return
     */
    List<String> findReportInfo(String id);

    /**
     * 写入站内信消息
     * @param weChartUserInfo
     */
    void setMessageInfo(WeChartUserInfo weChartUserInfo);

    /**
     * 收件人信息写入
     * @param str
     */
    void setMessageReceiveBoxInfo(WeChartUserInfo weChartUserInfo);

    /**
     * 检查账号是否已经存在
     * @param weChartUserInfo
     * @return
     * @throws Exception
     */
    int checkUserName(WeChartUserInfo weChartUserInfo);

    /**
     * 获取字段信息列表信息
     * @param tableColumn 字段信息查询条件
     * @return 字段信息列表信息
     */
    List<TableColumn> findTableColumnList(TableColumn tableColumn);

    /**
     * 获取字段信息列表信息数量
     * @param tableColumn 字段信息查询条件
     * @return 字段信息列表信息数量
     */
    int findTableColumnListForCount(TableColumn tableColumn);

    List<TableColumn> findTableList(TableColumn tableColumn);

    int findTableListForCount(TableColumn tableColumn);

    List<TableColumn> findColumnList(TableColumn tableColumn);
}
