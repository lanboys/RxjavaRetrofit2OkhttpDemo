package com.bing.lan.proxy;

/**
 * @author 蓝兵
 * @time 2017/3/15  9:58
 */

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 代理类的调用处理器
 */
class ProxyHandler implements InvocationHandler {

    protected final LogUtil log = LogUtil.getLogUtil(getClass(), LogUtil.LOG_VERBOSE);
    private Subject subject;

    public ProxyHandler(Subject subject) {
          this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {

        log.d("invoke(): ====before====");
        //定义预处理的工作，当然你也可以根据 method 的不同进行不同的预处理工作
        // Object result = method.invoke(subject, args);
        log.d("invoke(): ====after====");

        log.d("invoke(): proxy "   + (proxy==null));

        return null;
        // return result;
    }
}