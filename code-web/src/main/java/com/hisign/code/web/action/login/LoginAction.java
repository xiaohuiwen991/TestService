package com.hisign.code.web.action.login;

import com.alibaba.druid.util.StringUtils;
import com.hisign.code.api.system.SysModuleService;
import com.hisign.code.api.system.SysParameterService;
import com.hisign.code.api.system.SysUserService;
import com.hisign.code.constant.Constants;
import com.hisign.code.model.common.JsonResult;
import com.hisign.code.model.index.Index;
import com.hisign.code.model.system.SysModule;
import com.hisign.code.model.system.SysParam;
import com.hisign.code.model.system.SysUser;
import com.hisign.code.util.Md5Helper;
import com.hisign.code.web.bind.annotation.TranslateObject;
import com.hisign.code.web.util.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 登录action
 * @author wangping
 * @version 1.0
 * @since 2016/4/20 9:39
 */
@Controller
public class LoginAction {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private SysUserService sysUserService;

    @Resource
    private SysModuleService sysModuleService;

    @Resource
    private SysParameterService sysParameterService;

    public String intoLogin(HttpServletRequest request, HttpServletResponse response) {
        return "/login";
    }

    /**
     * 用户登录
     * @param user 用户对象
     * @param request 请求对象
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult login(@TranslateObject SysUser user, HttpServletRequest request) {
        JsonResult result = new JsonResult();
        result.setFlag(1);
        boolean loginFlag = false;
        String msg = "";
        String username = user.getUserName();
        String password = user.getUserPwd();
        try {
            logger.info("对用户[{}]进行登录验证..验证开始", username);
            SysUser sysUser = sysUserService.findSysUserByUserName(username);
            if (null == sysUser || (password != null && !StringUtils.equals(sysUser.getUserPwd(), Md5Helper.getMD5(password)))) {
                logger.info("对用户[{}]进行登录验证..验证未通过,用户名或密码错误", username);
                if("".equals(username)&&"".equals(password)) {
                    msg = "请输入用户名和密码！";
                }else if("".equals(username)){
                    msg = "请输入用户名！";
                }else if("".equals(password)){
                    msg = "请输入密码！";
                }else{
                    msg = "用户名或密码不正确，请重新输入！";
                }
            } else {
                if("1".equals(sysUser.getOpenFlag())){
                    loginFlag = true;
                    String tokenId = sysUserService.addUserToken(sysUser.getId());//添加token和用户id的关联
                    result = searchUserLimt(sysUser, tokenId);
                    getSysParameterMap();//给系统参数map赋值
                }else{
                    msg = "当前登录用户已被禁用，请联系管理员！";
                }
            }
        }catch(Exception ae){
            logger.error("对用户[{}]进行登录验证..验证未通过,堆栈轨迹如下", username, ae);
            msg = "无法成功登录";
        }
        //验证是否登录成功
        if(loginFlag){
            logger.info("用户[" + username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
        }else{
            result.setMsg(msg);
        }
        return result;
    }

    /**
     * 注销
     * @auth wangping 10:11 2016/7/13
     * @param user 用户对象
     * @param request 请求对象
     * @return
    **/
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult logout(@TranslateObject SysUser user, HttpServletRequest request) {
        JsonResult result = new JsonResult();
        String token = "";
        try {
            token = RequestUtil.getParameter(request, Constants.TOKEN);
            result.setFlag(1);
            sysUserService.deleteUserToken(token);
        } catch (Exception e) {
            logger.error("logout delete token error!token:{}", token, e);
            result.setFlag(0);
            result.setMsg("删除token出错！");
        }
        return result;
    }

    /**
     * 获取登录用户的模块权限
     * @author xiaohuwien
     * @param user 登录用户对象
     * @return
     */
    public JsonResult searchUserLimt(SysUser user,String tokenId) {
        JsonResult jsonResult = new JsonResult();
        List<SysModule> parentList = new ArrayList<SysModule>();//父id
        List<SysModule> childList = new ArrayList<SysModule>();//子id
        List<SysModule> pageLimit = new ArrayList<SysModule>();//页面权限
        List<SysModule> childNodeList = new ArrayList<SysModule>();//页面子权限
        Index indexParam = new Index();
        Map<String,List<SysModule>> tempMap = new HashMap<String,List<SysModule>>();
        try {
            List<SysModule> list = sysModuleService.findLogUserPower(user.getUserName());//查询所有页面权限
            for(int i=0 ; i<list.size();i++){
                if(null==list.get(i) || StringUtils.isEmpty(list.get(i).getParentId())){
                    parentList.add(list.get(i));
                }else{
                    childList.add(list.get(i));
                }
            }
            for (int i=0;i<childList.size();i++){
                for(int j=childList.size()-1;j>=0;j--){
                    if(childList.get(i).getModuleId().equals(childList.get(j).getParentId())){
                        childNodeList.add(0,childList.get(j));
                        childList.remove(j);
                        if(i>0){i--;}
                        j++;
                    }
                }
            }
            pageLimit = sysModuleService.toolsForList(parentList,childList,childNodeList);//拼接List
            List<SysModule> operateLimit = sysModuleService.findLogUserPowerLimt(user.getUserName());//查询所有操作权限
            List<Map<String,String>> roleList = searchUserRole(user);;//获取用户角色中文
            tempMap.put("pages",pageLimit);
            tempMap.put("operates",operateLimit);
            indexParam.setToken(tokenId);//接口id
            indexParam.setLimits(tempMap);//全部页面权限
            indexParam.setRoles(roleList);//用户角色
            indexParam.setCurrentUser(user);
            jsonResult.setData(indexParam);
            jsonResult.setFlag(1);
        } catch (Exception e) {
            logger.error("获取登录用户[{}]模块权限出错",user.getUserName(),e);
            jsonResult.setFlag(0);
        }
        return jsonResult;
    }

    /**
     * 获取登录用户的角色
     * @author xiaohuwien
     * @param user 登录用户对象
     * @return
     */
    public List<Map<String,String>> searchUserRole(SysUser user) {
        Map<String,String> map = new HashMap<String,String>();
        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
        List<Map<String,String>> resultRist = new ArrayList<Map<String,String>>();
        try {
            list = sysModuleService.findRoleList(user.getUserName());
            for(Map<String,String> tempMap:list){
                map = new HashMap<String,String>();
                map.put("cn",tempMap.get("ROLECN"));
                map.put("en",tempMap.get("ROLEEN"));
                resultRist.add(map);
            }
        } catch (Exception e) {
            logger.error("获取登录用户角色[{}]出错",user.getUserName(),e);
        }
        return resultRist;
    }


    public void getSysParameterMap() throws Exception {
        List<SysParam> list = sysParameterService.findSysConfigList();
        Map<String,String> paramMap = new HashMap<String,String>();
        for(SysParam sysParam:list){
            paramMap.put(sysParam.getEnglishName(),sysParam.getValue());
        }
        Constants.SYS_PARAM_MAP = paramMap;
    }
}
