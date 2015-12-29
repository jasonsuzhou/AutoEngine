package auto.freemarker.template;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Template;

public class MyBatisConfGenerator {

	public static void generateMyBatisConfFile(List<String> simpleClassNames) throws Exception {
		Template template = FreemarkerUtil.getTemplate(FreemarkerUtil.TEMPLATE_PATH, "myBatisConf.ftl");
		FileWriter fw = null;
		try {
			fw = new FileWriter(new File(FreemarkerUtil.getProjResourceDomainPath() + "myBatisConf.xml"));
			template.process(prepareData(simpleClassNames), fw);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				fw.close();
			}
		}
	}

	public static Map<String, Object> prepareData(List<String> simpleClassNames) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("generationTime", FreemarkerUtil.getGenerationDateTime());
		params.put("classNames", simpleClassNames);
		params.put("driver", "${driver}");
		params.put("url", "${url}");
		params.put("username", "${username}");
		params.put("password", "${password}");
		return params;
	}

}
