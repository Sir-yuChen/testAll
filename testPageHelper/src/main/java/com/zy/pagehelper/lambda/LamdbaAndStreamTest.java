package com.zy.pagehelper.lambda;

import com.zy.pagehelper.model.Person;
import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static java.util.stream.Collectors.*;

/**
 * @author Administrator
 * @date 2020/11/18 10:46
 **/

/*
    Stream是对集合的包装,通常和lambda一起使用。
    使用lambdas可以支持许多操作,如 map, filter, limit, sorted, count, min, max, sum, collect 等等。
    同样,Stream使用懒运算,他们并不会真正地读取所有数据,遇到像getFirst() 这样的方法就会结束链式语法。
    在接下来的例子中,我们将探索lambdas和streams 能做什么。
     我们创建了一个Person类并使用这个类来添加一些数据到list中,将用于进一步流操作。 Person 只是一个简单的POJO类:

 */

public class LamdbaAndStreamTest {

    //1. 创建两个list,都用来存放Person对象

    List<Person> javaProgrammers = new ArrayList<Person>() {
        {
            add(new Person("Elsdon", "Jaycob", "Java programmer", "male", 43, 2000));
            add(new Person("Tamsen", "Brittany", "Java programmer", "female", 23, 1500));
            add(new Person("Floyd", "Donny", "Java programmer", "male", 33, 1800));
            add(new Person("Sindy", "Jonie", "Java programmer", "female", 32, 1600));
            add(new Person("Vere", "Hervey", "Java programmer", "male", 22, 1200));
            add(new Person("Maude", "Jaimie", "Java programmer", "female", 27, 1900));
            add(new Person("Shawn", "Randall", "Java programmer", "male", 30, 2300));
            add(new Person("Jayden", "Corrina", "Java programmer", "female", 35, 1700));
            add(new Person("Palmer", "Dene", "Java programmer", "male", 33, 2000));
            add(new Person("Addison", "Pam", "Java programmer", "female", 34, 1300));
        }
    };

    List<Person> phpProgrammers = new ArrayList<Person>() {
        {
            add(new Person("Jarrod", "Pace", "PHP programmer", "male", 34, 1550));
            add(new Person("Clarette", "Cicely", "PHP programmer", "female", 23, 1200));
            add(new Person("Victor", "Channing", "PHP programmer", "male", 32, 1600));
            add(new Person("Tori", "Sheryl", "PHP programmer", "female", 21, 1000));
            add(new Person("Osborne", "Shad", "PHP programmer", "male", 32, 1100));
            add(new Person("Rosalind", "Layla", "PHP programmer", "female", 25, 1300));
            add(new Person("Fraser", "Hewie", "PHP programmer", "male", 36, 1100));
            add(new Person("Quinn", "Tamara", "PHP programmer", "female", 21, 1000));
            add(new Person("Alvin", "Lance", "PHP programmer", "male", 38, 1600));
            add(new Person("Evonne", "Shari", "PHP programmer", "female", 40, 1800));
        }
    };

    @Test
    public  void test1(){

        /* 1. 使用forEach方法来迭代输出集合中所有的程序员的名字
         *  注意：1. System.out.printf()表达式
         *          第一个%s代表的是第一个变量p.getFirstName(),中间的使用空格拼接.第二个百分号代表的是 p.getLastName(),\n则是换行
         *       2. 普通输出语句是println,而使用这种表达式的是printf,注意区分.
         *       3. 一个%s代表一个变量
         */
        System.out.println("使用forEach方法来迭代输出-->所有程序员的姓名:");
        javaProgrammers.forEach((p) -> System.out.printf("JAVA程序员 ： %s %s; \n", p.getFirstName(), p.getLastName()));
        phpProgrammers.forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));

        //3.同样使用forEach方法,增加程序员的工资5%
        System.out.println("给程序员加薪 5% :");
        Consumer<Person> giveRaise = e -> e.setSalary(e.getSalary() / 100 * 5 + e.getSalary());

        javaProgrammers.forEach(giveRaise);
        phpProgrammers.forEach(giveRaise);

    }

    @Test
    public void testFiltersAndLimit(){

        //3. 定义过滤器,然后重用它们来执行其他操作
        // 定义 filters
        Predicate<Person> ageFilter = (p) -> (p.getAge() > 25);
        Predicate<Person> salaryFilter = (p) -> (p.getSalary() > 1400);
        Predicate<Person> genderFilter = (p) -> ("female".equals(p.getGender()));

        System.out.println("下面是年龄大于 24岁且月薪在$1,400以上的女PHP程序员:");
        phpProgrammers.stream()
                .filter(ageFilter)
                .filter(salaryFilter)
                .filter(genderFilter)
                .forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));

        System.out.println();
        // 重用filters
        System.out.println("年龄大于 24岁的女性 Java programmers:");
        javaProgrammers.stream()
                .filter(ageFilter)
                .filter(genderFilter)
                .forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));


        System.out.println();
        //4. 使用limit方法,可以限制结果集的个数
        System.out.println("最前面的3个 Java programmers:");
        javaProgrammers.stream()
                .limit(3)
                .forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));

        System.out.println();
        System.out.println("最前面的3个女性 Java programmers:");
        javaProgrammers.stream()
                .filter(genderFilter)
                .limit(3)
                .forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));


        //删除集合中的符合条件的值
        //removeIf()删除集合中符合条件的值
        javaProgrammers.removeIf(ele -> ele.getSalary() == 2000);
        //通过 foreach 遍历，查看是否已经删除
        javaProgrammers.forEach(System.out::println);
    }

    /**
     *stream中实现排序
     *在下面的例子中,我们将根据名字和薪水排序Java程序员,放到一个list中,然后显示列表
     */
    @Test
    public void test2(){
        //1.stream中实现排序

        System.out.println("根据 name 排序,并显示前5个 Java programmers:");
        List<Person> sortedJavaProgrammers = javaProgrammers
                .stream()
                .sorted((p, p2) -> (p.getFirstName().compareTo(p2.getFirstName())))
                .limit(5)
                .collect(toList());//静态引入 import static java.util.stream.Collectors.toList;

        sortedJavaProgrammers.forEach((p) -> System.out.printf("%s %s; %n", p.getFirstName(), p.getLastName()));

        System.out.println("根据 salary 排序 Java programmers:");
        sortedJavaProgrammers = javaProgrammers
                .stream()
                .sorted( (p, p2) -> (p.getSalary() - p2.getSalary()) )
                .collect( toList() );

        sortedJavaProgrammers.forEach((p) -> System.out.printf("%s %s; %n", p.getFirstName(), p.getLastName()));

        //2.如果我们只对最低和最高的薪水感兴趣,比排序后选择第一个/最后一个 更快的是min和max方法:
        System.out.println("工资最低的 Java programmer:");
        Person pers = javaProgrammers
                .stream()
                .min((p1, p2) -> (p1.getSalary() - p2.getSalary()))
                .get();

        System.out.printf("Name: %s %s; Salary: $%,d.", pers.getFirstName(), pers.getLastName(), pers.getSalary());

        System.out.println();
        System.out.println("工资最高的 Java programmer:");
        Person person = javaProgrammers
                .stream()
                .max((p, p2) -> (p.getSalary() - p2.getSalary()))
                .get();

        System.out.printf("Name: %s %s; Salary: $%,d.", person.getFirstName(), person.getLastName(), person.getSalary());

    }

    /**
     *上面的例子中我们已经看到 collect 方法是如何工作的。
     *  结合 map 方法,我们可以使用 collect 方法来将我们的结果集放到一个字符串,一个 Set 或一个TreeSet中:
     */
    @Test
    public void test3(){
        String phpDevelopers = phpProgrammers
                .stream()
                .map(Person::getFirstName)
                .collect(joining(" ; ")); // 在进一步的操作中可以作为标记(token)
        System.out.println("将 PHP programmers 的 first name 拼接成字符串:"+phpDevelopers);


        Set<String> javaDevFirstName = javaProgrammers
                .stream()
                .map(Person::getFirstName)
                .collect(toSet());
        System.out.println("将 Java programmers 的 first name 存放到 Set:"+javaDevFirstName.toString());



        TreeSet<String> javaDevLastName = javaProgrammers
                .stream()
                .map(Person::getLastName)
                .collect(toCollection(TreeSet::new));
        System.out.println("将 Java programmers 的 first name 存放到 TreeSet:"+javaDevLastName.toString());
    }

    /**
     *Streams 还可以是并行的(parallel)。 示例如下:
     */
    @Test
    public void test4(){
        int totalSalary = javaProgrammers
                .parallelStream()
                .mapToInt(p -> p.getSalary())
                .sum();
        System.out.println("计算付给 Java programmers 的所有money:"+totalSalary);

    }

    /**
     *我们可以使用summaryStatistics方法获得stream 中元素的各种汇总数据。
     * 接下来,我们可以访问这些方法,比如getMax, getMin, getSum或getAverage:
     */
    @Test
    public void test5() {
        //计算 count, min, max, sum, and average for numbers
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        IntSummaryStatistics stats = numbers
                .stream()
                .mapToInt((x) -> x)
                .summaryStatistics();

        System.out.println("List中最大的数字 : " + stats.getMax());
        System.out.println("List中最小的数字 : " + stats.getMin());
        System.out.println("所有数字的总和   : " + stats.getSum());
        System.out.println("所有数字的平均值 : " + stats.getAverage());
    }


}
