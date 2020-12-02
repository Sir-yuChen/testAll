package com.zy.stream.methodReference;

import com.zy.stream.model.Apple;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Comparator.comparing;

/**
 * @author Administrator
 * @date 2020/12/1 9:38
 **/

public class methodReference {


    @Test
    public void test1(){
        //先前：
        List<Apple> lists= Arrays.asList(
                new Apple("red", 50),
                new Apple("green", 40),
                new Apple("blank", 90)
        );


        //使用lambda表达式排序
        lists.sort((Apple a1, Apple a2)
                -> a1.getWeight().compareTo(a2.getWeight()));
       // 之后（使用方法引用和java.util.Comparator.comparing）：
        lists.stream().sorted(comparing(Apple::getWeight)).forEach(System.out::println);


    }

    /**
     *方法引用---字符翻转示例
     */

    interface StringFunc
    {
        String func(String n);
    }

    static class MyStringOps
    {
        public static String strReverse(String str)
        {
            StringBuilder result = new StringBuilder();
            for (int i = str.length() - 1; i >= 0; i--)
            {
                result.append(str.charAt(i));
            }
            return result + "";
        }
    }

    public static class CRacer {
        public static String stringOp(StringFunc sf, String s) {
            return sf.func(s);
        }

        public static void main(String[] args) {
            String inStr = "labmda add power to Java";
            //MyStringOps::strReverse 相当于实现了接口方法func()
            // 并在接口方法func()中作了MyStringOps.strReverse()操作
            //表达式MyStringOps::strReverse的计算结果为对象引用，其中，strReverse提供了StringFunc的func()方法的实现。
            String outStr = stringOp(MyStringOps::strReverse, inStr);
            System.out.println("--------- " + inStr);
            System.out.println("++++++++ +" + outStr);
        }

    }



}
