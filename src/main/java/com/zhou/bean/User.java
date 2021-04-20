package com.zhou.bean;

import lombok.Data;

import java.util.Date;

/**
 * @Author：周申宇
 * @Date:2021/4/6 11:15
 * @Decription:  user对象实体
 */
@Data
public class User {

    // 主键
    private Integer id;

    private String openid;
    private String name;
    private String headIng;
    private String phone;
    private String sign;
    private Integer sex;
    private String city;
    private Date createTime;

}
