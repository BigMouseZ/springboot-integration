package com.integration.springbootjpa;

import com.integration.springbootjpa.entity.QSysUsers;
import com.integration.springbootjpa.entity.SysUsers;
import com.integration.springbootjpa.repository.SysUsersRepository;
import com.integration.springbootjpa.repository.SysUsersRepositoryDsl;
import com.integration.springbootjpa.repository.service.SysUsersRepositoryService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootJpaApplicationTests {

    @Resource
    private SysUsersRepository sysUsersRepository;
    @Resource
    private SysUsersRepositoryDsl sysUsersRepositoryDsl;
    @Autowired
    private SysUsersRepositoryService sysUsersRepositoryService;

    @Test
    public void save() {
        //保存或者更新
        SysUsers save = new SysUsers();
        save.setLoginName("cesss");
        save.setGuid("901ccb7c-e0f2-442e-b5ba-dad469d02a13");//临时生成guid
        save.setLoginPwd("123");
        save.setPhone("12323232312");
        save.setUserPosition(1);
        save.setJobNumber("123");
        save.setAddTime(new Date());
        save.setModifyTime(new Date());
        //保存和更新，存在则更新，不存在则新增
        sysUsersRepository.save(save);
    }

    @Test
    public void saveBatch() {
        //批量保存或者更新
        List<SysUsers> list = new ArrayList<>();
        SysUsers save = new SysUsers();
        save.setLoginName("cesssadad");
        save.setGuid("b1345e83-06bd-4c4b-816b-05030c2f5df2");//临时生成guid
        save.setLoginPwd("123");
        save.setPhone("43");
        save.setUserPosition(1);
        save.setJobNumber("1233333333");
        save.setAddTime(new Date());
        save.setModifyTime(new Date());
        list.add(save);
        SysUsers save1 = new SysUsers();
        save1.setLoginName("cesssadadasasas");
        save1.setGuid(UUID.randomUUID().toString());//临时生成guid
        save1.setLoginPwd("123");
        save1.setPhone("12323232312");
        save1.setUserPosition(1);
        save1.setJobNumber("123");
        save1.setAddTime(new Date());
        save1.setModifyTime(new Date());
        list.add(save1);
        //保存和更新，存在则更新，不存在则新增
        sysUsersRepository.saveAll(list);
    }

    @Test
    public void delete() {
        //删除
        SysUsers save = new SysUsers();
        save.setGuid("901ccb7c-e0f2-442e-b5ba-dad469d02a13");//临时生成guid
        //保存和更新，存在则更新，不存在则新增
        if (sysUsersRepository.existsById(save.getGuid())) {
            sysUsersRepository.deleteById(save.getGuid());
        }
    }

    @Test
    public void findPageListByCondition() {
        //分页动态查询查询
        SysUsers sysUsers = new SysUsers();
        int page = 0, size = 20;
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.ASC, "loginName"));
        orders.add(new Sort.Order(Sort.Direction.DESC, "addTime"));
        Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
        Page<SysUsers> listPage = sysUsersRepositoryService.findPageListByCondition(sysUsers, pageable);
        List<SysUsers> list = listPage.getContent();
        list.forEach(one -> System.out.println(one.toString()));
    }

    @Autowired
    private JPAQueryFactory queryFactory;

    @Test
    public void findOneDsl() {
        QSysUsers sysUsers = QSysUsers.sysUsers;
       List<SysUsers> list = queryFactory.selectFrom(sysUsers)
                .where(sysUsers.loginName.eq("cesssadadsdad"))
                .orderBy(sysUsers.addTime.desc())
                .fetch();
        list.forEach(one -> System.out.println(one.toString()));
    }


}
