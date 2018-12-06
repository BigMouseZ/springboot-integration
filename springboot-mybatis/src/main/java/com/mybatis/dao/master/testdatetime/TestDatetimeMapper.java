package com.mybatis.dao.master.testdatetime;

import com.mybatis.dao.master.testdatetime.TestDatetime;
import java.util.List;

public interface TestDatetimeMapper {
    int insert(TestDatetime record);

    List<TestDatetime> selectAll();
}