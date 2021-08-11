package com.pzy.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
    /**
     * @author : pangzy
     * @date : 2021/6/8 16:53
     * @return : 加密后的字符串
     * MD5加密类
     */
    public static String code(String str){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] bytes = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for(int offset = 0; offset< bytes.length;offset++){
                i = bytes[offset];
                if (i<0)
                    i+=256;
                if (i<16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            //32位加密
            return buf.toString();
            //16位加密
//            return buf.toString().substring(8,24);
        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

}
