package com.integration;

import com.integration.basic.PoliceCarPojo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
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
		policeCarList.add( new PoliceCarPojo(1L,"qw","ewe","sdas"));
		policeCarList.add(new PoliceCarPojo(2L,"qw","ewe","sdas"));
		policeCarList.add(new PoliceCarPojo(4L,"qw","ewe","sdas"));
		policeCarList.add(new PoliceCarPojo(5L,"qw","ewe","sdas"));
		policeCarList.add(new PoliceCarPojo(6L,"qw","ewe","sdas"));
		List<PoliceCarPojo> redisPoliceCarList = new ArrayList<>();
		redisPoliceCarList.add( new PoliceCarPojo(1L,"qw","ewe","sdas"));
		redisPoliceCarList.add(new PoliceCarPojo(2L,"qw","ewe","sdas"));
		redisPoliceCarList.add( new PoliceCarPojo(3L,"qw","ewe","sdas"));
		redisPoliceCarList.add( new PoliceCarPojo(4L,"qw","ewe","sdas"));
		redisPoliceCarList.add( new PoliceCarPojo(5L,"qw","ewe","sdas"));
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
				.filter( distinctByKey(PoliceCarPojo::getId) )
				.collect( Collectors.toList() );
		System.out.println("测试");
		//newAllPoliceCarList.forEach(one-> System.out.println(one.toString()));
		//combineList.forEach(one-> System.out.println(one.toString()));
		distinctElements.forEach(one-> System.out.println(one.toString()));
	}
	//Utility function
	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
	{
		Map<Object, Boolean> map = new ConcurrentHashMap<>();
		return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}
	@Test
	public void  test1(){


		String carno = "闽DM990W";
		System.out.println(carno.length());
	}

}
