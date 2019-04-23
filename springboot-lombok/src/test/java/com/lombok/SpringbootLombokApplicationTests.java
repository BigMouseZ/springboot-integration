package com.lombok;

import com.lombok.at_Getter_Setter.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootLombokApplicationTests {

	@Test
	public void contextLoads() {
		User  aa = new User();
	}

}
