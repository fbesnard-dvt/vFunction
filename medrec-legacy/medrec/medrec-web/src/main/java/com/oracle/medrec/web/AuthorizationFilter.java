package com.oracle.medrec.web;

import com.oracle.medrec.model.Administrator;
import com.oracle.medrec.model.Patient;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A Servlet Filter for authenticate if the user can request certain URI.
 * 
 * @author Copyright (c) 2007, 2014, Oracle and/or its
 *         affiliates. All rights reserved.
 */
@WebFilter(urlPatterns = { "/admin/*", "/patient/*" })
public class AuthorizationFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain filterChain) throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        Object user = httpRequest.getSession().getAttribute(
                Constants.AUTHENTICATED_USER_SESSION_KEY);
        if (httpRequest.getRequestURI().contains(Constants.ADMIN_BASE_PATH)
                && (user == null || !(user instanceof Administrator))) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + Constants.INDEX_PATH);
            return;
        }

        if (httpRequest.getRequestURI().contains(Constants.PATIENT_BASE_PATH)
                && (user == null || !(user instanceof Patient))) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + Constants.INDEX_PATH);
            return;
        }

        filterChain.doFilter(request, response);
    }

    public void init(FilterConfig filterConfig) {
    }

    public void destroy() {
    }
}
