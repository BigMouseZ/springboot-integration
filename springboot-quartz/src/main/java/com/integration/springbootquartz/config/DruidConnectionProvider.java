package com.integration.springbootquartz.config;

/**
 * Created by ZhangGang on 2019/12/25
 *
 * .在使用quartz做持久化的时候需要用到quartz的11张表，可以去quartz官网下载对应版本的quartz，
 * 解压打开docs/dbTables里面有对应数据库的建表语句。关于quartz.properties配置的详细解释可以查看quartz官网。
 * 另外新建一张表APP_QUARTZ用于存放定时任务基本信息和描述等信息，
 * 定时任务的增、删、改、执行等功能与此表没有任何关系。
 * ————————————————
 */
//包名没有复制
import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.quartz.SchedulerException;
import org.quartz.utils.ConnectionProvider;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * [Druid连接池的Quartz扩展类]
 */
@Data
public class DruidConnectionProvider implements ConnectionProvider {


    /**
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *
     * 常量配置，与quartz.properties文件的key保持一致(去掉前缀)，同时提供set方法，Quartz框架自动注入值。
     *
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */
    //JDBC驱动
    public String driver;
    //JDBC连接串
    public String URL;
    //数据库用户名
    public String user;
    //数据库用户密码
    public String password;
    //数据库最大连接数
    public int maxConnections;
    //数据库SQL查询每次连接返回执行到连接池，以确保它仍然是有效的。
    public String validationQuery;
    private boolean validateOnCheckout;
    private int idleConnectionValidationSeconds;
    public String maxCachedStatementsPerConnection;
    private String discardIdleConnectionsSeconds;
    public static final int DEFAULT_DB_MAX_CONNECTIONS = 10;
    public static final int DEFAULT_DB_MAX_CACHED_STATEMENTS_PER_CONNECTION = 120;
    //Druid连接池
    private DruidDataSource quartzDatasource;

    /**
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *
     * 接口实现
     *
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */
    @Override
    public Connection getConnection() throws SQLException {
        return quartzDatasource.getConnection();
    }

    @Override
    public void shutdown() throws SQLException {
        quartzDatasource.close();
    }

    @Override
    public void initialize() throws SQLException {
        if (this.URL == null) {
            throw new SQLException("DBPool could not be created: DB URL cannot be null");
        }
        if (this.driver == null) {
            throw new SQLException("DBPool driver could not be created: DB driver class name cannot be null!");
        }
        if (this.maxConnections < 0) {
            throw new SQLException("DBPool maxConnectins could not be created: Max connections must be greater than zero!");
        }
        quartzDatasource = new DruidDataSource();
        try{
            quartzDatasource.setDriverClassName(this.driver);
        } catch (Exception e) {
            try {
                throw new SchedulerException("Problem setting driver class name on datasource: " + e.getMessage(), e);
            } catch (SchedulerException e1) {
            }
        }

        quartzDatasource.setUrl(this.URL);
        quartzDatasource.setUsername(this.user);
        quartzDatasource.setPassword(this.password);
        quartzDatasource.setMaxActive(this.maxConnections);
        quartzDatasource.setMinIdle(1);
        quartzDatasource.setMaxWait(0);
        quartzDatasource.setMaxPoolPreparedStatementPerConnectionSize(DEFAULT_DB_MAX_CONNECTIONS);
        if (this.validationQuery != null) {
            quartzDatasource.setValidationQuery(this.validationQuery);
            if(!this.validateOnCheckout)
                quartzDatasource.setTestOnReturn(true);
            else
                quartzDatasource.setTestOnBorrow(true);
            quartzDatasource.setValidationQueryTimeout(this.idleConnectionValidationSeconds);
        }
    }



}


