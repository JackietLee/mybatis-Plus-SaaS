package com.bhuang.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (User)表实体类
 *
 * @author makejava
 * @since 2020-10-21 10:32:55
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class User{
    //主键ID
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    //姓名
    private String name;
    //年龄
    private Integer age;
    //邮箱
    private String email;
    //token
    @TableField(value = "tenant_id")
    private Integer tenantid;
    //密码
    private String password;
    //账号
    private String username;
    //公司名称
    @TableField(value = "office_name")
    private String officename;
    //创建者id
    @TableField(value = "master_id")
    private Integer masterid;
    //创建者名称
    @TableField(value = "master_name")
    private String mastername;
    private Integer company;
}