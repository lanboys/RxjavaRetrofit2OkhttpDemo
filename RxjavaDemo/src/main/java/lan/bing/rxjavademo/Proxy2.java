package lan.bing.rxjavademo;

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

        Subscription subscribe = Observable.range(10, 5).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                log.e("onNext(): " + integer);
                log.d("onNext(): " + integer);
            }
        });
    }
}
