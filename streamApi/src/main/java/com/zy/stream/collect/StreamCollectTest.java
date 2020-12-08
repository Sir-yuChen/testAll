package com.zy.stream.collect;

import com.zy.stream.model.Apple;
import com.zy.stream.model.Person;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream流中关于Collectors集合类中的相关方法，详解
 **/

@Controller
@RequestMapping("/")
@Slf4j
public class StreamCollectTest {


    @Resource
    Configuration configuration;

    @RequestMapping("index")
    public String main(Model model){
        String w="Welcome FreeMarker!";
        Map root = new HashMap();
        root.put("w",w);
        freeMarkerContent(root);
        model.addAttribute("w","Welcome FreeMarker!");
        return "test";
    }

    private void freeMarkerContent(Map<String,Object> root){
        try {
            //使用哪一个模板生成页面    这里使用的是test.ftl模板
            Template temp = configuration.getTemplate("test.ftl");
            //以classpath下面的static目录作为静态页面的存储目录，同时命名生成的静态html文件名称
            String path=this.getClass().getResource("/").toURI().getPath()+"test1.html";

            //*************路径：/D:/JetBrains/AllProjects/springboot-freemark/target/classes/test1.html
            log.info("*************路径："+path.toString());
            //文件写入流
            Writer file = new FileWriter(new File(path.substring(path.indexOf("/"))));
            temp.process(root, file);
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }




    List<Person> listPerson1= new ArrayList<Person>() {
        {
            add(new Person("Elsdon", "Jaycob", "Java programmer", "male", 2000, 18));
            add(new Person("Tamsen", "Brittany", "Java programmer", "female", 2371, 55));
            add(new Person("Floyd", "Donny", "Java programmer", "male", 3322, 25));
            add(new Person("Sindy", "Jonie", "Java programmer", "female", 35020, 15));
            add(new Person("Vere", "Hervey", "Java programmer", "male", 2272, 25));
            add(new Person("Maude", "Jaimie", "Java programmer", "female", 2057, 870));
            add(new Person("testMaude", null, "Java programmer", "female", 2057, 870));
            add(new Person("Maude", "Jaimie", "Java programmer", "female", 2057, 87));
            add(new Person("Shawn", "Randall", "Java programmer", "male", 3120, 99));
            add(new Person("Jayden", "Corrina", "Java programmer", "female", 345, 25));
            add(new Person("Palmer", "Dene", "Java programmer", "male", 3375, 14));
            add(new Person("Addison", "Pam", "Java programmer", "female", 3426, 20));
        }
    };
    List<Person> listPerson2= new ArrayList<Person>() {
        {
            add(new Person("Elsdon", "Jaycob", "Java programmer", "male", 2000, 18));
            add(new Person("Tamsen", "Brittany", "Java programmer", "female", 2371, 55));
            add(new Person("testFloyd", "Donny", "Java programmer", "male", 3322, 25));
            add(new Person("Sindy", "Jonie", "Java programmer", "female", 35020, 15));
            add(new Person("testVere", "Hervey", "Java programmer", "male", 2272, 25));
            add(new Person("Maude", "Jaimie", "Java programmer", "female", 2057, 87));
            add(new Person("testShawn", "Randall", "Java programmer", "male", 3120, 99));
            add(new Person("Jayden", "Corrina", "Java programmer", "female", 345, 25));
            add(new Person("testPalmer", "Dene", "Java programmer", "male", 3375, 14));
            add(new Person("Addison", "Pam", "Java programmer", "female", 3426, 20));
        }
    };


    @Test
    public void testAll(){

        List<Integer> collect = listPerson1.stream().map(Person::getSalary).collect(Collectors.toList());


        //交集 (list1 + list2)
        System.out.println("test=======交集[firstName中不含有test] ========>：");
        listPerson1.stream()
                    .filter(listPerson2::contains)
                    .collect(Collectors.toList())
                    .forEach(System.out::println);

        //差集 取差集是注意以那个集合为主
        System.out.println("test=======listPerson2差集[firstName中含有test] ========>：");
        listPerson2.stream()
                    .filter(e-> !listPerson1.contains(e))
                    .collect(Collectors.toList())
                    .forEach(System.out::println);

        //并集
        System.out.println("test=======并集[两个集合的所有元素] ========>：");
        List<Person> collect1 = listPerson1.parallelStream().collect(Collectors.toList());
        collect1.addAll(listPerson2.parallelStream().collect(Collectors.toList()));
        collect1.stream().forEach(System.out::println);
        System.out.println("test=======并集[两个集合的所有元素] 去重========>：");
        collect1.stream().distinct().forEach(System.out::println);


        HashMap<String, Integer> map = new HashMap<>();
        map.put("Apple1",30);
        map.put("Apple2",20);
        map.put("Apple3",10);
        map.put("Apple6",51);
        map.put("Apple4",99);

        System.out.println("test=======Map集合转 List[注意数据类型]按照key值排序========>：");
        //Map集合转 List
        map.entrySet().stream().sorted(Comparator.comparing(e -> e.getKey()))
                .map(e -> new Apple(e.getKey(), e.getValue())).collect(Collectors.toList())
                .forEach(System.out::println);

        System.out.println("test=======Map集合转 List[注意数据类型]按照value值排序========>：");
        map.entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry::getValue)).map(e -> new Apple(e.getKey(), e.getValue()))
                .collect(Collectors.toList())
                .forEach(System.out::println);

        System.out.println("test=======Map集合转 List[注意数据类型]按照key值排序Entry.comparingByKey()========>：");
        map.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByKey())
            .map(e -> new Apple(e.getKey(), e.getValue()))
            .collect(Collectors.toList())
            .forEach(System.out::println);


        //toList（）方法
        List<Integer> collectList = Stream.of(1, 2, 3, 4)
                .collect(Collectors.toList());
        System.out.println("test=======Collectors.toList()========》: " + collectList);



        HashMap<String, Object> objMap = new HashMap<>();
        //List转map集合    Collectors.toMap方法的第三个参数为键值重复处理策略，如果不传入第三个参数，当有相同的键时，会抛出一个IlleageStateException。
        System.out.println("test=======List元素对象转map集合[方式一]========>：");
        /*  toMap()参数一：key值，参数二：value值 参数三：当两个key值相同时，决定保留前一个value值还是后一个value值
             key为null    可以为null
            字典查询和数据转换 toMap时，如果value为null,会报空指针异常,需要校验
         */
        listPerson1.stream()
                .collect(Collectors.toMap(p -> p.getFirstName(), p -> Optional.ofNullable(p.getLastName()).orElse("value为null加非空检验"), (k1, k2) -> k1))
                .forEach(
                        (key, value) -> {
                        //map集合forEach（）循环时传两个参数，key，value值，
                        System.out.println("key: " + key + "    value: " + value);
                }
        );

        /*下面这种形式，通过方法的引用也可以去取重复的key，保证不抛出异常，但是只能保证出现的第一个key的数据（Map::putAll）
            key为null    可以为null
            value为null，不会报空指针异常
         */
        System.out.println("test=======List元素对象转map集合[方式二]========>：");
        listPerson1.stream()
                    .collect(HashMap::new,(maps,p)->maps.put(p.getFirstName(),p.getLastName()),Map::putAll)
                    .forEach(
                        (key, value) -> {
                            //map集合forEach（）循环时传两个参数，key，value值，
                            //如果value为null 打印：key: testMaude    value: null
                            System.out.println("++key: " + key + "    ++value: " + value);
                    }
        );
        System.out.println("test=======List元素对象转map集合[方式三]========>：");
        Stream<Apple> appleStream = Stream.of(new Apple("白色", 18),
                                            new Apple("红色", 180),
                                            new Apple("绿色", 187),
                                            new Apple("银色", 19));

        Map<String , Apple> appleMap = appleStream.collect(Collectors.toMap(Apple::getColor, i -> i));
        appleMap.forEach(
                (key, value) -> {
                    System.out.println("appleKey: " + key + "    appleValue: " + value);
                }
        );

        System.out.println("test========假设要得到按年龄分组的Map<Integer,List>：=======>：");
        Map<Integer, List<Person>> ageMap = listPerson1.stream().collect(Collectors.toMap(Person::getAge, Collections::singletonList, (a, b) -> {
            List<Person> resultList = new ArrayList<>(a);
            resultList.addAll(b);
            return resultList;
        }));
        ageMap.forEach(
                (key, value) -> {
                    System.out.println("ageKey: " + key + "   personValue: " + value);
                }
        );

        //List<String> 转String
        System.out.println("test========List<String> 转String使用Collectors.joining（）：=======>：");
        String str = Arrays.asList("voidcc.com", "voidmvn.com", "voidtool.com").stream().collect(Collectors.joining(","));
        System.out.println(str);

        //排序
        System.out.println("test=======排序sorted()========>：");
        //按照自然顺序进行排序 如果要自定义排序sorted 传入自定义的 Comparator
        listPerson1.stream().map(Person::getAge).sorted((x,y)->x.compareTo(y)).forEach(System.out::println);


        //比较
        System.out.println("test=======比较sorted()========>：");
        Comparator<Person> comparator = (p1, p2) -> p1.getFirstName().compareTo(p2.getFirstName());

        Person p1 = new Person("Addison", "Pam", "Java programmer", "female", 3426, 20);
        Person p2 = new Person("Addison", "Pam", "Java programmer", "female", 3426, 20);

        comparator.compare(p1, p2);             // > 0
        comparator.reversed().compare(p1, p2);  // < 0


        //分组
        System.out.println("test=======按照薪资分组Collectors.groupingBy（）=====返回map===>：");
        listPerson1.stream().collect(Collectors.groupingBy(Person::getSalary)).forEach(
                (key, value) -> {System.out.println("key: " + key + "    value: " + value);}
        );
        /*
         * groupingBy 分组后操作
                //Collectors中还提供了一些对分组后的元素进行downStream处理的方法：
                //counting方法返回所收集元素的总数；
                //summing方法会对元素求和；
                //maxBy和minBy会接受一个比较器，求最大值，最小值；
                //mapping函数会应用到downstream结果上，并需要和其他函数配合使用；
         */

        Map<Integer, Long> count = listPerson1.stream().collect(Collectors.groupingBy(Person::getSalary,Collectors.counting()));
        count.forEach((key, value) -> {System.out.println("counting方法返回所收集元素的总数:--->key: " + key + "    value: " + value);});

        Map<Integer, Integer> ageCount = listPerson1.stream().collect(Collectors.groupingBy(Person::getSalary,Collectors.summingInt(Person::getAge)));
        ageCount.forEach((key, value) -> {System.out.println("summing方法会对元素求和:--->key: " + key + "    value: " + value);});

        Map<Integer, Optional<Person>> ageMax =  listPerson1.stream().collect(Collectors.groupingBy(Person::getSalary,Collectors.maxBy(Comparator.comparing(Person::getAge))));
        ageMax.forEach((key, value) -> {System.out.println("maxBy和minBy会接受一个比较器，求最大值，最小值:--->key: " + key + "    value: " + value);});

        Map<Integer, List<String>> nameMap =  listPerson1.stream().collect(Collectors.groupingBy(Person::getSalary,Collectors.mapping(Person::getFirstName,Collectors.toList())));
        nameMap.forEach((key, value) -> {System.out.println("mapping函数会应用到downstream结果上，并需要和其他函数配合使用: " + key + "    value: " + value);});

        /*
         *1.    Collectors partitioningBy
         *      Collectors中还提供了partitioningBy方法，接受一个Predicate函数，该函数返回boolean值，用于将内容分为两组。
         *
         *2.    Collectors joining
         *      Collectors.joining 收集Stream中的值，该方法可以方便地将Stream得到一个字符串。joining函数接受三个参数，分别表示允（用以分隔元素）、前缀和后缀
         *
         *  Collectors分别提供了求平均值averaging、总数couting、最小值minBy、最大值maxBy、求和suming等操作。
         *  但是假如你希望将流中结果聚合为一个总和、平均值、最大值、最小值，
         *  那么Collectors.summarizing(Int/Long/Double)就是为你准备的，
         *  它可以一次行获取前面的所有结果，其返回值为(Int/Long/Double)SummaryStatistics。
         *
         *
         * 使用collect可以将Stream转换成值。maxBy和minBy允许用户按照某个特定的顺序生成一个值。
                averagingDouble:求平均值，Stream的元素类型为double
                averagingInt:求平均值，Stream的元素类型为int
                averagingLong:求平均值，Stream的元素类型为long
                counting:Stream的元素个数
                maxBy:在指定条件下的，Stream的最大元素
                minBy:在指定条件下的，Stream的最小元素
                reducing: reduce操作
                summarizingDouble:统计Stream的数据(double)状态，其中包括count，min，max，sum和平均。
                summarizingInt:统计Stream的数据(int)状态，其中包括count，min，max，sum和平均。
                summarizingLong:统计Stream的数据(long)状态，其中包括count，min，max，sum和平均。
                summingDouble:求和，Stream的元素类型为double
                summingInt:求和，Stream的元素类型为int
                summingLong:求和，Stream的元素类型为long
         *
         */


    }



}
