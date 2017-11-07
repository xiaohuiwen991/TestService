package com.hisign.code.web.action.business;



import com.alibaba.fastjson.JSON;
import com.hisign.code.api.business.CompanyManageService;
import com.hisign.code.api.business.TableColumnService;
import com.hisign.code.api.business.TranslationDictService;
import com.hisign.code.model.common.JsonResult;
import com.hisign.code.model.business.CompanyInfo;
import com.hisign.code.model.business.TableColumn;
import com.hisign.code.model.business.TranslationDict;
import com.hisign.code.model.system.SysUser;
import com.hisign.code.web.bind.annotation.CurrentUser;
import com.hisign.code.web.bind.annotation.TranslateObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 本地词库Action
 * Created by likangbo on 2017/5/31.
 */
@Controller
@Component
@RequestMapping(value="/api/{recordLog}/database/translation_dict")
public class TranslationDictAction {
    /**
     * 配置日志信息
     */
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    /**
     * 本地词库信息配置接口
     */
    @Resource
    private TranslationDictService translationDictService;
    /**
     * 字段信息接口
     */
    @Resource
    private TableColumnService tableColumnService;
    /**
     * 数据库连接接口
     */
    @Resource
    private CompanyManageService companyManageService;


    @RequestMapping(value = "/list",method = RequestMethod.POST,produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public JsonResult findTranslationDict(@TranslateObject TranslationDict translationDict){
        JsonResult jsonResult=new JsonResult();
        String paraStr= JSON.toJSONString(translationDict);
        logger.info("获取本地词库信息");
        try {
            List<TranslationDict> translationDictList=translationDictService.findTranslationDict(translationDict);
            int count=translationDictService.findTranslationDictForCount(translationDict);
            jsonResult.setSuccessData(translationDictList,count);
        } catch (Exception e) {
            logger.error("获取本地词库信息列表失败，请求参数为[{}]",paraStr,e);
            jsonResult.setErrorMsg("获取本地词库信息列表失败");
        }
        return jsonResult;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult insertTranslationDict(@TranslateObject TranslationDict translationDict, @CurrentUser SysUser user) throws InterruptedException {
        JsonResult jsonResult = new JsonResult();
        String paraStr = JSON.toJSONString(translationDict);
        logger.info("新增本地词库信息");
        try {
            TranslationDict translationDictInfo = translationDictService.findTranslationDictInfo(translationDict);
            if (translationDictInfo != null) {
                return new JsonResult(0, "该字段名已经存在");
            }
            translationDict.setUser(user);
            translationDictService.insertTranslationDict(translationDict);
            jsonResult.setFlag(1);
        } catch (Exception e) {
            logger.error("新增本地词库字段失败,请求参数为[{}]", paraStr, e);
            jsonResult.setErrorMsg("新增本地词库字段失败");
        }
        return jsonResult;
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST,produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public JsonResult deleteTranslationDict(@TranslateObject TranslationDict translationDict) throws InterruptedException{
        JsonResult jsonResult=new JsonResult();
        String paraStr=JSON.toJSONString(translationDict);
        logger.info("删除本地词库字段信息");
        try {
            translationDictService.deleteTranslationDict(translationDict);
            jsonResult.setFlag(1);
        } catch (Exception e) {
            logger.error("删除本地词库字段信息失败，请求参数为[{}]",paraStr,e);
            jsonResult.setErrorMsg("删除本地词库字段信息失败");
        }
        return jsonResult;
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST,produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public JsonResult updateTranslationDict(@TranslateObject TranslationDict translationDict) throws InterruptedException {
        JsonResult jsonResult = new JsonResult();
        String paraStr = JSON.toJSONString(translationDict);
        logger.info("修改本地词库字段信息");
        try {
            translationDictService.updateTranslationDict(translationDict);
            jsonResult.setFlag(1);
        } catch (Exception e) {
            logger.error("修改本地词库字段信息失败,请求参数为[{}]", paraStr, e);
            jsonResult.setErrorMsg("修改本地词库信息失败");
        }
        return jsonResult;
    }

    @RequestMapping(value = "/info",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult findTranslationDictInfo(@TranslateObject TranslationDict translationDict) throws InterruptedException {
        JsonResult jsonResult = new JsonResult();
        String paraStr = JSON.toJSONString(translationDict);
        logger.info("获取本地词库字段信息");
        try {
            TranslationDict translationDictInfo = translationDictService.findTranslationDictInfo(translationDict);
            jsonResult.setSuccessData(translationDictInfo);
        } catch (Exception e) {
            logger.error("获取本地词库字段信息失败,请求参数为[{}]", paraStr, e);
            jsonResult.setErrorMsg("获取本地词库字段信息失败");
        }
        return jsonResult;
    }

    /**
     * 定时插入本地词库信息
     * @throws InterruptedException
     */
    @Scheduled(cron = "* * 2 * * ?")
    public void findAndInsertTranslationDict() throws InterruptedException{
        TableColumn tableColumn=new TableColumn();
        CompanyInfo connectionInfo=new CompanyInfo();
        SysUser user=new SysUser();
        logger.info("正在定时插入字段信息");
        try {
            //获取所有数据库连接对象集合
            List<CompanyInfo> connectionInfoList=companyManageService.findConnectionInfoList(connectionInfo);
            //遍历每个数据库连接对象
            for (CompanyInfo connectionInfo2:connectionInfoList) {
                //获取连接名并设置给表字段信息
                user.setConnectionName(connectionInfo2.getName());
                tableColumn.setUser(user);
                //获取所有表字段信息并遍历
                List<TableColumn> tableColumnList = tableColumnService.findTableColumnList(tableColumn);
                for (TableColumn tableColumnInfo:tableColumnList) {
                    TranslationDict translationDict =new TranslationDict();
                    //获取字段信息与字段描述信息并赋给本地词库表对象
                    translationDict.setOriginalText(tableColumnInfo.getColumnName());
                    translationDict.setTranslation(tableColumnInfo.getComments());
                    if(StringUtils.isNotEmpty(translationDict.getTranslation())){
                    //判断该表字段是否存在，如果字段名不存在则插入到本地词库表
                    TranslationDict translationDictInfo=translationDictService.findTranslationDictInfo(translationDict);
                    if(translationDictInfo == null){
                            user.setUserName("common");
                            translationDict.setUser(user);
                            translationDictService.insertTranslationDict(translationDict);
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("定时插入翻译词典字段失败", e);
        }
    }

}
