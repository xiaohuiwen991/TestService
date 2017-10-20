package com.hisign.code.persist.mapper.database;


import com.hisign.code.model.database.TranslationDict;

import java.util.List;

/**
 * 本地词库模块配置接口
 * @author likangbo
 * @since 2017/5/31
 */
public interface TranslationDictMapper {
    /**
     * 查询本地词库信息列表
     * @param translationDict
     * @return
     */
    List<TranslationDict> findTranslationDict(TranslationDict translationDict);

    /**
     * 查询本地词库字段信息数量
     * @param translationDict
     * @return
     */
    int findTranslationDictForCount(TranslationDict translationDict);

    /**
     * 插入本地词库字段信息
     * @param translationDict
     */
    void insertTranslationDict(TranslationDict translationDict);

    /**
     * 根据单词信息查询具体对象
     * @param translationDict
     * @return
     */
    TranslationDict findTranslationDictInfo(TranslationDict translationDict);

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