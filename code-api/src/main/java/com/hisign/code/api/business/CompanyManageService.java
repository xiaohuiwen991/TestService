package com.hisign.code.api.business;

import com.hisign.code.model.business.CompanyInfo;
import java.util.List;

/**
 * 数据库连接接口
 * @author xiaohuiwen
 * @since 2017/05/23 18:19
 */
public interface CompanyManageService {

    /**
     * 获取数据库连接列表信息
     * @param connectionInfo 数据库连接查询条件
     * @return 数据库连接列表信息
     * @throws Exception
     */
    List<CompanyInfo> findConnectionInfoList(CompanyInfo connectionInfo) throws Exception;

    /**
     * 获取数据库连接列表信息数量
     * @param connectionInfo 数据库连接查询条件
     * @return 数据库连接列表信息数量
     * @throws Exception
     */
    int findConnectionInfoListForCount(CompanyInfo connectionInfo) throws Exception;

    /**
     * 删除数据库连接
     * @param connectionInfo 数据库连接信息
     * @throws Exception
     */
    void deleteConnectionInfo(CompanyInfo connectionInfo) throws Exception;

    /**
     * 修改数据库连接信息
     * @param connectionInfo 数据库连接信息
     * @throws Exception
     */
    void updateConnectionInfo(CompanyInfo connectionInfo) throws Exception;

    /**
     * 新增数据库连接
     * @param connectionInfo 数据库连接信息
     * @return 数据库连接编号
     * @throws Exception
     */
    String insertConnectionInfo(CompanyInfo connectionInfo) throws Exception;

    /**
     * 获得数据库连接信息
     * @param connectionInfo 数据库连接信息
     * @return 数据库连接信息
     * @throws Exception
     */
    CompanyInfo findConnectionInfoInfo(CompanyInfo connectionInfo) throws Exception;

    /**
     * 连接数据库
     * @param connectionInfo
     * @throws Exception
     */
    void connect(CompanyInfo connectionInfo) throws Exception;
}
