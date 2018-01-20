package com.hisign.code.web.action.system;


import com.hisign.code.api.common.ICacheService;
import com.hisign.code.api.system.DictManageService;
import com.hisign.code.model.common.JsonResult;
import com.hisign.code.model.system.SysDictModel;
import com.hisign.code.model.system.SysUser;
import com.hisign.code.web.bind.annotation.CurrentUser;
import com.hisign.code.web.bind.annotation.TranslateObject;
import com.hisign.commonutil.util.ClassUtil;
import com.hisign.excel.ExcelUtil;
import com.hisign.excel.ParseHtmlToXls;
import com.hisign.template.TemplateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字典管理控制器
 * @author xiaohuiwen
 * @since 2016/11/29 11:56
 */
@Controller
@RequestMapping(value = "/api/{recordLog}/system/dict_mng")
public class DictManageAction {


    /**
     * 记录日志
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 字典管理接口
     */
    @Resource
    private DictManageService dictManageService;

    /**
     * 缓存接口
     */
    @Resource
    private ICacheService cacheService;


    /**
     * 获得字典列表
     * @param dict 字典信息
     * @return 字典列表
     * @throws InterruptedException
     */
    @RequestMapping(value="/list", method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult getDictList(@TranslateObject SysDictModel dict) throws InterruptedException {
        JsonResult jsonResult = new JsonResult();
        logger.info("获得字典列表");
        try {
//            if(!StringUtils.isEmpty(dict.getPy())) {
//                dict.setPy(dict.getPy().toUpperCase());
//            }
            List<SysDictModel> list = dictManageService.findDictByFilter(dict);
            int count = dictManageService.findDictByFilterForCount(dict);
            //查询条件放入缓存
            cacheService.put("query_dict", dict);
            jsonResult.setSuccessData(list, count);
            return jsonResult;
        } catch (Exception e) {
            logger.error("获得字典列表失败", e);
            return new JsonResult(0, "获取字典列表失败！");
        }
    }

    /**
     * 删除字典
     * @param dict 字典信息
     * @return 删除字典标志
     * @throws InterruptedException
     */
    @RequestMapping(value="/delete", method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult deleteDict(@TranslateObject SysDictModel dict) throws InterruptedException {
        JsonResult jsonResult = new JsonResult();
        logger.info("删除编号为[{}]的字典", dict.getId());
        try {
            dictManageService.deleteDict(dict);
            jsonResult.setFlag(1);
            return jsonResult;
        } catch (Exception e) {
            logger.error("删除编号为[{}]的字典失败", dict.getId(), e);
            jsonResult = new JsonResult(0, "删除字典失败！");
            return jsonResult;
        }
    }

    /**
     * 字典新增
     * @param user 当前用户
     * @param dict 字典信息
     * @return 新增标志
     * @throws InterruptedException
     */
    @RequestMapping(value="/add", method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult insertDict(@CurrentUser SysUser user, @TranslateObject SysDictModel dict) throws InterruptedException {
        JsonResult jsonResult = new JsonResult();
        logger.info("新增字典代码为[{}]的字典", dict.getKey());
        try {
            if(StringUtils.isEmpty(dict.getParentKey())) {
                SysDictModel sysDict = ClassUtil.getObjectByArray(SysDictModel.class, "root", dict.getRoot());
                sysDict = dictManageService.findDict(sysDict);
                if (sysDict != null) {
                    return new JsonResult(0, "字典类型已存在!");
                }
            }
            dictManageService.insertDict(dict, user);
            jsonResult.setFlag(1);
            return jsonResult;
        } catch (Exception e) {
            logger.error("新增字典代码为[{}]的字典失败", dict.getKey(),  e);
            jsonResult = new JsonResult(0, "新增失败!");
            return jsonResult;
        }
    }

    /**
     * 字典修改
     * @param user 当前用户
     * @param dict 字典信息
     * @return 修改标志
     * @throws InterruptedException
     */
    @RequestMapping(value="/edit", method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult editDict(@CurrentUser SysUser user, @TranslateObject SysDictModel dict) throws InterruptedException {
        JsonResult jsonResult = new JsonResult();
        logger.info("修改字典代码为[{}]的字典", dict.getKey());
        try {
            if(StringUtils.isEmpty(dict.getParentKey())) {
                SysDictModel sysDict = ClassUtil.getObjectByArray(SysDictModel.class, "root,dictKey,localId", dict.getRoot(), dict.getDictKey(), dict.getId());
                sysDict = dictManageService.findDict(sysDict);
                if (sysDict != null) {
                    return new JsonResult(0, "字典类型已存在!");
                }
            }
            dictManageService.updateDict(dict, user);
            jsonResult.setFlag(1);
            return jsonResult;
        } catch (Exception e) {
            logger.error("修改字典代码为[{}]的字典失败", dict.getKey(), e);
            jsonResult = new JsonResult(0, "修改失败!");
            return jsonResult;
        }
    }

    /**
     * 获得字典信息
     * @param filter 字典信息
     * @return 字典信息
     * @throws InterruptedException
     */
    @RequestMapping(value="/dict_info", method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult getDict(@TranslateObject SysDictModel filter) throws InterruptedException {
        JsonResult jsonResult = new JsonResult();
        logger.info("获得字典编号为[{}]的字典信息", filter.getId());
        try {
            SysDictModel dict = dictManageService.findDict(filter);
            jsonResult.setSuccessData(dict, dict == null ? 0 : 1);
            return jsonResult;
        } catch (Exception e) {
            logger.error("获得字典编号为[{}]的字典信息失败", filter.getId(), e);
            jsonResult = new JsonResult(0, "获取字典信息失败！");
            return jsonResult;
        }
    }

    /**
     * 导出excel
     * @param request  request对象
     * @param response response对象
     * @return
     */
    @RequestMapping(value = "/export", produces = {"application/vnd.ms-excel;charset=UTF-8"})
    @ResponseBody
    public JsonResult exportDictList(HttpServletRequest request, HttpServletResponse response) {
        try{
            Map<String, Object> map = new HashMap<>();
            String sheetName = "字典管理信息";
            String fileName = "excelTemplates/dict_manager.vm";
            //获取查询条件
            SysDictModel dict= cacheService.get("query_dict", SysDictModel.class);
            dict.setBeginAndEnd(0, 0);
            List<SysDictModel> list = dictManageService.findDictByFilter(dict);
            map.put("resultList",list);
            String htmlStr = TemplateUtil.parseTemplate(map, fileName);
            if (StringUtils.isEmpty(htmlStr)) return new JsonResult(0,"导出失败!");
            //获得当前时间字符串
            Format format = new SimpleDateFormat("yyyyMMddHHmmss");
            String dateString = format.format(new Date());
            //生成Excel工作薄对象
            HSSFWorkbook wb = ParseHtmlToXls.parseHtmlToXlsForMultiTitle(htmlStr, sheetName);
            ExcelUtil.writeWorkbook(wb, request, response, sheetName + "_" + dateString);
            return new JsonResult(1,"");
        }catch (Exception e){
            logger.error("导出失败", e);
            return new JsonResult(0,"导出失败!");
        }
    }


}
