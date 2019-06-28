package com.integration.action.command.impl;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2019/6/9 0009.
 */
@Data
@Component
public class TestEntity {
    @Value("${test}")
    private String test;




}
