package com.hisign.code.service.impl.database;

import com.hisign.code.api.database.ConnectionInfoService;
import com.hisign.code.model.database.ConnectionInfo;
import com.hisign.code.model.database.UserConnection;
import com.hisign.code.persist.mapper.database.ConnectionInfoMapper;
import com.hisign.code.persist.mapper.database.UserConnectionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 数据库连接接口实现类
 * @author jiangpeng
 * @since 2017/05/23 18:19
 */
@Service("connectionInfoService")
public class ConnectionInfoServiceImpl implements ConnectionInfoService {

    /**
     *数据库连接mapper
     */
    @Resource
    public ConnectionInfoMapper connectionInfoMapper;

    @Resource
    public UserConnectionMapper userConnectionMapper;

    /**
     * 获取数据库连接列表信息
     * @param connectionInfo 数据库连接查询条件
     * @return 数据库连接列表信息
     * @throws Exception
     */
    public List<ConnectionInfo> findConnectionInfoList(ConnectionInfo connectionInfo) throws Exception {
        return connectionInfoMapper.findConnectionInfoList(connectionInfo);
    }

    /**
     * 获取数据库连接列表信息数量
     * @param connectionInfo 数据库连接查询条件
     * @return 数据库连接列表信息数量
     * @throws Exception
     */
    public int findConnectionInfoListForCount(ConnectionInfo connectionInfo) throws Exception {
        return connectionInfoMapper.findConnectionInfoListForCount(connectionInfo);
    }

    /**
     * 删除数据库连接
     * @param connectionInfo 数据库连接信息
     * @throws Exception
     */
    public void deleteConnectionInfo(ConnectionInfo connectionInfo) throws Exception {
        connectionInfoMapper.deleteConnectionInfo(connectionInfo);
    }

    /**
     * 修改数据库连接信息
     * @param connectionInfo 数据库连接信息
     * @throws Exception
     */
    public void updateConnectionInfo(ConnectionInfo connectionInfo) throws Exception {
        connectionInfoMapper.updateConnectionInfo(connectionInfo);
    }

    /**
     * 新增数据库连接
     * @param connectionInfo 数据库连接信息
     * @return 数据库连接编号
     * @throws Exception
     */
    public String insertConnectionInfo(ConnectionInfo connectionInfo) throws Exception {
        connectionInfoMapper.insertConnectionInfo(connectionInfo);
        return null;
    }

    /**
     * 获得数据库连接信息
     * @param connectionInfo 数据库连接信息
     * @return 数据库连接信息
     * @throws Exception
     */
    public ConnectionInfo findConnectionInfoInfo(ConnectionInfo connectionInfo) throws Exception {
        return connectionInfoMapper.findConnectionInfoInfo(connectionInfo);
    }

    /**
     * 连接数据库
     * @param connectionInfo
     * @throws Exception
     */
    @Override
    public void connect(ConnectionInfo connectionInfo) throws Exception {
        UserConnection userConnection = new UserConnection(connectionInfo.getUser().getUserName(), connectionInfo.getName());
        userConnectionMapper.deleteUserConnection(userConnection);
        userConnectionMapper.insertUserConnection(userConnection);
    }

}
