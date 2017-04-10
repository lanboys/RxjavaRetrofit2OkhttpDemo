package com.bing.lan.proxy;

/**
 * @author 蓝兵
 * @time 2017/3/15  9:57
 */

/**
 * 委托类
 */
class RealSubject implements Subject {

    protected final LogUtil log = LogUtil.getLogUtil(getClass(), LogUtil.LOG_VERBOSE);
    public void request() {
        log.d("request(): ====RealSubject Request====");
    }

    @Override
    public void load() {

        log.d("load(): ====RealSubject load====");
    }
}