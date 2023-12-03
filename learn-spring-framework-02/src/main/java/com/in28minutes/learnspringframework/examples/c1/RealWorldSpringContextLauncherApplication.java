package com.in28minutes.learnspringframework.examples.c1;

import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
class ClassA {
	
}

@Component
@Lazy //해당클래스를 사용할때만 초기화됨 
//항상 즉시 초기화 하는게 좋음 
class ClassB {
	private ClassA classA;
	public ClassB(ClassA classA) {
		//Logic
		System.out.println("Some Initialization logic");
		this.classA = classA;
	}
	
	public void doSomething() {
		System.out.println("Do something");
	}
}

//지연초기화 즉시 초기화 비교 
// 1) 지연초기화 
// 애플리케이션에서 처음 사용될때 초기화 
// @Lazy 사용 
// 오류시 런타임 예외가됨
// 드물게 사용함 
// 애플리케이션에 Bean이 아주 드물게 사용될때 지연초기화 쓰는게 좋음 

//2) 즉시초기화
// 애플리케이션이 시작되거나 Spring context가 시작될때 초기화 
//즉시 초기화가 기본값
// 오류시 애플리케이션이 시작 안됨 

@Configuration
@ComponentScan //따로 패키지 위치 지정을 안할경우에는 현재 패키지를 componentscan
public class RealWorldSpringContextLauncherApplication {
	
	public static void main(String[] args) {
		
		try(var context = new AnnotationConfigApplicationContext(RealWorldSpringContextLauncherApplication.class)) {
			
			System.out.println("Initialization of context is completed");
			context.getBean(ClassB.class).doSomething();
//			Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);			
//			System.out.println(context.getBean(BusinessCalculationService.class).findMax());
		}
	
	}

}
