package com.hisign.code.web.action.business;

import com.alibaba.fastjson.JSON;
import com.hisign.code.api.business.TableColumnService;
import com.hisign.code.api.business.TranslationDictService;
import com.hisign.code.model.business.WeChatModel;
import com.hisign.code.model.common.JsonResult;
import com.hisign.code.model.business.TableColumn;
import com.hisign.code.model.system.SysUser;
import com.hisign.code.web.bind.annotation.CurrentUser;
import com.hisign.code.web.bind.annotation.TranslateObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

/**
 * 字段信息action
 * @author jiangpeng
 * @since 2017/05/24 17:27
 */
@Controller
@RequestMapping(value="/wx/business/WeChart")
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


}