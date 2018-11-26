package com.utils;

import com.utils.redisutils.RedisItemUtil;
import com.utils.redisutils.RedisListUtil;
import com.utils.redisutils.RedisMapUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by ZhangGang on 2018/11/24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisItemUtil redisItemUtil;
    @Autowired
    private RedisMapUtil redisMapUtil;
    @Autowired
    private RedisListUtil redisListUtil;
    @Test
    public void RedisItemUtilTest(){
        UserInfo userInfo = new UserInfo();
        userInfo.setName("张刚");
        userInfo.setAge(90);
        redisItemUtil.setByKey("aaa",userInfo);
        redisItemUtil.expire("aaa",60);
        assert(redisItemUtil.expire("aaa",60));
        redisMapUtil.setByKey("bb","bb",userInfo);
        redisListUtil.set("cc",userInfo);

    }
    public class UserInfo{
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
