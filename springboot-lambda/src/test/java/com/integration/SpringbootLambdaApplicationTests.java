package com.integration;

import com.integration.basic.PoliceCarPojo;
import com.integration.basic.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootLambdaApplicationTests {

    @Test
    public void contextLoads() {

        List<PoliceCarPojo> policeCarList = new ArrayList<>();
        policeCarList.add(new PoliceCarPojo(1L, "qw", "ewe", "sdas"));
        policeCarList.add(new PoliceCarPojo(2L, "qw", "ewe", "sdas"));
        policeCarList.add(new PoliceCarPojo(4L, "qw", "ewe", "sdas"));
        policeCarList.add(new PoliceCarPojo(5L, "qw", "ewe", "sdas"));
        policeCarList.add(new PoliceCarPojo(6L, "qw", "ewe", "sdas"));
        List<PoliceCarPojo> redisPoliceCarList = new ArrayList<>();
        redisPoliceCarList.add(new PoliceCarPojo(1L, "qw", "ewe", "sdas"));
        redisPoliceCarList.add(new PoliceCarPojo(2L, "qw", "ewe", "sdas"));
        redisPoliceCarList.add(new PoliceCarPojo(3L, "qw", "ewe", "sdas"));
        redisPoliceCarList.add(new PoliceCarPojo(4L, "qw", "ewe", "sdas"));
        redisPoliceCarList.add(new PoliceCarPojo(5L, "qw", "ewe", "sdas"));
        //XLW是否有警车新增,根据警车id匹配 policeCarList-redisPoliceCarList 取差集。则为新增
        List<PoliceCarPojo> newAllPoliceCarList = policeCarList.stream()
                .filter(item -> !redisPoliceCarList.stream()
                        .map(PoliceCarPojo::getId)
                        .collect(Collectors.toList())
                        .contains(item.getId()))
                .collect(Collectors.toList());
        /* policeCarList 与redisPoliceCarList 取交集。则为新增*/
        List<PoliceCarPojo> combineList = policeCarList.stream()
                .filter(item -> redisPoliceCarList.stream()
                        .map(PoliceCarPojo::getId)
                        .collect(Collectors.toList())
                        .contains(item.getId()))
                .collect(Collectors.toList());
        /* policeCarList 与redisPoliceCarList 取并集。则为新增*/
        policeCarList.addAll(redisPoliceCarList);
        /* policeCarList 与redisPoliceCarList 取并集。g根据id去重*/
        List<PoliceCarPojo> distinctElements = policeCarList.stream()
                .filter(distinctByKey(PoliceCarPojo::getId))
                .collect(Collectors.toList());
        System.out.println("测试");
        //newAllPoliceCarList.forEach(one-> System.out.println(one.toString()));
        //combineList.forEach(one-> System.out.println(one.toString()));
        distinctElements.forEach(one -> System.out.println(one.toString()));
    }

    //Utility function
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    @Test
    public void test1() {


        String carno = "闽DM990W";
        System.out.println(carno.length());
    }

    @Test
    public void group() {
        List<User> userList = new ArrayList<>();
        //分组
        Map<String, List<User>> groupBySex = userList.stream().collect(Collectors.groupingBy(User::getName));
        //遍历分组
        for (Map.Entry<String, List<User>> entryUser : groupBySex.entrySet()) {
            String key = entryUser.getKey();
            List<User> entryUserList = entryUser.getValue();
        }
    }

    /**
     * lambda 排序
     */
    @Test
    public void sort() {


        List<User> list = initList();


        // jdk8之前的排序

        Collections.sort(list, new Comparator<User>() {

            @Override

            public int compare(User o1, User o2) {

                return o1.getAge().compareTo(o2.getAge());

            }

        });


        // jdk8 lambda排序，带参数类型

        list = initList();

        list.sort(Comparator.comparing(User::getAge));

        list.forEach(System.out::println);


        System.out.println();


        // jdk8 lambda排序，不带参数类型

        list = initList();

        list.sort((u1, u2) -> u1.getAge().compareTo(u2.getAge()));

        list.forEach(System.out::println);


        System.out.println();


        // jdk8 排序，User类静态方法引用

	/*	list = initList();

		list.sort(User::compareAge);

		list.forEach(System.out::println);



		System.out.println();*/


        // jdk8 升序排序，Comparator提供的静态方法

        list = initList();

        Collections.sort(list, Comparator.comparing(User::getAge));

        list.forEach(System.out::println);


        System.out.println();


        // jdk8 降序排序，Comparator提供的静态方法

        list = initList();

        Collections.sort(list, Comparator.comparing(User::getAge).reversed());

        list.forEach(System.out::println);


        System.out.println();


        // jdk8 组合排序，Comparator提供的静态方法，先按年纪排序，年纪相同的按名称排序

        list = initList();

        Collections.sort(list, Comparator.comparing(User::getAge).thenComparing(User::getName));

        list.forEach(System.out::println);

    }


    private static List<User> initList() {

        List<User> list = new ArrayList<>();

        list.add(new User("lisa", 23));

        list.add(new User("tom", 11));

        list.add(new User("john", 16));

        list.add(new User("jennis", 26));

        list.add(new User("tin", 26));

        list.add(new User("army", 26));

        list.add(new User("mack", 19));

        list.add(new User("jobs", 65));

        return list;
    }

    @Test
    public void TestSum(){

        List<User> list = initList();
        System.out.println(list.stream().mapToLong(User::getAge).sum());

    }
}
