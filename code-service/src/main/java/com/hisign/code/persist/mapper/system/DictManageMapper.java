package com.hisign.code.persist.mapper.system;


import com.hisign.code.model.system.SysDictModel;

import java.util.List;

/**
 * 字典管理mapper
 * @author jiangpeng
 * @since 2016/11/29 12:03
 */
public interface DictManageMapper {
    /**
     * 根据查询条件获得字典列表
     * @param filter 查询条件
     * @return 字典列表
     */
    List<SysDictModel> findDictByFilter(SysDictModel filter);

    /**
     * 获得字典列表数量
     * @param filter 查询条件
     * @return 字典列表数量
     */
    int findDictByFilterForCount(SysDictModel filter);

    /**
     * 删除字典
     * @param dict 字典编号
     */
    void deleteDict(SysDictModel dict);

    /**
     * 更新字典表
     * @param dict 字典信息
     */
    void updateDict(SysDictModel dict);

    /**
     * 字典表新增数据
     * @param dict 字典信息
     */
    void insertDict(SysDictModel dict);

    /**
     * 获得字典信息
     * @param dict 字典信息查询条件
     * @return 字典信息
     */
    SysDictModel findDict(SysDictModel dict);

    /**
     * 更新启用状态
     * @param id 字典id
     */
    void updateOpenFlag(SysDictModel id);
}
