package com.also.framework.datasource.config.properties;

import com.also.framework.datasource.config.properties.DataSourceProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@ConfigurationProperties(prefix = "also.datasource")
@Component
@Setter
@Getter
public class MultiDataSourceProperties {

    private String type;

    private List<DataSourceProperties> dataSourceConfigs;

//    private DruidProperties druidProperties;
}
