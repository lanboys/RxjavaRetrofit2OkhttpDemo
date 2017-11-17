package lan.bing.rxjavademo.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 蓝兵 on 2017/11/17.
 */

public class Student {

    private String mName;
    private List<Course> mCourses = new ArrayList<>();

    public Student(String name) {
        mName = name;
        mCourses.add(new Course(name + "--语文"));
        mCourses.add(new Course(name + "--数学"));
        mCourses.add(new Course(name + "--英语"));
    }

    public String getName() {
        return mName;
    }

    public List<Course> getCourses() {
        return mCourses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "mCourses=" + mCourses +
                ", mName='" + mName + '\'' +
                '}';
    }
}
