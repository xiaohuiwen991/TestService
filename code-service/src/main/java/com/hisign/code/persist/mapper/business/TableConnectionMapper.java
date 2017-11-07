package com.hisign.code.persist.mapper.business;

import com.hisign.code.model.business.TableConnection;
import java.util.List;

/**
 * 表连接mapper
 * @author jiangpeng
 * @since 2017/05/27 14:49
 */
public interface TableConnectionMapper {

    /**
     * 获取表连接列表信息
     * @param tableConnection 表连接查询条件
     * @return 表连接列表信息
     */
    List<TableConnection> findTableConnectionList(TableConnection tableConnection);

    /**
     * 获取表连接列表信息数量
     * @param tableConnection 表连接查询条件
     * @return 表连接列表信息数量
     */
    int findTableConnectionListForCount(TableConnection tableConnection);

    /**
     * 删除表连接
     * @param tableConnection 表连接信息
     */
    void deleteTableConnection(TableConnection tableConnection);

    /**
     * 修改表连接信息
     * @param tableConnection 表连接信息
     */
    void updateTableConnection(TableConnection tableConnection);

    /**
     * 新增表连接
     * @param tableConnection 表连接信息
     */
    void insertTableConnection(TableConnection tableConnection);

    /**
     * 获得表连接信息
     * @param tableConnection 表连接信息
     * @return 表连接信息
     */
    TableConnection findTableConnectionInfo(TableConnection tableConnection);

}
