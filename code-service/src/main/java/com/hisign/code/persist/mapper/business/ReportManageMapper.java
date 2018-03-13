package com.hisign.code.persist.mapper.business;

import com.hisign.code.model.business.ReportInfo;
import com.hisign.code.model.business.TableConnection;
import java.util.List;

/**
 * 表连接mapper
 * @author xiaohuiwen
 * @since 2017/05/27 14:49
 */
public interface ReportManageMapper {

    /**
     * 获取表连接列表信息
     * @param reportInfo 表连接查询条件
     * @return 表连接列表信息
     */
    List<TableConnection> queryReportList(ReportInfo reportInfo);

    /**
     * 获取表连接列表信息数量
     * @param reportInfo 表连接查询条件
     * @return 表连接列表信息数量
     */
    int queryReportListForCount(ReportInfo reportInfo);

    /**
     * 查询检测报告信息
     * @param reportinfo
     * @throws Exception
     */
    ReportInfo queryReportInfoById(ReportInfo reportinfo);

    /**
     * 删除表连接
     * @param reportinfo 表连接信息
     */
    void deleteReportInfo(ReportInfo reportinfo);

    /**
     * 修改表连接信息
     * @param reportinfo 表连接信息
     */
    void updateReportInfo(ReportInfo reportinfo);

    /**
     * 新增表连接
     * @param reportinfo 表连接信息
     */
    void insertReportInfo(ReportInfo reportinfo);

    /**
     * 查询系统参数
     * @param str 表连接信息
     */
    String querySysParam(String str);

    /**
     * 获取pdf路径
     * @param str
     * @return
     */
    String findPdfPath(String str);

    /**
     * 获取pdf路径
     * @param str
     * @return
     */
    ReportInfo findPicInfo(String str);

    /**
     * 获得表连接信息
     * @param tableConnection 表连接信息
     * @return 表连接信息
     */
    TableConnection findTableConnectionInfo(TableConnection tableConnection);

}
