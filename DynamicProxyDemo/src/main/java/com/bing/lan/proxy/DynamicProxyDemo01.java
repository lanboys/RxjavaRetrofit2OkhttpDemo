package com.bing.lan.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author 蓝兵
 * @time 2017/3/15  0:21
 */

public class DynamicProxyDemo01 {

    protected final LogUtil log = LogUtil.getLogUtil(getClass(), LogUtil.LOG_VERBOSE);

    public void test1() {

        // 1.创建委托对象
        RealSubject realSubject = new RealSubject();
        //2.创建调用处理器对象
        ProxyHandler handler = new ProxyHandler(realSubject);

        //3.动态生成代理对象
        Subject proxySubject = (Subject) Proxy.newProxyInstance(realSubject.getClass().getClassLoader(),
                realSubject.getClass().getInterfaces(), handler);

        //4.通过代理对象调用方法
        proxySubject.request();

        proxySubject.load();
    }

   private class ProxyHandler implements InvocationHandler {

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

    public void test2(final Class<?> service) {

        //3.动态生成代理对象
        Subject proxySubject = (Subject) create(service);
        //4.通过代理对象调用方法
        proxySubject.request();
        proxySubject.load();
    }

    @SuppressWarnings("unchecked")
    public <T> T create(final Class<T> service) {

        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[]{service},
                new InvocationHandler() {

                    protected final LogUtil log1 = LogUtil.getLogUtil(getClass(), LogUtil.LOG_VERBOSE);

                    @Override
                    public Object invoke(Object proxy, Method method, Object... args)
                            throws Throwable {
                        log1.d("invoke(): ====before====");

                        // Object result = method.invoke(subject, args);
                        log1.d("invoke(): ====after====");

                        log1.d("invoke(): proxy " + (proxy == null));
                        return "8888";
                    }
                });
    }
}
