package com.in28minutes.learnspringframework.helloworld;

import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.in28minutes.learnspringframework.game.GameRunner;
import com.in28minutes.learnspringframework.game.MarioGame;
import com.in28minutes.learnspringframework.game.PacmanGame;
import com.in28minutes.learnspringframework.game.SuperContraGame;

public class App02HelloWorldSpring {

	public static void main(String[] args) {
		// 자바 인터페이스 말고 spring bean을 사용 
		
		//1. Launch a Spring context
		try (var context = new AnnotationConfigApplicationContext(HelloWorldConfiguration.class)) {
			//2. @Configuration 사용
			// helloworldconfiguration - @Configuration
			// name - @Bean
			
			//3. Retrieving Beans managed by Spring
			System.out.println(context.getBean("name"));
			System.out.println();
			
			System.out.println(context.getBean("age"));
			System.out.println();
			
			System.out.println(context.getBean("person"));
			System.out.println();
			
			System.out.println(context.getBean("person2"));
			System.out.println();
			
			System.out.println(context.getBean("address2"));
			System.out.println();
			
			System.out.println(context.getBean("person3"));
			System.out.println();
			
			System.out.println(context.getBean("person4"));
			System.out.println();
			
			System.out.println(context.getBean("person5Parameters"));
			System.out.println();
			//System.out.println(context.getBean(Address.class)); //클래스 여러개면 오류뜸 , 그래서 클래스가 같으면 빈에 name 붙여주는게좋음
			
			//Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
			
		}
		
	}

}
