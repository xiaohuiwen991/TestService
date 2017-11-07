package com.hisign.code.web.action.business;

import com.alibaba.fastjson.JSON;
import com.hisign.code.api.business.TemplateSqlService;
import com.hisign.code.model.common.JsonResult;
import com.hisign.code.model.database.TemplateSql;
import com.hisign.code.model.system.SysUser;
import com.hisign.code.web.bind.annotation.CurrentUser;
import com.hisign.code.web.bind.annotation.TranslateObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 模版语句action
 * @author jiangpeng
 * @since 2017/05/25 13:55
 */
@Controller
@RequestMapping(value="/api/{recordLog}/database/template_sql")
public class TemplateSqlAction {

    /**
     * 记录日志
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 模版语句接口
     */
    @Resource
    private TemplateSqlService templateSqlService;

    /**
     * 获取模版语句列表信息
     * @param templateSql 模版语句查询条件
     * @return 模版语句列表信息
     * @throws InterruptedException
     */
    @RequestMapping(value="/list", method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult findTemplateSqlList(@TranslateObject TemplateSql templateSql) throws InterruptedException {
        JsonResult jsonResult = new JsonResult();
        String paraStr = JSON.toJSONString(templateSql);
        logger.info("获取模版语句列表信息");
        try {
            List<TemplateSql> list = templateSqlService.findTemplateSqlList(templateSql);
            int count =  templateSqlService.findTemplateSqlListForCount(templateSql);
            jsonResult.setSuccessData(list, count);
        } catch (Exception e) {
            logger.error("获取模版语句列表信息失败,请求参数为[{}]", paraStr, e);
            jsonResult.setErrorMsg("获取模版语句列表信息失败");
        }
        return jsonResult;
    }

    /**
     * 新增模版语句
     * @param user 当前用户
     * @param templateSql 模版语句信息
     * @return 新增编号
     * @throws InterruptedException
     */
    @RequestMapping(value="/add", method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult insertTemplateSql(@CurrentUser SysUser user, @TranslateObject TemplateSql templateSql) throws InterruptedException {
        JsonResult jsonResult = new JsonResult();
        String paraStr = JSON.toJSONString(templateSql);
        logger.info("新增模版语句");
        try {
            TemplateSql info = templateSqlService.findTemplateSqlInfo(templateSql);
            if(info != null) {
                return new JsonResult(0, "模版名已存在!");
            }
            templateSql.setUser(user);
            String id = templateSqlService.insertTemplateSql(templateSql);
            jsonResult.setSuccessData(id);
        } catch (Exception e) {
            logger.error("新增模版语句失败,请求参数为[{}]", paraStr, e);
            jsonResult.setErrorMsg("新增模版语句失败");
        }
        return jsonResult;
    }

    /**
     * 修改模版语句信息
     * @param user 当前用户
     * @param templateSql 模版语句信息
     * @return 修改标志
     * @throws InterruptedException
     */
    @RequestMapping(value="/edit", method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult updateTemplateSql(@CurrentUser SysUser user, @TranslateObject TemplateSql templateSql) throws InterruptedException {
        JsonResult jsonResult = new JsonResult();
        String paraStr = JSON.toJSONString(templateSql);
        logger.info("修改模版语句信息");
        try {
            templateSql.setUser(user);
            templateSqlService.updateTemplateSql(templateSql);
            jsonResult.setFlag(1);
        } catch (Exception e) {
            logger.error("修改模版语句信息失败,请求参数为[{}]", paraStr, e);
            jsonResult.setErrorMsg("修改模版语句信息失败");
        }
        return jsonResult;
    }

    /**
     * 删除模版语句
     * @param templateSql 模版语句信息
     * @return 删除标志
     * @throws InterruptedException
     */
    @RequestMapping(value="/delete", method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult deleteTemplateSql(@TranslateObject TemplateSql templateSql) throws InterruptedException {
        JsonResult jsonResult = new JsonResult();
        String paraStr = JSON.toJSONString(templateSql);
        logger.info("删除模版语句");
        try {
            templateSqlService.deleteTemplateSql(templateSql);
            jsonResult.setFlag(1);
        } catch (Exception e) {
            logger.error("删除模版语句失败,请求参数为[{}]", paraStr, e);
            jsonResult.setErrorMsg("删除模版语句失败");
        }
        return jsonResult;
    }

    /**
     * 获取模版语句信息
     * @param templateSql 模版语句信息
     * @return 删除标志
     * @throws InterruptedException
     */
    @RequestMapping(value="/info", method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult findTemplateSqlInfo(@TranslateObject TemplateSql templateSql) throws InterruptedException {
        JsonResult jsonResult = new JsonResult();
        String paraStr = JSON.toJSONString(templateSql);
        logger.info("获取模版语句信息");
        try {
            TemplateSql info = templateSqlService.findTemplateSqlInfo(templateSql);
            jsonResult.setSuccessData(info);
        } catch (Exception e) {
            logger.error("获取模版语句信息失败,请求参数为[{}]", paraStr, e);
            jsonResult.setErrorMsg("获取模版语句信息失败");
        }
        return jsonResult;
    }


    /**
     * 获取用户查询条件
     * @return 用户查询条件
     * @throws InterruptedException
     */
    @RequestMapping(value="/user_info", method= RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult findAllUser() throws InterruptedException {
        JsonResult jsonResult = new JsonResult();
        logger.info("获取用户查询条件");
        try {
            List<TemplateSql> userInfo = templateSqlService.findAllUser();
            jsonResult.setSuccessData(userInfo,0);
            return jsonResult;
        } catch (Exception e) {
            logger.error("获取用户查询条件失败", e);
            jsonResult = new JsonResult(0, "获取用户查询条件失败!");
            return jsonResult;
        }
    }





}