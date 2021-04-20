package com.zhou.config;

import lombok.Data;
import lombok.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author：周申宇
 * @Date:2021/4/5 20:42
 * @Decription:  微信配置类
 */

@Configuration
@Data
public class WeChatConfig {

    /**
     * 公众号appid
     */
    public static final String appId = "wxab8acb865bb1637e";

    /**
     * 公众号密钥
     */
    public static final String appsecret = "fb6813b55cd9ebab19b7bdc0f66c09d9";
    /**
     * 商户号ID
     */
    public static final String mchId = "11473623";

    /**
     * 支付key
     */
    public static final String key = "2ab9071b06b9f739b950ddb41db2690d";

    /**
     * 微信支付回调URL
     */
    public static final String payCallbackUrl ="http://bvsn4b.natappfree.cc/order/callback";

    /**
     * 微信统一下单url地址
     */
    public static final  String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    public String getUnifiedOrderUrl() {
        return UNIFIED_ORDER_URL;
    }

    public String getAppId() {
        return appId;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public String getMchId() {
        return mchId;
    }

    public String getKey() {
        return key;
    }

    public String getPayCallbackUrl() {
        return payCallbackUrl;
    }
}