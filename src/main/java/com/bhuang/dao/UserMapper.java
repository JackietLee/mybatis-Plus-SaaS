package com.bhuang.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bhuang.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @title: UserMapper
 * @Author Huangb
 * @Date: 2020/10/21 10:51
 * @Version 1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}