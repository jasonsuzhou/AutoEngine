<#list classlist>
<#items as clazz>
create table `${clazz.name}` (
<#list clazz.fieldList>
<#items as field>
	<#if field.name == "id">
	`${field.name}` INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT<#sep>,
	<#elseif field.type == "int">
	`${field.name}` INT(11) <#sep>,
	<#elseif field.type == "java.util.Date">
	`${field.name}` DATETIME <#sep>,
	<#elseif field.type == "java.lang.String">
	`${field.name}` VARCHAR(128) <#sep>,
	<#elseif field.type == "float">
	`${field.name}` FLOAT <#sep>,
	<#elseif field.type == "double">
	`${field.name}` FLOAT <#sep>,
	<#else>
	</#if>
</#items>
</#list>
);
</#items>
</#list>