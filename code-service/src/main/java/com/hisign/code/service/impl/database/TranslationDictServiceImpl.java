package com.hisign.code.service.impl.database;

import com.hisign.code.api.database.TranslationDictService;
import com.hisign.code.constant.Constants;
import com.hisign.code.model.database.TranslationDict;
import com.hisign.code.persist.mapper.database.TranslationDictMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 本地词库服务接口实现类
 * Created by likangbo on 2017/5/31.
 */
@Service("translationDictService")
public class TranslationDictServiceImpl implements TranslationDictService{
    /**
     * 本地词库mapper
     */
    @Resource
    public TranslationDictMapper translationDictMapper;

    /**
     * 本地词库接口实现类
     * @param translationDict
     * @return 本地词库字段信息列表
     */
    public List<TranslationDict> findTranslationDict(TranslationDict translationDict) {
        return translationDictMapper.findTranslationDict(translationDict);
    }

    /**
     * 查询所有本地词库信息数量
     * @param translationDict
     * @return
     * @throws Exception
     */
    public int findTranslationDictForCount(TranslationDict translationDict) throws Exception {
        return translationDictMapper.findTranslationDictForCount(translationDict);
    }

    /**
     * 插入词典信息
     * @param translationDict
     * @throws Exception
     */
    public void insertTranslationDict(TranslationDict translationDict) throws Exception {
        translationDictMapper.insertTranslationDict(translationDict);
        Constants.TRANSLATION_MAP.put(translationDict.getOriginalText(), translationDict.getTranslation());
    }

    /**
     * 查找具体字段信息
     * @param translationDict
     * @return
     * @throws Exception
     */
    public TranslationDict findTranslationDictInfo(TranslationDict translationDict) throws Exception {
        return translationDictMapper.findTranslationDictInfo(translationDict);
    }

    /**
     * 删除具体字段信息
     * @param translationDict
     * @throws Exception
     */
    public void deleteTranslationDict(TranslationDict translationDict) throws Exception {
        translationDictMapper.deleteTranslationDict(translationDict);
        Constants.TRANSLATION_MAP.remove(translationDict.getOriginalText());
    }

    /**
     * 修改本地词库字段信息
     * @param translationDict
     * @throws Exception
     */
    public void updateTranslationDict(TranslationDict translationDict) throws Exception {
        translationDictMapper.updateTranslationDict(translationDict);
        Constants.TRANSLATION_MAP.put(translationDict.getOriginalText(), translationDict.getTranslation());
    }

    /**
     * 初始化翻译map
     * @throws Exception
     */
    public void initTranslationMap()  {
//        List<TranslationDict> translationDict = translationDictMapper.findTranslationDict(new TranslationDict());
//        for (TranslationDict dict : translationDict) {
//            Constants.TRANSLATION_MAP.put(dict.getOriginalText(), dict.getTranslation());
//        }
    }
}
