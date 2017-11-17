package lan.bing.rxjavademo.domain;

/**
 * Created by 蓝兵 on 2017/11/17.
 */

public class Course {

    private String mName;

    public Course(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    @Override
    public String toString() {
        return "Course{" +
                "mName='" + mName + '\'' +
                '}';
    }
}
