package com.hisign.code.service.impl.database;

import com.hisign.code.model.database.TableConnection;
import com.hisign.code.persist.mapper.database.TableConnectionMapper;
import com.hisign.code.api.database.TableConnectionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * 表连接接口实现类
 * @author jiangpeng
 * @since 2017/05/27 14:49
 */
@Service("tableConnectionService")
public class TableConnectionServiceImpl implements TableConnectionService {

    /**
     *表连接mapper
     */
    @Resource
    public TableConnectionMapper tableConnectionMapper;

    /**
     * 获取表连接列表信息
     * @param tableConnection 表连接查询条件
     * @return 表连接列表信息
     * @throws Exception
     */
    public List<TableConnection> findTableConnectionList(TableConnection tableConnection) throws Exception {
        return tableConnectionMapper.findTableConnectionList(tableConnection);
    }

    /**
     * 获取表连接列表信息数量
     * @param tableConnection 表连接查询条件
     * @return 表连接列表信息数量
     * @throws Exception
     */
    public int findTableConnectionListForCount(TableConnection tableConnection) throws Exception {
        return tableConnectionMapper.findTableConnectionListForCount(tableConnection);
    }

    /**
     * 删除表连接
     * @param tableConnection 表连接信息
     * @throws Exception
     */
    public void deleteTableConnection(TableConnection tableConnection) throws Exception {
        tableConnectionMapper.deleteTableConnection(tableConnection);
    }

    /**
     * 修改表连接信息
     * @param tableConnection 表连接信息
     * @throws Exception
     */
    public void updateTableConnection(TableConnection tableConnection) throws Exception {
        tableConnectionMapper.updateTableConnection(tableConnection);
    }

    /**
     * 新增表连接
     * @param tableConnection 表连接信息
     * @return 表连接编号
     * @throws Exception
     */
    public String insertTableConnection(TableConnection tableConnection) throws Exception {
        String id = UUID.randomUUID().toString().replaceAll("-","");
        tableConnection.setId(id);
        tableConnectionMapper.insertTableConnection(tableConnection);
        return id;
    }

    /**
     * 获得表连接信息
     * @param tableConnection 表连接信息
     * @return 表连接信息
     * @throws Exception
     */
    public TableConnection findTableConnectionInfo(TableConnection tableConnection) throws Exception {
        return tableConnectionMapper.findTableConnectionInfo(tableConnection);
    }

}
