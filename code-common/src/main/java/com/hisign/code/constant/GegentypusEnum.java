package com.hisign.code.constant;

import com.hisign.code.model.business.Gegentypus;

import java.util.HashMap;
import java.util.Map;

/**
 * 对比类型枚举
 * @author jiangpeng
 * @since 2017/6/29 16:12
 */
public enum GegentypusEnum {
    CHAR("CHAR", "String"),
    VARCHAR2("VARCHAR2", "String", "VARCHAR"),
    NUMBER("NUMBER", "long"),
    DATE("DATE", "Date", "TIMESTAMP"),
    TIMESTAMP("TIMESTAMP", "Timestamp"),
    BLOB("BLOB", "Blob"),
    CLOB("CLOB", "Clob")
    ;

    private static final Map<String, Gegentypus> GEGENTYPUS_MAP = new HashMap<>();

    static {
        for (GegentypusEnum gegentypus : GegentypusEnum.values()) {
            GEGENTYPUS_MAP.put(gegentypus.gegentypus.getOracleType(), gegentypus.gegentypus);
        }
    }

    private Gegentypus gegentypus;

    GegentypusEnum(String oracleType) {
        this.gegentypus = new Gegentypus(oracleType);
    }

    GegentypusEnum(String oracleType, String javaType) {
        this.gegentypus = new Gegentypus(oracleType, javaType);
    }

    GegentypusEnum(String oracleType, String javaType, String jdbcType) {
        this.gegentypus = new Gegentypus(oracleType, javaType, jdbcType);
    }

    public static Gegentypus getGegentypus(String oracleType) {
        return GEGENTYPUS_MAP.get(oracleType);
    }
}
