package com.hisign.code.api.business;

import com.hisign.code.model.database.TableConnection;
import java.util.List;

/**
 * 表连接接口
 * @author jiangpeng
 * @since 2017/05/27 14:49
 */
public interface TableConnectionService {

    /**
     * 获取表连接列表信息
     * @param tableConnection 表连接查询条件
     * @return 表连接列表信息
     * @throws Exception
     */
    List<TableConnection> findTableConnectionList(TableConnection tableConnection) throws Exception;

    /**
     * 获取表连接列表信息数量
     * @param tableConnection 表连接查询条件
     * @return 表连接列表信息数量
     * @throws Exception
     */
    int findTableConnectionListForCount(TableConnection tableConnection) throws Exception;

    /**
     * 删除表连接
     * @param tableConnection 表连接信息
     * @throws Exception
     */
    void deleteTableConnection(TableConnection tableConnection) throws Exception;

    /**
     * 修改表连接信息
     * @param tableConnection 表连接信息
     * @throws Exception
     */
    void updateTableConnection(TableConnection tableConnection) throws Exception;

    /**
     * 新增表连接
     * @param tableConnection 表连接信息
     * @return 表连接编号
     * @throws Exception
     */
    String insertTableConnection(TableConnection tableConnection) throws Exception;

    /**
     * 获得表连接信息
     * @param tableConnection 表连接信息
     * @return 表连接信息
     * @throws Exception
     */
    TableConnection findTableConnectionInfo(TableConnection tableConnection) throws Exception;

}
