package com.hisign.code.api.system;


import com.hisign.code.model.system.SysDictModel;
import com.hisign.code.model.system.SysUser;

import java.util.List;

/**
 * 字典管理接口
 * @author xiaohuiwen
 * @since 2016/11/29 11:57
 */
public interface DictManageService {
    /**
     * 根据查询条件获得字典列表
     * @param filter 查询条件
     * @return 字典列表
     * @throws Exception
     */
    public List<SysDictModel> findDictByFilter(SysDictModel filter) throws Exception;

    /**
     * 获得字典列表数量
     * @param filter 查询条件
     * @return 字典列表数量
     * @throws Exception
     */
    public int findDictByFilterForCount(SysDictModel filter) throws Exception;

    /**
     * 根据字典编号删除字典
     * @param dict 字典信息
     * @throws Exception
     */
    public void deleteDict(SysDictModel dict) throws Exception;

    /**
     * 更新字典表
     * @param dict 字典信息
     * @param user 用户信息
     * @throws Exception
     */
    public void updateDict(SysDictModel dict, SysUser user) throws Exception;

    /**
     * 字典表新增数据
     * @param dict 字典信息
     * @param user 用户信息
     * @throws Exception
     */
    public String insertDict(SysDictModel dict, SysUser user) throws Exception;

    /**
     * 获得字典信息
     * @param dict 字典信息查询条件
     * @return 字典信息
     * @throws Exception
     */
    public SysDictModel findDict(SysDictModel dict) throws Exception;
}
