package com.restfulwebservices.restfulwebservices;

import com.restfulwebservices.restfulwebservices.lombokExample.Student;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;
import java.util.logging.Logger;

//short hand representation for logging - from lombok library
@Slf4j
@SpringBootApplication
public class RestfulWebServicesApplication {

	//If we want to log info- earlier we used syntax
	//private static final Logger log = LoggerFactory.getLogger(RestfulWebServicesApplication.class)


	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServicesApplication.class, args);
		Student student = new Student();
		student.setFirstName("sai");
		student.setLastName("akula");
		student.setId(1l);
		log.info("student info:{}",student);

		Student student1 = Student.builder().id(2l)
							.firstName("shravya").lastName("voodari").build();
		log.info("student1 info:{}",student1);
	}

	//configure internationalization
	@Bean
	public LocaleResolver localeResolver()
	{
		//SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		//Since we are sending local in accept-header
		AcceptHeaderLocaleResolver acceptHeaderLocaleResolver = new AcceptHeaderLocaleResolver();
		acceptHeaderLocaleResolver.setDefaultLocale(Locale.US);
		return acceptHeaderLocaleResolver;
	}

	// create the bean here to set the base for message properties or set the base in application properties
//	@Bean
//	public ResourceBundleMessageSource bundleMessageSource()
//	{
//		ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
//		resourceBundleMessageSource.setBasename("messages");
//		return resourceBundleMessageSource;
//	}
}
