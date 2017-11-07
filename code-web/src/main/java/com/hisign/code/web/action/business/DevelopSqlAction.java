package com.hisign.code.web.action.business;

import com.alibaba.fastjson.JSON;
import com.hisign.code.api.business.DevelopSqlService;
import com.hisign.code.api.business.TableColumnService;
import com.hisign.code.model.common.JsonResult;
import com.hisign.code.model.database.CreateSql;
import com.hisign.code.model.database.DevelopSql;
import com.hisign.code.model.database.TableColumn;
import com.hisign.code.model.system.SysUser;
import com.hisign.code.web.bind.annotation.CurrentUser;
import com.hisign.code.web.bind.annotation.TranslateObject;
import com.hisign.commonutil.util.ClassUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 开发语句action
 * @author jiangpeng
 * @since 2017/05/26 09:41
 */
@Controller
@RequestMapping(value="/api/{recordLog}/database/develop_sql")
public class DevelopSqlAction {

    /**
     * 记录日志
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 开发语句接口
     */
    @Resource
    private DevelopSqlService developSqlService;

    /**
     * 字段信息接口
     */
    @Resource
    private TableColumnService tableColumnService;

    /**
     * 获取开发语句列表信息
     * @param developSql 开发语句查询条件
     * @return 开发语句列表信息
     * @throws InterruptedException
     */
    @RequestMapping(value="/list", method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult findDevelopSqlList(@TranslateObject DevelopSql developSql) throws InterruptedException {
        JsonResult jsonResult = new JsonResult();
        String paraStr = JSON.toJSONString(developSql);
        logger.info("获取开发语句列表信息");
        try {
            List<DevelopSql> list = developSqlService.findDevelopSqlList(developSql);
            int count =  developSqlService.findDevelopSqlListForCount(developSql);
            jsonResult.setSuccessData(list, count);
        } catch (Exception e) {
            logger.error("获取开发语句列表信息失败,请求参数为[{}]", paraStr, e);
            jsonResult.setErrorMsg("获取开发语句列表信息失败");
        }
        return jsonResult;
    }

    /**
     * 新增开发语句
     * @param user 当前用户
     * @param developSql 开发语句信息
     * @return 新增编号
     * @throws InterruptedException
     */
    @RequestMapping(value="/add", method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult insertDevelopSql(@CurrentUser SysUser user, @TranslateObject DevelopSql developSql) throws InterruptedException {
        JsonResult jsonResult = new JsonResult();
        String paraStr = JSON.toJSONString(developSql);
        logger.info("新增开发语句");
        try {
            DevelopSql info = developSqlService.findDevelopSqlInfo(developSql);
            if(info != null) {
                return new JsonResult(0, "语句名已存在!");
            }
            developSql.setUser(user);
            String id = developSqlService.insertDevelopSql(developSql);
            jsonResult.setSuccessData(id);
        } catch (Exception e) {
            logger.error("新增开发语句失败,请求参数为[{}]", paraStr, e);
            jsonResult.setErrorMsg("新增开发语句失败");
        }
        return jsonResult;
    }

    /**
     * 修改开发语句信息
     * @param user 当前用户
     * @param developSql 开发语句信息
     * @return 修改标志
     * @throws InterruptedException
     */
    @RequestMapping(value="/edit", method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult updateDevelopSql(@CurrentUser SysUser user, @TranslateObject DevelopSql developSql) throws InterruptedException {
        JsonResult jsonResult = new JsonResult();
        String paraStr = JSON.toJSONString(developSql);
        logger.info("修改开发语句信息");
        try {
            developSql.setUser(user);
            developSqlService.updateDevelopSql(developSql);
            jsonResult.setFlag(1);
        } catch (Exception e) {
            logger.error("修改开发语句信息失败,请求参数为[{}]", paraStr, e);
            jsonResult.setErrorMsg("修改开发语句信息失败");
        }
        return jsonResult;
    }

    /**
     * 删除开发语句
     * @param developSql 开发语句信息
     * @return 删除标志
     * @throws InterruptedException
     */
    @RequestMapping(value="/delete", method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult deleteDevelopSql(@TranslateObject DevelopSql developSql) throws InterruptedException {
        JsonResult jsonResult = new JsonResult();
        String paraStr = JSON.toJSONString(developSql);
        logger.info("删除开发语句");
        try {
            developSqlService.deleteDevelopSql(developSql);
            jsonResult.setFlag(1);
        } catch (Exception e) {
            logger.error("删除开发语句失败,请求参数为[{}]", paraStr, e);
            jsonResult.setErrorMsg("删除开发语句失败");
        }
        return jsonResult;
    }

    /**
     * 获取开发语句信息
     * @param developSql 开发语句信息
     * @return 删除标志
     * @throws InterruptedException
     */
    @RequestMapping(value="/info", method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult findDevelopSqlInfo(@TranslateObject DevelopSql developSql) throws InterruptedException {
        JsonResult jsonResult = new JsonResult();
        String paraStr = JSON.toJSONString(developSql);
        logger.info("获取开发语句信息");
        try {
            DevelopSql info = developSqlService.findDevelopSqlInfo(developSql);
            jsonResult.setSuccessData(info);
        } catch (Exception e) {
            logger.error("获取开发语句信息失败,请求参数为[{}]", paraStr, e);
            jsonResult.setErrorMsg("获取开发语句信息失败");
        }
        return jsonResult;
    }


    @RequestMapping(value="/create_sql", method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult createSql(@TranslateObject CreateSql createSql, @CurrentUser SysUser user) throws InterruptedException {
        JsonResult jsonResult = new JsonResult();
        String paraStr = JSON.toJSONString(createSql);
        logger.info("获得语句");
        try {
            String templateSql = createSql.getTemplateSql();
            String excludeTable = CreateSql.getExcludeTable(templateSql);
            List<String> excludeColumn = null;
            if(StringUtils.isNotEmpty(excludeTable)) {
                //获得移除字段信息
                List<TableColumn> listExcludeTable = tableColumnService.findTableColumnList(ClassUtil.getObjectByArray(TableColumn.class, "tableNames,user", excludeTable, user));
                excludeColumn = ClassUtil.getListObjectByList(listExcludeTable, "columnName");
            }
            String sql = "";
            if(templateSql.contains("$submeter")) {
                sql = createSql.getSubmeterSql(excludeColumn);
            } else {
                sql = createSql.getCreateSql(excludeColumn);
            }
            jsonResult.setSuccessData(sql);
        } catch (Exception e) {
            logger.error("获得语句失败,请求参数为[{}]", paraStr, e);
            jsonResult.setErrorMsg("获得语句失败");
        }
        return jsonResult;
    }






}