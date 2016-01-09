package auto.freemarker.template;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Template;

public class ServiceImplGenerator {
	public static void generateServiceJavaCode(String simpleClassName) throws Exception {
		Template template = FreemarkerUtil.getTemplate(FreemarkerUtil.TEMPLATE_PATH, "ServiceImplTemplate.ftl");
		FileWriter fw = null;
		try {
			fw = new FileWriter(
					new File(FreemarkerUtil.getProjJavaServiceImplPath() + simpleClassName + "ServiceImpl.java"));
			template.process(prepareData(simpleClassName), fw);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				fw.close();
			}
		}
	}

	private static Map<String, Object> prepareData(String simpleClassName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("simpleClassName", simpleClassName);
		return params;
	}

}
