package com.melodiousplayer.config;

import com.melodiousplayer.common.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Mike
 * @date 2025/03/18
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;
    @Autowired
    private LoginFailureHandler loginFailureHandler;
    @Autowired
    private JwtLogoutSuccessHandler jwtLogoutSuccessHandler;
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired
    private MyUserDetailsServiceImpl myUserDetailsService;

    private static final String URL_WHITE_LIST[] = {
            "/",
            "/login",
            "/logout",
            "/captcha",
            "/compareCode",
            "/register",
            "/userInfo",
            "/password",
            "/image/**",
            "/audio/**",
            "/video/**",
            "/data/music/front_page",
            "/data/music/front_page2",
            "/data/mv/get_mv_list",
            "/get_mv_areas",
            "/data/list/get_list",
            "/sys/android/downloadAPK/**",
            "/js/**",
            "/css/**",
            "/img/**",
            "/favicon.ico"
    };

    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager());
        return jwtAuthenticationFilter;
    }

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/images/**", "/lrc/**", "/mp3/**", "/mp4/**");
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 开启跨域以及关闭csrf攻击
        http
                .cors()
                .and()
                .csrf()
                .disable()
                // 登录登出配置
                .formLogin()
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailureHandler)
                .and()
                .logout()
                .logoutSuccessHandler(jwtLogoutSuccessHandler)
                // session禁用配置
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 无状态
                // 拦截规则配置
                .and()
                .authorizeRequests()
                .antMatchers(URL_WHITE_LIST).permitAll() // 放行白名单
                .anyRequest().authenticated()
                // 异常处理配置
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                // 自定义过滤器配置
                .and()
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}