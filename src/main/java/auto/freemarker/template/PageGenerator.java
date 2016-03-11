package auto.freemarker.template;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

import com.mh.base.common.util.CommonUtils;

import freemarker.template.Template;

public class PageGenerator {

	public static void generateAddPage(String simpleClassName) throws Exception {
		Map<String, Object> root = prepareData(simpleClassName);
		Template template = FreemarkerUtil.getTemplate(FreemarkerUtil.TEMPLATE_PATH, "add_template.ftl");
		File outputFile = new File(FreemarkerUtil.getAdminResourceTemplatesPath(simpleClassName) + "add.ftl");
		FileWriter fw = null;
		try {
			fw = new FileWriter(outputFile);
			template.process(root, fw);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				fw.close();
			}
		}
	}

	private static Map<String, Object> prepareData(String simpleClassName) {
		String lowerName = CommonUtils.getFirstCharLower(simpleClassName);
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("add_title", "${label_operation} ${label_modal_name}");
		root.put("simpleClassName", simpleClassName);
		root.put("lowerSimpleClassName", lowerName);
		root.put("button_title", "${button_submit}");
		return root;
	}

	public static void main(String[] args) throws Exception {
		generateAddPage("Function");
	}

}
