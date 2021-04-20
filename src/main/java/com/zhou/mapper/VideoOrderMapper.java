package com.zhou.mapper;

import com.zhou.bean.VideoOrder;
import org.apache.ibatis.annotations.*;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author：周申宇
 * @Date:2021/4/5 21:03
 * @Decription:
 */
@Repository
public interface VideoOrderMapper {

//    @Insert("INSERT INTO `weixin`.`video_order` (`out_trade_no`, `state`, `create_time`, `total_fee`, `video_id`, " +
//            "`video_title`, `video_img`, `user_id`, `ip`, `openid`, `notify_time`, `nickname`, `head_img`, `del`) " +
//            "VALUES (#{outTradeNo},#{state},#{createTime},#{totalFee},#{videoId}," +
//            "#{videoTitle},#{videoImg},#{userId},#{ip},#{openid},#{notify_time},#{nickname},#{headImg},#{del})")
//    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int insert(VideoOrder videoOrder);

    /**
     * @Auther: 周申宇
     * @Date: 2021/4/5 22:24
     * @Description:  根据主键查找订单
     */
//    @Select("select * from video_order where id=#{order_id} and del=0")
    VideoOrder findById(@Param("id") Integer id);

    /**
     * @Auther: 周申宇
     * @Date: 2021/4/5 22:24
     * @Description:  根据交易订单号获取订单对象
     */
//    @Select("select * from video_order where out_trade_no=#{out_trade_no} and del=0")
    VideoOrder findByOutTradeNo(@Param("out_trade_no") Integer outTradeNo);

    /**
     * @Auther: 周申宇
     * @Date: 2021/4/5 22:25
     * @Description:  逻辑删除订单
     */
//    @Update("update video_order set del=0 where id=#{id} and user_id =#{userId}")
    int del(@Param("id")Integer id,@Param("userId") Integer userId);

    /**
     * @Auther: 周申宇
     * @Date: 2021/4/5 22:28
     * @Description:  查找我的全部订单
     */
//    @Select("select * from video_order where user_id =#{userId}")
    List<VideoOrder> findMyOrderList(@Param("userId") Integer userId);

    /**
     * @Auther: 周申宇
     * @Date: 2021/4/5 22:30
     * @Description:   根据订单流水号更新
     */
//    @Update("update video_order set state=#{state},notify_time=#{notifyTime},openid=#{openid} where out_trade_no=#{outTradeNo} and state=0 and del=0")
    int updateVideoOrderBYOutTradeNo(VideoOrder videoOrder);
}
