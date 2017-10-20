package com.hisign.code.constant;

/**
 * 标签枚举
 * @author jiangpeng
 * @since 2017/7/22 14:56
 */
public enum LableEnum {

    ARR("arr"),
    CONFIG("config"),
    GEGENTYPUS("gegentypus"),
    EXCLUDECOLUMN("excludeColumn"),
    CLUDECOLUMN("cludeColumn"),
    EXCLUDEBYTABLE("excludeByTable"),
    REPLACESTR("replaceStr")
    ;

    public String key;

    LableEnum(String key) {
        this.key = key;
    }

    public String getStart() {
        return String.format("<%s>", key);
    }

    public String getEnd() {
        return String.format("</%s>", key);
    }

    public String getLable(String str) {
        return getStart() + str + getEnd();
    }

}
