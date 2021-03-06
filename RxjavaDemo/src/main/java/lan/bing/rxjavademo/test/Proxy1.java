package lan.bing.rxjavademo.test;

import android.os.SystemClock;

import java.util.List;

import lan.bing.rxjavademo.LogUtil;
import lan.bing.rxjavademo.domain.Course;
import lan.bing.rxjavademo.domain.Student;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Author: 蓝兵
 * Email: lan_bing2013@163.com
 * Time: 2017/4/11  15:49
 */
public class Proxy1 {

    protected final LogUtil log = LogUtil.getLogUtil(getClass(), LogUtil.LOG_VERBOSE);

    private Student[] mStudents;
    private Student mXiaohong;
    private Student mXiaojuan;
    private Student mXiaoming;

    public void mian() {

        mXiaoming = new Student("小明");
        mXiaojuan = new Student("小娟");
        mXiaohong = new Student("小红");

        mStudents = new Student[]{mXiaoming, mXiaohong, mXiaojuan};

        test0();
        // test1();
        // test2();
        //test3();
    }

    private void test3() {

        // Observable<Student> studentObservable = Observable.from(mStudents);

        Observable.OnSubscribe<Student> f = new Observable.OnSubscribe<Student>() {
            @Override
            public void call(Subscriber<? super Student> subscriber) {
                subscriber.onNext(mXiaohong);
                // subscriber.onNext(mXiaoming);
                // subscriber.onNext(mXiaojuan);
                log.d("call():OnSubscribe " + Thread.currentThread().getName());
            }
        };

        Observable<Student> studentObservable = Observable.create(f);

        Observable<Observable<Course>> observableObservable = studentObservable.map(new Func1<Student, Observable<Course>>() {
            @Override
            public Observable<Course> call(Student student) {
                List<Course> courses = student.getCourses();

                final Course course = courses.get(0);
                final Course course1 = courses.get(1);
                final Course course2 = courses.get(2);

                Observable<Course> observable = Observable.create(new Observable.OnSubscribe<Course>() {
                    @Override
                    public void call(Subscriber<? super Course> subscriber) {

                        subscriber.onNext(course);
                        subscriber.onNext(course1);
                        subscriber.onNext(course2);
                    }
                });

                log.d("call(): ");
                return observable;
            }
        });
        // 正常流程是,调用下一级别的onNext(),但是这里是再次生成一个订阅者,并让
        Observable<Course> observable = Observable.merge(observableObservable);

        // Observable<Course> courseObservable = studentObservable.flatMap(new Func1<Student, Observable<Course>>() {
        //     @Override
        //     public Observable<Course> call(Student student) {
        //              //正常做法,这里的course 不一定是student的成员变量,可以任何对象
        //         // return Observable.from(student.getCourses());
        //         List<Course> courses = student.getCourses();
        //
        //         final Course course = courses.get(0);
        //         final Course course1 = courses.get(1);
        //         final Course course2 = courses.get(2);
        //
        //         Observable<Course> observable = Observable.create(new Observable.OnSubscribe<Course>() {
        //             @Override
        //             public void call(Subscriber<? super Course> subscriber) {
        //
        //                 subscriber.onNext(course);
        //                 subscriber.onNext(course1);
        //                 subscriber.onNext(course2);
        //             }
        //         });
        //
        //         log.d("call(): ");
        //         return observable;
        //     }
        // });

        Observable<Course> courseObservable = observable.observeOn(AndroidSchedulers.mainThread());

        courseObservable.subscribe(new Action1<Course>() {
            @Override
            public void call(Course course) {
                log.d("call(): " + course.getName());
            }
        });
    }

    private void test2() {
        //              List<Course>
        //Student-------------------------> Course?? 直接变换
        Observable.from(mStudents)
                .map(new Func1<Student, List<Course>>() {
                    @Override
                    public List<Course> call(Student student) {
                        return student.getCourses();
                    }
                })
                .subscribe(new Action1<List<Course>>() {
                    @Override
                    public void call(List<Course> courses) {
                        for (Course course : courses) {
                            log.d("call(): courseName  " + course.getName());
                        }
                    }
                });
    }
    // .doOnSubscribe(new Action0() {
    //     @Override
    //     public void call() {
    //         // 需要在主线程执行
    //         log.d("call(): 在事件发送之前执行");
    //     }
    // })
    // .subscribeOn(AndroidSchedulers.mainThread())
    // .doOnNext(new Action1<List<HotResult.HotInfoBean>>() {
    //     @Override
    //     public void call(List<HotResult.HotInfoBean> hotInfoBeen) {
    //在,最终订阅之前做一些操作,跟filter,map,filterMap区别在于,不用对数据做处理
    // (官方说的不改变数据流),可以另外开启一个线程,进行缓存,重试,调试等操作,
    //         Schedulers.io().createWorker().schedule(new Action0() {
    //             @Override
    //             public void call() {
    //                 try {
    //                     Thread.sleep(2000);
    //                 } catch (Exception e) {
    //                     e.printStackTrace();
    //                 }
    //             }
    //         });
    //     }
    // });

    /**
     * 有一个方法 Observable.doOnSubscribe() 。它和 Subscriber.onStart() 同样
     * 是在 subscribe() 调用后而且在事件发送前执行，但区别在于它可以指定线程。
     * 默认情况下， doOnSubscribe() 执行在 subscribe() 发生的线程；而如果在
     * doOnSubscribe() 之后有 subscribeOn() 的话，它将执行在离它最近的
     * subscribeOn() 所指定的线程。
     */
    private void test1() {
        //Student----> String

        Observable.OnSubscribe<Student> f = new Observable.OnSubscribe<Student>() {
            @Override
            public void call(Subscriber<? super Student> subscriber) {
                subscriber.onNext(mXiaohong);
                // subscriber.onNext(mXiaoming);
                // subscriber.onNext(mXiaojuan);
                log.d("call():OnSubscribe " + Thread.currentThread().getName());
            }
        };
        Func1<Student, String> func1 = new Func1<Student, String>() {
            @Override
            public String call(Student student) {
                log.d("call():map " + Thread.currentThread().getName());
                return student.getName();
            }
        };

        Action1<String> action1 = new Action1<String>() {
            @Override
            public void call(String s) {
                log.d("call(): StudentName  " + s);
                log.d("call():subscribe " + Thread.currentThread().getName());
            }
        };

        Observable<Student> studentObservable = Observable.create(f);

        Observable<Student> studentObservable1 = studentObservable.observeOn(Schedulers.newThread());

        Observable<Student> studentObservable2 = studentObservable1.subscribeOn(Schedulers.computation());

        Observable<String> stringObservable = studentObservable2.map(func1);
        // 指定 subscribe() 发生在 IO 线程
        Observable<String> stringObservable1 = stringObservable.subscribeOn(Schedulers.io());
        // 指定 Subscriber 的回调发生在主线程
        Observable<String> stringObservable2 = stringObservable1.observeOn(AndroidSchedulers.mainThread());
        stringObservable2.subscribe(action1);
    }

    private void test0() {

        Observable.OnSubscribe<Student> f = new Observable.OnSubscribe<Student>() {
            @Override
            public void call(Subscriber<? super Student> subscriber) {
                // log.d("call():subscriber " + subscriber);

                subscriber.onNext(mXiaohong);
                subscriber.onNext(mXiaoming);
                //subscriber.onNext(mXiaojuan);
                log.d("call(): OnSubscribe " + Thread.currentThread().getName());
            }
        };

        Observable<Student> studentObservable = Observable.create(f);
        //在生产事件之前发生
        Observable<Student> studentObservable1 = studentObservable.doOnSubscribe(new Action0() {
            @Override
            public void call() {
                log.d("call(): doOnSubscribe------ " + Thread.currentThread().getName());
                SystemClock.sleep(3000);

                //myDoOnSubscribe();

            }
        });

        // 指定 subscribe() 发生在 IO 线程
        Observable<Student> studentObservable2 = studentObservable1.subscribeOn(Schedulers.io());

        Observable<Student> studentObservable4 = studentObservable2.compose(new Observable.Transformer<Student, Student>() {
            @Override
            public Observable<Student> call(Observable<Student> studentObservable) {

                studentObservable = Observable.error(new RuntimeException("自定义的"));

                return studentObservable;
            }
        });

        //final Observable<Student> studentObservable5 = studentObservable4.doOnNext(new Action1<Student>() {
        //    @Override
        //    public void call(Student student) {
        //        log.d("call(): studentObservable5======  " + Thread.currentThread().getName());
        //    }
        //});

        // 指定 Subscriber 的回调发生在主线程
        //Observable<Student> studentObservable3 = studentObservable5.observeOn(AndroidSchedulers.mainThread());

        Observable<Student> studentObservable3 = studentObservable4.retryWhen(new Func1<Observable<? extends Throwable>, Observable<?>>() {
            @Override
            public Observable<?> call(final Observable<? extends Throwable> observable) {

                log.d("call(): retryWhen  " + observable + " " + Thread.currentThread().getName());

                Observable<Object> objectObservable = observable.flatMap(new Func1<Throwable, Observable<?>>() {
                    @Override
                    public Observable<?> call(Throwable throwable) {
                        Observable<?> error = null;
                        if (throwable instanceof RuntimeException) {

                            //error = myDoOnSubscribe();
                            //error = Observable.range(10, 2);
                            error = Observable.error(throwable);
                            //error = Observable.just(mXiaojuan);
                        } else {

                            error = Observable.error(throwable);

                        }
                        return error;
                    }
                });

                return objectObservable;
            }
        });

        Subscriber<Student> subscriber = new Subscriber<Student>() {
            @Override
            public void onStart() {
                log.d("onStart(): subscriber " + Thread.currentThread().getName());
            }

            @Override
            public void onNext(Student student) {
                log.d("call(): subscriber StudentName  " + student.getName() + " " + Thread.currentThread().getName());
                // log.d("call():subscribe " + Thread.currentThread().getName());
            }

            @Override
            public void onCompleted() {
                log.d("onCompleted(): subscriber " + Thread.currentThread().getName());
            }

            @Override
            public void onError(Throwable e) {
                log.d("onError(): subscriber " + e);
            }
        };

        //studentObservable5.subscribe(subscriber);
        studentObservable3.subscribe(subscriber);
    }

    private Observable<Integer> myDoOnSubscribe() {
        Observable<Integer> observable = Observable.range(10, 2);
        Subscription subscribe = observable
                .subscribe(new Subscriber<Integer>() {

                    @Override
                    public void onStart() {
                        log.e("onStart(): " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onCompleted() {
                        log.e("onCompleted(): " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable e) {
                        log.e("onError(): " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onNext(Integer integer) {
                        log.e("onNext(): " + integer + "  " + Thread.currentThread().getName());
                    }
                });
        log.d("call(): doOnSubscribe======= " + Thread.currentThread().getName());

        return observable;
    }
}
