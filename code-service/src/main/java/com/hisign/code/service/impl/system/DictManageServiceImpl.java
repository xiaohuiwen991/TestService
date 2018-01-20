package com.hisign.code.service.impl.system;

import com.hisign.code.api.system.DictManageService;
import com.hisign.code.model.system.SysDictModel;
import com.hisign.code.model.system.SysUser;
import com.hisign.code.persist.mapper.system.DictManageMapper;
import com.hisign.commonutil.util.ClassUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * 字典管理接口实现类
 * @author xiaohuiwen
 * @since 2016/11/29 12:00
 */
@Service("dictManageService")
public class DictManageServiceImpl implements DictManageService {

    /**
     * 字典管理mapper
     */
    @Resource
    private DictManageMapper dictManageMapper;

    /**
     * 根据查询条件获得字典列表
     * @param filter 查询条件
     * @return 字典列表
     * @throws Exception
     */
    public List<SysDictModel> findDictByFilter(SysDictModel filter) throws Exception{
        return dictManageMapper.findDictByFilter(filter);
    }

    /**
     * 获得字典列表数量
     * @param filter 查询条件
     * @return 字典列表数量
     * @throws Exception
     */
    public int findDictByFilterForCount(SysDictModel filter) throws Exception{
        return dictManageMapper.findDictByFilterForCount(filter);
    }

    /**
     * 删除字典
     * @param dict 字典信息
     * @throws Exception
     */
    public void deleteDict(SysDictModel dict) throws Exception{
        SysDictModel sysDict = dictManageMapper.findDict(dict);
        dictManageMapper.deleteDict(sysDict);

    }

    /**
     * 更新字典表
     * @param dict 字典信息
     * @param user 用户信息
     * @throws Exception
     */
    public void updateDict(SysDictModel dict, SysUser user) throws Exception{
        //获得字典原有信息
        SysDictModel sysDict = dictManageMapper.findDict(ClassUtil.getObjectByArray(SysDictModel.class, "id", dict.getId()));
        dict.setUser(user);
        dictManageMapper.updateDict(dict);
        if(!dict.getOpenFlag().equals(sysDict.getOpenFlag())) {
            //更新启用状态
            dictManageMapper.updateOpenFlag(dict);
        }
    }

    /**
     * 字典表新增数据
     * @param dict 字典信息
     * @param user 用户信息
     * @throws Exception
     */
    public String insertDict(SysDictModel dict, SysUser user) throws Exception{
        String id = UUID.randomUUID().toString().replaceAll("-","");
        dict.setId(id);
        dict.setUser(user);
        dictManageMapper.insertDict(dict);
        return id;
    }

    /**
     * 获得字典信息
     * @param dict 字典信息查询条件
     * @return 字典信息
     * @throws Exception
     */
    public SysDictModel findDict(SysDictModel dict) throws Exception{
        return dictManageMapper.findDict(dict);
    }
}
