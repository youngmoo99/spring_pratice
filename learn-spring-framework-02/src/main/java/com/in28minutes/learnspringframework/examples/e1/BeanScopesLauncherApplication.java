package com.in28minutes.learnspringframework.examples.e1;

import java.util.Arrays;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component // 싱글톤
class NormalClass { 
	
}

@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE) // 프로토타입 
@Component
class PrototypeClass {
	
}


@Configuration
@ComponentScan //따로 패키지 위치 지정을 안할경우에는 현재 패키지를 componentscan
public class BeanScopesLauncherApplication {
	
	public static void main(String[] args) {
		
		try(var context = new AnnotationConfigApplicationContext(BeanScopesLauncherApplication.class)) {
			
			//동일한 빈이 검색 해시코드가 한개
			// 반환되는 인스턴스가 같음
			//싱글톤
			System.out.println(context.getBean(NormalClass.class));
			System.out.println(context.getBean(NormalClass.class));
			System.out.println(context.getBean(NormalClass.class));
			
			//새로운 빈이 검색 해시코드가 계속 바뀜
			//반환되는 인스턴스 모두 다름
			//프로토타입
			System.out.println(context.getBean(PrototypeClass.class));
			System.out.println(context.getBean(PrototypeClass.class));
			System.out.println(context.getBean(PrototypeClass.class));
		}
	
	}

}
