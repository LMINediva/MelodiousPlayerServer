package com.melodiousplayer.common.security;

import com.melodiousplayer.common.constant.JwtConstant;
import com.melodiousplayer.entity.CheckResult;
import com.melodiousplayer.entity.SysUser;
import com.melodiousplayer.service.SysUserService;
import com.melodiousplayer.util.JwtUtils;
import com.melodiousplayer.util.StringUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * JWT身份认证过滤器
 *
 * @author Mike
 * @date 2025/06/22
 */
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private MyUserDetailsServiceImpl myUserDetailsService;

    private static final String URL_WHITE_LIST[] = {
            "/",
            "/login",
            "/logout",
            "/captcha",
            "/compareCode",
            "/password",
            "/image/**",
            "/js/**",
            "/css/**",
            "/img/**",
            "/favicon.ico"
    };

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader("token");
        System.out.println("请求url：" + request.getRequestURI());
        // 如果token是空或者url在白名单里，则放行
        if (StringUtil.isEmpty(token) || new ArrayList<String>(Arrays.asList(URL_WHITE_LIST))
                .contains(request.getRequestURI())) {
            chain.doFilter(request, response);
            return;
        }
        CheckResult checkResult = JwtUtils.validateJWT(token);
        if (!checkResult.isSuccess()) {
            switch (checkResult.getErrCode()) {
                case JwtConstant.JWT_ERRCODE_NULL:
                    throw new JwtException("Token不存在");
                case JwtConstant.JWT_ERRCODE_FAIL:
                    throw new JwtException("Token验证不通过");
                case JwtConstant.JWT_ERRCODE_EXPIRE:
                    throw new JwtException("Token过期");
            }
        }
        Claims claims = JwtUtils.parseJWT(token);
        String username = claims.getSubject();
        SysUser sysUser = sysUserService.getByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(username, null,
                        myUserDetailsService.getUserAuthority(sysUser.getId()));
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        chain.doFilter(request, response);
    }

}
