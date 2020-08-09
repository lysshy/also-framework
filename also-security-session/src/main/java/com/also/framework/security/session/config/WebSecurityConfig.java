package com.also.framework.security.session.config;

import com.also.framework.security.common.handler.AuthorityService;

import com.also.framework.security.common.handler.DefaultAccessDeniedHandler;
import com.also.framework.security.session.validate.code.ValidateCodeFilter;
import com.also.framework.security.session.validate.smscode.SmsAuthenticationConfig;
import com.also.framework.security.session.validate.smscode.SmsCodeFilter;
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
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("CustomUserDetailsService")
    private UserDetailsService customUserDetailsService;

    @Autowired
    private AuthenticationSuccessHandler authenticationSucessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private ValidateCodeFilter validateCodeFilter;

    @Autowired
    private SmsCodeFilter smsCodeFilter;

    @Autowired
    private SmsAuthenticationConfig smsAuthenticationConfig;

    @Autowired
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    @Autowired
    private InvalidSessionStrategy invalidSessionStrategy;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

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
        httpSecurity.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class) // 添加验证码校验过滤器
                .addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class); // 添加短信验证码校验过滤器

        httpSecurity.formLogin() // 表单登录
                // http.httpBasic() // HTTP Basic
                .loginPage("/authentication/require") // 登录跳转 URL
                .loginProcessingUrl("/login") // 处理表单登录 URL
                .successHandler(authenticationSucessHandler) // 处理登录成功
                .failureHandler(authenticationFailureHandler); // 处理登录失败

        httpSecurity.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login.html");

        httpSecurity.sessionManagement() // 添加 Session管理器
                .invalidSessionStrategy(invalidSessionStrategy)
//                .invalidSessionUrl("/session/invalid") // Session失效后跳转到这个链接
                .maximumSessions(1)
//                .maxSessionsPreventsLogin(true)
                .expiredSessionStrategy(sessionInformationExpiredStrategy);

        httpSecurity.authorizeRequests() // 授权配置
                .antMatchers("/authentication/require",
                        "/login.html", "/code/image", "/code/sms", "/session/invalid").permitAll()// 无需认证的请求路径
//                .anyRequest()  // 所有请求
//                .authenticated() // 都需要认证
                .anyRequest()
                .access("@authorityService.checkPermission(request, authentication)")
                .and()
                .csrf().disable()
                .apply(smsAuthenticationConfig); // 将短信验证码认证配置加到 Spring Security 中

        //设置异常处理类
        httpSecurity.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
        // 禁用缓存
        httpSecurity.headers().cacheControl();

//        httpSecurity
//                .exceptionHandling().accessDeniedHandler(accessDeniedHandler).and()
//                // 由于使用的是JWT，我们这里不需要csrf
//                .csrf().disable()
////                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
////                .and()
//                // 基于token，所以不需要session
////                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//
//                .authorizeRequests()
//
//                // 对于获取token的rest api要允许匿名访问
//                .antMatchers("/api/v1/auth", "/api/v1/signout", "/error/**", "/api/**").permitAll()
//                // 除上面外的所有请求全部需要鉴权认证
//                .anyRequest().authenticated().and().formLogin();

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
                "/swagger-ui.html", "/login.html", "/css/**",
                "/authentication/require",
                "/code/image", "/code/sms", "/session/invalid"
        );
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
