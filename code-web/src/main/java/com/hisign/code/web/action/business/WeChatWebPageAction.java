package com.hisign.code.web.action.business;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.hisign.code.api.business.ApplicationService;
import com.hisign.code.api.business.ReportManageService;
import com.hisign.code.api.business.WeChartUserService;
import com.hisign.code.api.business.WeChatWebPageService;
import com.hisign.code.model.business.ApplicationInfo;
import com.hisign.code.model.business.ReportInfo;
import com.hisign.code.model.business.TableColumn;
import com.hisign.code.model.business.WeChartUserInfo;
import com.hisign.code.model.common.JsonResult;
import com.hisign.code.model.system.SysUser;
import com.hisign.code.web.bind.annotation.CurrentUser;
import com.hisign.code.web.bind.annotation.TranslateObject;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.AccessController;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivilegedAction;
import java.text.Collator;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

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
     * 开发语句接口
     */
    @Resource
    private WeChartUserService weChartUserService;

    /**
     * 表连接接口
     */
    @Resource
    private ReportManageService reportManageService;

    @Resource
    private ApplicationService applicationService;

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
            if (loginUser==null) {
                jsonResult.setFlag(0);
                return jsonResult;
            } else {
                jsonResult.setSuccessData(loginUser, 1);
            }
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
    @RequestMapping(value="/query/loginId", method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult queryLoginByIdWebPage(@TranslateObject WeChartUserInfo weChartUserInfo) throws Exception {
        JsonResult jsonResult = new JsonResult();
        String paraStr = JSON.toJSONString(weChartUserInfo);
        logger.info("获取表信息");
        try {
            WeChartUserInfo loginUser = weChatWebPageService.queryLoginByIdWebPage(weChartUserInfo);
            if (loginUser==null) {
                jsonResult.setFlag(0);
                return jsonResult;
            } else {
                jsonResult.setSuccessData(loginUser, 1);
            }
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
     * 更新用户信息
     * @param weChartUserInfo 字段信息查询条件
     * @return 字段信息列表信息
     * @throws InterruptedException
     */
    @RequestMapping(value="/register/update", method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult registerUpdateWebPageUser(@TranslateObject WeChartUserInfo weChartUserInfo) throws InterruptedException {
        JsonResult jsonResult = new JsonResult();
        String paraStr = JSON.toJSONString(weChartUserInfo);
        logger.info("获取字段信息列表信息");
        try {
            weChartUserService.registerUpdateWebPageUser(weChartUserInfo);
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
     * 查询检测申请
     * @param applicationInfo
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/application/query", method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult queryApplication(@TranslateObject ApplicationInfo applicationInfo)  throws Exception {
        JsonResult jsonResult = new JsonResult();
        String paraStr = JSON.toJSONString(applicationInfo);;
        logger.info("获取字段信息列表信息");
        try {
            applicationInfo.setBegin(0);
            applicationInfo.setEnd(9999);
            List<ApplicationInfo> list = applicationService.findApplicationInfo(applicationInfo);
            jsonResult.setData(list);
            jsonResult.setFlag(1);
        } catch (Exception e) {
            jsonResult.setFlag(0);
            logger.error("查询检测申请出错,请求参数为[{}]", paraStr, e);
        }
        return jsonResult;
    }

    /**
     * 新增检测申请
     * @param applicationInfo
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/application/add", method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult insertApplication(@TranslateObject ApplicationInfo applicationInfo)  throws Exception {
        JsonResult jsonResult = new JsonResult();
        String paraStr = JSON.toJSONString(applicationInfo);;
        logger.info("获取字段信息列表信息");
        try {
            applicationService.insertApplication(applicationInfo);
            jsonResult.setFlag(1);
        } catch (Exception e) {
            jsonResult.setFlag(0);
            logger.error("新增检测申请出错,请求参数为[{}]", paraStr, e);
        }
        return jsonResult;
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

            pathOut = pathOut + System.getProperty("file.separator") + "tempPdf";
            file = new File(pathOut);
            if(!file.exists()) {
                file.mkdir();
            }
            deleteDir(dateStr,dateBerforeStr,pathOut);
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

            String picPath = dir + System.getProperty("file.separator") + "file" + System.getProperty("file.separator") + "tempPic";
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

            String fileName = time+".pdf";
            byte2File(bytes,pdfPath, fileName);
            changePdfToImg(pdfPath,picPath,time+"");
            List<String> list = new ArrayList<>();
            list.add(pdfPath + fileName);
            list.add(picPath);
            jsonResult.setData(list);
            jsonResult.setFlag(1);
        } catch (Exception e) {
            logger.error("获取表连接信息失败,请求参数为[{}]", e);
            jsonResult.setErrorMsg("获取表连接信息失败");
        }
        return jsonResult;
    }

    /**
     * 获取字段信息列表信息
     * @param id 字段信息查询条件
     * @return 字段信息列表信息
     * @throws InterruptedException
     */
    @RequestMapping(value="/pic", method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult findPicInfo(@TranslateObject String id,HttpServletResponse response) throws InterruptedException {
        JsonResult jsonResult = new JsonResult();
        try {
            ReportInfo reportinfo = reportManageService.findPicInfo(id);
            Map<String,Object> map = new HashMap<>();
            map.put("pic",reportinfo);
            String path = reportinfo.getFilePath();
            File file = new File(path);
            List<String> list = new ArrayList<>();
            List<String> pathList = new ArrayList<>();
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
                pathList.add(file + System.getProperty("file.separator") + str);
            }
            map.put("img",pathList);
            jsonResult.setData(map);
            jsonResult.setFlag(1);
        } catch (Exception e) {
            logger.error("获取字段信息列表信息失败,请求参数为[{}]", id, e);
            jsonResult.setErrorMsg("获取字段信息列表信息失败");
        }
        return jsonResult;
    }

    /**
     * 获取字段信息列表信息
     * @return 字段信息列表信息
     * @throws InterruptedException
     */
    @RequestMapping(value="/download/pdf", method= RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ResponseEntity<byte[]> download(HttpServletRequest request, HttpServletResponse response) throws Exception {
        boolean flag = isOSLinux();
        String dir = "";
        if(flag) {
            dir = reportManageService.querySysParam("filePath");
        }
        String fileName = request.getParameter("fileName");
        if(fileName!=null) {
            fileName = URLDecoder.decode(fileName,"UTF-8");
        }
        String pdfPath = request.getParameter("pdfPath");
        if(fileName!=null) {
            pdfPath = URLDecoder.decode(pdfPath,"UTF-8");
        }
        if(fileName==null || fileName=="" ){
            return null;
        }
        HttpHeaders headers = new HttpHeaders();
        File file= new File(dir+System.getProperty("file.separator")+pdfPath);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", new String(fileName.getBytes("utf-8"), "ISO8859-1"));
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);
    }

    /**
     * pdf转png
     * @param instructiopath
     * @param picturepath
     * @return
     */
    public static int changePdfToImg(String instructiopath,String picturepath,String fileName) {
        int countpage =0;
        try {
            File file = new File(instructiopath + System.getProperty("file.separator") + fileName +".pdf");
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            FileChannel channel = raf.getChannel();
            MappedByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY,
                    0, channel.size());
            PDFFile pdffile = new PDFFile(buf);
            //创建图片文件夹
            File dirfile = new File(picturepath);
            if(!dirfile.exists()){
                dirfile.mkdirs();
            }
            //获得图片页数
            countpage = pdffile.getNumPages();
            for (int i=0;i<countpage;i++) {
                if (pdffile.getNumPages() > 0) {
                    PDFPage page = pdffile.getPage(i+1);
                    Rectangle rect = new Rectangle(0, 0, ((int) page.getBBox()
                            .getWidth()), ((int) page.getBBox().getHeight()));
                    int n = 2;
                    /** 图片清晰度（n>0且n<7）【pdf放大参数】 */
                    Image img = page.getImage(rect.width * n, rect.height * n,
                            rect, /** 放大pdf到n倍，创建图片。 */
                            null, /** null for the ImageObserver */
                            true, /** fill background with white */
                            true /** block until drawing is done */
                    );
                    BufferedImage tag = new BufferedImage(rect.width * n,
                            rect.height * n, BufferedImage.TYPE_INT_RGB);
                    tag.getGraphics().drawImage(img, 0, 0, rect.width * n,
                            rect.height * n, null);
                    /**
                     * File imgfile = new File("D:\\work\\mybook\\FilesNew\\img\\" +
                     * i + ".jpg"); if(imgfile.exists()){
                     * if(imgfile.createNewFile()) { System.out.println("创建图片："+
                     * "D:\\work\\mybook\\FilesNew\\img\\" + i + ".jpg"); } else {
                     * System.out.println("创建图片失败！"); } }
                     */
                    FileOutputStream out = new FileOutputStream(picturepath + System.getProperty("file.separator") + fileName + "_" + i+ ".png");
                    /** 输出到文件流 */
                    JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
                    JPEGEncodeParam param2 = encoder.getDefaultJPEGEncodeParam(tag);
                    param2.setQuality(1f, true);
                    /** 1f~0.01f是提高生成的图片质量 */
                    encoder.setJPEGEncodeParam(param2);
                    encoder.encode(tag);
                    /** JPEG编码 */
                    out.close();
                }
            }
            channel.close();
            raf.close();
            unmap(buf);
            /** 如果要在转图片之后删除pdf，就必须要这个关闭流和清空缓冲的方法 */
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return countpage;
    }

    @SuppressWarnings("unchecked")
    public static void unmap(final Object buffer) {
        AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                try {
                    Method getCleanerMethod = buffer.getClass().getMethod(
                            "cleaner", new Class[0]);
                    getCleanerMethod.setAccessible(true);
                    sun.misc.Cleaner cleaner = (sun.misc.Cleaner) getCleanerMethod
                            .invoke(buffer, new Object[0]);
                    cleaner.clean();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
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