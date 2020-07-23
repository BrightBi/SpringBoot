package com.bright.learn.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class MultipleDataSourceConfig {
	
	@Primary
	@Bean(name = "primaryDataSourceProperties")
	@Qualifier("primaryDataSourceProperties")
	@ConfigurationProperties(prefix = "spring.datasource.primary")
	public DataSourceProperties primaryDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Primary
	@Bean(name = "primaryDataSource")
	@Qualifier("primaryDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.primary")
	public DataSource primaryDataSource() {
		/*
		 * 这里使用 DataSourceProperties 来处理数据库链接相关的配置参数，并获得 DataSource。
		 * 
		 * 也可以使用 DataSourceBuilder.create().build(); 来获得 DataSource。
		 * 但是对于多数据源会有问题，报错：jdbcUrl is required with driverClassName，
		 * 需要将配置文件中的 spring.datasource.secondary.url 改为 spring.datasource.primary.jdbc-url
		 * 参考：
		 *  https://blog.csdn.net/newbie_907486852/article/details/81391525
		 *  https://blog.csdn.net/newhanzhe/article/details/80763581
		 */
		return primaryDataSourceProperties().initializeDataSourceBuilder().build();
	}

	@Bean(name = "secondaryDataSourceProperties")
	@Qualifier("secondaryDataSourceProperties")
	@ConfigurationProperties(prefix = "spring.datasource.secondary")
	public DataSourceProperties secondaryDataSourceProperties() {
		return new DataSourceProperties();
	}
	
	@Bean(name = "secondaryDataSource")
	@Qualifier("secondaryDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.secondary")
	public DataSource secondaryDataSource() {
		return secondaryDataSourceProperties().initializeDataSourceBuilder().build();
	}

	@Bean(name = "primaryJdbcTemplate")
	public JdbcTemplate primaryJdbcTemplate(@Qualifier("primaryDataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean(name = "secondaryJdbcTemplate")
	public JdbcTemplate secondaryJdbcTemplate(@Qualifier("secondaryDataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
}
