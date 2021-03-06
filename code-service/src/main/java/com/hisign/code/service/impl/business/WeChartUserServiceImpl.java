package com.hisign.code.service.impl.business;

import com.hisign.code.api.business.WeChartUserService;
import com.hisign.code.model.business.DevelopSql;
import com.hisign.code.model.business.WeChartUserInfo;
import com.hisign.code.persist.mapper.business.WeChartUserMapper;
import com.hisign.code.util.Md5Helper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 开发语句接口实现类
 * @author xiaohuiwen
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
     * @param weChartUserInfo 开发语句信息
     * @throws Exception
     */
    public void deleteDevelopSql(WeChartUserInfo weChartUserInfo) throws Exception {
        weChartUserMapper.deleteDevelopSql(weChartUserInfo);
    }

    /**
     * 修改开发语句信息
     * @param weChartUserInfo 开发语句信息
     * @throws Exception
     */
    public void updateDevelopSql(WeChartUserInfo weChartUserInfo) throws Exception {
        weChartUserMapper.updateDevelopSql(weChartUserInfo);
    }

    /**
     * 新增开发语句
     * @param weChartUserInfo 开发语句信息
     * @return 开发语句编号
     * @throws Exception
     */
    public String insertDevelopSql(WeChartUserInfo weChartUserInfo) throws Exception {
        weChartUserInfo.setPassword(Md5Helper.getMD5(weChartUserInfo.getPassword()));
        weChartUserMapper.insertDevelopSql(weChartUserInfo);
        return null;
    }

    /**
     * 获得开发语句信息
     * @param weChartUserInfo 开发语句信息
     * @return 开发语句信息
     * @throws Exception
     */
    public WeChartUserInfo findDevelopSqlInfo(WeChartUserInfo weChartUserInfo) throws Exception {
        return weChartUserMapper.findDevelopSqlInfo(weChartUserInfo);
    }

    /**
     * 启用用户信息
     * @param weChartUserInfo
     * @throws Exception
     */
    public void enableUserStatus(WeChartUserInfo weChartUserInfo) throws Exception {
        weChartUserMapper.enableUserStatus(weChartUserInfo);
    }

    /**
     * 手机端更新用户信息
     * @param weChartUserInfo
     * @throws Exception
     */
    public void registerUpdateWebPageUser(WeChartUserInfo weChartUserInfo) throws Exception {
        weChartUserMapper.registerUpdateWebPageUser(weChartUserInfo);
    }

}
