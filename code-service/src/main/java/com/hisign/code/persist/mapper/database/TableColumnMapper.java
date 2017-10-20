package com.hisign.code.persist.mapper.database;

import com.hisign.code.model.database.TableColumn;
import java.util.List;

/**
 * 字段信息mapper
 * @author jiangpeng
 * @since 2017/05/24 17:27
 */
public interface TableColumnMapper {

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
