package com.zhou.service;

import com.zhou.bean.VideoOrder;
import com.zhou.dto.VideoOrderDto;

import java.util.List;

/**
 * @Author：周申宇
 * @Date:2021/4/5 20:31
 * @Decription:
 */
public interface VideoOrderService {


    /**
     * 下单操作 你会问 不应该是int返回么 为什么String？ 你dao层写int service就写String，因为需要拿微信返回的code_url 所以这里写String
     * @return
     */
    String saveVideoOrder(VideoOrderDto videoOrderDto) throws Exception;

    /**
     * 查询用户订单列表
     * @param userId 用户id
     * @return
     */
    List<VideoOrder> listOrderByUserId(Integer userId);

    /**
     * 根据订单流水号查找订单对象
     * @param outTradeNo
     * @return
     */
    VideoOrder findByOutTradeNo(String outTradeNo);

    /**
     * 根据流水号更新订单状态
     * @param videoOrder
     * @return
     */
    int updateVideoOrderByOutTradeNo(VideoOrder videoOrde);

}
