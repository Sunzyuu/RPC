package com.github.sunzy.utils;

/**
 * @author sunzy
 * @date 2023/8/5 16:04
 */
public class StringUtil {
    public static boolean isBlank(String s) {
        if(s == null || s.length() == 0) {
            return true;
        }
        for (int i = 0; i < s.length(); ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
