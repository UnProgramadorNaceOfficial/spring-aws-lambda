package com.spring.aws.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecurityFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String token = httpServletRequest.getHeader("token");

        if(token == null || token.isEmpty() || !token.equalsIgnoreCase("ABC123")){

            ObjectMapper mapper = new ObjectMapper();

            ObjectNode error = mapper.createObjectNode();
            error.put("Error", "Token invalido o token vacío");

            httpServletResponse.setStatus(401);
            httpServletResponse.setContentType("application/json");

            httpServletResponse.getOutputStream().write(mapper.writeValueAsBytes(error));
            httpServletResponse.getOutputStream().flush();
        } else{
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
