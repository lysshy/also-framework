package com.also.framework.datasource.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.also.framework.datasource.enums.DatasourceTypeEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiDataSourceConfig {

    @Bean(name = {"dynamicDataSource"})
    @Primary
    public DynamicDataSource dataSource(MultiDataSourceProperties multiDataSourceProperties) {
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
        if (DatasourceTypeEnum.DRUID.getName().equals(type)) {
            DruidDataSource dataSource = new DruidDataSource();
            dataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
            dataSource.setUrl(dataSourceProperties.getUrl());
            dataSource.setUsername(dataSourceProperties.getUsername());
            dataSource.setPassword(dataSourceProperties.getPassword());
            return dataSource;
        }
        return null;
    }
}
