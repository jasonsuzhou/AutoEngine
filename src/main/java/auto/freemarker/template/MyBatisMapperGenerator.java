package auto.freemarker.template;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Template;

public class MyBatisMapperGenerator {

	public static void generateMyBatisMapperFile(String simpleClassName, String propertyList, String propertyValueList,
			String updatePropertyList, List<Field> fieldList) throws Exception {
		Template template = FreemarkerUtil.getTemplate(FreemarkerUtil.TEMPLATE_PATH, "myBatisTemplateMapper.ftl");
		FileWriter fw = null;
		try {
			fw = new FileWriter(
					new File(FreemarkerUtil.getProjResourceDomainPath() + "myBatis" + simpleClassName + "Mapper.xml"));
			template.process(
					prepareData(simpleClassName, propertyList, propertyValueList, updatePropertyList, fieldList), fw);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				fw.close();
			}
		}
	}

	public static Map<String, Object> prepareData(String simpleClassName, String propertyList, String propertyValueList,
			String updatePropertyList, List<Field> fieldList) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("generationTime", FreemarkerUtil.getGenerationDateTime());
		params.put("className", simpleClassName);
		params.put("parameterType", "int");
		params.put("propertyList", propertyList);
		params.put("propertyValueList", propertyValueList);
		params.put("updatePropertyList", updatePropertyList);
		params.put("primaryKeyName", "#{id}");
		params.put("fieldList", fieldList);
		return params;
	}

}
