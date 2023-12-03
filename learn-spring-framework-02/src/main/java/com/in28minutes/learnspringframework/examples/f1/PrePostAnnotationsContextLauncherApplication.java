package com.in28minutes.learnspringframework.examples.f1;

import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
class SomeClass {
	
	private SomeDependency someDependency;
	public SomeClass(SomeDependency someDependency) {
		super();
		this.someDependency = someDependency;
		//의존성 준비
		System.out.println("All dependencies are ready!");
	}
	
	@PostConstruct
	public void initialize() {
		someDependency.getReady();
	}
	
	@PreDestroy // 컨테이너에서 인스턴스를 삭제하는 과정중에 있음을 알려주는 콜백 알림
	public void cleanup() {
		//bean이 삭제되기전에 나타남
		System.out.println("Clean up!");
	}
}

@Component 
class SomeDependency {

	public void getReady() {
		//의존성이 준비된후에 초기화 
		System.out.println("Some logic using someDependency");
		
	}
	
}

@Configuration
@ComponentScan //따로 패키지 위치 지정을 안할경우에는 현재 패키지를 componentscan
public class PrePostAnnotationsContextLauncherApplication {
	
	public static void main(String[] args) {
		
		try(var context = new AnnotationConfigApplicationContext(PrePostAnnotationsContextLauncherApplication.class)) {
			
			Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
		}
	
	}

}
