package com.hisign.code.model.system;

import com.hisign.code.model.common.BaseModel;

/**
 * 用户token的model
 * @author xiaohuiwen
 * @date 2016/7/8 16:31
 */
public class SysUserToken extends BaseModel {

    /**
     * token编号
     */
    private String token;

    /**
     * 用户编号
     */
    private String userId;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
