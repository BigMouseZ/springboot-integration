package com.lombok.at_Data;

import lombok.Data;

/**
 * Created by ZhangGang on 2018/11/28.
 *@Data 包含了 @ToString、@EqualsAndHashCode、@Getter / @Setter和@RequiredArgsConstructor的功能
 */
@Data
public class User {
    private Long id;
    private String phone;
    private Integer status;
}


