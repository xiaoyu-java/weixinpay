package com.zhou.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author：周申宇
 * @Date:2021/4/5 20:34
 * @Decription:  视屏实体
 */
@Data
public class Video implements Serializable {

    private Integer id;

    /**
     * 视频标题
     */
    private String title;

    /**
     * 描述
     */
    private String summary;

    /**
     * 封面图路径
     */
    @JsonProperty("cover_img")
    private String coverImg;

    /**
     * 价格
     */
    private Integer price;

    /**
     * 视频分类
     */
    @JsonProperty("c_id")
    private Integer cId;

    /**
     * 评分
     */
    private Double point;

    /**
     * 视频创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", locale = "zh", timezone = "GMT+8")
    @JsonProperty("create_time")
    private Date createTime;

    /**
     * 观看数
     */
    @JsonProperty("view_num")
    private Integer viewNum;

    /**
     * 0表示未上线，1表示上线
     */
    private Integer online;


//    @JsonProperty("chapter_list")
//    private List<Chapter> chapterList;


}
