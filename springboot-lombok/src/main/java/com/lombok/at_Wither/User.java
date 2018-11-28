package com.lombok.at_Wither;

import lombok.experimental.Wither;

/**
 * Created by ZhangGang on 2018/11/28.
 *提供了给final字段赋值的一种方法
 */
public class User {
    @Wither
    private final String country;

    public User(String country) {
        this.country = country;
    }
}
/*public class User {
    private final String country;

    public User(String country) {
        this.country = country;
    }

    public User withCountry(String country) {
        return this.country == country?this:new User(country);
    }
}
*/

