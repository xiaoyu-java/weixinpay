package com.zhou.util;

import java.security.MessageDigest;
import java.util.UUID;

/**
 * @Author：周申宇
 * @Date:2021/4/5 20:56
 * @Decription:
 */
public class CommonUtils {


    /**
     * @Auther: 周申宇
     * @Date: 2021/4/6 11:05
     * @Description: 生成UUID
     */
    public static String generateUUID(){

        String uuid = UUID.randomUUID().toString().replace("-","").substring(0,32);
        return uuid;
    }


    /**
     * @Auther: 周申宇
     * @Date: 2021/4/5 22:11
     * @Description: MD5加密
     */
    public static String MD5(String data){
        try{
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte [] array = md5.digest(data.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for(byte item:array){
                sb.append(Integer.toHexString((item & 0XFF) | 0x100).substring(1,3));
            }
            return sb.toString().toUpperCase();

        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }



}
