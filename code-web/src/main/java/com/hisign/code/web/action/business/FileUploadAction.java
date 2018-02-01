package com.hisign.code.web.action.business;

import com.hisign.code.constant.Constants;
import com.hisign.code.model.common.JsonResult;
import com.hisign.code.model.system.SysUser;
import com.hisign.code.web.bind.annotation.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * 文件上传action
 * @author xiaohuiwen
 * @since 2017/05/27 14:49
 */
@Controller
@RequestMapping(value="/file/{recordLog}/business/report")
public class FileUploadAction {

    /**
     * 记录日志
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 获取表连接信息
     * @param request 表连接信息
     * @return 删除标志
     * @throws InterruptedException
     */
    @RequestMapping(value="/upload", method= RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonResult findTableConnectionInfo(@CurrentUser SysUser user,
                                              HttpServletRequest request,
                                              @RequestParam(value = "upLoadFile", required = false) MultipartFile ff) throws InterruptedException {
        JsonResult jsonResult = new JsonResult();
        logger.info("获取表连接信息");
        try {
            byte[] bytes = ff.getBytes();
            String path = Constants.SYS_PARAM_MAP.get("filePath");
            byte2File(bytes,path,"test.doc");
//            TableConnection info = reportManageService.findTableConnectionInfo(tableConnection);
//            jsonResult.setSuccessData(info);
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
}
