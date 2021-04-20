package com.zhou.mapper;

import com.zhou.bean.Video;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author：周申宇
 * @Date:2021/4/5 21:04
 * @Decription:
 */
@Repository
public interface VideoMapper {
    /**
     * @Auther: 周申宇
     * @Date: 2021/4/6 11:34
     * @Description:  根据id查询视频信息
     */
    Video findById(Integer id);
}
