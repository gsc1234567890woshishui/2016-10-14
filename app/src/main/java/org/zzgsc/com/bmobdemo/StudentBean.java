package org.zzgsc.com.bmobdemo;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016/10/13.
 */

public class StudentBean extends BmobObject {
    private int age;
    private String Name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }


}
