package com.also.framework.datasource.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.stat.DruidDataSourceStatManager;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.also.framework.datasource.config.properties.DataSourceProperties;
import com.also.framework.datasource.config.properties.DruidProperties;
import com.also.framework.datasource.config.properties.MultiDataSourceProperties;
import com.also.framework.datasource.config.properties.StatViewServletProperties;
import com.also.framework.datasource.enums.DataSourceTypeEnum;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.servlet.Filter;
import javax.servlet.Servlet;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableConfigurationProperties
public class MultiDataSourceConfig {

    @Bean(name = {"dynamicDataSource"})
    @Primary
    public DataSource dataSource(MultiDataSourceProperties multiDataSourceProperties) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        List<DataSourceProperties> dataSourceConfigs = multiDataSourceProperties.getDataSourceConfigs();
        DataSource defaultDataSource = null;

        for (DataSourceProperties dataSourceProperties : dataSourceConfigs) {
            DataSource dataSource = this.buildDataSource(multiDataSourceProperties.getType(), dataSourceProperties);
            targetDataSources.put(dataSourceProperties.getName(), dataSource);
            if (defaultDataSource == null && dataSourceProperties.isDefaultDatabase()) {
                defaultDataSource = dataSource;
            }
        }

        return new DynamicDataSource(defaultDataSource, targetDataSources);
    }

    private DataSource buildDataSource(String type, DataSourceProperties dataSourceProperties) {
        if (DataSourceTypeEnum.DRUID.getName().equals(type)) {
            DruidDataSource dataSource = new DruidDataSource();
            dataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
            dataSource.setUrl(dataSourceProperties.getUrl());
            dataSource.setUsername(dataSourceProperties.getUsername());
            dataSource.setPassword(dataSourceProperties.getPassword());
            DruidProperties druidProperties = dataSourceProperties.getDruid();
            if (druidProperties != null) {
                druidProperties.build(dataSource);
            }
//            DruidDataSourceStatManager.addDataSource(dataSource, dataSourceProperties.getName());
            try {
                dataSource.init();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return dataSource;
        } else if (DataSourceTypeEnum.HIKARI.getName().equals(type)) {
            HikariDataSource dataSource = new HikariDataSource();
            dataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
            dataSource.setJdbcUrl(dataSourceProperties.getUrl());
            dataSource.setUsername(dataSourceProperties.getUsername());
            dataSource.setPassword(dataSourceProperties.getPassword());
            HikariConfig hikariConfig = dataSourceProperties.getHikari();
            if (hikariConfig != null) {
                dataSource.setIdleTimeout(hikariConfig.getIdleTimeout());
            }
        }
        return null;
    }



    @Bean
    @ConditionalOnProperty(name = "also.datasource.allow-page", havingValue = "true")
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
