package com.songspot.server.filter;

import com.songspot.server.client.UserClient;
import com.songspot.server.configuration.AuthenticationConfig;
import com.songspot.server.controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Objects;

@Component
@Order(0)
public class AuthenticationFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationFilter.class);

    @Autowired
    private AuthenticationConfig authenticationConfig;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        Enumeration<String> headerNames = request.getHeaderNames();

        String username = null;
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                String val = request.getHeader(name);
                if (UserClient.USER_NAME_KEY.equals(name)) {

                    username = URLDecoder.decode(val, StandardCharsets.UTF_8.toString());

                    MDC.put(UserClient.USER_NAME_KEY, username);
                    break;
                }
            }
        }

        if(Objects.isNull(username)) {
            if(this.authenticationConfig.isEnableDefaultUser()) {
                username = this.authenticationConfig.getDefaultUsername();
                MDC.put(UserClient.USER_NAME_KEY, username);
            } else {
                LOGGER.error(request.getRequestURI() + ": user not logged in");
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        LOGGER.info(request.getRequestURI());
        if (UserController.LOGOUT_ROUTE.equals(request.getRequestURI())) {
            Cookie cookie = new Cookie("proxy", null);
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            response.addHeader("Location", "https://www.songspot.cn/home/index.html");
        }
        chain.doFilter(req, res);
    }
}
