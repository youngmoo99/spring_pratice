package com.in28minutes.learnspringframework.helloworld;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

record Person (String name, int age, Address address) {}; //record는 게터세터 메서드 + 자바빈을 만드는 번거로움 없애기위해 도입된 기능

record Address(String country, String city) {} ;
@Configuration
public class HelloWorldConfiguration {
	//스프링 빈 정의 
	@Bean
	public String name() {
		return "YoungMoo";
	}
	
	@Bean
	public int age() {
		return 25;
	}
	
	@Bean
	public Person person() {
		var person = new Person("YOUNGSIK", 26, new Address("Japan","Tokyo"));
		
		return person;
	}
	
	@Bean(name="person2")
	public Person person2MethodCall() {
		var person = new Person(name(),age(), address()); //name, age, 주소(빈 재사용)
		
		return person;
	}
	
	@Bean(name="person3")
	public Person person3Parameters(String name, int age, Address address3) { //name ,age, address2
		var person = new Person(name,age, address3); //name, age, 주소(빈 재사용)
		
		return person;
	}
	
	@Bean(name="person4")
	@Primary
	public Person person4Parameters(String name, int age, Address address3) { //name ,age, address2
		var person = new Person(name,age, address3); //name, age, 주소(빈 재사용)
		
		return person;
	}
	
	@Bean//(name="person5")
	public Person person5Parameters(String name, int age, @Qualifier("address3qualifier") Address address) { //name ,age, address3
		var person = new Person(name,age, address); //name, age, 주소(빈 재사용)
		
		return person;
	}
	
	@Bean(name = "address2")
	public Address address() {
		var address = new Address("Korea", "Gimhae");
		return address;
	}
	
	@Bean(name = "address3")
	@Qualifier("address3qualifier")
	public Address address3() {
		var address = new Address("USA", "New York");
		return address;
	}
	
	

	
}
