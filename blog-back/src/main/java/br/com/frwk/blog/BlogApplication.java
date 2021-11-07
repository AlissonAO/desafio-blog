package br.com.frwk.blog;

import br.com.frwk.blog.controller.TopicosController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.File;
import java.io.IOException;
/**
 * @author Alisson.Oliveira
 */
@SpringBootApplication
@EnableSpringDataWebSupport
@EnableCaching
@EnableSwagger2
public class BlogApplication {
	public BlogApplication() {
	}

	public static void main(String[] args) throws IOException {
		new File(TopicosController.uploadingDir).mkdirs();
		SpringApplication.run(BlogApplication.class, args);
	}
}
