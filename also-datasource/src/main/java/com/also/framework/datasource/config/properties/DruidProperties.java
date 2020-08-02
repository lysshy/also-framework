package com.also.framework.datasource.config.properties;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;

@Setter
@Getter
@Slf4j
public class DruidProperties {
    private int initialSize = 0;
    private int maxActive = 8;
    private int minIdle = 0;
    private long maxWait = -1;

    private volatile String validationQuery = null;
    private volatile int validationQueryTimeout = -1;
    private volatile boolean testOnBorrow = false;
    private volatile boolean testOnReturn = false;
    private volatile boolean testWhileIdle = true;
    private volatile boolean poolPreparedStatements = false;
    private volatile boolean sharePreparedStatements = false;
    private volatile int maxPoolPreparedStatementPerConnectionSize = 10;

    private String filters;

    public void build(DruidDataSource druidDataSource) {
        druidDataSource.setInitialSize(this.initialSize);
        druidDataSource.setMaxActive(this.maxActive);
        druidDataSource.setMinIdle(this.minIdle);
        druidDataSource.setMaxWait(this.maxWait);
        druidDataSource.setValidationQuery(this.validationQuery);
        druidDataSource.setValidationQueryTimeout(this.validationQueryTimeout);
        druidDataSource.setTestOnBorrow(this.testOnBorrow);
        druidDataSource.setTestOnReturn(this.testOnReturn);
        druidDataSource.setTestWhileIdle(this.testWhileIdle);
        druidDataSource.setPoolPreparedStatements(this.poolPreparedStatements);
        druidDataSource.setSharePreparedStatements(this.sharePreparedStatements);
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(this.maxPoolPreparedStatementPerConnectionSize);
        try {
            druidDataSource.setFilters(this.filters);
        } catch (SQLException e) {
            log.error("druidDataSource set filters fail.", e);
        }
    }

}
