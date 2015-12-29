package auto.freemarker.template;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Template;

public class MySQLGenerator {

	private static List<DomainClass> classList = new ArrayList<DomainClass>();

	public static void preapareData(String className, List<java.lang.reflect.Field> listField) {
		List<DomainClassField> fields = new ArrayList<DomainClassField>();
		for (java.lang.reflect.Field field : listField) {
			DomainClassField tempField = new DomainClassField();
			tempField.setName(field.getName());
			tempField.setType(field.getType().getName());
			fields.add(tempField);
		}
		DomainClass clazz = new DomainClass();
		clazz.setName(className);
		clazz.setFieldList(fields);
		classList.add(clazz);
	}

	public static void startGenerateSQL() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("classlist", classList);
		Template template = FreemarkerUtil.getTemplate(FreemarkerUtil.TEMPLATE_PATH, "createTable_MySQL.ftl");
		FileWriter fw = null;
		try {
			fw = new FileWriter(new File(FreemarkerUtil.getProjResourceDomainPath() + "createTable_MySQL.sql"));
			template.process(params, fw);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				fw.close();
			}
		}
	}
}


