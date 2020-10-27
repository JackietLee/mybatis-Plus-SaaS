package com.bhuang.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.plugins.parser.ISqlParser;
import com.baomidou.mybatisplus.plugins.parser.tenant.TenantHandler;
import com.baomidou.mybatisplus.plugins.parser.tenant.TenantSqlParser;
import com.bhuang.tenant.ApiContext;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @title: MybatisPlusConfig
 * @Author Huangb
 * @Date: 2020/10/21 10:37
 * @Version 1.0
 */
@Component
public class MybatisPlusConfig {
    //需要注入的字段
    private static final String SYSTEM_TENANT_ID = "tenant_id";
    //需要忽略的表
    private static final List<String> IGNORE_TENANT_TABLES = new ArrayList<String>(Arrays.asList("user"));

    @Autowired
    private ApiContext apiContext;

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setLocalPage(true);// 开启 PageHelper 的支持

        List<ISqlParser> sqlParserList = new ArrayList<>();
        //SQL解析处理拦截，增加租户处理回调
        TenantSqlParser tenantSqlParser = new TenantSqlParser();
        tenantSqlParser.setTenantHandler(new TenantHandler() {
            @Override
            public Expression getTenantId() {
                //取出当前请求的tenantid，通过解析器注入到SQL中
                Long currentProviderId = apiContext.getCurrentProviderId();
                if(null == currentProviderId){
                    throw new RuntimeException("#1129 getcurrentProviderId error.");
                }
                return new LongValue(currentProviderId);
            }

            @Override
            public String getTenantIdColumn() {
                return SYSTEM_TENANT_ID;
            }

            @Override
            public boolean doTableFilter(String tableName) {
                // 忽略掉一些表：如user表就不需要执行这样的处理。
                return IGNORE_TENANT_TABLES.stream().anyMatch((e) -> e.equalsIgnoreCase(tableName));
            }
        });
        sqlParserList.add(tenantSqlParser);
        paginationInterceptor.setSqlParserList(sqlParserList);
        return paginationInterceptor;

    }

    @Bean(name = "performanceInterceptor")
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }
}