package com.integration.springbootjpa.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private String userId;
    private String loginName;
    private String username;
    private String nickname;
    private String birthday;
}
