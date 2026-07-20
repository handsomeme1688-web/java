package com.zoee.day6.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zoee.day6.entity.User;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper extends BaseMapper<User> {
}
