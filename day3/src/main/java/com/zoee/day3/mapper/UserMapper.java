package com.zoee.day3.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zoee.day3.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user where id=#{id}")
    User findById(Long id);
}
