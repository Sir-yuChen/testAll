package com.zy.pagehelper.lambda;

import com.zy.pagehelper.Interface.PersonCreatorBlankConstruct;
import com.zy.pagehelper.Interface.PersonCreatorParamContruct;
import com.zy.pagehelper.Interface.ReturnOneParam;
import com.zy.pagehelper.model.Person;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
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



    /**
     * 在本节中,我们将看到lambda表达式如何影响我们编码的方式。
     * 假设有一个玩家List ,程序员可以使用 for 语句 ("for 循环")来遍历,在Java SE 8中可以转换为另一种形式:
     */
    @Test
    public void test5(){

        String[] atp = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka",
                "David Ferrer","Roger Federer",
                "Andy Murray","Tomas Berdych",
                "Juan Martin Del Potro"};
        List<String> players =  Arrays.asList(atp);

        // 以前的循环方式
        for (String player : players) {
            System.out.print(player + "; ");
        }

        // 使用 lambda 表达式以及函数操作(functional operation)
        players.forEach((player) -> System.out.print(player + "; "));

        // 在 Java 8 中使用双冒号操作符(double colon operator)
        players.forEach(System.out::println);

    }

    /**
     * Lambda实现Runnable接口
     * Runnable 的 lambda表达式,使用块格式,将五行代码转换成单行语句
     */
    @Test
    public  void test6() {
        // 1.1使用匿名内部类
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello world !使用匿名内部类，开线程");
            }
        }).start();

        // 1.2使用 lambda expression
        new Thread(() -> System.out.println("Hello world !使用 lambda expression，开线程")).start();

        // 2.1使用匿名内部类
        Runnable race1 = new Runnable() {
            @Override

            public void run() {
                System.out.println("Hello world !使用匿名内部类，不开线程");
            }
        };

        // 2.2使用 lambda expression
        Runnable race2 = () -> System.out.println("Hello world !使用 lambda expression,不开线程");

        // 直接调用 run 方法(没开新线程哦!)
        race1.run();
        race2.run();
    }

    /**
     *Lambdas排序集合
     * 在Java中,Comparator 类被用来排序集合。
     * 在下面的例子中,我们将根据球员的 name, surname, name 长度 以及最后一个字母。
     * 和前面的示例一样,先使用匿名内部类来排序,然后再使用lambda表达式精简我们的代码。
     * 在第一个例子中,我们将根据name来排序list。
     */
    @Test
    public void comparatorTest(){
        String[] players = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka", "David Ferrer",
                "Roger Federer", "Andy Murray",
                "Tomas Berdych", "Juan Martin Del Potro",
                "Richard Gasquet", "John Isner"};

        // 1.1 使用匿名内部类根据 name 排序 players
        Arrays.sort(players, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return (s1.compareTo(s2));
            }
        });
        System.out.println("使用静态内部类排序结果:"+Arrays.toString(players));
        System.out.println("-----------------------分割线-------------------------");

        String[] players2 = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka", "David Ferrer",
                "Roger Federer", "Andy Murray",
                "Tomas Berdych", "Juan Martin Del Potro",
                "Richard Gasquet", "John Isner"};

        // 1.2 使用 lambda expression 排序 players
        Comparator<String> sortByName = (String s1, String s2) -> (s1.compareTo(s2));
        Arrays.sort(players2, sortByName);
        System.out.println("使用lambda排序结果（方式一）:"+Arrays.toString(players2));
        // 1.3 也可以采用如下形式:
        Arrays.sort(players2, (String s1, String s2) -> (s1.compareTo(s2)));
        System.out.println("使用lambda排序结果（方式二）:"+Arrays.toString(players2));


        System.out.println("-----------------------分割线-------------------------");

        String[] players3 = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka", "David Ferrer",
                "Roger Federer", "Andy Murray",
                "Tomas Berdych", "Juan Martin Del Potro",
                "Richard Gasquet", "John Isner"};

        // 1.1 使用匿名内部类根据 surname 排序 players
        Arrays.sort(players3, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return (s1.substring(s1.indexOf(" ")).compareTo(s2.substring(s2.indexOf(" "))));
            }
        });
        System.out.println("匿名内部类根据 surname 排序:"+Arrays.toString(players3));

        // 1.2 使用 lambda expression 排序,根据 surname
        Comparator<String> sortBySurname = (String s1, String s2) ->
                ( s1.substring(s1.indexOf(" ")).compareTo( s2.substring(s2.indexOf(" ")) ) );
        Arrays.sort(players3, sortBySurname);
        System.out.println("根据 surname使用lambda排序结果（方式一）:"+Arrays.toString(players3));
        // 1.3 或者这样,怀疑原作者是不是想错了,括号好多...
        Arrays.sort(players3, (String s1, String s2) ->
                ( s1.substring(s1.indexOf(" ")).compareTo( s2.substring(s2.indexOf(" ")) ) )
        );

        System.out.println("根据 surname使用lambda排序结果方式二）:"+Arrays.toString(players3));

        // 2.1 使用匿名内部类根据 name lenght 排序 players
        Arrays.sort(players, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return (s1.length() - s2.length());
            }
        });

        // 2.2 使用 lambda expression 排序,根据 name lenght
        Comparator<String> sortByNameLenght = (String s1, String s2) -> (s1.length() - s2.length());
        Arrays.sort(players, sortByNameLenght);

        // 2.3 or this
        Arrays.sort(players, (String s1, String s2) -> (s1.length() - s2.length()));

        // 3.1 使用匿名内部类排序 players, 根据最后一个字母
        Arrays.sort(players, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return (s1.charAt(s1.length() - 1) - s2.charAt(s2.length() - 1));
            }
        });

        // 3.2 使用 lambda expression 排序,根据最后一个字母
        Comparator<String> sortByLastLetter =
                (String s1, String s2) ->
                        (s1.charAt(s1.length() - 1) - s2.charAt(s2.length() - 1));
        Arrays.sort(players, sortByLastLetter);

        // 3.3 or this
        Arrays.sort(players, (String s1, String s2) -> (s1.charAt(s1.length() - 1) - s2.charAt(s2.length() - 1)));

    }
    /**
      1. lambda 表达式创建线程
     我们以往都是通过创建 Thread 对象，然后通过匿名内部类重写 run() 方法，一提到匿名内部类我们就应该想到可以使用 lambda 表达式来简化线程的创建过程。

     */
    @Test
    public void test7(){
        Thread t = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(2 + ":" + i);
            }
        });
        t.start();
    }


    /**
     *lambda 表达式引用方法
     * 有时候我们不是必须要自己重写某个匿名内部类的方法，我们可以可以利用 lambda表达式的接口快速指向一个已经被实现的方法。
     *
     * 语法
     *
     * ​ 方法归属者::方法名 静态方法的归属者为类名，普通方法归属者为对象
     */
    public static void main(String[] args) {
        ReturnOneParam lambda1 = a -> doubleNum(a);
        System.out.println(lambda1.method(3));

        //lambda2 引用了已经实现的 doubleNum 方法
        ReturnOneParam lambda2 = LambdaTest::doubleNum;
        System.out.println(lambda2.method(3));

        LambdaTest exe = new LambdaTest();

        //lambda4 引用了已经实现的 addTwo 方法
        ReturnOneParam lambda4 = exe::addTwo;
        System.out.println(lambda4.method(2));

    }

    /**
     * 要求
     * 1.参数数量和类型要与接口中定义的一致
     * 2.返回值类型要与接口中定义的一致
     */
    public static int doubleNum(int a) {
        return a * 2;
    }

    public int addTwo(int a) {
        return a + 2;
    }


    /**
     *构造方法的引用
     * 一般我们需要声明接口，该接口作为对象的生成器，通过 类名::new 的方式来实例化对象，然后调用方法返回对象。
     * 该接口作为对象的生成器---->创建一个无参构造器，
     * 该接口作为对象的生成器---->创建一个有参构造器，
     */
    @Test
    public  void test9() {
        /**
         *1.lambda表达式创建对象，返回无参函数接口，生成无参对象
         */
        PersonCreatorBlankConstruct creator = () -> new Person();
        Person person = creator.getPerson();

        PersonCreatorBlankConstruct creator2 = Person::new;
        Person person1 = creator2.getPerson();

        PersonCreatorParamContruct creator3 = Person::new;
        Person person2 = creator3.getPerson("名称", "修改名称","职位","男",23,2000);

        String str = "_ga=GA1.3.626751450.1603845434; d=oa; u=NwAwAGQANgA4ADkAOABiAC0AMgA4ADMANAAtADQAMgBkADIALQA5ADkANQBlAC0AYgBkADAAMABjAGIANwAwAGMANQA4AGQAfAAyADAAMgAwADEAMAAyADkAMAA4ADMAOAAxADkAfABiADcAOQA0ADYAYwA3ADgALQA0AGMAOAA1AC0ANAA4AGYAOAAtADgAYQBmAGUALQAyADUANABmAGEAZgAyAGYAYQA2ADAAYwB8ADgAMABjAGMAOQBiAGEAZgAzAGEANwA3ADIANAAzAGIANwAwADQAYgBlAGYANwA5ADQAMwA3ADgAMgBhAGQAOAA=; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22175bfa4cdcf7bf-060299c2607ef2-5437971-250125-175bfa4cdd0700%22%2C%22first_id%22%3A%22%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22%24device_id%22%3A%22175bfa4cdcf7bf-060299c2607ef2-5437971-250125-175bfa4cdd0700%22%7D; ag_fid=IjkTBJxaaRuauZFF; sessionId=61829dbcde8b4b1ba2f2862c4de0e911v9zdQY; _gid=GA1.3.668217704.1605486104; _gat=1; JSESSIONID=50b1d136-9442-4a64-8053-686f6a032732";

        List<String> strings = Arrays.asList(str.split(";"));
      //  strings.forEach(System.out::println);

        Consumer<String> con = x -> Arrays.asList(x.split(";")).forEach(System.out::println);
        con.accept(str);


    }



}
