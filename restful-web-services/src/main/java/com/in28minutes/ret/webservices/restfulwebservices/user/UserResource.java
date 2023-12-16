package com.in28minutes.ret.webservices.restfulwebservices.user;

import java.net.URI;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
//순서 
//localhost로 /users호출하면 GetMapping으로 들어와서 UserDaoService로 간다음 
// -> 모든 사용자 정보를 반환하고 그걸 UserResource에서 받아 JSON으로 변환해서 결과가 보임 


@RestController
public class UserResource {
	
	private UserDaoService service; 
	
	public UserResource(UserDaoService service) {
		this.service = service;
	}
	
	//GET /users
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}
	//GET /users 특정id
	@GetMapping("/users/{id}")
	public User retrieveAllUsers1(@PathVariable int id) {
		User user =  service.findOne(id);
		if(user == null)
			throw new UserNotFoundException("id:"+id);
		return user;
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		service.deleteById(id);
		
	}
	
	//POST /users 사용자 생성 
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) { //메소드 인자를 웹 요청의 본문과 매핑
		User savedUser = service.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").
				buildAndExpand(savedUser.getId()).
				toUri();
		return ResponseEntity.created(location).build();
	}
}
