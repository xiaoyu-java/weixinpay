package com.zhou.util;

import com.zhou.bean.Dto;

/**
 * 用于返回Dto的工具类
 * Created by XX on 17-5-8.
 */
public class DtoUtil {

    public static String success="true";

    public static String fail="false";

    public static String errorCode="0";
    /***
     * 统一返回成功的DTO
     */
    public static Dto returnSuccess(){
        Dto dto=new Dto();
        dto.setSuccess(success);
        return  dto;
    }
    /***
     * 统一返回成功的DTO 带数据
     */
    public static Dto returnSuccess(String message,Object data){
        Dto dto=new Dto();
        dto.setSuccess(success);
        dto.setMsg(message);
        dto.setErrorCode(errorCode);
        dto.setData(data);
        return  dto;
    }
    /***
     * 统一返回成功的DTO 不带数据
     */
    public static Dto returnSuccess(String message){
        Dto dto=new Dto();
        dto.setSuccess(success);
        dto.setMsg(message);
        dto.setErrorCode(errorCode);
        return  dto;
    }
    /***
     * 统一返回成功的DTO 带数据 没有消息
     */
    public static Dto returnDataSuccess(Object data){
        Dto dto=new Dto();
        dto.setSuccess(success);
        dto.setErrorCode(errorCode);
        dto.setData(data);
        return  dto;
    }

    /**
     * 请求失败，返回错误语句及错误码
     * @param message
     * @param errorCode
     * @return
     */
    public static Dto returnFail(String message, String errorCode){
        Dto dto=new Dto();
        dto.setSuccess(fail);
        dto.setMsg(message);
        dto.setErrorCode(errorCode);
        return  dto;
    }

    /**
     * 返回数据 并返回数据数量
     * @param data
     * @param count
     * @return
     */
    public static Dto returnPage(Object data,int count){
        Dto dto=new Dto();
        dto.setSuccess(success);
        dto.setErrorCode(errorCode);
        dto.setData(data);
        dto.setCount(count);

        return  dto;
    }
}
