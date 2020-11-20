package com.zy.pagehelper.Comparator;

import com.zy.pagehelper.model.Persons;
import com.zy.pagehelper.model.Student;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * @author Administrator
 * @date 2020/11/20 15:28
 **/

public class ComparatorMethodTest {



    Persons persons = new Persons();
    List<Persons> personList = new ArrayList<Persons>() {
        {
            add(new Persons("a", new BigDecimal(12), 170));
            add(new Persons("b", new BigDecimal(24), 175, new Student(27)));
            add(new Persons("c", new BigDecimal(12), 177));
            add(new Persons("a", new BigDecimal(12), 177));
            add(new Persons("b", new BigDecimal(54), 174, new Student(9)));
            add(new Persons("b", new BigDecimal(5), 174, new Student(19)));
           //测试nullsLast（）/nullsFirst()方法时添加null数据
           // add(null);
        }
    };

    @Test
    public void testNaturalOrder(){
        // naturalOrder  自然比较，根据实体类定义的Comparable
        System.out.println("naturalOrder : ");
        personList.sort(Comparator.naturalOrder());
        personList.forEach(System.out::println);
    }
    @Test
    public void testComparing(){
        //comparing、comparingLong、comparingInt、comparingDouble - 常用比较方法，可以指定参数类型

        // comparing 1.0  比较集合中对象的年龄，取最大值
        Optional<Persons> optional = personList.stream().max(Comparator.comparing(Persons::getAge));
        System.out.println("comparing 1.0 : get max age " + optional.get().toString() + "\n");

        // comparing 2.1
        optional = personList.stream().max(Comparator.comparing(Persons::getName, Comparator.reverseOrder()));
        System.out.println("comparing 2.1 : get min name " + optional.get().toString() + "\n");

        // comparing 2.2
        optional = personList.stream().max(Comparator.comparing(Persons::getName, String::compareTo));
        System.out.println("comparing 2.2 : get max name " + optional.get().toString() + "\n");

        // comparing 2.3        该方法多了一个参数 keyComparator ，keyComparator 是创建一个自定义的比较器。示例种：通过cmmpare()方法进行学生年龄比较，
        optional = personList.stream().max(Comparator.comparing(Persons::getStudent, (o1, o2) -> new Student().compare(o1, o2)));
        System.out.println("comparing 2.3 : get max student.age " + optional.get().toString() + "\n");

        // 升序 comparing方法的具体实现一
        System.out.println("升序 : ");
        personList.sort(Comparator.comparingInt(Persons::getHeight));
        personList.forEach(System.out::println);


        // 降序 comparing方法的具体实现二
        System.out.println("降序 : ");
        personList.sort(Comparator.comparingInt(Persons::getHeight).reversed());
        personList.forEach(System.out::println);


    }
    @Test
    public void test2(){
        //根据第一个属性进行排序，如果相同则以此根据下一个thenComparing（）中的属性进行排序

        // thenComparing 1.0
        System.out.println("thenComparing 1.0 : ");
        personList.sort(Comparator.comparing(Persons::getAge));
        personList.forEach(System.out::println);

        // thenComparing 1.1
        System.out.println("thenComparing 1.1 : ");
        personList.sort(Comparator.comparing(Persons::getAge).thenComparing(Persons::getHeight));
        personList.forEach(System.out::println);

        // thenComparing 2.0
        System.out.println("thenComparing 2.0 : ");
        personList.sort(Comparator.comparing(Persons::getAge).thenComparing(Persons::getHeight).thenComparing(Persons::getName));
        personList.forEach(System.out::println);

    }
    @Test
    public void testNulls() {

        // nullsLast
        System.out.println("nullsLast -------------->: ");
        personList.sort(Comparator.nullsLast(Comparator.comparing(Persons::getName)));
        personList.forEach(System.out::println);

        // nullsFirst
        System.out.println("nullsFirst -------------->: ");
        personList.sort(Comparator.nullsFirst(Comparator.comparing(Persons::getName)));
        personList.forEach(System.out::println);
    }
}
