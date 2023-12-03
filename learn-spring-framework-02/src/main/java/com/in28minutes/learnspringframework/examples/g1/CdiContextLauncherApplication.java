package com.in28minutes.learnspringframework.examples.g1;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import jakarta.inject.Inject;
import jakarta.inject.Named;

//@Component
@Named //CDI 어노테이션 방식
class BusinessService {
	DataService dataService;
	
	
	public DataService getDataService() {
		
		return dataService;
	}
	//@Autowired //Spring 어노테이션 방식
	@Inject //CDI 어노테이션 방식
	public void setDataService(DataService dataService) {
		System.out.println("Setter Injection");
		this.dataService = dataService;
	}
	
	
}

//@Component
@Named //CDI 어노테이션 방식
class DataService {
	
}


@Configuration
@ComponentScan //따로 패키지 위치 지정을 안할경우에는 현재 패키지를 componentscan
public class CdiContextLauncherApplication {
	
	public static void main(String[] args) {
		
		try(var context = new AnnotationConfigApplicationContext(CdiContextLauncherApplication.class)) {
			
			Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
			
			System.out.println(context.getBean(BusinessService.class).getDataService());
		}
	
	}

}
