package com.in28minutes.ret.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.net.URI;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutes.ret.webservices.restfulwebservices.jpa.PostRepository;
import com.in28minutes.ret.webservices.restfulwebservices.jpa.UserRepository;

import jakarta.validation.Valid;
//순서 
//localhost로 /users호출하면 GetMapping으로 들어와서 UserDaoService로 간다음 
// -> 모든 사용자 정보를 반환하고 그걸 UserResource에서 받아 JSON으로 변환해서 결과가 보임 


@RestController
public class UserJpaResource {
	
 
	private UserRepository userRepository;
	
	private PostRepository postrepository;
	
	public UserJpaResource(UserRepository userRepository,PostRepository postRepository) {
		this.userRepository = userRepository;
		this.postrepository = postRepository;
	}
	
	//GET /users
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}
	
	//EntityModel
	//WebMvcLinkBuilder
	
	//GET /users 특정id
	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> retrieveAllUsers1(@PathVariable int id) {
		Optional<User> user =  userRepository.findById(id);
		if(user.isEmpty())
			throw new UserNotFoundException("id:"+id);
		
		EntityModel<User> entityModel = EntityModel.of(user.get());
		
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		
		entityModel.add(link.withRel("all-users"));
		
		return entityModel;
	}

	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
		
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrievePostsForUser(@PathVariable int id) {
		Optional<User> user =  userRepository.findById(id);
		if(user.isEmpty())
			throw new UserNotFoundException("id:"+id);
		
		return user.get().getPosts();
		
		
	}
	
	//POST /users 사용자 생성 
	@PostMapping("/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) { //메소드 인자를 웹 요청의 본문과 매핑
		User savedUser = userRepository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").
				buildAndExpand(savedUser.getId()).
				toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPostsForUser(@PathVariable int id,@Valid @RequestBody Post post ) {
		Optional<User> user =  userRepository.findById(id);
		if(user.isEmpty())
			throw new UserNotFoundException("id:"+id);
		post.setUser(user.get());
		Post savedPost = postrepository.save(post);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").
				buildAndExpand(savedPost.getId()).
				toUri();
		return ResponseEntity.created(location).build();
	
		
		
	}
}
