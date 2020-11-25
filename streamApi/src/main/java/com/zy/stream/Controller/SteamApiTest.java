package com.zy.stream.Controller;

import com.zy.stream.model.Person;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Administrator
 * @date 2020/11/24 15:07
 **/

public class SteamApiTest {


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
            add(new Person("Addison", "Pam", "Java programmer", "female", 3422, 20));
            add(new Person("Addison", "Pam", "Java programmer", "female", 3429, 20));
           // add(new Person("Addison", "Pam", "Java programmer", null, 3422, 20));
        }
    };


    /**
     *java.util.Stream表示了某一种元素的序列，在这些元素上可以进行各种操作。Stream操作可以是中间操作，也可以是完结操作。
     * 完结操作会返回一个某种类型的值，而中间操作会返回流对象本身，
     * 并且你可以通过多次调用同一个流操作方法来将操作结果串起来（就像StringBuffer的append方法一样————译者注）。
     * Stream是在一个源的基础上创建出来的，例如java.util.Collection中的list或者set（map不能作为Stream的源）。
     * Stream操作往往可以通过顺序或者并行两种方式来执行。
     */
    @Test
    public  void test1() {
        //Stream是在一个源的基础上创建出来的，例如java.util.Collection中的list或者set（map不能作为Stream的源）。
        HashMap<String, Object> map = new HashMap<>();
        map.put("ss","d");

        ArrayList<Object> list = new ArrayList<>();
        list.add("sss");
        list.stream().forEach(System.out::println);

    }

    /**
     *创建Stream流的方式
     * 1. 通过集合
     * 2. 通过数组
     * 3. 通过Stream的of
     * 4. 通过无限流
     */
    @Test
    public void test2() {
        //1.1   通过集合创建Stream流------> default Stream<E> stream：返回一个顺序流
        Stream<Person> stream=javaProgrammers.stream();

        //1.2   通过集合创建Stream流------> default Stream<E> parallelStream：返回一个并行流
        Stream<Person> parallelStream = javaProgrammers.parallelStream();


        //2.1   通过数组创建Stream流-------> 调用Arrays类的static <T> Stream<T>  Stream<T[] array> ：返回一个流
        Person[] arrPerson = new Person[]{javaProgrammers.get(0),javaProgrammers.get(1)};
        Stream<Person> streamObj = Arrays.stream(arrPerson);

        //3.1   通过of创建Stream
        Stream<Integer> streamOf = Stream.of(1, 2, 3, 4, 5, 6);

        Stream<Person> streamOfs = Stream.of(javaProgrammers.get(0),javaProgrammers.get(1));

        //4.1   通过无限流的方式创建Stream流
        /*4.1.1 迭代 ----》public static<T> Stream<T> iterate(final T seed,final UnaruOperator<T> f)
         * 遍历前10个偶数
         */
        Stream.iterate(0,t->t+2).limit(10).forEach(System.out::println);  //seed是起始数值，limit代表循环前10次

        //4.1.2 生成----》public static<T> Stream<T> generate(Supplier<T> s)
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }

    /*
     * Stream流的中间操作，（也就是常用方法）
     * 中间操作有：
     * 1. **筛选和切片**
     *     ⚪`filter`(Predicate p)–接受Lambda，从流中排除某些元素
     *     ⚪`limit(n)`–截断流，使其元素不超过给定数量
     *     ⚪`skip(n)`–跳过元素，返回一个扔掉了前n个元素的流。若流中元素不足n个，则返回一个空流。与limit(n)互补
     *     ⚪`distinct`–筛选，通过流所生成的元素的 hashCode() 和 equals() 去除重复元素
     * 2. **映射**
     *    ⚪`map(Function f)`–接受一个函数作为参数，将元素转换成其他形式或提取信息，该函数会被应用到每个元素上，并将其映射成一个新的元素。
     *    ⚪`flatMap(Function f)`–接受一个函数作为参数，将流中的每个值都转换成另一个流，然后把所有流连成一个流
     * 3. ** 排序**
     *    ⚪sorted()–自然排序，产生一个新流，其中按自然顺序排序
     *    ⚪sorted(Comparator com)–定制排序，产生一个新流，其中按比较器顺序排序
     */
    @Test
    public void testFilter() {
        //1.1   `filter`(Predicate p)–接受Lambda，从流中排除某些元素
        //1.1.1 原生方式  所有的中间操作不会做任何的处理
        Stream<Person> stream = javaProgrammers.stream()
                .filter((e) -> {
                    System.out.println("测试中间操作");
                    return e.getAge() < 20;
                });
        stream.forEach(System.out::println);//只有当做终止操作时，所有的中间操作会一次性的全部执行，称为“惰性求值”

        //1.1.2     使用lambdas表达式优化
        javaProgrammers.stream().filter(e->{
            System.out.println("测试中间操作");
            return e.getAge() <20;
        }).forEach(System.out::println);
    }


    @Test
    public void testLimitAndSkip() {
        // 1.2    `limit(n)`–截断流，使其元素不超过给定数量  类似于mysql中的limit，但这里只有最大值
        //1.3   `skip(n)`–跳过元素，返回一个扔掉了前n个元素的流。若流中元素不足n个，则返回一个空流。与limit(n)互补

        //通过无限流创建一个无限流，如果不使用Limit方法进行限制阶段，无限流将会形成一个类似于死循环的操作
        //当使用skip（）将会跳过前5个数据从第6个参数返回
        Stream.iterate(0,t->t+1).limit(10).skip(5).forEach(System.out::println);  //seed是起始数值，limit代表循环前10次
    }
    @Test
    public void testDistinct() {
        //1.4   `distinct`–筛选，通过流所生成的元素的 hashCode() 和 equals() 去除重复元素

        //1.4.1   distinct对集合中的对象进行去重
       // javaProgrammers.stream().distinct().forEach(System.out::println);

        //1.4.2    distinct对集合中对象的某一属性进行去重

        javaProgrammers.stream().filter(distinctByKey(e->e.getAge())).distinct().forEach(System.out::println);

        //解析distinctByKey方法
        Person person = javaProgrammers.get(0);
        Predicate<Integer> tPredicate = distinctByKey( (Integer persons) -> person.getAge());

    }


    @Test
    public void testMapAndFlatMap(){
        /*2.1  `map(Function f)`–接受一个函数作为参数，将元素转换成其他形式或提取信息，该函数会被应用到每个元素上，并将其映射成一个新的元素。
         *
         * 这个类似于list集合中的add和addAll的区别，add添加一个集合是把集合当成一个元素，addAll添加一个集合是把集合中的每个元素当成一个元素
         *  map相当于add，flatMap相当于addAll
         */

        //map(Function f)--接受一个函数作为参数，将元素转换成其他形式或提取信息，该函数会被应用到每个元素上，并将其映射成一个新的元素。
        List<String> list = Arrays.asList("测试", "bb", "cc", "dd","dsf","dsf");
        list.stream().map(s -> s.toUpperCase()).forEach(System.out::println);

        //练习2.1.1   获取集合中年龄大于20的人
        javaProgrammers.stream().map(Person::getAge).filter(e->e>20).forEach(System.out::println);

        //练习2.1.2   将字符串中的多个字符构成的集合转换为对应的Stream的实例
        Stream<Stream<Character>> streamStream = list.stream().map(SteamApiTest::fromStringToStream);//有点麻烦
        streamStream.forEach(s->s.forEach(System.out::println));

        // 2.2  `flatMap(Function f)`–接受一个函数作为参数，将流中的每个值都转换成另一个流，然后把所有流连成一个流
        Stream<Character> characterStream = list.stream().flatMap(SteamApiTest::fromStringToStream);
        characterStream.forEach(System.out::println);

        javaProgrammers.stream().flatMap(e->Stream.of(e).filter(x->x.getAge()>20)).forEach(System.out::println);

    }

    @Test
    public void testStore(){
        //3.1   sorted()–自然排序，产生一个新流，其中按自然顺序排序  sorted（）参数可选，有两个重载一个有参Comparator，一个无参

        List<Integer> list = Arrays.asList(12, 43, 65, 3, 4, 0,01, -98);
        list.stream().sorted().forEach(System.out::println);


        //3.1.1    抛异常，原因：Person没有实现Comparable接口
        //javaProgrammers.stream().sorted().forEach(System.out::println);

        //3.2    sorted(Comparator com)–定制排序，产生一个新流，其中按比较器顺序排序，根据条件排序
        //多集合中对象根据年龄进行排序
        javaProgrammers.stream().sorted((e1,e2)->Integer.compare(e1.getAge(),e2.getAge())).forEach(System.out::println);

    }

    /**
     *测试Stream流的终止操作
     * 1. **匹配与查找**
     *     allMatch(Predicate p)：检查是否匹配所有元素
     *     anyMatch(Predicate p)：检查是否至少匹配一个元素
     *     noneMatch(Predicate p)：检查是否没有匹配的元素
     *     findFirst()：返回第一个元素
     *     findAny()：返回当前流中的任意元素
     *     count()：返回流中元素的总数
     *     max(Comparator c)：返回流中最大值
     *     min(Comparator c)：返回流中最小值
     *     forEach(Consumer c)：内部迭代
     * 2. **规约**
     *     reduce(T identity,BinaryOperator accumulator)：可以将流中元素反复结合起来，得到一个值。返回T
     *     reduce(BinaryOperator accumulator)：可以将流中元素反复结合起来，得到一个值。返回Optional
     * 3. **收集**
     *     collect(Collector c)：将流转换成其他形式。接受一个Collector接口的实现，用于给Stream中元素做汇总的方法
     */

    @Test
    public void testMatchAndFind() {
                /**
                * 1. 匹配与查找
                *     allMatch(Predicate p)：检查是否匹配所有元素
                *     anyMatch(Predicate p)：检查是否至少匹配一个元素
                *     noneMatch(Predicate p)：检查是否没有匹配的元素
                *     findFirst()：返回第一个元素
                *     findAny()：返回当前流中的任意元素
                *     count()：返回流中元素的总数
                *     max(Comparator c)：返回流中最大值
                *     min(Comparator c)：返回流中最小值
                *     forEach(Consumer c)：内部迭代
                **/

        //1.1   allMatch()相当于制定一个规则，将集合中的对象一一与规则进行对象，判断是否所有的集合对象都符合该规则, true/false
        boolean allMatch = javaProgrammers.stream().allMatch(e -> e.getAge()>18);
        System.out.println("测试Stream流的终止操作----allMatch()--->"+allMatch);

        //1.2   anyMatch(Predicate p)：检查是否至少匹配一个元素  类似于多选一即可
        boolean anyMatch = javaProgrammers.stream().anyMatch(e -> e.getSalary()>1000);
        System.out.println("检查是否至少匹配一个元素----anyMatch()--->"+anyMatch);

        //1.3   noneMatch(Predicate p)：检查是否没有匹配的元素 没有返回true，反之false
        boolean noneMatch = javaProgrammers.stream().noneMatch(e -> e.getSalary() > 10000);
        System.out.println("检查是否没有匹配的元素----noneMatch()--->"+noneMatch);

        //1.4     findFirst()：返回第一个元素
        Person person = javaProgrammers.stream().findFirst().get();
        System.out.println("返回第一个元素----findFirst()--->"+person.toString());

        for (int i = 0; i <1000 ; i++) {
            //1.5     findAny()：返回当前流中的任意元素
            Optional<Person> AnyPerson = javaProgrammers.stream().findAny();
            //System.out.println("返回当前流中的任意元素----findAny()--->"+AnyPerson.toString()+"--------->"+i);
        }

        //1.6   count()：返回流中元素的总数
        long count = javaProgrammers.stream().count();
        System.out.println("返回流中元素的总数----count()--->"+count);

        //1.7    max(Comparator c)：返回流中最大值   Optional<T> max(Comparator<? super T> comparator);
        Person maxPersonSalary = javaProgrammers.stream().max(Comparator.comparing(Person::getSalary)).get();
        System.out.println("返回流中最大值----max()--->"+maxPersonSalary);

        //1.8   min(Comparator c)：返回流中最小值
        Person minPersonSalary = javaProgrammers.stream().min(Comparator.comparing(Person::getSalary)).get();
        System.out.println("返回流中最小值----max()--->"+minPersonSalary);

    }

    @Test
    public void testStatute(){
       /*
        * 2. **规约**
        *     reduce(T identity,BinaryOperator accumulator)：可以将流中元素反复结合起来，得到一个值。返回T
        *     reduce(BinaryOperator accumulator)：可以将流中元素反复结合起来，得到一个值。返回Optional
        *
        *     reduce（）这个方法经常用到很方便，也更总要，我会再详细的分析总结，这里只做简单认识即可。
        */

        //2.1   reduce(T identity,BinaryOperator accumulator)：可以将流中元素反复结合起来，得到一个值。返回T
        //2.1.1 练习1：计算1-10的自然数的和
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer sum = list.stream().reduce(0, Integer::sum);
        System.out.println("计算1-10的自然数的和-------reduce(T identity,BinaryOperator accumulator)------>"+sum);

        //2.2   reduce(BinaryOperator accumulator)：可以将流中元素反复结合起来，得到一个值。返回Optional
        Integer integers = javaProgrammers.stream().map(Person::getSalary).reduce((e1, e2) -> e1 + e2).get();//lambda表达式
        Integer integer = javaProgrammers.stream().map(Person::getSalary).reduce(Integer::sum).get();//使用引用
        System.out.println("工资和-------reduce(BinaryOperator accumulator)使用引用------>"+integer);
        System.out.println("工资和-------reduce(BinaryOperator accumulator)lambda表达式------>"+integers);

    }

    @Test
    public void testSalary(){
       /*
        * 3. **收集**
        *     collect(Collector c)：将流转换成其他形式。接受一个Collector接口的实现，用于给Stream中元素做汇总的方法
        *       Collector接口中方法的实现决定了如何对流执行收集的操作（如收集到List、Set、Map)。
        *       Collector需要使用Collectors提供实例。另外， Collectors实用类提供了很多静态方法，可以方便地创建常见收集器实例，具体方法与实例如下：
                    ⚪toList：返回类型List< T>，作用是把流中元素收集到List
                    ⚪toSet：返回类型Set< T>，作用是把流中元素收集到Set
                    ⚪toCollection：返回类型Collection< T>，作用是把流中元素收集到创建的集合
        *
        */

        //3.1    collect(Collector c)：将流转换成其他形式。返回一个set
        System.out.println("将流转换成其他形式。返回一个set-------toSet()------>:");
        javaProgrammers.stream().filter(e -> e.getSalary() > 3000).collect(Collectors.toSet()).forEach(System.out::println);

        //3.2   collect(Collector c)：将流转换成其他形式。返回一个list
        System.out.println("将流转换成其他形式。返回一个list-------toList()------>:");
        javaProgrammers.stream().filter(e -> e.getSalary() > 3000).limit(2).collect(Collectors.toList()).forEach(System.out::println);

        //3.2   collect(Collector c)：将流转换成其他形式。返回一个map
        System.out.println("将流转换成其他形式。返回一个map-------toMap()------>:");
        javaProgrammers.stream().filter(e -> !e.getFirstName().equals("测试"))
                        // 注意：key不能重复  toMap()参数一：key   参数二：value   参数三：对key值进行去重，当有重复的key，map中保留第一条重复数据
                        .collect(Collectors.toMap(Person::getAge,person -> person,(key1, key2) -> key1))  //value 为对象 student -> student jdk1.8返回当前对象,也可以为对象的属性
                        .forEach((key, value) -> System.out.println("key--"+key+"   value--"+value.toString()));

    }




    //将字符串中的多个字符构成的集合转换为对应的Stream的实例
    public static Stream<Character> fromStringToStream(String str){
        ArrayList<Character> list = new ArrayList<>();

        for (Character c:str.toCharArray()){
            list.add(c);
        }
        return list.stream();
    }


    static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object,Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }


}
