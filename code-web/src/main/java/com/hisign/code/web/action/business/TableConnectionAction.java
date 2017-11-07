package com.hisign.code.web.action.business;

import com.alibaba.fastjson.JSON;
import com.hisign.code.api.business.TableConnectionService;
import com.hisign.code.model.common.JsonResult;
import com.hisign.code.model.database.TableConnection;
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
 * 表连接action
 * @author jiangpeng
 * @since 2017/05/27 14:49
 */
@Controller
@RequestMapping(value="/api/{recordLog}/database/table_connection")
public class TableConnectionAction {

    /**
     * 记录日志
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 表连接接口
     */
    @Resource
    private TableConnectionService tableConnectionService;

    /**
     * 获取表连接列表信息
     * @param tableConnection 表连接查询条件
     * @return 表连接列表信息
     * @throws InterruptedException
     */
    @RequestMapping(value="/list", method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult findTableConnectionList(@TranslateObject TableConnection tableConnection, @CurrentUser SysUser user) throws InterruptedException {
        JsonResult jsonResult = new JsonResult();
        String paraStr = JSON.toJSONString(tableConnection);
        logger.info("获取表连接列表信息");
        try {
            tableConnection.setUser(user);
            List<TableConnection> list = tableConnectionService.findTableConnectionList(tableConnection);
            int count =  tableConnectionService.findTableConnectionListForCount(tableConnection);
            jsonResult.setSuccessData(list, count);
        } catch (Exception e) {
            logger.error("获取表连接列表信息失败,请求参数为[{}]", paraStr, e);
            jsonResult.setErrorMsg("获取表连接列表信息失败");
        }
        return jsonResult;
    }

    /**
     * 新增表连接
     * @param user 当前用户
     * @param tableConnection 表连接信息
     * @return 新增编号
     * @throws InterruptedException
     */
    @RequestMapping(value="/add", method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult insertTableConnection(@CurrentUser SysUser user, @TranslateObject TableConnection tableConnection) throws InterruptedException {
        JsonResult jsonResult = new JsonResult();
        String paraStr = JSON.toJSONString(tableConnection);
        logger.info("新增表连接");
        try {
            tableConnection.setUser(user);
            String id = tableConnectionService.insertTableConnection(tableConnection);
            jsonResult.setSuccessData(id);
        } catch (Exception e) {
            logger.error("新增表连接失败,请求参数为[{}]", paraStr, e);
            jsonResult.setErrorMsg("新增表连接失败");
        }
        return jsonResult;
    }

    /**
     * 修改表连接信息
     * @param user 当前用户
     * @param tableConnection 表连接信息
     * @return 修改标志
     * @throws InterruptedException
     */
    @RequestMapping(value="/edit", method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult updateTableConnection(@CurrentUser SysUser user, @TranslateObject TableConnection tableConnection) throws InterruptedException {
        JsonResult jsonResult = new JsonResult();
        String paraStr = JSON.toJSONString(tableConnection);
        logger.info("修改表连接信息");
        try {
            tableConnection.setUser(user);
            tableConnectionService.updateTableConnection(tableConnection);
            jsonResult.setFlag(1);
        } catch (Exception e) {
            logger.error("修改表连接信息失败,请求参数为[{}]", paraStr, e);
            jsonResult.setErrorMsg("修改表连接信息失败");
        }
        return jsonResult;
    }

    /**
     * 删除表连接
     * @param tableConnection 表连接信息
     * @return 删除标志
     * @throws InterruptedException
     */
    @RequestMapping(value="/delete", method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult deleteTableConnection(@TranslateObject TableConnection tableConnection) throws InterruptedException {
        JsonResult jsonResult = new JsonResult();
        String paraStr = JSON.toJSONString(tableConnection);
        logger.info("删除表连接");
        try {
            tableConnectionService.deleteTableConnection(tableConnection);
            jsonResult.setFlag(1);
        } catch (Exception e) {
            logger.error("删除表连接失败,请求参数为[{}]", paraStr, e);
            jsonResult.setErrorMsg("删除表连接失败");
        }
        return jsonResult;
    }

    /**
     * 获取表连接信息
     * @param tableConnection 表连接信息
     * @return 删除标志
     * @throws InterruptedException
     */
    @RequestMapping(value="/info", method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult findTableConnectionInfo(@TranslateObject TableConnection tableConnection) throws InterruptedException {
        JsonResult jsonResult = new JsonResult();
        String paraStr = JSON.toJSONString(tableConnection);
        logger.info("获取表连接信息");
        try {
            TableConnection info = tableConnectionService.findTableConnectionInfo(tableConnection);
            jsonResult.setSuccessData(info);
        } catch (Exception e) {
            logger.error("获取表连接信息失败,请求参数为[{}]", paraStr, e);
            jsonResult.setErrorMsg("获取表连接信息失败");
        }
        return jsonResult;
    }






}