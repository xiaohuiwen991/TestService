package com.hisign.code.api.business;

import com.hisign.code.model.business.ReportInfo;
import com.hisign.code.model.business.TableConnection;
import java.util.List;

/**
 * 表连接接口
 * @author xiaohuiwen
 * @since 2017/05/27 14:49
 */
public interface ReportManageService {

    /**
     * 获取表连接列表信息
     * @param reportInfo 表连接查询条件
     * @return 表连接列表信息
     * @throws Exception
     */
    List<TableConnection> queryReportList(ReportInfo reportInfo) throws Exception;

    /**
     * 获取表连接列表信息数量
     * @param reportInfo 表连接查询条件
     * @return 表连接列表信息数量
     * @throws Exception
     */
    int queryReportListForCount(ReportInfo reportInfo) throws Exception;

    /**
     * 查询检测报告信息
     * @param reportinfo
     * @throws Exception
     */
    ReportInfo queryReportInfoById(ReportInfo reportinfo) throws Exception;

    /**
     * 删除表连接
     * @param reportinfo 表连接信息
     * @throws Exception
     */
    void deleteReportInfo(ReportInfo reportinfo) throws Exception;

    /**
     * 修改表连接信息
     * @param reportinfo 表连接信息
     * @throws Exception
     */
    void updateReportInfo(ReportInfo reportinfo) throws Exception;

    /**
     * 新增表连接
     * @param reportinfo 表连接信息
     * @return 表连接编号
     * @throws Exception
     */
    void insertReportInfo(ReportInfo reportinfo) throws Exception;

    /**
     * 查询系统参数
     * @param param
     * @return
     * @throws Exception
     */
    String querySysParam(String param) throws Exception;

    /**
     * 获得pic信息
     * @param id
     * @return
     * @throws Exception
     */
    ReportInfo findPicInfo(String id) throws Exception;

    /**
     * 获得pdf路径
     * @param id
     * @return
     * @throws Exception
     */
    String findPdfPath(String id) throws Exception;

    /**
     * 获得表连接信息
     * @param tableConnection 表连接信息
     * @return 表连接信息
     * @throws Exception
     */
    TableConnection findTableConnectionInfo(TableConnection tableConnection) throws Exception;

}
