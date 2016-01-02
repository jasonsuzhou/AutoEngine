package auto.freemarker.template;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProjResourcesCleaner {
	static List<String> cleanPaths = new ArrayList<String>();

	static {
		cleanPaths.add(FreemarkerUtil.getProjJavaDAOPath());
		cleanPaths.add(FreemarkerUtil.getProjJavaDAOImplPath());
		cleanPaths.add(FreemarkerUtil.getProjJavaDomainPath());
		cleanPaths.add(FreemarkerUtil.getProjResourceDomainPath());
	}

	public static void main(String[] args) {
		for (String path : cleanPaths) {
			File file = new File(path);
			if (file.exists() && file.isDirectory()) {
				File[] files = file.listFiles();
				for (File javaFile : files) {
					if (javaFile.isFile() && !javaFile.getName().equals("DonotRemove.java")
							&& !javaFile.getName().equals("mysql_db_info.properties")) {
						javaFile.deleteOnExit();
					}
				}
			}
		}
	}
}
