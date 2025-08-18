package com.melodiousplayer.controller;

import com.alibaba.druid.util.StringUtils;
import com.google.code.kaptcha.Producer;
import com.melodiousplayer.entity.R;
import com.melodiousplayer.entity.VerificationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 验证码Controller控制器
 *
 * @author Mike
 * @date 2025/08/17
 */
@RestController
public class KaptchaController {

    @Autowired
    private Producer producer;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 获取验证码图片，并将验证码存入Redis中
     *
     * @param request  请求
     * @param response 响应
     * @throws IOException IO异常
     */
    @GetMapping("/captcha")
    public void getVerifyCode(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setHeader("Cache-Control", "no-store,no-cache");
        response.setContentType("image/jpeg");
        // 生成文字验证码
        String text = producer.createText();
        // 生成图片验证码
        BufferedImage image = producer.createImage(text);
        // 使用redis缓存验证码的值，并设置过期时间为60秒
        redisTemplate.opsForValue().set("imageCode", text, 60, TimeUnit.SECONDS);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        out.flush();
        out.close();
    }

    /**
     * 比较用户输入的验证码是否和Redis中存储的验证码相同
     *
     * @param verificationCode 用户输入的验证码
     * @return 页面响应entity
     */
    @PostMapping("/compareCode")
    public R compareCode(@RequestBody VerificationCode verificationCode) {
        if (!redisTemplate.hasKey("imageCode")) {
            return R.error(500, "验证码已过期");
        }
        String code = redisTemplate.opsForValue().get("imageCode").toString();
        if (StringUtils.equals(verificationCode.getCode(), code)) {
            return R.ok();
        } else {
            return R.error(500, "验证码输入错误");
        }
    }

}
