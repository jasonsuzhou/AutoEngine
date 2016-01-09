package com.mh.base.web.springboot.config;

import javax.servlet.Filter;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@PropertySource(value={"classpath:application.properties"},ignoreResourceNotFound=false)
@EnableConfigurationProperties(DataSourceProperties.class)
public class ApplicationConfigure {

	@Autowired
	private DataSourceProperties properties;
	
	@Bean(destroyMethod = "close")
	@ConfigurationProperties(prefix = DataSourceProperties.PREFIX)
	public DataSource dataSource() {
		DataSource dataSource = DataSourceBuilder.create(this.properties.getClassLoader())
				 .url(this.properties.getUrl())
				 .driverClassName(this.properties.getDriverClassName())
				 .username(this.properties.getUsername())
				 .password(this.properties.getPassword())
				.build();
		try {
			dataSource.getConnection().setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataSource;
	}

	@Bean
	public Filter characterEncodingFilter() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return characterEncodingFilter;
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setConfigLocation(new ClassPathResource("com/mh/base/persist/domain/myBatisConf.xml"));
		return sessionFactory.getObject();
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

}
