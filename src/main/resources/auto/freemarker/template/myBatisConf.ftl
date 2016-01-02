<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
<!-- Auto generated at ${generationTime} -->
<configuration>
	<properties resource="com/mh/proj/persist/domain/mysql_db_info.properties" />

	<typeAliases>
		<!-- set alias name one by one -->
		<#list classNames as item>
		<typeAlias type="com.mh.proj.persist.domain.${item}" alias="_${item}"/>
		</#list>
		<!-- batch set alias, default alias name is the class name
		<package name="test.com.mh.base.persist.impl"/>
		 -->
	</typeAliases>

	<environments default="development">
		<environment id="development">
			<transactionManager type="JDBC" />
			<dataSource type="POOLED">
				<property name="driver" value="${driver}" />
				<property name="url" value="${url}" />
				<property name="username" value="${username}" />
				<property name="password" value="${password}" />
			</dataSource>
		</environment>
	</environments>
	
	<mappers>
		<#list classNames as item>
		<mapper resource="com/mh/proj/persist/domain/myBatis${item}Mapper.xml"/>
		</#list>
	</mappers>
	
</configuration>