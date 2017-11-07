package com.hisign.code.model.business;

/**
 * 比对类型
 * @author jiangpeng
 * @since 2017/7/22 14:22
 */
public class Gegentypus {

    private String oracleType;

    private String javaType;

    private String jdbcType;

    public Gegentypus() {

    }

    public Gegentypus(String oracleType) {
        this.oracleType = oracleType;
    }

    public Gegentypus(String oracleType, String javaType) {
        this.oracleType = oracleType;
        this.javaType = javaType;
    }

    public Gegentypus(String oracleType, String javaType, String jdbcType) {
        this.oracleType = oracleType;
        this.javaType = javaType;
        this.jdbcType = jdbcType;
    }

    public String getOracleType() {
        return oracleType;
    }

    public String getJavaType() {
        return javaType == null ? "" : javaType;
    }

    public String getJdbcType() {
        return jdbcType == null ? oracleType : jdbcType;
    }

    public void setOracleType(String oracleType) {
        this.oracleType = oracleType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }
}
