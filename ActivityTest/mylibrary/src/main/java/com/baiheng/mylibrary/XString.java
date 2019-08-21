package com.baiheng.mylibrary;

public class XString {
    /**
     *判断字符串是否为空
     * @param string
     * @return
     */
    public static boolean isEmpty(String string) {
        return string == null || (string.trim().length() <= 0);
    }
}
