package auto.freemarker.template;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProjDomainClassScanner {

	public static void main(String[] args) throws Exception {
		String projDomainPath = FreemarkerUtil.getProjJavaDomainPath();
		File file = new File(projDomainPath);
		List<String> simpleClassNames = new ArrayList<String>();
		if (file.exists() && file.isDirectory()) {
			String[] fileNames = file.list();
			for (String fileName : fileNames) {
				if (!fileName.equals("DonotRemove.java")) {
					processGenerationForMyBatisMapper(fileName.substring(0, fileName.indexOf(".")));
					processGenerationForDAOLayer(fileName.substring(0, fileName.indexOf(".")));
					simpleClassNames.add(fileName.substring(0, fileName.indexOf(".")));
				}
			}
		}
		MyBatisConfGenerator.generateMyBatisConfFile(simpleClassNames);
		MySQLGenerator.startGenerateSQL();
	}

	public static void processGenerationForDAOLayer(String domainFileName) throws Exception {
		DAOGenerator.generateDAOJavaCode(domainFileName);
		MyBatisDAOImplGenerator.generateDAOImplJavaCode(domainFileName);
	}

	public static void processGenerationForMyBatisMapper(String domainFileName) throws Exception {
		Set<String> fieldNames = new HashSet<String>();
		List<Field> fieldList = new ArrayList<Field>();
		String classPath = FreemarkerUtil.getProjDomainClassPath(domainFileName);
		Class domainClass = Class.forName(classPath);
		Class superBaseEntity = domainClass.getSuperclass();
		Field[] fields = superBaseEntity.getDeclaredFields();
		for (Field field : fields) {
			if (!field.getName().equals("serialVersionUID")) {
				if (!fieldNames.contains(field.getName())) {
					fieldList.add(field);
				}
				fieldNames.add(field.getName());
			}
		}
		fields = domainClass.getDeclaredFields();
		for (Field field : fields) {
			if (!field.getName().equals("serialVersionUID")) {
				if (!fieldNames.contains(field.getName())) {
					fieldList.add(field);
				}
				fieldNames.add(field.getName());
			}
		}
		fieldNames.remove("id");
		StringBuilder propertyList = new StringBuilder(64);
		StringBuilder propertyValueList = new StringBuilder(64);
		StringBuilder updatePropertyList = new StringBuilder(64);
		for (String fieldName : fieldNames) {
			propertyList.append(fieldName).append(",");
			propertyValueList.append("#{").append(fieldName).append("}").append(",");
			updatePropertyList.append(fieldName).append("=").append("#{").append(fieldName).append("}").append(",");
		}
		propertyList.deleteCharAt(propertyList.length() - 1);
		propertyValueList.deleteCharAt(propertyValueList.length() - 1);
		updatePropertyList.deleteCharAt(updatePropertyList.length() - 1);
		MyBatisMapperGenerator.generateMyBatisMapperFile(domainFileName, propertyList.toString(),
				propertyValueList.toString(), updatePropertyList.toString());

		MySQLGenerator.preapareData(domainFileName, fieldList);
	}

}
