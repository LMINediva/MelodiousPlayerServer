package com.melodiousplayer.config;

import org.springframework.beans.factory.annotation.Value;
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

    // 用户头像目录
    @Value("${avatarImagesFilePath}")
    private String avatarImagesDirectory;

    // 音乐相关图片目录
    @Value("${musicImagesFilePath}")
    private String musicImagesDirectory;

    // 音乐音频文件目录
    @Value("${audioFilePath}")
    private String audioFileDirectory;

    // 音乐歌词文件目录
    @Value("${lyricFilePath}")
    private String lyricFileDirectory;

    // MV相关图片目录
    @Value("${mvImagesFilePath}")
    private String mvImagesDirectory;

    // MV视频文件目录
    @Value("${mvFilePath}")
    private String mvFileDirectory;

    // 悦单相关图片目录
    @Value("${listImagesFilePath}")
    private String listImagesDirectory;

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
                .addResourceLocations("file:" + avatarImagesDirectory,
                        "file:" + musicImagesDirectory,
                        "file:" + audioFileDirectory,
                        "file:" + lyricFileDirectory,
                        "file:" + mvImagesDirectory,
                        "file:" + mvFileDirectory,
                        "file:" + listImagesDirectory);
    }

}
