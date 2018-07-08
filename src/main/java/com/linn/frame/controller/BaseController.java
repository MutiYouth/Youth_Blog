package com.linn.frame.controller;


import com.alibaba.fastjson.JSON;
import com.linn.frame.entity.ResultBean;
import com.linn.frame.entity.ResultTable;
import com.linn.frame.util.SysContent;
import com.linn.home.service.NoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


/**
 * 基础的Controller,所有的Controller必须继承此类
 *
 * @Author:LNN 2017-12-28 下午 1:52
 */
public class BaseController
{
    /**
     * 日志变量
     **/
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 输出到请求端
     *
     * @param response
     * @param content
     */
    protected void writer(HttpServletResponse response, String content)
    {
        PrintWriter writer = null;
        response.setHeader("Content-Type", "text/xml;charset=UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding(SysContent.DEFAULT_CODE);
        try
        {
            writer = response.getWriter();
            writer.write(content);
        }
        catch (IOException e)
        {
            logger.error(e.getMessage(), e);
        }
        finally
        {
            writer.close();
        }
    }
}
