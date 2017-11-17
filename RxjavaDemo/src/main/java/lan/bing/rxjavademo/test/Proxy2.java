package lan.bing.rxjavademo.test;

import lan.bing.rxjavademo.LogUtil;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * Author: 蓝兵
 * Email: lan_bing2013@163.com
 * Time: 2017/4/11  15:52
 */
public class Proxy2 {

    protected final LogUtil log = LogUtil.getLogUtil(getClass(), LogUtil.LOG_VERBOSE);

    public void mian() {

        Observable<Integer> observable = Observable.range(10, 5);

        Subscription subscribe = observable
                .subscribe(new Subscriber<Integer>() {

                    @Override
                    public void onStart() {
                        log.e("onStart(): ");
                    }

                    @Override
                    public void onCompleted() {
                         log.e("onCompleted(): ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        log.e("onError(): ");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        log.e("onNext(): " + integer);
                        log.d("onNext(): " + integer);
                    }
                });
    }
}
