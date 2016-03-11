
import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//@SpringBootApplication same as //@Configuration @EnableAutoConfiguration @ComponentScan
@Configuration
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
@ComponentScan(basePackages = { "com.mh.base.persist.dao", "com.mh.base.web.springboot.config",
		"com.mh.base.manage.service" })
// @Import import the configuration class
// @ImportResource import the XML configuration file
public class Application {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			// System.out.println("======================" + beanName);
		}
	}

}
