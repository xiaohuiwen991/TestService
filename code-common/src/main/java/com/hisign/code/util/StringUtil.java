package com.hisign.code.util;

import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author jiangpeng
 * @since 2017/7/22 14:14
 */
public class StringUtil {


    /**
     * 字符串首字母转大写(第二个字母不是大写)
     * @param str 字符串
     * @return 首字母大写字符串
     */
    public static String firstChar2Upper(String str) {
        if (StringUtils.isEmpty(str)) return str;
        String firstChar = String.valueOf(str.charAt(0));
        //如果第二个字母大写，则返回原字母
        if(str.length() > 1) {
            String secondChar = String.valueOf(str.charAt(1));
            if(secondChar.toUpperCase().equals(secondChar)) {
                return str;
            }
        }
        String lowerCase = firstChar.toUpperCase();
        return str.replaceFirst(firstChar, lowerCase);
    }

    /**
     * 替换全部开头出现的字符串
     * @param str
     * @return
     */
    public static String replaceFirstAll(String str, String... replaceStr) {
        for (String s : replaceStr) {
            str = replaceFirst(str, s, "");
        }
        return str;
    }

    /**
     * 替换开头出现的字符串
     * @param str
     * @return
     */
    public static String replaceFirst(String str, String oldStr, String newStr) {
        if(str.indexOf(oldStr) == 0) {
            Pattern p = Pattern.compile(oldStr);
            Matcher m = p.matcher(str);
            str = m.replaceFirst(newStr);
        }
        return str;
    }
}
