package com.game.zhujuan.util;

import java.util.UUID;

/**
 * @author xuchunpeng 2021/12/7
 */
public class UUIDUtil {

    /**
     * 获取32位随机码
     *
     * @return 32位随机码
     * @author peng
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 获取指定前缀的随机码
     *
     * @param prefix 前缀
     * @return 指定前缀的随机码
     * @author peng
     */
    public static String getUUID(String prefix) {
        String uuid = getUUID();
        if (prefix == null || prefix.length() > uuid.length()) {
            return uuid;
        }
        return prefix + uuid.substring(prefix.length());
    }
}