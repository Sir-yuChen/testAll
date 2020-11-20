package com.zy.pagehelper.Comparator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Administrator
 * @date 2020/11/20 13:32
 **/

public class GroupTest {

    @Data
    @AllArgsConstructor
    @ToString
    class Apple {
        public String color;
        public int weight;
    }

    /**
     *   是否为同一组的判断标准        参数一：需要处理的数据源  datas，参数二分组时的比较逻辑c
     */
    public static <T> List<List<T>> divider(Collection<T> datas, Comparator<? super T> c) {
        List<List<T>> result = new ArrayList<List<T>>();
        for (T t : datas) {
            boolean isSameGroup = false;
            for (int j = 0; j < result.size(); j++) {
                if (c.compare(t, result.get(j).get(0)) == 0) {
                    isSameGroup = true;
                    result.get(j).add(t);
                    break;
                }
            }
            if (!isSameGroup) {
                // 创建
                List<T> innerList = new ArrayList<T>();
                result.add(innerList);
                innerList.add(t);
            }
        }
        return result;
    }

    /**
     *优化divider方法
     */
    public static <T> List<List<T>> divider_Lambda(Collection<T> datas, Comparator<? super T> c) {
        List<List<T>> result = new ArrayList<List<T>>();
        datas.forEach(x ->{
            AtomicBoolean isSameGroup = new AtomicBoolean(false);
            result.forEach(s ->{
                if (c.compare(x, s.get(0)) == 0) {
                    isSameGroup.set(true);
                    s.add(x);
                }
            });
            Optional.ofNullable(isSameGroup.get() == true ? isSameGroup.get() :null).orElseGet(()-> result.add(new ArrayList<T>(){{add(x);}}));
        });
        return result;
    }



    List<Apple> list = new ArrayList<Apple>(){
        {
            add(new Apple("红", 205));
            add(new Apple("红", 131));
            add(new Apple("绿", 248));
            add(new Apple("绿", 22));
            add(new Apple("黄", 119));
            add(new Apple("黄", 224));
            add(new Apple("白", 2024));
        }
    };

    /**
     *使用lambda优化（按照颜色分组）
     */
   @Test
   public void test1_1() {
       //按照颜色分组
       divider_Lambda(list, (o1, o2) -> o1.color.compareTo(o2.color)).forEach(System.out::println);
   }


    @Test
    public void test2(){
        //按照重量级进行分组
        List<List<Apple>> byWeight = divider(list, new Comparator<Apple>() {

            @Override
            public int compare(Apple o1, Apple o2) {
                // 按重量级
                return (o1.getWeight() / 100 == o2.getWeight() / 100) ? 0 : 1;
            }
        });
        byWeight.forEach(x-> System.out.println("按照总量分组："+x));
        //System.out.println("按重量级分组" + byWeight);
    }

    /**
     *使用lambda优化（按照重量级进行分组）
     */
    @Test
    public void test2_1() {
        //按照颜色分组
        divider_Lambda(list, (o1, o2) -> (o1.getWeight() / 100 == o2.getWeight() / 100) ? 0 : 1).forEach(System.out::println);
    }

}
