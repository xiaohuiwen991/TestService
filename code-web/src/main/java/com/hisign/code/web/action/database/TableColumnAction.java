package com.hisign.code.web.action.database;

import com.alibaba.fastjson.JSON;
import com.hisign.code.api.database.TableColumnService;
import com.hisign.code.api.database.TranslationDictService;
import com.hisign.code.model.common.JsonResult;
import com.hisign.code.model.database.TableColumn;
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
 * 字段信息action
 * @author jiangpeng
 * @since 2017/05/24 17:27
 */
@Controller
@RequestMapping(value="/api/{recordLog}/database/column")
public class TableColumnAction {

    /**
     * 记录日志
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 字段信息接口
     */
    @Resource
    private TableColumnService tableColumnService;

    /**
     *本地词库翻译接口
     */
    @Resource
    private TranslationDictService translationDictService;

    /**
     * 获取字段信息列表信息
     * @param tableColumn 字段信息查询条件
     * @return 字段信息列表信息
     * @throws InterruptedException
     */
    @RequestMapping(value="/list", method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult findTableColumnList(@TranslateObject TableColumn tableColumn, @CurrentUser SysUser user) throws InterruptedException {
        JsonResult jsonResult = new JsonResult();
        String paraStr = JSON.toJSONString(tableColumn);
        logger.info("获取字段信息列表信息");
        try {
            tableColumn.setUser(user);
            List<TableColumn> list = tableColumnService.findTableColumnList(tableColumn);
            int count =  tableColumnService.findTableColumnListForCount(tableColumn);
            jsonResult.setSuccessData(list, count);
        } catch (Exception e) {
            logger.error("获取字段信息列表信息失败,请求参数为[{}]", paraStr, e);
            jsonResult.setErrorMsg("获取字段信息列表信息失败");
        }
        return jsonResult;
    }

    /**
     * 获取表信息
     * @param tableColumn 表信息查询条件
     * @return 表信息列表信息
     * @throws InterruptedException
     */
    @RequestMapping(value="/table_list", method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult findTableList(@TranslateObject TableColumn tableColumn, @CurrentUser SysUser user) throws InterruptedException {
        JsonResult jsonResult = new JsonResult();
        String paraStr = JSON.toJSONString(tableColumn);
        logger.info("获取表信息");
        try {
            tableColumn.setUser(user);
            List<TableColumn> list = tableColumnService.findTableList(tableColumn);
            int count =  tableColumnService.findTableListForCount(tableColumn);
            jsonResult.setSuccessData(list, count);
        } catch (Exception e) {
            logger.error("获取表信息失败,请求参数为[{}]", paraStr, e);
            jsonResult.setErrorMsg("获取表信息失败");
        }
        return jsonResult;
    }


    /**
     * 获取字段信息列表信息
     * @param tableColumn 字段信息查询条件
     * @return 字段信息列表信息
     * @throws InterruptedException
     */
    @RequestMapping(value="/column_list", method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult findColumnList(@TranslateObject TableColumn tableColumn, @CurrentUser SysUser user) throws InterruptedException {
        JsonResult jsonResult = new JsonResult();
        String paraStr = JSON.toJSONString(tableColumn);
        logger.info("获取字段信息列表信息");
        try {
            tableColumn.setUser(user);
            List<TableColumn> list = tableColumnService.findColumnList(tableColumn);
            jsonResult.setSuccessData(list);
        } catch (Exception e) {
            logger.error("获取字段信息列表信息失败,请求参数为[{}]", paraStr, e);
            jsonResult.setErrorMsg("获取字段信息列表信息失败");
        }
        return jsonResult;
    }







}