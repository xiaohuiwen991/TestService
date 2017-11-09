package com.hisign.code.model.business;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hisign.code.model.common.BaseModel;
import com.hisign.commonutil.util.ClassUtil;
import com.hisign.multiDatabase.SpringBeanInfo;

import java.util.Date;

/**
 *
 * @author xiaohuiwen
 * @since 2017/5/23 17:35
 */
public class CompanyInfo extends BaseModel {

    /**
     *
     */
    private String id;

    /**
     *
     */
    private String companyName;

    /**
     *
     */
    private String reportAdress;

    /**
     *
     */
    private String email;

    /**
     *
     */
    private String invoiceAdress;

    /**
     *
     */
    private String createPid;

    /**
     *
     */
    private Date createDate;

    /**
     *
     */
    private String modifyPid;

    /**
     *
     */
    private Date modifyDate;

    /**
     * 操作标志
     */
    private int operateFlag;

    private String contact;

    private String key;

    private String value;

    private String keyStr;

    private String telphone;

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String getKeyStr() {
        return keyStr;
    }

    @Override
    public void setKeyStr(String keyStr) {
        this.keyStr = keyStr;
        this.setKey(this.keyStr);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public CompanyInfo() {
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReportAdress() {
        return reportAdress;
    }

    public void setReportAdress(String reportAdress) {
        this.reportAdress = reportAdress;
    }

    public String getInvoiceAdress() {
        return invoiceAdress;
    }

    public void setInvoiceAdress(String invoiceAdress) {
        this.invoiceAdress = invoiceAdress;
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