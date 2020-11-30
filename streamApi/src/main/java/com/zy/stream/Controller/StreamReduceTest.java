package com.zy.stream.Controller;

import com.zy.stream.model.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

/**
 * Stream流中的聚合操作(汇聚操作)--reduce（）
 * @date 2020/11/30 9:57
 **/

public class StreamReduceTest {


    List<Person> javaProgrammers = new ArrayList<Person>() {
        {
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

    /**
     *对Stream中的数据通过累加器accumulator迭代计算，最终得到一个Optional对象
     *
     */

    @Test
    public void Test() {
        int asInt = javaProgrammers.stream()
                                    .mapToInt(Person::getSalary)//返回数值流，减少拆箱封箱操作，避免占用内存  IntStream
                                    .reduce((x, y) -> x += y)// int
                                    .getAsInt(); //return int
        System.out.printf("方式一   reduce(BinaryOperator<T> accumulator)   求薪资测试结果："+asInt);

        /*解析：
             1. reduce(BinaryOperator<T> accumulator)    reduce方法接受一个函数，这个函数有两个参数
             2. 第一个参数是上次函数执行的返回值（也称为中间结果），第二个参数是stream中的元素，这个函数把这两个值相加，得到的和会被赋值给下次执行这个函数的第一个参数
         *注意：
             1.第一次执行的时候第一个参数的值是Stream的第一个元素，第二个参数是Stream的第二个元素
             2.方法返回值类型是Optional
         */
    }
    
    /**
     *T reduce(T identity, BinaryOperator<T> accumulator);
     * 提供一个跟Stream中数据同类型的初始值identity，通过累加器accumulator迭代计算Stream中的数据，得到一个跟Stream中数据相同类型的最终结果
     */
    @Test
    public void test1(){
        System.out.println();
        int reduce = javaProgrammers.stream()
                                    .mapToInt(Person::getSalary)
                                    .reduce(10000, (x, y) -> x += y);//return IntBinaryOperator类    数据int类型
        System.out.printf("方式二  reduce(T identity, BinaryOperator<T> accumulator)   求薪资测试结果："+reduce);

        /*注意：
         *      1.与方式一相比设置了累加器的初始值，参数一（x）则不再是Stream中的第一个数据而是设置的初始值（10000）
         *      2. 直接返回int类型【 int reduce(int identity, IntBinaryOperator op);】
         */
    }

    /**
     * <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner);
     *
     */
    @Test
    public void test2() {
        ArrayList<Integer> accResult_ = Stream.of(1, 2, 3, 4)
                //第一个参数，初始值为ArrayList
                .reduce(new ArrayList<Integer>(),
                        //第二个参数，实现了BiFunction函数式接口中apply方法，并且打印BiFunction
                        new BiFunction<ArrayList<Integer>, Integer, ArrayList<Integer>>() {
                            @Override
                            public ArrayList<Integer> apply(ArrayList<Integer> acc, Integer item) {

                                acc.add(item);
                                System.out.println("item: " + item);
                                System.out.println("acc+ : " + acc);
                                System.out.println("BiFunction");
                                return acc;
                            }
                            //第三个参数---参数的数据类型必须为返回数据类型，改参数主要用于合并多个线程的result值
                            // （Stream是支持并发操作的，为了避免竞争，对于reduce线程都会有独立的result）
                        }, new BinaryOperator<ArrayList<Integer>>() {
                            @Override
                            public ArrayList<Integer> apply(ArrayList<Integer> acc, ArrayList<Integer> item) {
                                System.out.println("BinaryOperator");
                                acc.addAll(item);
                                System.out.println("item: " + item);
                                System.out.println("acc+ : " + acc);
                                System.out.println("--------");
                                return acc;
                            }
                        });
        System.out.println("accResult_: " + accResult_);

        System.out.println("------------------lambda优化代码-----------------");

        ArrayList<Integer> newList = new ArrayList<>();

        ArrayList<Integer> accResult_s = Stream.of(1,2,3,4)
                .reduce(newList,
                        (acc, item) -> {
                            acc.add(item);
                            System.out.println("item: " + item);
                            System.out.println("acc+ : " + acc);
                            System.out.println("BiFunction");
                            return acc;
                        }, (acc, item) -> null);
        System.out.println("accResult_s: " + accResult_s);

        /**
         *注意：
         *  1. 方式三:第三个参数用来处理并发操作，如何处理数据的重复性，应多做考虑，否则会出现重复数据！
         */

    }


}
