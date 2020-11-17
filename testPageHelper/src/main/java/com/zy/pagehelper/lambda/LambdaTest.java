package com.zy.pagehelper.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Consumer;

/**
 * @author Administrator
 * @date 2020/11/17 16:54
 **/

public class LambdaTest {

    @Test
    public  void  lambdaTest(){
       //使用匿名内部类
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {  //降序排列
                return Integer.compare(o2,o1);
            }
        };
        //使用lambda表达式
        Comparator<Integer> lamdbaTestCom = (x, y) -> Integer.compare(y, x);
    }

    /**
     *Lambda表达式的基本语法
     * 1. Java8中引入了一个新的操作符，"->"，该操作符称为箭头操作符或者Lambda操作符，箭头操作符将Lambda表达式拆分成两部分；
     *      左侧: Lambda表达式的参数列表，对应的是接口中抽象方法的参数列表；
     *      右侧: Lambda表达式中所需要执行的功能(Lambda体)，对应的是对抽象方法的实现；(函数式接口(只能有一个抽象方法))
     * 2. Lambda表达式的实质是　对接口的实现；
     *
     * @author zhangyu
     * @date 2020/11/17 17:04
     * @return void
     */


    @Test
    public void test1(){
        /**
         *语法格式一、
         *  接口中的抽象方法 : 无参数，无返回值；
         */

        /*final */int num = 2; //jdk1.7之前必须定义为final的下面的匿名内部类中才能访问

        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello world!" + num); //本质还是不能对num操作(只是jdk自己为我们设置成了final的)
            }
        };
        r.run();

        System.out.println("----------使用Lambda输出-----------");

        Runnable r1 = () -> System.out.println("Hello world!" + num);//省去乐：大括号，分号
        r1.run();
    }

    @Test
    public void test2(){
        /**
         *语法格式二、
         *  接口中的抽象方法 : 一个参数且无返回值； (若只有一个参数，那么小括号可以省略不写)
         */

        Consumer<String> con = x -> System.out.println(x);
        con.accept("Lambda牛逼!");
    }

    @Test
    public void test3(){
        /**
         *语法格式三、
         *  两个参数，有返回值，并且有多条语句 ：　要用大括号括起来，而且要写上return
         */
        Comparator<Integer> com = (x,y) -> {
            System.out.println("函数式接口,");
            return Integer.compare(y,x); //降序
        };

        Integer[] nums = {4,2,8,1,5};
        Arrays.sort(nums,com);
        System.out.println(Arrays.toString(nums));
    }

    @Test
    public void test4(){
        /**
         *语法格式四、
         *  两个参数，有返回值，但是只有一条语句:　大括号省略，return省略
         */
        //运行流程，运行过程？？？？？？？？？？？？？？
        Comparator<Integer> com = (x,y) -> Integer.compare(x,y);//升序
        Integer[] nums = {4,2,8,1,5};
        Arrays.sort(nums,com);
        System.out.println(Arrays.toString(nums));
    }


    /**
     *语法格式五、
     *  Lambda表达式的参数列表的数据类型 可以省略不写，因为JVM编译器通过上下文推断出数据类型，即"类型推断"，
     *  (Integer x,Integer y ) -> Integer.compare(x,y)可以简写成(x,y) -> Integer.compare(x,y)；
     */





}
