package com.linn.frame.aop;

/**
 * 记录用户操作日志的方面组件
 * Created by Administrator on 2018-04-25.
 */
public class OperateLogger {

    /**
     * 前置通知 后置通知 最终通知使用的方法
     */
    public void before(){
        System.out.println("-->记录用户操作信息");
    }
}
