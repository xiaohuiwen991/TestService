package com.hisign.code.persist.mapper.database;

import com.hisign.code.model.database.ConnectionInfo;
import java.util.List;

/**
 * 数据库连接mapper
 * @author jiangpeng
 * @since 2017/05/23 18:19
 */
public interface ConnectionInfoMapper {

    /**
     * 获取数据库连接列表信息
     * @param connectionInfo 数据库连接查询条件
     * @return 数据库连接列表信息
     */
    List<ConnectionInfo> findConnectionInfoList(ConnectionInfo connectionInfo);

    /**
     * 获取数据库连接列表信息数量
     * @param connectionInfo 数据库连接查询条件
     * @return 数据库连接列表信息数量
     */
    int findConnectionInfoListForCount(ConnectionInfo connectionInfo);

    /**
     * 删除数据库连接
     * @param connectionInfo 数据库连接信息
     */
    void deleteConnectionInfo(ConnectionInfo connectionInfo);

    /**
     * 修改数据库连接信息
     * @param connectionInfo 数据库连接信息
     */
    void updateConnectionInfo(ConnectionInfo connectionInfo);

    /**
     * 新增数据库连接
     * @param connectionInfo 数据库连接信息
     */
    void insertConnectionInfo(ConnectionInfo connectionInfo);

    /**
     * 获得数据库连接信息
     * @param connectionInfo 数据库连接信息
     * @return 数据库连接信息
     */
    ConnectionInfo findConnectionInfoInfo(ConnectionInfo connectionInfo);

}
