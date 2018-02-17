package com.hisign.code.service.impl.business;

import com.hisign.code.api.business.ApplicationService;
import com.hisign.code.constant.Constants;
import com.hisign.code.model.business.ApplicationInfo;
import com.hisign.code.model.business.TranslationDict;
import com.hisign.code.persist.mapper.business.ApplicationMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 本地词库服务接口实现类
 * Created by likangbo on 2017/5/31.
 */
@Service("applicationService")
public class ApplicationServiceImpl implements ApplicationService {
    /**
     * 本地词库mapper
     */
    @Resource
    public ApplicationMapper applicationMapper;

    /**
     * 本地词库接口实现类
     * @param applicationInfo
     * @return 本地词库字段信息列表
     */
    public List<ApplicationInfo> findApplicationInfo(ApplicationInfo applicationInfo) throws Exception {
        return applicationMapper.findApplicationInfo(applicationInfo);
    }

    /**
     * 查询所有本地词库信息数量
     * @param applicationInfo
     * @return
     * @throws Exception
     */
    public int findApplicationInfoForCount(ApplicationInfo applicationInfo) throws Exception {
        return applicationMapper.findApplicationInfoForCount(applicationInfo);
    }

    /**
     * 插入词典信息
     * @param translationDict
     * @throws Exception
     */
    public void insertTranslationDict(TranslationDict translationDict) throws Exception {
        applicationMapper.insertTranslationDict(translationDict);
        Constants.TRANSLATION_MAP.put(translationDict.getOriginalText(), translationDict.getTranslation());
    }

    /**
     * 查找具体字段信息
     * @param applicationInfo
     * @return
     * @throws Exception
     */
    public void updateApplicationInfo(ApplicationInfo applicationInfo)throws Exception {
        applicationMapper.updateApplicationInfo(applicationInfo);
    }

    /**
     * 新增请求信息
     * @param applicationInfo
     * @throws Exception
     */
    public void insertApplication(ApplicationInfo applicationInfo) throws Exception {
        applicationMapper.insertApplication(applicationInfo);
    }

    /**
     * 删除具体字段信息
     * @param translationDict
     * @throws Exception
     */
    public void deleteTranslationDict(TranslationDict translationDict) throws Exception {
        applicationMapper.deleteTranslationDict(translationDict);
        Constants.TRANSLATION_MAP.remove(translationDict.getOriginalText());
    }

    /**
     * 修改本地词库字段信息
     * @param translationDict
     * @throws Exception
     */
    public void updateTranslationDict(TranslationDict translationDict) throws Exception {
        applicationMapper.updateTranslationDict(translationDict);
        Constants.TRANSLATION_MAP.put(translationDict.getOriginalText(), translationDict.getTranslation());
    }

    /**
     * 初始化翻译map
     * @throws Exception
     */
    public void initTranslationMap()  {
//        List<TranslationDict> translationDict = applicationMapper.findApplicationInfo(new TranslationDict());
//        for (TranslationDict dict : translationDict) {
//            Constants.TRANSLATION_MAP.put(dict.getOriginalText(), dict.getTranslation());
//        }
    }
}
