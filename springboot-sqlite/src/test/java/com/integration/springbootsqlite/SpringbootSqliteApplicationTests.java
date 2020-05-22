package com.integration.springbootsqlite;

import com.integration.springbootsqlite.service.JdbcService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootSqliteApplicationTests {
    @Autowired
   private JdbcService jdbcService;

    @Test
    void contextLoads() {
        jdbcService.selectUser(null);
    }

}
