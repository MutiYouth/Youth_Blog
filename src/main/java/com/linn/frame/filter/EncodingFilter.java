package com.linn.frame.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * 乱码过滤
 * Created by admin on 2018/1/6.
 */
public class EncodingFilter implements Filter {
    private static Logger logger = LoggerFactory.getLogger(EncodingFilter.class);
    private String encoding;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(encoding);
        servletResponse.setCharacterEncoding(encoding);
        filterChain.doFilter(new HttpServletRequestWrapper((HttpServletRequest) servletRequest) {
            @Override
            public String getParameter(String name) {
                String value = super.getParameter(name);
                if (this.getMethod().equalsIgnoreCase("GET")) {

                    if (value != null) {
                        try {
                            return new String(value.getBytes("iso8859-1"), encoding);
                        } catch (UnsupportedEncodingException e) {
                            logger.error(e.getMessage(),e);
                        }
                        return value;
                    }
                }
                return super.getParameter(name);
            }
        },servletResponse);
    }

    @Override
    public void destroy() {
        encoding = null;
    }
}
