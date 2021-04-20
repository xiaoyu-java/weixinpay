package com.zhou.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author：周申宇
 * @Date:2021/4/5 20:36
 * @Decription:  视屏订单实体
 */
//@Data
public class VideoOrder {

    private Integer id;

    /**
     * 订单流水号
     */
    @JsonProperty("out_trade_no")
    private String outTradeNo;

    /**
     * 订单状态
     */
    private Integer state;

    /**
     * 订单创建时间
     */
    @JsonProperty("cover_img")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date createTime;

    /**
     * 订单金额
     */
    @JsonProperty("total_fee")
    private Integer totalFee;
//    private double totalFee;

    /**
     * 视频id
     */
    @JsonProperty("video_id")
    private Integer videoId;

    /**
     * 视频荣誉字段-标题
     */
    @JsonProperty("video_title")
    private String videoTitle;

    /**
     * 视频冗余字段-图片
     */
    @JsonProperty("video_img")
    private String videoImg;

    /**
     * 用户id
     */
    @JsonProperty("user_id")
    private Integer userId;

    /**
     * 用户ip地址
     */
    private String ip;

    @JsonProperty("openid")
    private String openid;

    /**
     * 支付回调时间2
     */
    @JsonProperty("notify_time")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date notifyTime;

    /**
     * 冗余字段：微信昵称
     */
    @JsonProperty("nick_name")
    private String nickName;

    /**
     * 冗余字段：微信头像
     */
    @JsonProperty("head_img")
    private String headImg;

    /**
     * 0表示未删除，1表示已经删除
     */
    private Integer del;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

//    public double getTotalFee() {
//        return totalFee;
//    }
//
//    public void setTotalFee(double totalFee) {
//        this.totalFee = totalFee;
//    }

        public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoImg() {
        return videoImg;
    }

    public void setVideoImg(String videoImg) {
        this.videoImg = videoImg;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Date getNotifyTime() {
        return notifyTime;
    }

    public void setNotifyTime(Date notifyTime) {
        this.notifyTime = notifyTime;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
}
