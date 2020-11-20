package com.zy.pagehelper.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;

/**
 * @author Administrator
 * @date 2020/11/20 15:55
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Comparator {

    private int age;

    @Override
    public int compare(Object o1, Object o2) {

        Student p1 = (Student) o1;
        Student p2 = (Student) o2;

        int result = Integer.compare(p1.age, p2.age);

        result = result == 0 ? ((p1.age > p2.age) ? 1 : -1) : result;

        return result;
    }
}