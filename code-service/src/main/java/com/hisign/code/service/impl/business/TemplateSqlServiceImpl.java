package com.hisign.code.service.impl.business;

import com.hisign.code.api.business.TemplateSqlService;
import com.hisign.code.model.business.TemplateSql;
import com.hisign.code.persist.mapper.business.TemplateSqlMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 模版语句接口实现类
 * @author jiangpeng
 * @since 2017/05/25 13:55
 */
@Service("templateSqlService")
public class TemplateSqlServiceImpl implements TemplateSqlService {

    /**
     *模版语句mapper
     */
    @Resource
    public TemplateSqlMapper templateSqlMapper;

    /**
     * 获取模版语句列表信息
     * @param templateSql 模版语句查询条件
     * @return 模版语句列表信息
     * @throws Exception
     */
    public List<TemplateSql> findTemplateSqlList(TemplateSql templateSql) throws Exception {
        return templateSqlMapper.findTemplateSqlList(templateSql);
    }

    /**
     * 获取模版语句列表信息数量
     * @param templateSql 模版语句查询条件
     * @return 模版语句列表信息数量
     * @throws Exception
     */
    public int findTemplateSqlListForCount(TemplateSql templateSql) throws Exception {
        return templateSqlMapper.findTemplateSqlListForCount(templateSql);
    }

    /**
     * 删除模版语句
     * @param templateSql 模版语句信息
     * @throws Exception
     */
    public void deleteTemplateSql(TemplateSql templateSql) throws Exception {
        templateSqlMapper.deleteTemplateSql(templateSql);
    }

    /**
     * 修改模版语句信息
     * @param templateSql 模版语句信息
     * @throws Exception
     */
    public void updateTemplateSql(TemplateSql templateSql) throws Exception {
        templateSqlMapper.updateTemplateSql(templateSql);
    }

    /**
     * 新增模版语句
     * @param templateSql 模版语句信息
     * @return 模版语句编号
     * @throws Exception
     */
    public String insertTemplateSql(TemplateSql templateSql) throws Exception {
        templateSqlMapper.insertTemplateSql(templateSql);
        return null;
    }

    /**
     * 获得模版语句信息
     * @param templateSql 模版语句信息
     * @return 模版语句信息
     * @throws Exception
     */
    public TemplateSql findTemplateSqlInfo(TemplateSql templateSql) throws Exception {
        return templateSqlMapper.findTemplateSqlInfo(templateSql);
    }

    @Override
    public List<TemplateSql> findAllUser() throws Exception {
        return templateSqlMapper.findAllUser();
    }

}
