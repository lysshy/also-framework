package com.also.framework.datasource.config.properties;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StatViewServletProperties {

    private String urlPattern = "/druid/*";
    //      IP 白名单，没有配置或者为空，则允许所有访问
    private String allow = "127.0.0.1";
    //      IP 黑名单，若白名单也存在，则优先使用
    private String deny = "192.168.31.253";
    //      禁用 HTML 中 Reset All 按钮
    private boolean resetEnable = false;
    //      # 登录用户名/密码
    private String loginUsername = "admin";
    private String loginPassword = "admin";
}
