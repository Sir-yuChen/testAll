package com.zy.pagehelper.Comparator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.junit.Test;

import java.text.Collator;
import java.util.*;


/**
 * Comparator 接口实现排序
 */
public class SortTest {

    @Data
    @AllArgsConstructor
    @ToString
    class Dog{ //内部类
        public int age;
        public String name;
        public String num;
    }

    List<Dog> list= new ArrayList<Dog>(){
        {
            add(new Dog(5, "DogA","001"));
            add(new Dog(5, "DogB","002"));
            add(new Dog(5, "DogC","003"));
            add(new Dog(9, "DogA","004"));
            add(new Dog(35, "DogF","005"));
            add(new Dog(74, "Dogg","006"));
        }
    };
    /**
     *单一条件排序
     */

    @Test
    public void test1(){
        //按照年龄排序
        Collections.sort(list, new Comparator<Dog>() {
            //实现compare(T o1, To2) 方法，返回正数，零，负数各代表大于，等于小于
            @Override
            public int compare(Dog o1, Dog o2) {
                //return o2.age - o1.age; //排序规则----升序
                return String.valueOf(o1.getAge()).compareTo(String.valueOf(o2.getAge())); //compareTo（）传String
            }
        });
        System.out.println("给狗狗按照年龄倒序："+list);
    }

    @Test
    public void test2(){
        //按照名字排序
        Collator comparator = Collator.getInstance(Locale.CANADA);

        Collections.sort(list, new Comparator<Dog>() {

            @Override
            public int compare(Dog o1, Dog o2) {
               // return o1.name.compareTo(o2.name);
                return comparator.compare(o1.getName(),o2.getName());
            }
        });
        System.out.println("给狗狗按名字字母顺序排序："+list);
    }

    /**
     *使用Lambda表达式优化比较器代码(单一条件排序)
     */
    @Test
    public void test3() {
        //对学生集合按年龄进行排序
        Collections.sort(list,(s1, s2) -> (s1.getAge() - s2.getAge()) );
    }


    /**
     *多条件排序
     */
    @Test
    public void test4() {

        Collections.sort(list,new Comparator<Dog>() {

            @Override
            public int compare(Dog s1, Dog s2) {
                int flag;
                // 首选按年龄升序排序
                flag = s1.getAge()-s2.getAge();
                if(flag==0){
                    // 如果年龄按编号降序排序
                    flag = s2.getNum().compareTo(s1.getNum());
                }
                return flag;
            }
        });
        list.forEach(System.out::println);
       }
       /**
        *多条件排序 ----使用lambda表达式优化
        */
    @Test
    public void test5() {
        Collections.sort(list,(s1,s2)->{
            int flag;
            // 首选按年龄升序排序
            flag = s1.getAge()-s2.getAge();

            // 方案一、判断是否为空 --- 为空执行lambda表达式，返回一个对象
            flag = Optional.ofNullable(flag == 0 ? null: flag).orElseGet(() -> s2.getNum().compareTo(s1.getNum()));

            //方案二、
            if(flag==0){
                // 如果年龄按编号降序排序
                flag =s2.getNum().compareTo(s1.getNum());
            }
            //最终返回
            return flag;
        });
        list.forEach(System.out::println);
    }


    /**
     *自定义条件排序
     */
    //定义排序规则  通过asList()方法将数组转为list集合
    String[] order = {"语文","数学","英语","物理","化学","生物","政治","历史","地理","总分"};
    final List<String> definedOrder = Arrays.asList(order);

    //需要排序的数据
    List<String> listClass = new ArrayList<String>(){
        {
            add("总分");
            add("英语");
            add("政治");
            add("总分");
            add("数学");
        }
    };
    //自定义条件排序方案一
    @Test
    public void test6(){

        Collections.sort(listClass,new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                //int indexOf(String str) ：返回第一次出现的指定子字符串在此字符串中的索引。
                int io1 = definedOrder .indexOf(o1);
                int io2 = definedOrder .indexOf(o2);
                return io1-io2;
            }
        });

        for(String s:listClass){
            System.out.print(s+"   ");
        }
        //打印结果：数学   英语   政治   总分   总分
    }

    //自定义条件排序方案二 使用Lambda表达式优化比较器代码
    @Test
    public void test7(){

        Collections.sort(listClass,(s1,s2)->definedOrder.indexOf(s1) - definedOrder.indexOf(s2));
        listClass.forEach(System.out::print);
    }




}
