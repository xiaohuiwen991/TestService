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
import java.text.Collator;
import java.text.SimpleDateFormat;
import java.util.*;

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

            String pathOut = dir + System.getProperty("file.separator") + "file";
            File file = new File(pathOut);
            if(!file.exists()) {
                file.mkdir();
            }

            pathOut = pathOut + System.getProperty("file.separator") + "pdfFile";
            file = new File(pathOut);
            if(!file.exists()) {
                file.mkdir();
            }

            pathOut = pathOut + System.getProperty("file.separator") + dateStr;
            file = new File(pathOut);
            if(!file.exists()) {
                file.mkdir();
            }
            String pdfPath = pathOut + System.getProperty("file.separator") + uuid + System.getProperty("file.separator");
            file = new File(pdfPath);
            if(!file.exists()) {
                file.mkdir();
            }

            String picPath = dir + System.getProperty("file.separator") + "file" + System.getProperty("file.separator") + "picFile";
            file = new File(picPath);
            if(!file.exists()) {
                file.mkdir();
            }

            picPath = picPath + System.getProperty("file.separator") + dateStr;
            file = new File(picPath);
            if(!file.exists()) {
                file.mkdir();
            }
            picPath = picPath + System.getProperty("file.separator") + uuid + System.getProperty("file.separator");
            file = new File(picPath);
            if(!file.exists()) {
                file.mkdir();
            }

            String fileName = reportinfo.getFileName();
            copyFile(reportinfo.getFilePath(),pdfPath+fileName);

            copyDir(reportinfo.getPicPath(),picPath);

            reportinfo.setPdfPath(pdfPath+fileName);
            reportinfo.setFilePath(picPath);
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
                ReportInfo info = reportManageService.queryReportInfoById(reportinfo);
                if (info!=null) {
                    if (!com.alibaba.druid.util.StringUtils.isEmpty(info.getPdfPath())) {
                        if(info.getPdfPath().lastIndexOf(File.separator)!=-1) {
                            deleteDirectory(info.getPdfPath().substring(0,info.getPdfPath().lastIndexOf(File.separator)), true);
                        }
                    }
                    if (!com.alibaba.druid.util.StringUtils.isEmpty(info.getFilePath())) {
                        deleteDirectory(info.getFilePath(), true);
                    }
                }
                reportManageService.deleteReportInfo(reportinfo);
            }
            jsonResult.setFlag(1);
        } catch (Exception e) {
            logger.error("删除表连接失败,请求参数为[{}]", paraStr, e);
            jsonResult.setErrorMsg("删除表连接失败"+e);
        }
        return jsonResult;
    }

    /**
     * 拷贝目录
     * @param oldPath
     * @param newPath
     */
    public void copyDir(String oldPath, String newPath) {
        File file = new File(oldPath);
        List<String> list = new ArrayList<>();
        if (file != null) {
            File[] fileList = file.listFiles();
            if(fileList!=null && fileList.length>0) {
                for (File f : fileList) {
                    if(f.isFile()) {
                        list.add(f.getName());
                    }
                }
            }
        }
        Collections.sort(list, Collator.getInstance(java.util.Locale.CHINA));
        for (String str : list) {
            copyFile(oldPath + System.getProperty("file.separator") + str,newPath + System.getProperty("file.separator") + str);
        }
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
            fs.close();
        } catch (Exception e) {
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

    /**
     * 删除文件目录及目录下文件
     * @param dirPath
     * @param isDel
     * @return
     */
    public boolean deleteDirectory(String dirPath,boolean isDel) {// 删除目录（文件夹）以及目录下的文件
        // 如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!dirPath.endsWith(File.separator)) {
            dirPath = dirPath + File.separator;
        }
        File dirFile = new File(dirPath);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        File[] files = dirFile.listFiles();// 获得传入路径下的所有文件
        for (int i = 0; i < files.length; i++) {// 循环遍历删除文件夹下的所有文件(包括子目录)
            if (files[i].isFile()) {// 删除子文件
                flag = deleteFile(files[i].getAbsolutePath());
                System.out.println(files[i].getAbsolutePath() + " 删除成功");
                if (!flag)
                    break;// 如果删除失败，则跳出
            } else {// 运用递归，删除子目录
                flag = deleteDirectory(files[i].getAbsolutePath(),true);
                if (!flag)
                    break;// 如果删除失败，则跳出
            }
        }
        if (!flag)
            return false;
        if(isDel) {
            if (dirFile.delete()) {// 删除当前目录
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    public boolean deleteFile(String filePath) {// 删除单个文件
        boolean flag = false;
        File file = new File(filePath);
        if (file.isFile() && file.exists()) {// 路径为文件且不为空则进行删除
            file.delete();// 文件删除
            flag = true;
        }
        return flag;
    }

}