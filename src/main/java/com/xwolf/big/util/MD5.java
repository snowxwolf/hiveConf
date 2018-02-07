package com.xwolf.big.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5
 * @author xwolf
 * @since 1.8
 **/
public class MD5 {

    private static final char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};

    public static String md5(String str) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] btInput = str.getBytes();
            digest.reset();
            digest.update(btInput);
            byte[] md = digest.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char strChar[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                strChar[k++] = hexDigits[byte0 >>> 4 & 0xf];
                strChar[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(strChar);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

}
