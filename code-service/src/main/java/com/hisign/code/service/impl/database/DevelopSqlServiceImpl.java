package com.hisign.code.service.impl.database;

import com.hisign.code.api.business.DevelopSqlService;
import com.hisign.code.model.database.DevelopSql;
import com.hisign.code.persist.mapper.database.DevelopSqlMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 开发语句接口实现类
 * @author jiangpeng
 * @since 2017/05/26 09:41
 */
@Service("developSqlService")
public class DevelopSqlServiceImpl implements DevelopSqlService {

    /**
     *开发语句mapper
     */
    @Resource
    public DevelopSqlMapper developSqlMapper;

    /**
     * 获取开发语句列表信息`
     * @param developSql 开发语句查询条件
     * @return 开发语句列表信息
     * @throws Exception
     */
    public List<DevelopSql> findDevelopSqlList(DevelopSql developSql) throws Exception {
        return developSqlMapper.findDevelopSqlList(developSql);
    }

    /**
     * 获取开发语句列表信息数量
     * @param developSql 开发语句查询条件
     * @return 开发语句列表信息数量
     * @throws Exception
     */
    public int findDevelopSqlListForCount(DevelopSql developSql) throws Exception {
        return developSqlMapper.findDevelopSqlListForCount(developSql);
    }

    /**
     * 删除开发语句
     * @param developSql 开发语句信息
     * @throws Exception
     */
    public void deleteDevelopSql(DevelopSql developSql) throws Exception {
        developSqlMapper.deleteDevelopSql(developSql);
    }

    /**
     * 修改开发语句信息
     * @param developSql 开发语句信息
     * @throws Exception
     */
    public void updateDevelopSql(DevelopSql developSql) throws Exception {
        developSqlMapper.updateDevelopSql(developSql);
    }

    /**
     * 新增开发语句
     * @param developSql 开发语句信息
     * @return 开发语句编号
     * @throws Exception
     */
    public String insertDevelopSql(DevelopSql developSql) throws Exception {
        developSqlMapper.insertDevelopSql(developSql);
        return null;
    }

    /**
     * 获得开发语句信息
     * @param developSql 开发语句信息
     * @return 开发语句信息
     * @throws Exception
     */
    public DevelopSql findDevelopSqlInfo(DevelopSql developSql) throws Exception {
        return developSqlMapper.findDevelopSqlInfo(developSql);
    }

}
