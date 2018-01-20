package com.hisign.code.service.impl.business;

import com.hisign.code.api.business.TranslationDictService;
import com.hisign.code.api.business.WeChatWebPageService;
import com.hisign.code.model.business.TableColumn;
import com.hisign.code.model.business.WeChartUserInfo;
import com.hisign.code.persist.mapper.business.CompanyManageMapper;
import com.hisign.code.persist.mapper.business.WeChatWebPageMapper;
import com.hisign.multiDatabase.ILoadBean;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 字段信息接口实现类
 * @author xiaohuiwen
 * @since 2017/05/24 17:27
 */
@Service("weChatWebPageService")
public class WeChatWebPageServiceImpl implements WeChatWebPageService {

    /**
     *字段信息mapper
     */
    @Resource
    public WeChatWebPageMapper weChatWebPageMapper;

    @Resource
    public CompanyManageMapper companyManageMapper;

    @Resource
    public ILoadBean loadBean;

    @Resource
    public TranslationDictService translationDictService;

    /**
     * 配置日志信息
     */
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    /**
     * 获取手机端登录信息
     * @param weChartUserInfo
     * @return
     * @throws Exception
     */
    public WeChartUserInfo queryLoginInfo(WeChartUserInfo weChartUserInfo) throws Exception {
        WeChartUserInfo result = weChatWebPageMapper.queryLoginInfo(weChartUserInfo);
        return result;
    }

    public void handleTableList(List<TableColumn> list) throws Exception {
    }




    /**
     * 获取字段信息列表信息
     * @param tableColumn 字段信息查询条件
     * @return 字段信息列表信息
     * @throws Exception
     */
    public List<TableColumn> findTableColumnList(TableColumn tableColumn) throws Exception {
        SqlSession sqlSession = getSqlSession(tableColumn.getUser().getConnectionName());
        weChatWebPageMapper = sqlSession.getMapper(WeChatWebPageMapper.class);
        List<TableColumn> tableColumnList = weChatWebPageMapper.findTableColumnList(tableColumn);
        sqlSession.close();
        handleTableList(tableColumnList);
        return tableColumnList;
    }


    /**
     * 获取字段信息列表信息
     * @param tableColumn 字段信息查询条件
     * @return 字段信息列表信息
     * @throws Exception
     */
    public List<TableColumn> findTableColumnListOld(TableColumn tableColumn) throws Exception {
        return null;
    }

    /**
     * 获取字段信息列表信息数量
     * @param tableColumn 字段信息查询条件
     * @return 字段信息列表信息数量
     * @throws Exception
     */
    public int findTableColumnListForCount(TableColumn tableColumn) throws Exception {
        SqlSession sqlSession = getSqlSession(tableColumn.getUser().getConnectionName());
        weChatWebPageMapper = sqlSession.getMapper(WeChatWebPageMapper.class);
        int tableColumnListForCount = weChatWebPageMapper.findTableColumnListForCount(tableColumn);
        sqlSession.close();
        return tableColumnListForCount;
    }

    @Override
    public List<TableColumn> findTableList(TableColumn tableColumn) throws Exception {
        long time1 = System.currentTimeMillis();
        SqlSession sqlSession = getSqlSession(tableColumn.getUser().getConnectionName());
        weChatWebPageMapper = sqlSession.getMapper(WeChatWebPageMapper.class);
        long time2 = System.currentTimeMillis();
        logger.info("初始化表数据源花费[{}]毫秒", time2 - time1);
        List<TableColumn> tableList = weChatWebPageMapper.findTableList(tableColumn);
        long time3 = System.currentTimeMillis();
        logger.info("查询表信息花费[{}]毫秒", time3 - time2);
        sqlSession.close();
        return tableList;
    }

    @Override
    public int findTableListForCount(TableColumn tableColumn) throws Exception {
        long time1 = System.currentTimeMillis();
        SqlSession sqlSession = getSqlSession(tableColumn.getUser().getConnectionName());
        weChatWebPageMapper = sqlSession.getMapper(WeChatWebPageMapper.class);
        long time2 = System.currentTimeMillis();
        logger.info("初始化表数量数据源花费[{}]毫秒", time2 - time1);
        int tableListForCount = weChatWebPageMapper.findTableListForCount(tableColumn);
        sqlSession.close();
        return tableListForCount;
    }

    /**
     *
     * @param tableColumn
     * @return
     * @throws Exception
     */
    @Override
    public  List<TableColumn> findColumnList(TableColumn tableColumn) throws Exception {
        SqlSession sqlSession = getSqlSession(tableColumn.getUser().getConnectionName());
        weChatWebPageMapper = sqlSession.getMapper(WeChatWebPageMapper.class);
        List<TableColumn> tableColumnList = weChatWebPageMapper.findColumnList(tableColumn);
        sqlSession.close();
        return tableColumnList;
    }

    /**
     * 获得数据库连接
     * @param name
     * @return
     * @throws Exception
     */
    public SqlSession getSqlSession(String name) throws Exception {
        if(StringUtils.isEmpty(name)) name = "default";
//        CompanyInfo connectionInfo = companyManageMapper.findConnectionInfoInfo(new CompanyInfo(name));
//        SqlSessionFactory sqlSessionFactory = SqlSessionGetFactory.getConnection(connectionInfo.getSpringBeanInfo(), loadBean, SqlSessionFactory.class);
        return null;
    }

}
