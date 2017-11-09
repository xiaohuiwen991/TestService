package com.hisign.code.web.action.business;

import com.alibaba.fastjson.JSON;
import com.hisign.code.api.business.CompanyManageService;
import com.hisign.code.model.common.JsonResult;
import com.hisign.code.model.business.CompanyInfo;
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
 * 公司管理action
 * @author xiaohuiwen
 * @since 2017/05/23 18:19
 */
@Controller
@RequestMapping(value="/api/{recordLog}/business/company")
public class CompanyManageAction {

    /**
     * 记录日志
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 数据库连接接口
     */
    @Resource
    private CompanyManageService companyManageService;

    /**
     * 获取数据库连接列表信息
     * @param companyinfo 数据库连接查询条件
     * @return 数据库连接列表信息
     * @throws InterruptedException
     */
    @RequestMapping(value="/list", method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult findConnectionInfoList(@TranslateObject CompanyInfo companyinfo) throws InterruptedException {
        JsonResult jsonResult = new JsonResult();
        String paraStr = JSON.toJSONString(companyinfo);
        logger.info("获取公司数据列表信息");
        try {
            companyinfo.setBegin(companyinfo.getBegin()-1);
            List<CompanyInfo> list = companyManageService.findConnectionInfoList(companyinfo);
            int count =  companyManageService.findConnectionInfoListForCount(companyinfo);
            jsonResult.setSuccessData(list, count);
        } catch (Exception e) {
            logger.error("获取公司数据列表信息失败,请求参数为[{}]", paraStr, e);
            jsonResult.setErrorMsg("获取公司数据列表信息失败");
        }
        return jsonResult;
    }

    /**
     * 新增数据库连接
     * @param user 当前用户
     * @param companyinfo 数据库连接信息
     * @return 新增编号
     * @throws InterruptedException
     */
    @RequestMapping(value="/add", method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult insertConnectionInfo(@CurrentUser SysUser user, @TranslateObject CompanyInfo companyinfo) throws InterruptedException {
        JsonResult jsonResult = new JsonResult();
        String paraStr = JSON.toJSONString(companyinfo);
        logger.info("新增公司数据");
        try {
            CompanyInfo info = companyManageService.findConnectionInfoInfo(companyinfo);
            if(info != null) {
                return new JsonResult(0, "公司名称已存在!");
            }
            companyinfo.setUser(user);
            String id = companyManageService.insertConnectionInfo(companyinfo);
            jsonResult.setSuccessData(id);
        } catch (Exception e) {
            logger.error("新增公司数据失败,请求参数为[{}]", paraStr, e);
            jsonResult.setErrorMsg("新增公司数据失败");
        }
        return jsonResult;
    }

    /**
     * 修改数据库连接信息
     * @param user 当前用户
     * @param companyinfo 数据库连接信息
     * @return 修改标志
     * @throws InterruptedException
     */
    @RequestMapping(value="/update", method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult updateConnectionInfo(@CurrentUser SysUser user, @TranslateObject CompanyInfo companyinfo) throws InterruptedException {
        JsonResult jsonResult = new JsonResult();
        String paraStr = JSON.toJSONString(companyinfo);
        logger.info("修改数据库连接信息");
        try {
            companyinfo.setUser(user);
            companyManageService.updateConnectionInfo(companyinfo);
            jsonResult.setFlag(1);
        } catch (Exception e) {
            logger.error("修改数据库连接信息失败,请求参数为[{}]", paraStr, e);
            jsonResult.setErrorMsg("修改数据库连接信息失败");
        }
        return jsonResult;
    }

    /**
     * 删除数据库连接
     * @param companyinfo 数据库连接信息
     * @return 删除标志
     * @throws InterruptedException
     */
    @RequestMapping(value="/delete", method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult deleteConnectionInfo(@TranslateObject CompanyInfo companyinfo) throws InterruptedException {
        JsonResult jsonResult = new JsonResult();
        String paraStr = JSON.toJSONString(companyinfo);
        logger.info("删除公司信息");
        try {
            companyManageService.deleteConnectionInfo(companyinfo);
            jsonResult.setFlag(1);
        } catch (Exception e) {
            logger.error("删除公司信息失败,请求参数为[{}]", paraStr, e);
            jsonResult.setErrorMsg("删除公司信息失败");
        }
        return jsonResult;
    }

    /**
     * 删除数据库连接
     * @return 删除标志
     * @throws InterruptedException
     */
    @RequestMapping(value="/dict", method= RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult queryCompanyDict() throws InterruptedException {
        JsonResult jsonResult = new JsonResult();
        logger.info("查询公司信息");
        try {
            List<CompanyInfo> list = companyManageService.queryCompanyDict();
            jsonResult.setData(list);
            jsonResult.setFlag(1);
        } catch (Exception e) {
            logger.error("删除公司信息失败,请求参数为[{}]", e);
            jsonResult.setErrorMsg("删除公司信息失败");
        }
        return jsonResult;
    }
}