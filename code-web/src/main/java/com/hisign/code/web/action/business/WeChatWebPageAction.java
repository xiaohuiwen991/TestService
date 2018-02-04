package com.hisign.code.web.action.business;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.hisign.code.api.business.ReportManageService;
import com.hisign.code.api.business.WeChatWebPageService;
import com.hisign.code.model.business.TableColumn;
import com.hisign.code.model.business.WeChartUserInfo;
import com.hisign.code.model.common.JsonResult;
import com.hisign.code.model.system.SysUser;
import com.hisign.code.web.bind.annotation.CurrentUser;
import com.hisign.code.web.bind.annotation.TranslateObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 字段信息action
 * @author xiaohuiwen
 * @since 2017/05/24 17:27
 */
@Controller
@RequestMapping(value="/wx/business/WeChart")
public class WeChatWebPageAction {

    /**
     * 记录日志
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 字段信息接口
     */
    @Resource
    private WeChatWebPageService weChatWebPageService;

    /**
     * 表连接接口
     */
    @Resource
    private ReportManageService reportManageService;

    /**
     * 获取web端用户登录信息
     * @param weChartUserInfo web端用户登录信息
     * @return  web端用户登录信息
     * @throws InterruptedException
     */
    @RequestMapping(value="/login", method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult loginWebPage(@TranslateObject WeChartUserInfo weChartUserInfo) throws Exception {
        JsonResult jsonResult = new JsonResult();
        String paraStr = JSON.toJSONString(weChartUserInfo);
        logger.info("获取表信息");
        try {
            WeChartUserInfo loginUser = weChatWebPageService.queryLoginInfo(weChartUserInfo);
            jsonResult.setSuccessData(loginUser, 1);
        } catch (Exception e) {
            logger.error("获取表信息失败,请求参数为[{}]", paraStr, e);
            jsonResult.setErrorMsg("获取表信息失败");
        }
        return jsonResult;
    }

    /**
     * 获取web端用户登录信息
     * @param weChartUserInfo web端用户登录信息
     * @return  web端用户登录信息
     * @throws InterruptedException
     */
    @RequestMapping(value="/check", method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult checkUserName(@TranslateObject WeChartUserInfo weChartUserInfo) throws Exception {
        JsonResult jsonResult = new JsonResult();
        String paraStr = JSON.toJSONString(weChartUserInfo);
        logger.info("获取表信息");
        try {
            int num = weChatWebPageService.checkUserName(weChartUserInfo);
            jsonResult.setSuccessData(num, 1);
        } catch (Exception e) {
            logger.error("获取表信息失败,请求参数为[{}]", paraStr, e);
            jsonResult.setErrorMsg("获取表信息失败");
        }
        return jsonResult;
    }

    /**
     * 获取字段信息列表信息
     * @param weChartUserInfo 字段信息查询条件
     * @return 字段信息列表信息
     * @throws InterruptedException
     */
    @RequestMapping(value="/register", method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult registerWebPageUser(@TranslateObject WeChartUserInfo weChartUserInfo) throws InterruptedException {
        JsonResult jsonResult = new JsonResult();
        String paraStr = JSON.toJSONString(weChartUserInfo);
        logger.info("获取字段信息列表信息");
        try {
            weChartUserInfo.setPassword(weChartUserInfo.getPassword1());
            weChatWebPageService.registerWebPageUser(weChartUserInfo);
            jsonResult.setData(1);
            jsonResult.setFlag(1);
        } catch (Exception e) {
            logger.error("获取字段信息列表信息失败,请求参数为[{}]", paraStr, e);
            jsonResult.setData(0);
            jsonResult.setErrorMsg("获取字段信息列表信息失败");
        }
        return jsonResult;
    }

    /**
     * 获取字段信息列表信息
     * @param id 用户id
     * @return 字段信息列表信息
     * @throws InterruptedException
     */
    @RequestMapping(value="/report", method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult findReportInfo(@TranslateObject String id) throws InterruptedException {
        JsonResult jsonResult = new JsonResult();
        logger.info("获取字段信息列表信息");
        try {
            List<String> list = weChatWebPageService.findReportInfo(id);
            jsonResult.setData(list);
            jsonResult.setFlag(1);
        } catch (Exception e) {
            logger.error("获取字段信息列表信息失败,请求参数为[{}]", e);
            jsonResult.setData(0);
            jsonResult.setErrorMsg("获取字段信息列表信息失败");
        }
        return jsonResult;
    }

    /**
     * 获取字段信息列表信息
     * @param id 字段信息查询条件
     * @return 字段信息列表信息
     * @throws InterruptedException
     */
    @RequestMapping(value="/view", method= RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public void findColumnList(@TranslateObject String id,HttpServletResponse response) throws InterruptedException {
        JsonResult jsonResult = new JsonResult();
        String paraStr = id;
        logger.info("获取字段信息列表信息");
        try {
            String path = reportManageService.findPdfPath(id);
            if (!StringUtils.isEmpty(path)) {
                File file = new File(path);
                InputStream in = new FileInputStream(file);
                FileCopyUtils.copy(in,response.getOutputStream());
            }
        } catch (Exception e) {
            logger.error("获取字段信息列表信息失败,请求参数为[{}]", paraStr, e);
        }
    }


    /**
     * 获取字段信息列表信息
     * @param signature 字段信息查询条件
     * @return 字段信息列表信息
     * @throws InterruptedException
     */
    @RequestMapping(value="/apply", method= RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public void applyWeChartService(HttpServletResponse response, @RequestParam(value="signature", required=true) String signature, @RequestParam(value="timestamp", required=true) String timestamp, @RequestParam(value="nonce", required=true) String nonce, @RequestParam(value="echostr", required=true) String echostr) throws InterruptedException {
        JsonResult jsonResult = new JsonResult();
        logger.info("获取字段信息列表信息:signature:"+signature+"timestamp:"+timestamp+"nonce:"+nonce+"echostr:"+echostr);
        try {
            PrintWriter out = response.getWriter();
            if (checkSignature(signature, timestamp, nonce)) {
                logger.info("1:"+echostr);
                out.print(echostr);
            }else{
                logger.info("2:"+echostr);
                out.print(echostr);
            }

        } catch (Exception e) {
            logger.error("获取字段信息列表信息失败,请求参数为[{}]", signature+timestamp+nonce+echostr, e);
            jsonResult.setErrorMsg("获取字段信息列表信息失败");
        }
    }

    /**
     * 获取表连接信息
     * @return 删除标志
     * @throws InterruptedException
     */
    @RequestMapping(value="/upload", method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult findTableConnectionInfo( HttpServletRequest request,@RequestParam(value = "upLoadFile", required = false) MultipartFile ff) throws InterruptedException {
        JsonResult jsonResult = new JsonResult();
        logger.info("获取表连接信息");
        try {
            byte[] bytes = ff.getBytes();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            long time = System.currentTimeMillis();
            String  dateStr = sdf.format(time);//获取当前日期
            String  dateBerforeStr = sdf.format(time- 86400000);//获取一天前的日期
            deleteDirectory(dateBerforeStr,true);
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

            pathOut = pathOut + System.getProperty("file.separator") + "tempFfile";
            file = new File(pathOut);
            if(!file.exists()) {
                file.mkdir();
            }
            deleteDir(dateStr,dateBerforeStr,pathOut);
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
            String fileName = time+".pdf";
            byte2File(bytes,pdfPath, fileName);
            jsonResult.setData(pdfPath + fileName);
            jsonResult.setFlag(1);
        } catch (Exception e) {
            logger.error("获取表连接信息失败,请求参数为[{}]", e);
            jsonResult.setErrorMsg("获取表连接信息失败");
        }
        return jsonResult;
    }

    /**
     * 根据byte数组，生成文件
     * @param bfile 文件数组
     * @param filePath 文件存放路径
     * @param fileName 文件名称
     */
    public static void byte2File(byte[] bfile,String filePath,String fileName){
        BufferedOutputStream bos=null;
        FileOutputStream fos=null;
        File file=null;
        try{
            File dir=new File(filePath);
            if(!dir.exists() && !dir.isDirectory()){//判断文件目录是否存在
                dir.mkdirs();
            }
            file=new File(filePath+fileName);
            fos=new FileOutputStream(file);
            bos=new BufferedOutputStream(fos);
            bos.write(bfile);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        finally{
            try{
                if(bos != null){
                    bos.close();
                }
                if(fos != null){
                    fos.close();
                }
            }
            catch(Exception e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private static String token = "souvcweixin";
    /**
     * 方法名：checkSignature</br>
     * 详述：验证签名</br>
     * 开发人员：souvc</br>
     * 创建时间：2015-9-29  </br>
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     * @throws
     */
    public static boolean checkSignature(String signature, String timestamp,String nonce) {
        // 1.将token、timestamp、nonce三个参数进行字典序排序
        String[] arr = new String[] { token, timestamp, nonce };
        Arrays.sort(arr);

        // 2. 将三个参数字符串拼接成一个字符串进行sha1加密
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        MessageDigest md = null;
        String tmpStr = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
            // 将三个参数字符串拼接成一个字符串进行sha1加密
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        content = null;
        // 3.将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
    }

    /**
     * 方法名：byteToStr</br>
     * 详述：将字节数组转换为十六进制字符串</br>
     * 开发人员：souvc </br>
     * 创建时间：2015-9-29  </br>
     * @param byteArray
     * @return
     * @throws
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
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
     * 方法名：byteToHexStr</br>
     * 详述：将字节转换为十六进制字符串</br>
     * 开发人员：souvc</br>
     * 创建时间：2015-9-29  </br>
     * @param mByte
     * @return
     * @throws
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A','B', 'C', 'D', 'E', 'F' };
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];
        String s = new String(tempArr);
        return s;
    }

    /**
     * 删除通用目录
     * @param dateStr
     * @param dateBerforeStr
     */
    public void deleteDir(String dateStr, String dateBerforeStr, String pathOut){
        List<String> fileNames = getFileNames(pathOut); //获得压缩文件目录删除该目录下两天前的目录
        if (fileNames != null && fileNames.size()>0) {
            for( String str : fileNames ) {
                if (!str.equals(dateStr) && !str.equals(dateBerforeStr)) {
                    deleteDirectory(pathOut + System.getProperty("file.separator") + str,true);//删除目录及子目录
                }
            }
        }
    }

    /**
     * 获取所有文件夹名称
     * @param path
     * @return
     */
    public static List<String> getFileNames (String path) {
        File f = new File(path);
        if (!f.exists()) {
            System.out.println(path + " not exists");
            return null;
        }

        File fa[] = f.listFiles();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < fa.length; i++) {
            File fs = fa[i];
            if (fs.isDirectory()) {
                list.add(fs.getName());
            }
        }
        return list;
    }

}