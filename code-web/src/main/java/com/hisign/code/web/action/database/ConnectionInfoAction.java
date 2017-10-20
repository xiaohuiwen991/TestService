package com.hisign.code.web.action.database;

import com.alibaba.fastjson.JSON;
import com.hisign.code.api.database.ConnectionInfoService;
import com.hisign.code.model.common.JsonResult;
import com.hisign.code.model.database.ConnectionInfo;
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
 * 数据库连接action
 * @author jiangpeng
 * @since 2017/05/23 18:19
 */
@Controller
@RequestMapping(value="/api/{recordLog}/database/connection")
public class ConnectionInfoAction {

    /**
     * 记录日志
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 数据库连接接口
     */
    @Resource
    private ConnectionInfoService connectionInfoService;

    /**
     * 获取数据库连接列表信息
     * @param connectionInfo 数据库连接查询条件
     * @return 数据库连接列表信息
     * @throws InterruptedException
     */
    @RequestMapping(value="/list", method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult findConnectionInfoList(@TranslateObject ConnectionInfo connectionInfo) throws InterruptedException {
        JsonResult jsonResult = new JsonResult();
        String paraStr = JSON.toJSONString(connectionInfo);
        logger.info("获取数据库连接列表信息");
        try {
            List<ConnectionInfo> list = connectionInfoService.findConnectionInfoList(connectionInfo);
            int count =  connectionInfoService.findConnectionInfoListForCount(connectionInfo);
            jsonResult.setSuccessData(list, count);
        } catch (Exception e) {
            logger.error("获取数据库连接列表信息失败,请求参数为[{}]", paraStr, e);
            jsonResult.setErrorMsg("获取数据库连接列表信息失败");
        }
        return jsonResult;
    }

    /**
     * 新增数据库连接
     * @param user 当前用户
     * @param connectionInfo 数据库连接信息
     * @return 新增编号
     * @throws InterruptedException
     */
    @RequestMapping(value="/add", method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult insertConnectionInfo(@CurrentUser SysUser user, @TranslateObject ConnectionInfo connectionInfo) throws InterruptedException {
        JsonResult jsonResult = new JsonResult();
        String paraStr = JSON.toJSONString(connectionInfo);
        logger.info("新增数据库连接");
        try {
            ConnectionInfo info = connectionInfoService.findConnectionInfoInfo(connectionInfo);
            if(info != null) {
                return new JsonResult(0, "连接名已存在!");
            }
            connectionInfo.setUser(user);
            String id = connectionInfoService.insertConnectionInfo(connectionInfo);
            jsonResult.setSuccessData(id);
        } catch (Exception e) {
            logger.error("新增数据库连接失败,请求参数为[{}]", paraStr, e);
            jsonResult.setErrorMsg("新增数据库连接失败");
        }
        return jsonResult;
    }

    /**
     * 修改数据库连接信息
     * @param user 当前用户
     * @param connectionInfo 数据库连接信息
     * @return 修改标志
     * @throws InterruptedException
     */
    @RequestMapping(value="/edit", method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult updateConnectionInfo(@CurrentUser SysUser user, @TranslateObject ConnectionInfo connectionInfo) throws InterruptedException {
        JsonResult jsonResult = new JsonResult();
        String paraStr = JSON.toJSONString(connectionInfo);
        logger.info("修改数据库连接信息");
        try {
            connectionInfo.setUser(user);
            connectionInfoService.updateConnectionInfo(connectionInfo);
            jsonResult.setFlag(1);
        } catch (Exception e) {
            logger.error("修改数据库连接信息失败,请求参数为[{}]", paraStr, e);
            jsonResult.setErrorMsg("修改数据库连接信息失败");
        }
        return jsonResult;
    }

    /**
     * 删除数据库连接
     * @param connectionInfo 数据库连接信息
     * @return 删除标志
     * @throws InterruptedException
     */
    @RequestMapping(value="/delete", method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult deleteConnectionInfo(@TranslateObject ConnectionInfo connectionInfo) throws InterruptedException {
        JsonResult jsonResult = new JsonResult();
        String paraStr = JSON.toJSONString(connectionInfo);
        logger.info("删除数据库连接");
        try {
            connectionInfoService.deleteConnectionInfo(connectionInfo);
            jsonResult.setFlag(1);
        } catch (Exception e) {
            logger.error("删除数据库连接失败,请求参数为[{}]", paraStr, e);
            jsonResult.setErrorMsg("删除数据库连接失败");
        }
        return jsonResult;
    }

    /**
     * 获取数据库连接信息
     * @param connectionInfo 数据库连接信息
     * @return 删除标志
     * @throws InterruptedException
     */
    @RequestMapping(value="/info", method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult findConnectionInfoInfo(@TranslateObject ConnectionInfo connectionInfo) throws InterruptedException {
        JsonResult jsonResult = new JsonResult();
        String paraStr = JSON.toJSONString(connectionInfo);
        logger.info("获取数据库连接信息");
        try {
            ConnectionInfo info = connectionInfoService.findConnectionInfoInfo(connectionInfo);
            jsonResult.setSuccessData(info);
        } catch (Exception e) {
            logger.error("获取数据库连接信息失败,请求参数为[{}]", paraStr, e);
            jsonResult.setErrorMsg("获取数据库连接信息失败");
        }
        return jsonResult;
    }


    /**
     * 连接数据库
     * @param connectionInfo 数据库连接信息
     * @return 删除标志
     * @throws InterruptedException
     */
    @RequestMapping(value="/connect", method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult findConnectionInfoInfo(@TranslateObject ConnectionInfo connectionInfo, @CurrentUser SysUser user) throws InterruptedException {
        JsonResult jsonResult = new JsonResult();
        String paraStr = JSON.toJSONString(connectionInfo);
        logger.info("连接数据库");
        try {
            connectionInfo.setUser(user);
            connectionInfoService.connect(connectionInfo);
            jsonResult.setFlag(1);
        } catch (Exception e) {
            logger.error("连接数据库失败,请求参数为[{}]", paraStr, e);
            jsonResult.setErrorMsg("连接数据库失败");
        }
        return jsonResult;
    }






}