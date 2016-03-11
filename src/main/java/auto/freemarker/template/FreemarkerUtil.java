package auto.freemarker.template;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;

public class FreemarkerUtil {

	public static final String TEMPLATE_PATH = "/auto/freemarker/template/";

	public static Template getTemplate(String packagePath, String ftlFileName) throws Exception {
		Configuration cfg = new Configuration(new Version("2.3.23"));
		cfg.setClassForTemplateLoading(MyBatisMapperGenerator.class, packagePath);
		return cfg.getTemplate(ftlFileName);
	}

	public static String getAppRootPath() {
		String filePath = FreemarkerUtil.class.getResource("").getPath();
		File tempFile = new File(filePath);
		tempFile = tempFile.getParentFile().getParentFile().getParentFile().getParentFile().getParentFile();
		return tempFile.getPath();
	}

	public static String getAdminResourceTemplatesPath(String simpleClassName) {
		String folderPath = getAppRootPath() + "/src/main/resources/templates/adminmgr/" + simpleClassName.toLowerCase() + "/";
		File file = new File(folderPath);
		if (!file.exists()) {
			file.mkdir();
		}
		return folderPath;
	}

	public static String getProjResourceDomainPath() {
		return getAppRootPath() + "/src/main/resources/com/mh/proj/persist/domain/";
	}

	public static String getProjJavaDAOPath() {
		return getAppRootPath() + "/src/main/java/com/mh/proj/persist/dao/";
	}

	public static String getProjJavaServicePath() {
		return getAppRootPath() + "/src/main/java/com/mh/proj/manage/service/";
	}

	public static String getProjJavaDAOImplPath() {
		return getAppRootPath() + "/src/main/java/com/mh/proj/persist/dao/impl/";
	}

	public static String getProjJavaServiceImplPath() {
		return getAppRootPath() + "/src/main/java/com/mh/proj/manage/service/impl/";
	}

	public static String getProjJavaDomainPath() {
		return getAppRootPath() + "/src/main/java/com/mh/proj/persist/domain/";
	}

	public static String getProjDomainClassPath(String simpleClassName) {
		return "com.mh.proj.persist.domain." + simpleClassName;
	}

	public static String getGenerationDateTime() {
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(date);
	}

}
