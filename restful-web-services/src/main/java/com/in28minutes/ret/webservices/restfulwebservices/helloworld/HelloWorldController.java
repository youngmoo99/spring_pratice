package com.in28minutes.ret.webservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//REST API	
@RestController
public class HelloWorldController {
	//GET 사용자가 게시물의 상세정보를 얻고 싶을때 GET요청 메서드 사용 
	//POST 새 리소스를 만들고자 할때 사용 
	//PUT 기존 사용자의 상세 정보를 업데이트 할떄 사용 
	//PATCH 기존 리소스의 일부만 업데이트 
	//DELETE 리소스 삭제 
	
	//Users REST API 
	//1) 모든 사용자 검색 GET /users 
	//2) 사용자 생성 POST /users
	//3) 특정사용자 검색 GET /users/{id} -> /users/1
	//4) 사용자 삭제 DELETE /users/{id} -> /users/1
	
	//5) Posts REST API 
	//특정 사용자가 올린 모든 게시물 검색 GET /usres/{id}/posts 
	
	//6) 특정 사용자를 위해 게시물 생성 POST /users/{id}/posts 
	
	//7) 특정 게시물의 상세정보 검색 GET /users/{id}/posts/{post id} 

	//@RequestMapping(method = RequestMethod.GET, path = "/hello-world") //포트 8080 
	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return "Hello World";
	}
	
	//json반환 
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World");
	}
	
	//파라미터 사용
	@GetMapping(path = "/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorlPathVariable(@PathVariable String name) { //@PathVariable 파라미터 매개변수값 확인하기위해 사용
		return new HelloWorldBean(String.format("Hello World, %s", name));
				
}
	
}