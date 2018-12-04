package com.mybatis;

import com.github.pagehelper.PageInfo;
import com.mybatis.dao.master.testdatetime.TestDatetime;
import com.mybatis.dao.master.testdatetime.TestDatetimeMapper;
import com.mybatis.entity.UserEntity;
import com.mybatis.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMybatisApplicationTests {
    @Autowired
    UserService userService;

    @Test
    public void contextLoads() {
        //往master数据库插入数据
        UserEntity userEntity = UserEntity.builder()
                .id(UUID.randomUUID().toString())
                .userName("testname")
                .userSex("男").build();
        userService.setuserinfo(userEntity);
        UserEntity result = userService.getuserinfo(userEntity.getId());
        System.out.println(result.toString());
    }

    @Test
    public void contextClusterLoads() {
        //往master数据库插入数据
        UserEntity userEntity = UserEntity.builder()
                .id(UUID.randomUUID().toString())
                .userName("testname")
                .userSex("男").build();
        userService.setclusteruserinfo(userEntity);
        UserEntity result = userService.getclusteruserinfo(userEntity.getId());
        System.out.println(result.toString());
    }

    @Test
    public void pagehelper() {
        PageInfo<UserEntity> list = userService.getUserBySearch(2, 2);
        System.out.println(list);
    }
    @Autowired
    private TestDatetimeMapper testDatetimeMapper;
    @Test
    public void testDateTime(){
        TestDatetime record  = TestDatetime.builder().id(UUID.randomUUID().toString())
                .testDate(LocalDate.now())
                .testTime(LocalTime.now())
                .testDatetime(LocalDateTime.now()).build();
        testDatetimeMapper.insert(record);
    }
}
