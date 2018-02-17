package com.hisign.code.persist.mapper.business;


import com.hisign.code.model.business.ApplicationInfo;
import com.hisign.code.model.business.TranslationDict;

import java.util.List;

/**
 * 本地词库模块配置接口
 * @author likangbo
 * @since 2017/5/31
 */
public interface ApplicationMapper {
    /**
     * 查询本地词库信息列表
     * @param applicationinfo
     * @return
     */
    List<ApplicationInfo> findApplicationInfo(ApplicationInfo applicationinfo);

    /**
     * 查询本地词库字段信息数量
     * @param applicationInfo
     * @return
     */
    int findApplicationInfoForCount(ApplicationInfo applicationInfo);

    /**
     * 插入本地词库字段信息
     * @param translationDict
     */
    void insertTranslationDict(TranslationDict translationDict);

    /**
     * 根据单词信息查询具体对象
     * @param applicationInfo
     * @return
     */
    void updateApplicationInfo(ApplicationInfo applicationInfo);

    /**
     * 新增请求信息
     * @param applicationInfo
     */
    void insertApplication(ApplicationInfo applicationInfo);

    /**
     * 删除字段信息
     * @param translationDict
     */
    void deleteTranslationDict(TranslationDict translationDict);

    /**
     * 修改本地词库字段信息
     * @param translationDict
     * @throws Exception
     */
    void updateTranslationDict(TranslationDict translationDict);

}