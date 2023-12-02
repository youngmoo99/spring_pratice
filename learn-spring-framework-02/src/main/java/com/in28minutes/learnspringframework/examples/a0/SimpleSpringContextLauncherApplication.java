package com.in28minutes.learnspringframework.examples.a0;

import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan //따로 패키지 위치 지정을 안할경우에는 현재 패키지를 componentscan
public class SimpleSpringContextLauncherApplication {
	
	public static void main(String[] args) {
		
		try(var context = new AnnotationConfigApplicationContext(SimpleSpringContextLauncherApplication.class)) {
			
			Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
		}
	
	}

}
