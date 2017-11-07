package com.hisign.code.api.business;

import com.hisign.code.model.business.TranslationDict;

import java.util.List;

/**
 * 本地词库服务配置接口
 * Created by likangbo on 2017/5/31.
 */
public interface TranslationDictService {
    /**
     * 查询所有词典
     * @param translationDict
     * @return
     */
    List<TranslationDict> findTranslationDict(TranslationDict translationDict) throws Exception;

    /**
     * 查询所有词典信息数量
     * @param translationDict
     * @return
     * @throws Exception
     */
    int findTranslationDictForCount(TranslationDict translationDict) throws Exception;
    /**
     * 插入本地词库字段信息
     * @param translationDict
     */
    void insertTranslationDict(TranslationDict translationDict) throws Exception;

    /**
     * 根据字段信息查询具体对象
     * @param translationDict
     * @return
     */
    TranslationDict findTranslationDictInfo(TranslationDict translationDict) throws Exception;

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
