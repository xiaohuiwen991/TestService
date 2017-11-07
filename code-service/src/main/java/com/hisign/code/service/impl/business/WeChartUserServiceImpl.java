package com.hisign.code.service.impl.business;

import com.hisign.code.api.business.WeChartUserService;
import com.hisign.code.model.business.DevelopSql;
import com.hisign.code.model.business.WeChartUserInfo;
import com.hisign.code.persist.mapper.business.WeChartUserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 开发语句接口实现类
 * @author jiangpeng
 * @since 2017/05/26 09:41
 */
@Service("weChartUserService")
public class WeChartUserServiceImpl implements WeChartUserService {

    /**
     *开发语句mapper
     */
    @Resource
    public WeChartUserMapper weChartUserMapper;

    /**
     * 获取开发语句列表信息`
     * @param weChartUserInfo 开发语句查询条件
     * @return 开发语句列表信息
     * @throws Exception
     */
    public List<DevelopSql> findDevelopSqlList(WeChartUserInfo weChartUserInfo) throws Exception {
        return weChartUserMapper.findDevelopSqlList(weChartUserInfo);
    }

    /**
     * 获取开发语句列表信息数量
     * @param weChartUserInfo 开发语句查询条件
     * @return 开发语句列表信息数量
     * @throws Exception
     */
    public int findDevelopSqlListForCount(WeChartUserInfo weChartUserInfo) throws Exception {
        return weChartUserMapper.findDevelopSqlListForCount(weChartUserInfo);
    }

    /**
     * 删除开发语句
     * @param developSql 开发语句信息
     * @throws Exception
     */
    public void deleteDevelopSql(DevelopSql developSql) throws Exception {
        weChartUserMapper.deleteDevelopSql(developSql);
    }

    /**
     * 修改开发语句信息
     * @param developSql 开发语句信息
     * @throws Exception
     */
    public void updateDevelopSql(DevelopSql developSql) throws Exception {
        weChartUserMapper.updateDevelopSql(developSql);
    }

    /**
     * 新增开发语句
     * @param developSql 开发语句信息
     * @return 开发语句编号
     * @throws Exception
     */
    public String insertDevelopSql(DevelopSql developSql) throws Exception {
        weChartUserMapper.insertDevelopSql(developSql);
        return null;
    }

    /**
     * 获得开发语句信息
     * @param developSql 开发语句信息
     * @return 开发语句信息
     * @throws Exception
     */
    public DevelopSql findDevelopSqlInfo(DevelopSql developSql) throws Exception {
        return weChartUserMapper.findDevelopSqlInfo(developSql);
    }

}
