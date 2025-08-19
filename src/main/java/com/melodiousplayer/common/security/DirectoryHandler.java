package com.melodiousplayer.common.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;

/**
 * SpringBoot启动时，自动创建文件资源目录
 *
 * @author Mike
 * @date 2025/08/19
 */
@Component
public class DirectoryHandler {

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

    @PostConstruct
    public void createDirectory() {
        // 用户头像目录
        createDirectoryFile(avatarImagesDirectory, "用户头像目录");
        // 音乐相关图片目录
        createDirectoryFile(musicImagesDirectory, "音乐相关图片目录");
        // 音乐音频文件目录
        createDirectoryFile(audioFileDirectory, "音乐音频文件目录");
        // 音乐歌词文件目录
        createDirectoryFile(lyricFileDirectory, "音乐歌词文件目录");
        // MV相关图片目录
        createDirectoryFile(mvImagesDirectory, "MV相关图片目录");
        // MV视频文件目录
        createDirectoryFile(mvFileDirectory, "MV视频文件目录");
        // 悦单相关图片目录
        createDirectoryFile(listImagesDirectory, "悦单相关图片目录");
    }

    /**
     * 根据路径创建目录文件
     *
     * @param path        目录路径
     * @param description 目录描述
     */
    private void createDirectoryFile(String path, String description) {
        File directory = new File(path);
        if (!directory.exists()) {
            boolean result = directory.mkdirs();
            if (result) {
                System.out.println(description + "创建成功");
            } else {
                System.out.println(description + "创建失败");
            }
        } else {
            System.out.println(description + "已经存在");
        }
    }

}
