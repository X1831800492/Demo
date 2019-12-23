package cn.tedu.store;

import javax.servlet.MultipartConfigElement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

@SpringBootApplication
@MapperScan("cn.tedu.store.mapper")
@Configuration
public class StoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
		
	}
	@Bean
	public MultipartConfigElement a() {
		MultipartConfigElement element;
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize(DataSize.ofMegabytes(50));
		factory.setMaxRequestSize(DataSize.ofMegabytes(50));
		
		element = factory.createMultipartConfig();
		return element;
		
	}

}
