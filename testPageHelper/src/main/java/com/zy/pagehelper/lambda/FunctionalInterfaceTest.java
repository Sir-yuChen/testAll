package com.zy.pagehelper.lambda;

/**
 * @author Administrator
 * @date 2020/11/23 10:12
 **/

import com.zy.pagehelper.model.Person;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.*;

/**
 *
 *JDK 1.8 API中包含了很多内置的函数式接口。有些是在以前版本的Java中大家耳熟能详的，
 * 例如Comparator接口，或者Runnable接口。对这些现成的接口进行实现，
 * 可以通过@FunctionalInterface 标注来启用Lambda功能支持
 *
 * 此外，Java 8 API 还提供了很多新的函数式接口，来降低程序员的工作负担。
 * 有些新的接口已经在Google Guava库中很有名了。如果你对这些库很熟的话，
 * 你甚至闭上眼睛都能够想到，这些接口在类库的实现过程中起了多么大的作用。
 */
public class FunctionalInterfaceTest {


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
            add(new Person("Evonne", "Shari", "PHP programmer", "female", 40, 1800));
        }
    };


    /**
     *Predicates
     * Predicate是一个布尔类型的函数，该函数只有一个输入参数。
     * Predicate接口包含了多种默认方法，用于处理复杂的逻辑动词（and, or，negate）
     */
    @Test
    public void testPredicates(){
        Predicate<String> predicateTest = (s) -> s.length() > 2;

        boolean foo = predicateTest.test("foo");// true
        boolean foo1 = predicateTest.test("a");// false
        System.out.println("测试test："+foo1);

        //测试and和test方法
        boolean b = testAndMethod("zhangssss",
                stringOne -> stringOne.equals("zhangsan"), stringTwo -> stringTwo.length() > 5);
        System.out.println("测试and方法打印结果："+b);

        //测试negate()和test()
        boolean f = testNageteMethod("zhangsan", stringOne -> stringOne.equals("zhangsan"));
        System.out.println("测试negate方法打印结果："+f);

        //测试or方法
        boolean a = testOrMethod("zhangsan"
                , stringOne -> stringOne.equals("zhangsan111")
                , stringTwo -> stringTwo.length() > 50
                , stringThree -> stringThree.length() % 2 == 0);
        System.out.println("测试or方法打印结果："+a);

        //测试isEqual()方法
        System.out.println(testMethodIsEquals("zhangsan","zhangsan"));
        System.out.println("~~~   ~~~   ~~~   ~~~");
        System.out.println(testMethodIsEquals("zhangsan","lisi"));
        System.out.println("~~~   ~~~   ~~~   ~~~");
        System.out.println(testMethodIsEquals(null,"zhangsan")); /* 我们来Debug一下这个程序*/


        Predicate<Person> nonNull = Objects::nonNull;
        boolean test = nonNull.test(null);
        boolean test1 = nonNull.test(new Person(null, "Shari", "PHP programmer", "female", 40, 1800));

        Predicate<Boolean> isNull = Objects::isNull;

        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();

    }

    /**
     *Consumer函数式接口测试
     */
    @Test
    public void  testConsumer(){
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        // 通过lambda表达式构造出Consumer对象
        Consumer listener = i -> System.out.println(i);
        list.forEach(listener);

        // 通过lambda表达式构造出Consumer对象
        list.forEach(i -> System.out.println(i * 2));
        //测试andThen方法
        print(list, item -> System.out.print(" consumer1-->:" + item * 2), item -> System.out.println(" consumer2-->:" + item * 3));

    }

    /**
     *supplier供给型函数接口测试
     */
    @Test
    public void testSupplier() {
        Supplier<Person> supplier = Person::new;
        Person person = supplier.get();
        System.out.println("通过Supplier接口创建Person空对象："+person);
        System.out.println("---------------------------");

        Supplier<Person> supplier1 =() -> new Person("Evonne", "Shari", "JAVA", "female", 40, 1800);
        Person person1 = supplier1.get();
        System.out.println("通过Supplier接口创建Person非空对象："+person1);
    }

    /**
     *Function函数式接口
     */
    @Test
    public void testFunction() {
        Function<Integer,Integer> test= i->i*1;
        Integer apply = test.apply(5);
        System.out.printf("function测试："+apply);
        System.out.println();

        //当然实际开发中的逻辑可能很复杂，比如两个方法F1,F2都需要两个个逻辑AB，但是F1需要A->B，F2方法需要B->A。这样的我们用刚才的方法也可以实现，源码如下：
        Function<Integer,Integer> A=i->i+1;
        Function<Integer,Integer> B=i->i*i;
        System.out.println("F1:"+B.apply(A.apply(5)));
        System.out.println("F2:"+A.apply(B.apply(5)));

        //也很简单呢，但是这还不够复杂，假如我们F1,F2需要四个逻辑ABCD，那我们还这样写就会变得很麻烦了。
        Function<Integer,Integer> A1=i->i+1;
        Function<Integer,Integer> B1=i->i*i;

        System.out.println("F1_1:"+B1.apply(A1.apply(5)));
        System.out.println("F1_1:"+B1.compose(A1).apply(5));//compose接收一个Function参数，返回时先用传入的逻辑执行apply，然后使用当前Function的apply。

        System.out.println("F2_1:"+A1.apply(B1.apply(5)));
        System.out.println("F2_1:"+B1.andThen(A1).apply(5));//andThen跟compose正相反，先执行当前的逻辑，再执行传入的逻辑。

        Integer apply1 = B1.compose(A1).compose(A1).andThen(A1).apply(5);
        System.out.println("-------50---------"+apply1);

    }






    /*
        andThen方法， 将参数传递给调用者执行accept方法，然后再传给第二个consumer执行accept方法。
     */
    public void print(List<Integer> list, IntConsumer con1, IntConsumer con2) {
        list.forEach(item -> con1.andThen(con2).accept(item));
    }



    /**
     *
     * @param stringOne         待判断的字符串
     * @param predicateOne      断定表达式1
     * @param predicateTwo      断定表达式2
     * @return                    是否满足两个条件
     */
    public boolean testAndMethod(String stringOne, Predicate<String> predicateOne,Predicate<String> predicateTwo) {

        return predicateOne.and(predicateTwo).test(stringOne);//and 方法返回一个Predicate<? super T>
    }


    public boolean testNageteMethod(String stringValue, Predicate<String> predicate) {
        return predicate.negate().test(stringValue);
    }

    public boolean testOrMethod(String stringOne, Predicate<String> predicateOne, Predicate<String> predicateTwo, Predicate<String> predicateThree) {

        return predicateOne.or(predicateTwo).or(predicateThree).test(stringOne);
    }

    public boolean testMethodIsEquals(String strValue, String strValue2) {

        return Predicate.isEqual(strValue).test(strValue2);
    }

}
