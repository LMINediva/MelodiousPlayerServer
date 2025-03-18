package com.melodiousplayer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Mike
 * @date 2025/03/18
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/images/**", "/lrc/**", "/mp3/**", "/mp4/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/front_page.json").permitAll()  // 允许匿名访问 /front_page.json
                .anyRequest().authenticated()          // 其他所有请求都需要认证
                .and()
                .formLogin()
                .and()
                .csrf().disable();
    }

}