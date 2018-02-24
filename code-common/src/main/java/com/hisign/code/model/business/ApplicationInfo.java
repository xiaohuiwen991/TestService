package com.hisign.code.model.business;

import com.hisign.code.model.common.BaseModel;
import com.hisign.code.util.StringUtil;
import org.springframework.util.StringUtils;

/**
 * 申请信息mode
 */
public class ApplicationInfo extends BaseModel {

    private String userName;

    private String reportName;

    private String filePath;

    private String wordPath;

    private String pdfPath;

    private String fileName;

    private String status;

    private String companyId;

    private String companyName;

    private String createDateStr;

    private String email;

    private String companyCode;

    private String reportAdress;

    private String invoiceAdress;

    private String telphone;

    private String createDateBegin;

    private String createDateEnd;

    private String fileBase64;

    private String picPath;

    private String remarks;

    private String token;

    private String statusStr;

    private String pid;

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getStatusStr() {
        if(StringUtils.isEmpty(this.getStatus())) {
            return "未受理";
        } else if ("1".equals(this.getStatus())) {
            return "已受理";
        } else if ("2".equals(this.getStatus())) {
            return "报告已上传";
        } else if ("3".equals(this.getStatus())) {
            return "报告已寄送";
        } else if ("4".equals(this.getStatus())) {
            return "不予受理";
        } else {
            return "未受理";
        }
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileBase64() {
        return fileBase64;
    }

    public void setFileBase64(String fileBase64) {
        this.fileBase64 = fileBase64;
    }

    public String getCreateDateBegin() {
        return createDateBegin;
    }

    public void setCreateDateBegin(String createDateBegin) {
        this.createDateBegin = createDateBegin;
    }

    public String getCreateDateEnd() {
        return createDateEnd;
    }

    public void setCreateDateEnd(String createDateEnd) {
        this.createDateEnd = createDateEnd;
    }

    public String getInvoiceAdress() {
        return invoiceAdress;
    }

    public void setInvoiceAdress(String invoiceAdress) {
        this.invoiceAdress = invoiceAdress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getReportAdress() {
        return reportAdress;
    }

    public void setReportAdress(String reportAdress) {
        this.reportAdress = reportAdress;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getCreateDateStr() {
        return createDateStr;
    }

    public void setCreateDateStr(String createDateStr) {
        this.createDateStr = createDateStr;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getWordPath() {
        return wordPath;
    }

    public void setWordPath(String wordPath) {
        this.wordPath = wordPath;
    }

    public String getPdfPath() {
        return pdfPath;
    }

    public void setPdfPath(String pdfPath) {
        this.pdfPath = pdfPath;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
}
