package com.hisign.code.service.impl.business;

import com.hisign.code.model.business.ReportInfo;
import com.hisign.code.model.business.TableConnection;
import com.hisign.code.persist.mapper.business.ReportManageMapper;
import com.hisign.code.api.business.ReportManageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * 表连接接口实现类
 * @author xiaohuiwen
 * @since 2017/05/27 14:49
 */
@Service("reportManageService")
public class ReportManageServiceImpl implements ReportManageService {

    /**
     *表连接mapper
     */
    @Resource
    public ReportManageMapper reportManageMapper;

    /**
     * 获取表连接列表信息
     * @param reportInfo 表连接查询条件
     * @return 表连接列表信息
     * @throws Exception
     */
    public List<TableConnection> queryReportList(ReportInfo reportInfo) throws Exception {
        return reportManageMapper.queryReportList(reportInfo);
    }

    /**
     * 获取表连接列表信息数量
     * @param reportInfo 表连接查询条件
     * @return 表连接列表信息数量
     * @throws Exception
     */
    public int queryReportListForCount(ReportInfo reportInfo) throws Exception {
        return reportManageMapper.queryReportListForCount(reportInfo);
    }

    /**
     * 查询检测报告信息
     * @param reportinfo
     * @throws Exception
     */
    public ReportInfo queryReportInfoById(ReportInfo reportinfo) throws Exception {
        ReportInfo info = reportManageMapper.queryReportInfoById(reportinfo);
        return info;
    }

    /**
     * 删除表连接
     * @param reportinfo 表连接信息
     * @throws Exception
     */
    public void deleteReportInfo(ReportInfo reportinfo) throws Exception {
        reportManageMapper.deleteReportInfo(reportinfo);
    }

    /**
     * 修改表连接信息
     * @param reportinfo 表连接信息
     * @throws Exception
     */
    public void updateReportInfo(ReportInfo reportinfo) throws Exception {
        reportManageMapper.updateReportInfo(reportinfo);
    }

    /**
     * 新增表连接
     * @param reportinfo 表连接信息
     * @return 表连接编号
     * @throws Exception
     */
    public void insertReportInfo(ReportInfo reportinfo) throws Exception {
        reportManageMapper.insertReportInfo(reportinfo);
    }

    /**
     * 查询系统参数
     * @param str 表连接信息
     */
    public String querySysParam(String str) throws Exception {
        String param = reportManageMapper.querySysParam(str);
        return param;
    }

    /**
     * 获得pdf路径
     * @param id
     * @return
     * @throws Exception
     */
    public String findPdfPath(String id) throws Exception {
        String param = reportManageMapper.findPdfPath(id);
        return param;
    }

    /**
     * 获得pic信息
     * @param id
     * @return
     * @throws Exception
     */
    public ReportInfo findPicInfo(String id) throws Exception {
        ReportInfo reportinfo = reportManageMapper.findPicInfo(id);
        return reportinfo;
    }

    /**
     * 获得表连接信息
     * @param tableConnection 表连接信息
     * @return 表连接信息
     * @throws Exception
     */
    public TableConnection findTableConnectionInfo(TableConnection tableConnection) throws Exception {
        return reportManageMapper.findTableConnectionInfo(tableConnection);
    }

}
