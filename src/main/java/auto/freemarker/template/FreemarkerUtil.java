package auto.freemarker.template;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;

public class FreemarkerUtil {

	public static final String TEMPLATE_PATH = File.separator+"auto"+File.separator+"freemarker"+File.separator+"template"+File.separator;

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
		String folderPath = getAppRootPath() + File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"templates"+File.separator+"adminmgr"+File.separator + simpleClassName.toLowerCase() + File.separator+"";
		File file = new File(folderPath);
		if (!file.exists()) {
			file.mkdir();
		}
		return folderPath;
	}

	public static String getProjResourceDomainPath() {
		return getAppRootPath() + File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"com"+File.separator+"mh"+File.separator+"proj"+File.separator+"persist"+File.separator+"domain"+File.separator;
	}

	public static String getProjJavaDAOPath() {
		String path = getAppRootPath() + File.separator+"src"+File.separator+"main"+File.separator+"java"+File.separator+"com"+File.separator+"mh"+File.separator+"proj"+File.separator+"persist"+File.separator+"dao"+File.separator;
		return getAppRootPath() + File.separator+"src"+File.separator+"main"+File.separator+"java"+File.separator+"com"+File.separator+"mh"+File.separator+"proj"+File.separator+"persist"+File.separator+"dao"+File.separator;
	}

	public static String getProjJavaServicePath() {
		return getAppRootPath() + File.separator+"src"+File.separator+"main"+File.separator+"java"+File.separator+"com"+File.separator+"mh"+File.separator+"proj"+File.separator+"manage"+File.separator+"service"+File.separator;
	}

	public static String getProjJavaDAOImplPath() {
		return getAppRootPath() + File.separator+"src"+File.separator+"main"+File.separator+"java"+File.separator+"com"+File.separator+"mh"+File.separator+"proj"+File.separator+"persist"+File.separator+"dao"+File.separator+"impl"+File.separator;
	}

	public static String getProjJavaServiceImplPath() {
		return getAppRootPath() + File.separator+"src"+File.separator+"main"+File.separator+"java"+File.separator+"com"+File.separator+"mh"+File.separator+"proj"+File.separator+"manage"+File.separator+"service"+File.separator+"impl"+File.separator;
	}

	public static String getProjJavaDomainPath() {
		return getAppRootPath() + File.separator+"src"+File.separator+"main"+File.separator+"java"+File.separator+"com"+File.separator+"mh"+File.separator+"proj"+File.separator+"persist"+File.separator+"domain"+File.separator;
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
