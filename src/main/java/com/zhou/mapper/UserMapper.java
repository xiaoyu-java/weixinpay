package com.zhou.mapper;

import com.zhou.bean.User;
import com.zhou.bean.Video;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Author：周申宇
 * @Date:2021/4/5 21:03
 * @Decription:
 */

@Repository
public interface UserMapper {

    /**
     * @Auther: 周申宇
     * @Date: 2021/4/6 11:32
     * @Description:   根据id用户信息
     */

    User findByUserId(Integer id);
}
