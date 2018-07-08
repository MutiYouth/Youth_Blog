package com.linn.frame.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 统一异常处理
 * Created by Administrator on 2018\1\16 0016.
 */
public class MyMappingExceptionResolver implements HandlerExceptionResolver {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public ModelAndView resolveException(HttpServletRequest req, HttpServletResponse resp, Object handler, Exception ex) {
        Map<String,Object> model = new HashMap<String, Object>();
        model.put("ex",ex);
        logger.error(ex.getMessage(),ex);
        //根据不同错误转向不同页面
        return new ModelAndView("common/error",model);
    }
}
