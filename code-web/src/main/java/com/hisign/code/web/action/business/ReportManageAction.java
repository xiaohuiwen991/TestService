package com.hisign.code.web.action.business;

import com.alibaba.fastjson.JSON;
import com.hisign.code.api.business.ReportManageService;
import com.hisign.code.model.business.ReportInfo;
import com.hisign.code.model.business.TableConnection;
import com.hisign.code.model.common.JsonResult;
import com.hisign.code.model.system.SysUser;
import com.hisign.code.web.bind.annotation.CurrentUser;
import com.hisign.code.web.bind.annotation.TranslateObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

/**
 * 表连接action
 * @author xiaohuiwen
 * @since 2017/05/27 14:49
 */
@Controller
@RequestMapping(value="/api/{recordLog}/business/report")
public class ReportManageAction {

    /**
     * 记录日志
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 表连接接口
     */
    @Resource
    private ReportManageService reportManageService;

    /**
     * 获取表连接列表信息
     * @param reportinfo 表连接查询条件
     * @return 表连接列表信息
     * @throws InterruptedException
     */
    @RequestMapping(value="/list", method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult queryReportList(@TranslateObject ReportInfo reportinfo, @CurrentUser SysUser user) throws InterruptedException {
        JsonResult jsonResult = new JsonResult();
        String paraStr = JSON.toJSONString(reportinfo);
        logger.info("获取表连接列表信息");
        try {
            reportinfo.setUser(user);
            reportinfo.setBegin(reportinfo.getBegin()-1);
            List<TableConnection> list = reportManageService.queryReportList(reportinfo);
            int count =  reportManageService.queryReportListForCount(reportinfo);
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
     * @param reportinfo 表连接信息
     * @return 新增编号
     * @throws InterruptedException
     */
    @RequestMapping(value="/add", method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult insertReportInfo(@CurrentUser SysUser user, @TranslateObject ReportInfo reportinfo) throws InterruptedException {
        JsonResult jsonResult = new JsonResult();
        String paraStr = JSON.toJSONString(reportinfo);
        logger.info("新增表连接");
        try {
            reportinfo.setUser(user);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            long time = System.currentTimeMillis();
            String dateStr = sdf.format(time);//获取当前日期
            boolean flag = isOSLinux();
            String dir = "";
            if(flag) {
                dir = reportManageService.querySysParam("filePath");
            } else {
                dir = "D:" + System.getProperty("file.separator");
            }

            String pathOut = dir + "file";
            File file = new File(pathOut);
            if(!file.exists()) {
                file.mkdir();
            }

            pathOut = pathOut + System.getProperty("file.separator") + "pdfFile";
            file = new File(pathOut);
            if(!file.exists()) {
                file.mkdir();
            }

            String path = pathOut + System.getProperty("file.separator") + dateStr;
            file = new File(path);
            if(!file.exists()) {
                file.mkdir();
            }
            String pdfPath = path + System.getProperty("file.separator") + uuid + System.getProperty("file.separator");
            file = new File(pdfPath);
            if(!file.exists()) {
                file.mkdir();
            }
            String fileName = reportinfo.getFileName();
            copyFile(reportinfo.getFilePath(),pdfPath+fileName);
            reportinfo.setPdfPath(pdfPath+fileName);
            reportManageService.insertReportInfo(reportinfo);
            File tempFile = new File(reportinfo.getFilePath());
            if (tempFile.isFile() && tempFile.exists()) {// 路径为文件且不为空则进行删除
                tempFile.delete();// 文件删除
            }
            jsonResult.setSuccessData("");
        } catch (Exception e) {
            logger.error("新增表连接失败,请求参数为[{}]", paraStr, e);
            jsonResult.setErrorMsg("新增表连接失败");
        }
        return jsonResult;
    }

    /**
     * 修改表连接信息
     * @param user 当前用户
     * @param reportinfo 表连接信息
     * @return 修改标志
     * @throws InterruptedException
     */
    @RequestMapping(value="/edit", method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult updateTableConnection(@CurrentUser SysUser user, @TranslateObject  ReportInfo reportinfo) throws InterruptedException {
        JsonResult jsonResult = new JsonResult();
        String paraStr = JSON.toJSONString(reportinfo);
        logger.info("修改表连接信息");
        try {
            reportinfo.setUser(user);
            if(!StringUtils.isEmpty(reportinfo.getId())) {
                reportManageService.updateReportInfo(reportinfo);
            }
            jsonResult.setFlag(1);
        } catch (Exception e) {
            logger.error("修改表连接信息失败,请求参数为[{}]", paraStr, e);
            jsonResult.setErrorMsg("修改表连接信息失败");
        }
        return jsonResult;
    }

    /**
     * 删除表连接
     * @param reportinfo 表连接信息
     * @return 删除标志
     * @throws InterruptedException
     */
    @RequestMapping(value="/delete", method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult deleteTableConnection(@TranslateObject  ReportInfo reportinfo) throws InterruptedException {
        JsonResult jsonResult = new JsonResult();
        String paraStr = JSON.toJSONString(reportinfo);
        logger.info("删除表连接");
        try {
            if(!StringUtils.isEmpty(reportinfo.getId())) {
                reportManageService.deleteReportInfo(reportinfo);
            }
            jsonResult.setFlag(1);
        } catch (Exception e) {
            logger.error("删除表连接失败,请求参数为[{}]", paraStr, e);
            jsonResult.setErrorMsg("删除表连接失败");
        }
        return jsonResult;
    }

    /**
     * 复制文件
     * @param oldPath
     * @param newPath
     * <br/>
     * 2016年12月19日  下午3:31:50
     */
    public void copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            InputStream inStream = new FileInputStream(oldPath); //读入原文件
            FileOutputStream fs = new FileOutputStream(newPath);
            byte[] buffer = new byte[1444];
            int length;
            while ( (byteread = inStream.read(buffer)) != -1) {
                bytesum += byteread; //字节数 文件大小
                System.out.println(bytesum);
                fs.write(buffer, 0, byteread);
            }
            inStream.close();
        }
        catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();

        }

    }

    public static boolean isOSLinux() {
        Properties prop = System.getProperties();
        String os = prop.getProperty("os.name");
        if (os != null && os.toLowerCase().indexOf("linux") > -1) {
            return true;
        } else {
            return false;
        }
    }


}