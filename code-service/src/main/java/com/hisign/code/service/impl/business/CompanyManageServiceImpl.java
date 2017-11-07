package com.hisign.code.service.impl.business;

import com.hisign.code.api.business.CompanyManageService;
import com.hisign.code.model.business.CompanyInfo;
import com.hisign.code.model.business.UserConnection;
import com.hisign.code.persist.mapper.business.CompanyManageMapper;
import com.hisign.code.persist.mapper.business.UserConnectionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 数据库连接接口实现类
 * @author xiaohuwien
 * @since 2017/05/23 18:19
 */
@Service("companyManageService")
public class CompanyManageServiceImpl implements CompanyManageService {

    /**
     *数据库连接mapper
     */
    @Resource
    public CompanyManageMapper companyManageMapper;

    @Resource
    public UserConnectionMapper userConnectionMapper;

    /**
     * 获取数据库连接列表信息
     * @param connectionInfo 数据库连接查询条件
     * @return 数据库连接列表信息
     * @throws Exception
     */
    public List<CompanyInfo> findConnectionInfoList(CompanyInfo connectionInfo) throws Exception {
        return companyManageMapper.findConnectionInfoList(connectionInfo);
    }

    /**
     * 获取数据库连接列表信息数量
     * @param connectionInfo 数据库连接查询条件
     * @return 数据库连接列表信息数量
     * @throws Exception
     */
    public int findConnectionInfoListForCount(CompanyInfo connectionInfo) throws Exception {
        return companyManageMapper.findConnectionInfoListForCount(connectionInfo);
    }

    /**
     * 删除数据库连接
     * @param connectionInfo 数据库连接信息
     * @throws Exception
     */
    public void deleteConnectionInfo(CompanyInfo connectionInfo) throws Exception {
        companyManageMapper.deleteConnectionInfo(connectionInfo);
    }

    /**
     * 修改数据库连接信息
     * @param connectionInfo 数据库连接信息
     * @throws Exception
     */
    public void updateConnectionInfo(CompanyInfo connectionInfo) throws Exception {
        companyManageMapper.updateConnectionInfo(connectionInfo);
    }

    /**
     * 新增数据库连接
     * @param connectionInfo 数据库连接信息
     * @return 数据库连接编号
     * @throws Exception
     */
    public String insertConnectionInfo(CompanyInfo connectionInfo) throws Exception {
        companyManageMapper.insertConnectionInfo(connectionInfo);
        return null;
    }

    /**
     * 获得数据库连接信息
     * @param connectionInfo 数据库连接信息
     * @return 数据库连接信息
     * @throws Exception
     */
    public CompanyInfo findConnectionInfoInfo(CompanyInfo connectionInfo) throws Exception {
        return companyManageMapper.findConnectionInfoInfo(connectionInfo);
    }

    /**
     * 连接数据库
     * @param connectionInfo
     * @throws Exception
     */
    @Override
    public void connect(CompanyInfo connectionInfo) throws Exception {
        UserConnection userConnection = new UserConnection(connectionInfo.getUser().getUserName(), connectionInfo.getName());
        userConnectionMapper.deleteUserConnection(userConnection);
        userConnectionMapper.insertUserConnection(userConnection);
    }

}
