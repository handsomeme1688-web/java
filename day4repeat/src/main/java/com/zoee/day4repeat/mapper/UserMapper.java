package com.zoee.day4repeat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zoee.day4repeat.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
