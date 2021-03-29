package com.example.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@SpringBootApplication
@ServletComponentScan //서블릿 클래스들을 스캔해서 빈으로 자동등록하겠다.
public class MvcApplication {

	//뷰 리졸버 등록 : 컨트롤러가 리턴한 문자열을 가지고 뷰 파일을 포워딩해주는 객체
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	public static void main(String[] args) {
		SpringApplication.run(MvcApplication.class, args);
	}

}