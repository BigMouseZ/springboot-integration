package com.integration;

import com.integration.action.command.impl.TestEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootJavapatternApplicationTests {
@Autowired
	TestEntity testEntity ;
	@Test
	public void contextLoads() {
		TestEntity aa = new TestEntity();
		System.out.println(testEntity.getTest());
	}

}
