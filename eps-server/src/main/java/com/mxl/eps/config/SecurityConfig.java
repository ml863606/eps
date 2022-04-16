package com.mxl.eps.config;

import com.alibaba.fastjson.JSON;
import com.mxl.eps.model.UsersModel;
import com.mxl.eps.model.common.ResultModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author MXL
 * @date 2022/4/16
 **/
@Configuration
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 密码不需要加密
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String utf8 = MediaType.APPLICATION_JSON_UTF8_VALUE;

        //授权请求
        http.authorizeRequests()
                //任何请求 都要 认证
                .anyRequest().authenticated()
                .and()
                //表单登录
                .formLogin()
                //处理登录的url
                .loginProcessingUrl("/login").successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        response.setContentType(utf8);
                        log.info("登录成功!");
                        PrintWriter out = response.getWriter();
                        Object principal = authentication.getPrincipal();
                        UsersModel user = (UsersModel)principal;
                        //隐藏密码
                        user.setPassword(null);
                        out.println(JSON.toJSONString(ResultModel.okData(user)));
                        out.close();
                        out.flush();
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                        response.setContentType(utf8);
                        log.error("登录失败!原因: {}", exception.getMessage());
                        PrintWriter out = response.getWriter();
                        out.println(JSON.toJSONString(ResultModel.failMsg(exception.getMessage())));

                        out.close();
                        out.flush();
                    }
                })
                //表示登录相关的页面或者接口不要被拦截
                .permitAll()
                .and()
                .csrf()
                //禁止csrf攻击
                .disable();
    }
}
