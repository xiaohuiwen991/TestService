package com.hisign.code.model.business;

import com.hisign.code.model.common.BaseModel;

/**
 * Created by Administrator on 2018\1\27 0027.
 */
public class ReportInfo extends BaseModel {

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
