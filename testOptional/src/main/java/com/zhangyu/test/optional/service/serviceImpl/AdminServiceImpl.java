package com.zhangyu.test.optional.service.serviceImpl;

import com.zhangyu.test.optional.model.Admin;
import com.zhangyu.test.optional.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Supplier;

/**
 * @author Administrator
 * @date 2020/11/9 10:43
 **/
@Service
@Slf4j
public class AdminServiceImpl implements AdminService {


    /**
     * JAVA8       Optional
     *
     * @return
     */
    @Override
    public HashMap<String, Object> optionalMethod() {

        //返回map
        HashMap<String, Object> retMap = new HashMap<>();

        HashMap<String, Object> ifNullMap = new HashMap<>();

        //一、Optional判断对象是否为空
        Admin admin = new Admin();
        Optional<Admin> admin1 = Optional.ofNullable(admin);
        ifNullMap.put("判断实体类model对象是否为空：admin1=", admin1);
        log.info(new Date().getTime() + "-----判断实体类model对象是否为空：admin1=" + admin1);

        //判断list对象是否为空ofNullable（）方法， 为空则返回:
        List<Object> list = new ArrayList<>();
        list.add("新增元素");
        list.add("修改元素");
        Optional<List<Object>> list1 = Optional.ofNullable(list);
        ifNullMap.put("判断list集合是否为空：list=[]", list1);
        log.info(new Date().getTime() + "-----判断list集合是否为空：list=[]:" + list1);

        //判断map是否为空     为空返回:
        HashMap<String, Object> map = new HashMap<>();
        Optional<HashMap<String, Object>> map1 = Optional.ofNullable(map);
        ifNullMap.put("判断Map集合是否为空：map1={}", map1);
        log.info(new Date().getTime() + "-----判断Map集合是否为空：map1={}" + map1);

        retMap.put("Optional判断对象是否为空", ifNullMap);

        log.info("*********************************分割线*******************************************************");


        HashMap<String, Object> getMethodMap = new HashMap<>();

        //二、Optional.get() 方法 (返回对象的值)        如果 value 不为空则做返回，如果为空则抛出异常 "No value present" 简单实例展示

        Admin newAdmin = new Admin();
        newAdmin.setName("get方法获取对象值");
        Admin Nadmin = Optional.ofNullable(newAdmin).get();
        getMethodMap.put("optionald的get方法获得对象的值：Nadmin=", Nadmin);
        log.info(new Date().getTime() + "-----optionald的get方法获得对象的值：Nadmin=" + Nadmin);

        List<Object> listValue = Optional.ofNullable(list).get();
        getMethodMap.put("optionald的get方法获得list集合对象的值：listValue=[]", listValue);
        log.info(new Date().getTime() + "-----optionald的get方法获得list集合对象的值：listValue=[]" + listValue);//[新增元素, 修改元素]

        map.put("get方法", "get方法Value值");
        HashMap<String, Object> map2 = Optional.ofNullable(map).get();
        getMethodMap.put("optionald的get方法获得map集合对象的值：map2：{}", map2);
        log.info(new Date().getTime() + "-----optionald的get方法获得map集合对象的值：map2:{}" + map2);//{get方法=get方法Value值}

        retMap.put("Optional.get() 方法 :", getMethodMap);

        log.info("*********************************分割线*******************************************************");

        HashMap<String, Object>  isPresentMap= new HashMap<>();
        //三、Optional.isPresent() 方法 (判读是否为空)  isPresent() 方法就是会返回一个 boolean 类型值，如果对象不为空则为true，如果为空则 false
        Admin admin3 = new Admin();
        admin3.setName("isPresent方法判断是否为空");
        boolean present1 = Optional.ofNullable(admin3).isPresent();
        log.info(new Date().getTime() + "-----optionald的ofNullable判断对象是否为空，present1="+present1 );



        log.info("*********************************分割线*******************************************************");

        HashMap<String, Object>  filterMap= new HashMap<>();
        // Optional.filter() 方法 (过滤对象)  接受一个对象，然后对他进行条件过滤，如果条件符合则返回 Optional 对象本身，如果不符合则返回空 Optional
        Admin admin4 = new Admin();
        admin4.setName("filter方法，根据条件过滤对象");
        Optional<Admin> adminfilter = Optional.ofNullable(admin4).filter(p -> p.getName().equals("filter方法，根据条件过滤对象"));
        filterMap.put("optionald的filter方法根据条件过滤对象",adminfilter);
        log.info(new Date().getTime() + "-----optionald的filter方法根据条件过滤对象，adminfilter:"+ adminfilter);

        List<Object> list2 = new ArrayList<>();
        list2.add("filter方法过滤list集合");
        Optional<List<Object>> filterlist = Optional.ofNullable(list2).filter(p -> p.size() >= 1);
        filterMap.put("optionald的filter方法根据条件过滤List集合对象的值：filterlist=[]", filterlist);
        log.info(new Date().getTime() + "-----optionald的filter方法根据条件过滤List集合对象的值：filterlist=[]" + filterlist);

        retMap.put("Optional.filter() 方法",filterMap);


        log.info("*********************************分割线*******************************************************");

        //Optional.flatMap() 方法 (Optional 对象进行二次包装)
        HashMap<String, Object>  flatMap= new HashMap<>();
        Admin admin5 = new Admin();
        Optional<String>  adminFlatMap= Optional.ofNullable(admin5).map(m -> Optional.ofNullable(m.getName()).orElse("name为空"));
        flatMap.put("flatMap对对象进行二次包装",adminFlatMap);
        log.info(new Date().getTime() + "-----optionald的flatMap对对象进行二次包装,通过map进行封装：adminFlatMap=" + adminFlatMap);

        retMap.put("Optional.flatMap() 方法",flatMap);

        //Optional.orElse() 方法 (为空返回对象)如果包装对象为空的话，就执行 orElse 方法里的 value，如果非空，则返回写入对象


        log.info("*********************************分割线*******************************************************");

        HashMap<String, Object>  supMap= new HashMap<>();
        //Optional.orElseGet() 方法 (为空返回 Supplier 对象)    这个与 orElse 很相似，入参不一样，入参为 Supplier 对象，为空返回传入对象的. get() 方法，如果非空则返回当前对象
        /**
         * Supplier 也是创建对象的一种方式, 简单来说，Suppiler 是一个接口，是类似 Spring 的懒加载，
         * 声明之后并不会占用内存，只有执行了 get() 方法之后，才会调用构造方法创建出对象创建对象的语法的话就是：
         *          Supplier<Person> supPerson= Person::new;
         * 需要使用时supPerson.get()即可
         */
        Admin admin6 = new Admin();
        Optional<Supplier<Admin>> sup=Optional.ofNullable(Admin::new);
        Admin admin2 = Optional.ofNullable(admin6).orElseGet(sup.get());//调用get()方法，此时才会调用对象的构造方法，即获得到真正对象
        //admin2.setName("这是Supplier对象");
        supMap.put(" Supplier 对象",admin2);
        log.info(new Date().getTime() + "-----Optional.orElseGet() 方法,获得Supplier对象：supMap=" + supMap);
        retMap.put("Optional.orElseGet() 方法,获得Supplier对象",supMap);


        log.info("*********************************分割线*******************************************************");

        //Optional.orElseThrow() 方法 (为空返回异常)如果为空，就抛出你定义的异常，如果不为空返回当前对象
        ArrayList<String> list3 = new ArrayList<>();
        ArrayList<String> list4 = Optional.ofNullable(list3).orElseThrow(() -> new NullPointerException());
        log.info(new Date().getTime() + "-----Optional.orElseThrow()如果为空，就抛出你定义的异常，如果不为空返回当前对象：list4=" + list4);


        return retMap;
    }
}
