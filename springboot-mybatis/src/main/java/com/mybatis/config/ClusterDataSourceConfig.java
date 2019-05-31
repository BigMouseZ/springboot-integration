package com.mybatis.config;


import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * mysql主库配置类
 * @日期： 2018年7月5日 下午10:05:25
 * @作者： Chendb
 */
@Configuration
@MapperScan(basePackages = "com.mybatis.dao.cluster",sqlSessionTemplateRef = "clusterSqlSessionTemplate")
public class ClusterDataSourceConfig {

	/**
	 * 创建数据源
	 *@return DataSource
	 */
	@Bean(name = "clusterDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.druid.cluster")
	public DataSource masterDataSource() {
		return DruidDataSourceBuilder.create().build();
		//	return DataSourceBuilder.create().build();
	}
	
	/**
	 * 创建工厂
	 *@param dataSource
	 *@throws Exception
	 *@return SqlSessionFactory
	 */
	@Bean(name = "clusterSqlSessionFactory")
	public SqlSessionFactory masterSqlSessionFactory(@Qualifier("clusterDataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
//		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/master/*.xml"));
		//分页插件
      /*当多数据源为不同类型的数据库是，使用此方式配置分页插件，
       Interceptor interceptor = new PageInterceptor();
        Properties properties = new Properties();
        // properties.setProperty("helperDialect","postgresql");    //配置mysql数据库的方言
        properties.setProperty("helperDialect", "mysql");
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("params", "pageNum=pageNumKey;pageSize=pageSizeKey;");
        interceptor.setProperties(properties);
        bean.setPlugins(new Interceptor[]{interceptor});*/
		return bean.getObject();
	}
	
	/**
	 * 创建事务
	 *@param dataSource
	 *@return DataSourceTransactionManager
	 */
	@Bean(name = "clusterTransactionManager")
	public DataSourceTransactionManager masterDataSourceTransactionManager(@Qualifier("clusterDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
	
	/**
	 * 创建模板
	 *@param sqlSessionFactory  
	 *@return SqlSessionTemplate
	 */
	@Bean(name = "clusterSqlSessionTemplate")
	public SqlSessionTemplate masterSqlSessionTemplate(@Qualifier("clusterSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	
}
