package com.also.framework.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AccessDeniedHandler accessDeniedHandler;

    private final UserDetailsService customUserDetailsService;

    @Autowired
    public WebSecurityConfig(@Qualifier("RestAuthenticationAccessDeniedHandler") AccessDeniedHandler accessDeniedHandler
            , @Qualifier("CustomUserDetailsService") UserDetailsService customUserDetailsService) {
        this.accessDeniedHandler = accessDeniedHandler;
        this.customUserDetailsService = customUserDetailsService;
    }


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 设置UserDetailsService
        auth.userDetailsService(this.customUserDetailsService)
               .passwordEncoder(passwordEncoder());
    }

    // 装载BCrypt密码编码器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler).and()
                // 由于使用的是JWT，我们这里不需要csrf
                .csrf().disable()
//                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
//                .and()
                // 基于token，所以不需要session
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                .authorizeRequests()

                // 对于获取token的rest api要允许匿名访问
                .antMatchers("/api/v1/auth", "/api/v1/signout", "/error/**", "/api/**").permitAll()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated().and().formLogin();

        // 禁用缓存
        httpSecurity.headers().cacheControl();

        // 添加JWT filter
//        httpSecurity
//                .addFilterBefore(UsernamePasswordAuthenticationFilter.class);
//        httpSecurity
//                .authorizeRequests()
//                .antMatchers("/super").hasRole("admin")
//                .antMatchers("/user").hasAnyRole("admin", "user")
//                .and()
//                .formLogin();
    }

    //用于影响全局安全性(配置资源，设置调试模式，通过实现自定义防火墙定义拒绝请求)的配置设置。
    //一般用于配置全局的某些通用事物，例如静态资源等
    @Override
    public void configure(WebSecurity web) {
        //放开swagger的限制
        web.ignoring().antMatchers("/v2/api-docs",
                "/swagger-resources/**", "/webjars/**",
                "/swagger-ui.html"
        );
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
