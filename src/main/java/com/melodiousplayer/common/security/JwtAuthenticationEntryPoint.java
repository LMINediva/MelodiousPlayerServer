package com.melodiousplayer.common.security;

import cn.hutool.json.JSONUtil;
import com.melodiousplayer.entity.R;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT认证失败处理
 *
 * @author Mike
 * @date 2025/06/23
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().append(JSONUtil.toJsonStr(R.error(HttpServletResponse.SC_UNAUTHORIZED,
                "认证失败，请登录")));
    }

}
