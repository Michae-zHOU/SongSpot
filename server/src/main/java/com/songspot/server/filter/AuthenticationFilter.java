package com.songspot.server.filter;

import com.songspot.server.authentication.AuthenticationService;
import com.songspot.server.client.UserClient;
import com.songspot.server.configuration.AuthenticationConfig;
import com.songspot.server.controller.UserController;
import com.songspot.server.controller.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
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

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;


        String requestUri = request.getRequestURI();
        if (doBypass(requestUri)) {
            LOGGER.info(requestUri);
            chain.doFilter(req, res);
            return;
        }

        Enumeration<String> headerNames = request.getHeaderNames();

        String username = null;
        boolean tokenExpired = false;
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                String val = request.getHeader(name);
                if (UserClient.USER_TOKEN_KEY.equals(name)) {
                    String token = URLDecoder.decode(val, StandardCharsets.UTF_8.toString());
                    User user = authenticationService.parseToken(token);
                    if (Objects.isNull(user)) {
                        tokenExpired = true;
                        break;
                    }

                    username = user.getUsername();
                    MDC.put(UserClient.USER_NAME_KEY, username);
                    LOGGER.info("User " + username + " logged in");
                    break;
                }
                if (this.authenticationConfig.isEnableUserHeader() && UserClient.USER_NAME_KEY.equals(name)) {
                    username = URLDecoder.decode(val, StandardCharsets.UTF_8.toString());
                    MDC.put(UserClient.USER_NAME_KEY, username);
                    break;
                }
            }
        }

        if (Objects.isNull(username)) {
            if (this.authenticationConfig.isEnableDefaultUser() && !tokenExpired) {
                username = this.authenticationConfig.getDefaultUsername();
                MDC.put(UserClient.USER_NAME_KEY, username);
            } else {
                LOGGER.error(requestUri + ": user not logged in");
                Cookie cookie = new Cookie("proxy", null);
                cookie.setPath("/");
                cookie.setHttpOnly(true);
                cookie.setMaxAge(0);
                response.addCookie(cookie);
                response.addHeader("Location", "https://www.songspot.cn/login");
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                return;
            }
        }

        LOGGER.info(requestUri);
        if (UserController.LOGOUT_ROUTE.equals(requestUri)) {
            LOGGER.info("User " + username + " logged out");
            Cookie cookie = new Cookie("proxy", null);
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            response.addHeader("Location", "https://www.songspot.cn/home/index.html");
        }
        chain.doFilter(req, res);
    }

    private boolean doBypass(String requestUri) {
        return (UserController.REGISTER_ROUTE.equals(requestUri) || UserController.LOGIN_ROUTE.equals(requestUri));
    }
}
