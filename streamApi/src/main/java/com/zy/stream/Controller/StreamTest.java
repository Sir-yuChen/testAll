package com.zy.stream.Controller;

import com.zy.stream.model.Person;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Administrator
 * @date 2020/11/26 9:05
 **/

public class StreamTest {


    @Test
    public void test(){
        //Lists是Guava中的一个工具类
        List<Integer> numberlist = Lists.newArrayList(1,null,3,4,null,6);

        numberlist.stream().filter(num -> num != null).count();
        /**
         * 1.通过.Stream（）创建Stream实例----------创建Stream
         * 2.通过.filter（）中间操作，根据内部迭代对list集合中的每一个元素判断是否为null---------转换Stream
         * 3.通过。count（）方法统计数量------------聚合
         */

        List<Person> listPerson = new ArrayList<Person>() {
            {
                //元素序列-----翻译过来就是集合中的所有元素对象
                add(new Person("Elsdon", "Jaycob", "Java programmer", "male", 2000, 18));
                add(new Person("Tamsen", "Brittany", "Java programmer", "female", 2371, 55));
                add(new Person("Floyd", "Donny", "Java programmer", "male", 3322, 25));
                add(new Person("Sindy", "Jonie", "Java programmer", "female", 35020, 15));
                add(new Person("Vere", "Hervey", "Java programmer", "male", 2272, 25));
                add(new Person("Maude", "Jaimie", "Java programmer", "female", 2057, 87));
                add(new Person("Shawn", "Randall", "Java programmer", "male", 3120, 99));
                add(new Person("Jayden", "Corrina", "Java programmer", "female", 345, 25));
                add(new Person("Palmer", "Dene", "Java programmer", "male", 3375, 14));
                add(new Person("Addison", "Pam", "Java programmer", "female", 3426, 20));
            }
        };
        //获取集合中年龄大于三十的人的名字，返回集合
        List<String> reList = listPerson
                .stream()//创建Stream实例
                .filter(e -> e.getAge() > 30).map(e -> e.getFirstName())//中间操作
                .collect(Collectors.toList());//终端操作
        Stream<Person> stream1 = listPerson.stream();
        stream1.forEach(System.out::print);

        Stream<Person> stream2 = listPerson.stream();
        stream2.forEach(System.out::print);


    }

}
