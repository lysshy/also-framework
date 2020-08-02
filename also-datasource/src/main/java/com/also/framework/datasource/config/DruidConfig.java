package com.also.framework.datasource.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.also.framework.datasource.config.properties.StatFilterProperties;
import com.also.framework.datasource.config.properties.StatViewServletProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.servlet.Servlet;

@Configuration
@ConditionalOnProperty(name = "also.datasource.type", havingValue = "druid")
public class DruidConfig {

    @Bean
    @ConfigurationProperties(prefix = "also.datasource.stat-view-servlet")
    public StatViewServletProperties statViewServletProperties() {
        return new StatViewServletProperties();
    }

    @Bean
    @ConditionalOnBean(StatViewServletProperties.class)
    public ServletRegistrationBean<Servlet> druidStatViewServlet(StatViewServletProperties properties) {
        ServletRegistrationBean<Servlet> servletRegistrationBean = new ServletRegistrationBean<>(new StatViewServlet(),
                properties.getUrlPattern());
        //白名单：
        servletRegistrationBean.addInitParameter("allow", properties.getAllow());
        servletRegistrationBean.setEnabled(true);
        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
        servletRegistrationBean.addInitParameter("deny", properties.getDeny());
        //登录查看信息的账号密码.
        servletRegistrationBean.addInitParameter("loginUsername", properties.getLoginUsername());
        servletRegistrationBean.addInitParameter("loginPassword", properties.getLoginPassword());
        //是否能够重置数据.
        servletRegistrationBean.addInitParameter("resetEnable", String.valueOf(properties.isResetEnable()));

        return servletRegistrationBean;
    }

    @Bean
    @ConfigurationProperties(prefix = "also.datasource.web-stat-filter")
    public StatFilterProperties statFilterProperties() {
        return new StatFilterProperties();
    }

    @Bean
    @ConditionalOnBean(StatFilterProperties.class)
    public FilterRegistrationBean<Filter> druidStatFilter(StatFilterProperties properties) {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>(new WebStatFilter());
        //添加过滤规则.
        filterRegistrationBean.addUrlPatterns(properties.getUrlPattern());
        filterRegistrationBean.setEnabled(true);
        //添加不需要忽略的格式信息.
        filterRegistrationBean.addInitParameter("exclusions", properties.getExclusions());
        return filterRegistrationBean;
    }
}
