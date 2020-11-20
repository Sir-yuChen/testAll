package com.zy.pagehelper.model;

/**
 * @author Administrator
 * @date 2020/11/20 15:40
 **/

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Builder
public class Persons implements Comparable {
    private String name;
    private BigDecimal age;
    private Integer height;
    private Student student;

    public Persons(String name, BigDecimal age, Integer height) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.student = new Student(0);
    }

    public Persons(String name, BigDecimal age, Integer height, Student student) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.student = student;
    }


    @Override
    public int compareTo(Object o) {
        Persons p1 = (Persons) o;

        if (this.age.equals(p1.age)) {
            return p1.height - this.height;
        }
        return this.age.compareTo(p1.age);
    }
}

