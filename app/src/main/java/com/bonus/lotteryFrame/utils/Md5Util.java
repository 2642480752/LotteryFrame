package com.bonus.lotteryFrame.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author 菜旗
 * @date 2022/5/23 16:27
 * @description MD5加密工具类
 */
public class Md5Util {
    /**
     * 对文本执行 md5 摘要加密, 此算法与 mysql,JavaScript生成的md5摘要进行过一致性对比.
     *
     * @param str String
     * @return 返回值中的字母为小写
     */
    public static String encryption(String str) {
        if (null == str) {
            str = "";
        }
        String md5Str = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] bytes = md.digest();
            int i;
            StringBuilder builder = new StringBuilder(32);
            for (byte data : bytes) {
                i = data;
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    builder.append("0");
                }
                builder.append(Integer.toHexString(i));
            }
            md5Str = builder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5Str;
    }
}
