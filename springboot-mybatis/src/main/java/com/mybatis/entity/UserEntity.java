package com.mybatis.entity;

import lombok.Builder;
import lombok.Data;

/**
 * Created by ZhangGang on 2018/5/11.
 */
@Builder
@Data
public class UserEntity  {
    private String id;
    private String userName;
    private String passWord;
    private String userSex;
}
