package com.mybatis.dao.master.testdatetime;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
@Data
@Builder
public class TestDatetime {
    private String id;

    private LocalDate testDate;

    private LocalTime testTime;

    private LocalDateTime testDatetime;

}