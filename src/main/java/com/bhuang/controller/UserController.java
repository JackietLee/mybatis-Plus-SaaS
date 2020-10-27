package com.bhuang.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.bhuang.entity.User;
import com.bhuang.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @title: UserController
 * @Author Huangb
 * @Date: 2020/10/21 10:51
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static Calendar calendar = Calendar.getInstance();
    private static Random random = new Random(1);
    private static int year = calendar.get(Calendar.YEAR);
    private static int month = calendar.get(Calendar.MONTH) +1;
    private static int day = calendar.get(Calendar.DATE);


    @Autowired
    UserService service;

    @RequestMapping("/Login")
    public User login(@RequestBody User user){
        return service.login(user);
    }

    @RequestMapping("/queryUser")
    public List<User> queryUser(@RequestParam Integer tenantid){
        return service.queryUserAll(tenantid);
    }

    @RequestMapping("/queryCompany")
    public List<User> queryCompany(@RequestParam Integer masterid, @RequestParam String mastername){
        return service.queryCompanyAll(masterid, mastername);
    }

    @RequestMapping("/insertUser")
    public  String insertUser(@RequestBody User user){
        System.out.println("company+"+user.getCompany());
        if(user.getCompany() == 1){
            user.setTenantid(Integer.parseInt(
                    year+""+month+""+day+""+Integer.parseInt(String.format("%02d",random.nextInt(99)))
            ));
        }
        return service.insertUser(user);
    }
}