package com.bhuang.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bhuang.dao.UserMapper;
import com.bhuang.entity.User;
import com.bhuang.service.IUserService;
import com.bhuang.tenant.ApiContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @title: UserService
 * @Author Huangb
 * @Date: 2020/10/22 17:46
 * @Version 1.0
 */
@Service
public class UserService extends ServiceImpl<UserMapper,User> implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    ApiContext apiContext;

    /**
     * 登陆验证
     * @param user
     * @return
     */
    public User login(User user){
        apiContext.setCurrentProviderId(user.getTenantid().longValue());
        return userMapper.selectOne(user);
    }

    public List<User> queryUserAll(Integer tenantid){
        EntityWrapper<User> wrapper = new EntityWrapper<>();
        wrapper.eq("tenant_id",tenantid);
        return userMapper.selectList(wrapper);
    }

    public List<User> queryCompanyAll(Integer masterid, String mastername){
        EntityWrapper<User> wrapper = new EntityWrapper<>();
        wrapper.eq("master_id",masterid).eq("master_name",mastername).eq("company",1);
        return userMapper.selectList(wrapper);
    }

    public String insertUser(User user) {
        return userMapper.insert(user) > 0 ? "success" : "error";
    }
}