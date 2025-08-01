package com.melodiousplayer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web项目配置类
 *
 * @author Mike
 * @date 2025/06/23
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .maxAge(3600);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/userAvatar/**", "/image/musicPicture/**",
                        "/audio/music/**", "/audio/lyric/**", "/image/mvPicture/**",
                        "/video/mv/**", "/image/listPicture/**")
                .addResourceLocations("file:F:\\Projects\\Android\\GraduationProject\\UserAvatar\\",
                        "file:F:\\Projects\\Android\\GraduationProject\\MusicPicture\\",
                        "file:F:\\Projects\\Android\\GraduationProject\\Audio\\",
                        "file:F:\\Projects\\Android\\GraduationProject\\Lyric\\",
                        "file:F:\\Projects\\Android\\GraduationProject\\MVPicture\\",
                        "file:F:\\Projects\\Android\\GraduationProject\\ListPicture\\");
    }

}
