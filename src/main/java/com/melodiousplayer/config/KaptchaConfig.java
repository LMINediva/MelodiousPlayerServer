package com.melodiousplayer.config;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * Kaptcha图形验证码配置类
 *
 * @author Mike
 * @date 2025/08/17
 */
@Configuration
public class KaptchaConfig {

    @Bean
    Producer kaptcha() {
        Properties properties = new Properties();
        // 图片宽
        properties.setProperty("kaptcha.image.width", "150");
        // 图片高
        properties.setProperty("kaptcha.image.height", "50");
        // 图片边框
        properties.setProperty("kaptcha.border", "yes");
        // 边框颜色
        properties.setProperty("kaptcha.border.color", "105,179,90");
        // 字体颜色
        properties.setProperty("kaptcha.textproducer.font.color", "blue");
        // 验证码长度
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        // 字体大小
        properties.setProperty("kaptcha.textproducer.font.size", "40");
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

}
