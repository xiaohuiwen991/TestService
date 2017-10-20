package com.hisign.code.model.database;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hisign.code.model.common.BaseModel;
import com.hisign.commonutil.util.ClassUtil;
import com.hisign.multiDatabase.SpringBeanInfo;

import java.util.Date;

/**
 *
 * @author jiangpeng
 * @since 2017/5/23 17:35
 */
public class ConnectionInfo extends BaseModel {
    private String name;

    private String url;

    private String username;

    private String password;

    private String createPid;

    private Date createDate;

    private String modifyPid;

    private Date modifyDate;

    /**
     * 操作标志
     */
    private int operateFlag;

    public ConnectionInfo() {
    }

    public ConnectionInfo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getCreatePid() {
        return createPid;
    }

    public void setCreatePid(String createPid) {
        this.createPid = createPid == null ? null : createPid.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getModifyPid() {
        return modifyPid;
    }

    public void setModifyPid(String modifyPid) {
        this.modifyPid = modifyPid == null ? null : modifyPid.trim();
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public int getOperateFlag() {
        return operateFlag;
    }

    public void setOperateFlag(int operateFlag) {
        this.operateFlag = operateFlag;
    }

    @JsonIgnore
    @JSONField(serialize = false)
    public SpringBeanInfo getSpringBeanInfo() {
       return ClassUtil.getObjectByArray(SpringBeanInfo.class, this, new String[][]{
                {"name", "beanName"}
        }, new Object[][]{{"driverClassName", "oracle.jdbc.driver.OracleDriver"}});
    }
}