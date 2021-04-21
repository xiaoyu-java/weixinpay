package com.zhou.service.impl;

import com.zhou.bean.User;
import com.zhou.bean.Video;
import com.zhou.bean.VideoOrder;
import com.zhou.dto.VideoOrderDto;
import com.zhou.config.WeChatConfig;
import com.zhou.mapper.UserMapper;
import com.zhou.mapper.VideoMapper;
import com.zhou.mapper.VideoOrderMapper;
import com.zhou.service.VideoOrderService;
import com.zhou.util.CommonUtils;
import com.zhou.util.HttpClient4Util;
import com.zhou.util.HttpUtils;
import com.zhou.util.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Author：周申宇
 * @Date:2021/4/5 20:32
 * @Decription:
 */
@Service("videoOrderService")
public class VideoOrderServiceImpl implements VideoOrderService {

    @Autowired
    private VideoOrderMapper videoOrderMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private WeChatConfig weChatConfig;


    /**
     * 下单操作
     * 未来版本 优惠卷功能、微信支付、风控用户检查、生成订单基础信息、生成支付信息
     * @return11
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)//默认隔离级别
    public String saveVideoOrder(VideoOrderDto videoOrderDto) throws Exception {

        int videoId = videoOrderDto.getVideoId();
        int userId = videoOrderDto.getUserId();
        String  ip = videoOrderDto.getIp();



        //判断是否已经购买  订单状态码1
//        VideoOrder videoOrder = videoOrderMapper.findByUserIdAndVideoIdAndState(userId,videoId,1);

//        if(videoOrder!=null){
//            //已经支付过了，订单存在
//            return null;
//        }

        //查询视频信息
        Video video = videoMapper.findById(videoId);
        //查询用户信息
        User user = userMapper.findByUserId(userId);

        //生成订单
        //构造订单实体 根据用户购买哪个视频做处理
        VideoOrder newvideoOrder = new VideoOrder();
        newvideoOrder.setCreateTime(new Date());//订单创建时间
        newvideoOrder.setOutTradeNo(CommonUtils.generateUUID());//唯一流水号
        newvideoOrder.setTotalFee(video.getPrice());//价格
//        newvideoOrder.setTotalFee(0.01);//价格

        newvideoOrder.setState(0);//支付状态
        newvideoOrder.setUserId(userId);//用户id
        newvideoOrder.setVideoId(video.getId());//视频id
        newvideoOrder.setHeadImg(user.getHeadIng());//微信头像
        newvideoOrder.setNickName(user.getName());//微信昵称
        newvideoOrder.setVideoImg(video.getCoverImg());//冗余字段
        newvideoOrder.setVideoTitle(video.getTitle());//冗余字段

        newvideoOrder.setDel(0);
        newvideoOrder.setIp(ip);

        //保存订单
        int num = videoOrderMapper.insert(newvideoOrder);


        //生成签名
        String codeUrl = unifiedOrder(newvideoOrder);

        //统一下单
        //获取code_url
        //生成二维码

        return codeUrl;
    }

    /**
     * 统一下单
     * @return
     */
    private String unifiedOrder(VideoOrder videoOrder) throws Exception {

        //生成签名
        SortedMap<String,String> params = new TreeMap<>();
        params.put("appid",weChatConfig.getAppId());//公众号AppId
        params.put("mch_id",weChatConfig.getMchId());//商户ID
        params.put("nonce_str", CommonUtils.generateUUID());
        params.put("body",videoOrder.getVideoTitle());//商品描述
        params.put("out_trade_no",videoOrder.getOutTradeNo());//订单流水号
        params.put("total_fee",videoOrder.getTotalFee()+"");//商品金额
        params.put("spbill_create_ip",videoOrder.getIp());//终端IP
        params.put("notify_url",weChatConfig.getPayCallbackUrl());//通知地址
        params.put("trade_type","NATIVE");//交易类型 扫码支付

        //sign签名 调用工具类
        String sign = WXPayUtil.createSign(params,weChatConfig.getKey());
        params.put("sign",sign);

        //生成签名后转map 进行校验 map>xml
        String payXml = WXPayUtil.mapToXml(params);
        System.out.println(payXml);
        System.out.println(sign);

        //统一下单
        //发送post请求
        String orderStr = HttpClient4Util.getResponse4PostByString(weChatConfig.getUnifiedOrderUrl(),payXml,"UTF-8");
        if(orderStr == null){
            return null;
        }
        //接收返回结果 将微信返回的结果xml转map
        Map<String,String> unifiedOrderMap = WXPayUtil.xmlToMap(orderStr);
        if(unifiedOrderMap != null){
            return unifiedOrderMap.get("code_url");
        }
        return null;
    }

    @Override
    public List<VideoOrder> listOrderByUserId(Integer userId) {
        return null;
    }

    @Override
    public VideoOrder findByOutTradeNo(String outTradeNo) {
        return null;
    }

    @Override
    public int updateVideoOrderByOutTradeNo(VideoOrder videoOrde) {
        return 0;
    }

}
