package com.wgh.common.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author wughua
 * @Description AuthFilter
 * @Date 2023/4/15
 */
@Component
public class AuthFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        response.addHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
//        response.addHeader("Access-Control-Allow-Origin", "*");
//        response.addHeader("Content-type", "application/json");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }


}