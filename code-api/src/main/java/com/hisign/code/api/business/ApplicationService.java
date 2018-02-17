package com.hisign.code.api.business;

import com.hisign.code.model.business.ApplicationInfo;
import com.hisign.code.model.business.TranslationDict;

import java.util.List;

/**
 * 本地词库服务配置接口
 * Created by likangbo on 2017/5/31.
 */
public interface ApplicationService {
    /**
     * 查询所有申请
     * @param applicationInfo
     * @return
     */
    List<ApplicationInfo> findApplicationInfo(ApplicationInfo applicationInfo) throws Exception;

    /**
     * 查询所有词典信息数量
     * @param applicationInfo
     * @return
     * @throws Exception
     */
    int findApplicationInfoForCount(ApplicationInfo applicationInfo) throws Exception;
    /**
     * 插入本地词库字段信息
     * @param translationDict
     */
    void insertTranslationDict(TranslationDict translationDict) throws Exception;

    /**
     * 根据字段信息查询具体对象
     * @param applicationInfo
     * @return
     */
    void updateApplicationInfo(ApplicationInfo applicationInfo) throws Exception;

    /**
     * 新增请求信息
     * @param applicationInfo
     * @throws Exception
     */
    void insertApplication(ApplicationInfo applicationInfo) throws Exception;

    /**
     * 删除字段信息
     * @param translationDict
     */
    void deleteTranslationDict(TranslationDict translationDict) throws Exception;

    /**
     * 修改本地词库字段信息
     * @param translationDict
     * @throws Exception
     */
    void updateTranslationDict(TranslationDict translationDict) throws Exception;

    /**
     * 初始化翻译map
     * @throws Exception
     */
    void initTranslationMap() ;
}
