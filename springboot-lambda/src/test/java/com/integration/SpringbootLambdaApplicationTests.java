package com.integration;

import com.integration.basic.PoliceCarPojo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
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
						.map(e -> e.getId())
						.collect(Collectors.toList())
						.contains(item.getId()))
				.collect(Collectors.toList());
		System.out.println("测试");
		newAllPoliceCarList.forEach(one-> System.out.println(one.toString()));
	}

	@Test
	public void  test1(){


		String carno = "闽DM990W";
		System.out.println(carno.length());
	}

}
