package com.zy.stream.Controller;

import com.zy.stream.model.Person;
import org.junit.Test;

import java.util.*;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 *  Stream(二)—数值流与对象流的转化及其方法使用
 * @date 2020/11/27 10:01
 *
 *
 **/

public class NumericalFlowConversionTest {

    /**
     *1.   什么是数值流，什么是对象流？
     **/
    @Test
    public void test(){

        /*
         *1.1   数值流就是流中的元素都是基本数据类型（int），对象流就是流中元素为基本数据类型的包装数据类型（Integer）
         *      解析：
         *          * 数组的数据类型为包装类 ：Integer  返回为包装类的流
         *          * 数组的数据类型为基本数据类型 ：int  返回为流对象
         */

        //1.1.1   返回对象流
        Integer[]  arr = {1,2,3,4,5,6,7,8,9};
        Stream<Integer> stream = Arrays.stream(arr);

        //1.1.2   返数值流
        int[]  arrInts = {1,2,3,4,5,6,7,8,9};
        IntStream stream1 = Arrays.stream(arrInts);

    }

    List<Person> personList = new ArrayList<Person>() {
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
     *2. 为什么用数值流
     **/
    @Test
    public void test2() {

        //2.1.1    计算集合中的所有人的薪资总数
        int reduce = personList.stream()
                                    .map(Person::getSalary)    // private int salary,age;map()方法通过方法的引用获取的每一个人的薪资都是int类型的
                                    .reduce(0, Integer::sum);
        /*解析：
         *  我们将代码分为两个部分：
         *       map()方法作为第一部分：返回一个对象流
         *       reduce（）方法作为第二部分：规约操作时，当进行sum操作操作时，就会将Integer转为Int类型(sum源码)
         *
         * 总结：反应出一个问题，也就是这段完整的流操作中间暗含了一个封箱操作（int->Integer）,以及一个拆箱操作（Integer->int）
         */

        //personList.stream().map(Person::getSalary)操作返回时一个对象（Integer）流：
        Stream<Integer> integerStream = personList.stream().map(Person::getSalary);//第一部分代码

        //2.1.2 优化上面的操作（主要是减少封箱拆箱操作）
        int sum = personList.stream()
                            .mapToInt(Person::getSalary)   //  IntStream mapToInt(ToIntFunction<? super T> mapper);
                            .sum();    // int sum();


        /*  优化总结：
         *    使用对象流Stream<Integer>操作的弊端，就是会频繁的进行封箱，拆箱，严重浪费了内存资源，
         *    另外操作对象流也没有数值流这样简单直接，也这是java8提供数值流的原因
         */

    }

    /**
     *3. Numeric streams 数值流的使用
         * java8 引入了三个基本数据类型流接口：
         *  IntStream、DoubleStream和LongStream，分别将流中的元素特化为int、long和double，
         *  从而避免了暗含的装箱、拆箱成本。同时每个接口都带来了进行常用数值reduce操作的新方法，
         *  比如对数值流求和的sum（如果流是空的，sum返回值为0），还有max、min、average等。
         *
     */
    @Test
    public void test3() {

        //3.1   对象流转数值流    将对象流转换为数值流的常用方法是mapToInt、mapToDouble和mapToLong为例：
        Integer[]  arr = {1,2,3,4,5,6,7,8,9};
        Double[]  arrs = {1.1,2.5,3.6,4.4,5.5,6.4,7.1,8.1,9.7};

        //3.1.1     mapToInt()方法
        IntStream intStream = Arrays.stream(arr).mapToInt(e -> e.intValue());

        //3.1.2     mapToDouble()方法
        DoubleStream doubleStream = Arrays.stream(arrs).mapToDouble(e -> e.doubleValue());

        //3.1.3     mapToLong()方法
        LongStream longStream = Arrays.stream(arr).mapToLong(e -> e.longValue());


        /*3.2   数值流转为对象流       boxed()方法
         *  为什么：为数值流里面的方法受众面可能比较窄，方法拓展性也弱
         */
        Integer[]  arrBoxed = {1,2,3,4,5,6,7,8,9};
        Stream<Integer> objStream = Arrays.stream(arrBoxed);//返回对象流
        IntStream intStream1 = objStream.mapToInt(e -> e.intValue());//对象流---->数值流
        Stream<Integer> boxed = intStream1.boxed();//数值流---->对象流

        //优化
        Arrays.stream(arrBoxed).mapToInt(e -> e.intValue()).boxed();


        //3.3   默认值OptionalInt
        Integer[] arrNull = {};
        Stream<Integer> stream = Arrays.stream(arrNull);//对象流
        OptionalInt max1 = stream.mapToInt(i -> i.intValue()).max();//转为数值流求最大值
        int reduce = max1.orElse(10000000);//如果max方法返回值为空，则返回100000000
        System.out.println(reduce);

        int i1 = Arrays.stream(arrNull).mapToInt(i -> i.intValue()).max().orElse(10000000);
        System.out.printf("优化："+i1);



    }

    //数值流综合应用案例
    @Test
    public void testA(){
        /*
         *从整数 1-50 里面创建一个勾股数流（勾股数都为整数），并输出前3组勾股数组，打印出来格式 like this：
         * 3, 4, 5
         * 5, 12, 13
         * 6, 8, 10
         */
        //给出值域范围
        Stream<double[]> rStream2 = IntStream.rangeClosed(1, 50)
                //转为对象流
                .boxed()
                //flatMap 把下面生成的一个个单独三元数组流，扁平化为一个三元数组流
                .flatMap(q ->
                        IntStream.rangeClosed(q, 50)
                                //mapToObj 求出所有满足q*q+w*w+(q*q+w*w)的三元数组 组成的一个个流
                                .mapToObj(w -> new double[]{q, w, Math.sqrt(q * q + w * w)})
                                //q,w是从整数（1-50）里取的，肯定为整数，所以只要过滤掉三元数组中第三个元素不为整数的即可
                                .filter(d -> d[2] % 1 == 0)
                );
        //打印前三组
        rStream2.limit(3).forEach(t->System.out.println(t[0]+"-"+t[1]+"-"+t[2]));

    }


}
