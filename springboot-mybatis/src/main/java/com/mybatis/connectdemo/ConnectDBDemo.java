package com.mybatis.connectdemo;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
/**
 * Created by ZhangGang on 2018/12/1.
 *
 */
public class ConnectDBDemo {
    public static void main( String[] args ) throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/pg_java8","admin","123456");
        PreparedStatement st = conn.prepareStatement("insert into tb_java8date (t_date,t_time,t_datetime)values(?,?,?)");
        System.out.println(st.getClass());
        st.setObject(1, LocalDate.now());
        st.setObject(2, LocalTime.now());
        st.setObject(3, LocalDateTime.now());
        st.executeQuery();
        st.execute();
        st.close();
        conn.close();
    }
}
