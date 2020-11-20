package com.zy.pagehelper.Comparator;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 *比较器Comparator接口的应用
 */
public class comparatorTest {

    /**
     * 实体对象类 静态内部类
     */
    @Data
    @AllArgsConstructor
    static class User {
        String name;
        int age;
    }

    /**
     * 自定义比较器
     * 根据名称比较
     */
    static class MyComparatorName implements Comparator<User> {
        @Override
        public int compare(User u1, User u2) {
            return u1.getName().compareTo(u2.getName()) * -1;
        }
    }
    /**
     * 自定义比较器
     * 根据年龄比较
     */
    static class MyComparatorAge implements Comparator<User>{
        @Override
        public int compare(User u1, User u2) {
            return (u1.getAge() - u2.getAge());
        }
    }

    //测试
    public static void main(String[] args) {
        List<User> list =new ArrayList<User>();
        User u1 = new User("a", 2);
        User u2 = new User("d", 0);
        User u3 = new User("c", 3);
        list.add(u1);
        list.add(u2);
        list.add(u3);

        Collections.sort(list, new MyComparatorName());
        for(User u :list){
            System.out.println(u.getName());
        }

        Collections.sort(list, new MyComparatorAge());
        for(User u :list){
            System.out.println(u.getAge());
        }
    }


}