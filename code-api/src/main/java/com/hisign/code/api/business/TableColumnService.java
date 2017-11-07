package com.hisign.code.api.business;

import com.hisign.code.model.business.TableColumn;
import java.util.List;

/**
 * 字段信息接口
 * @author jiangpeng
 * @since 2017/05/24 17:27
 */
public interface TableColumnService {

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
