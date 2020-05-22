package com.integration.springbootjpa;

import com.integration.springbootjpa.entity.SysAuthority;
import com.integration.springbootjpa.entity.SysUsers;
import com.integration.springbootjpa.entity.SysUsersRole;
import com.integration.springbootjpa.entity.UserDTO;
import com.integration.springbootjpa.repository.SysAuthorityRepository;
import com.integration.springbootjpa.repository.SysUsersRepositoryDsl;
import com.integration.springbootjpa.repository.service.SysUsersRepositoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootJpaDSLTests {

    @Resource
    private SysAuthorityRepository sysAuthorityRepository;
    @Resource
    private SysUsersRepositoryDsl sysUsersRepositoryDsl;
    @Autowired
    private SysUsersRepositoryService sysUsersRepositoryService;

    @Test
    public void save() {
        //保存或者更新
        SysUsers sysUsers= sysUsersRepositoryService.findByUsernameAndPassword("zg","c4ca4238a0b923820dcc509a6f75849b");
        System.out.println(sysUsers.toString());
    }
    @Test
    public void findAllUserDto() {
        //保存或者更新
        List<Sort.Order> orders = new ArrayList<>();
        int page = 0, size = 20;
        orders.add(new Sort.Order(Sort.Direction.ASC, "loginName"));
        orders.add(new Sort.Order(Sort.Direction.DESC, "addTime"));
        Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
        List<UserDTO> list = sysUsersRepositoryService.findAllUserDto(pageable);
        list.forEach(one-> System.out.println(one.toString()));
    }
    @Test
    public void getFileList() {
        List<SysUsersRole> list = sysUsersRepositoryService.getFileList();
        list.forEach(one-> System.out.println(one.toString()));
    }
    @Test
    public void findUserInfo() {
        List<SysAuthority> list = sysAuthorityRepository.findUserInfo("20190527160213_BABD6598EF59414D9B70424468DB0753");
        list.forEach(one-> System.out.println(one.toString()));
    }

}
