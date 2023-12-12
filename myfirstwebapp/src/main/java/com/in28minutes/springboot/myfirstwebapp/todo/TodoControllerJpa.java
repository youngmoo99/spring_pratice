package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("name") //세션에 값 저장
public class TodoControllerJpa {
	public TodoControllerJpa(TodoService todoService, TodoRepository todoRepository) {
		super();
		this.todoService = todoService;
		this.todoRepository = todoRepository;
	}
	
	private TodoService todoService;
	
	private TodoRepository todoRepository;
	
	
	
	//리스트 출력 
	@RequestMapping("list-todos")
	public String listAllTodos(ModelMap model) {
		String username= getLoggedInUsername(model);
		List<Todo> todos = todoRepository.findByUsername(username);
		model.addAttribute("todos",todos);
		return "listTodos";
		
	}

	private String getLoggedInUsername(ModelMap model) {
		 org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 
		 return authentication.getName();
	
	}
	
	//리스트 추가 GET
	@RequestMapping(value="add-todo", method = RequestMethod.GET) // url주소이름 add-todo
	public String showNewTodoPage(ModelMap model) {
		String username= getLoggedInUsername(model);
		Todo todo = new Todo(0,username,"",LocalDate.now().plusYears(1),false);
		model.put("todo",todo);
		return "todo"; //jsp파일이름
		
	}
	
	//리스트 추가 POST
	@RequestMapping(value="add-todo" , method = RequestMethod.POST)
	public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult result) {	
		if(result.hasErrors()) {
			return "todo";
		}
		String username= getLoggedInUsername(model);
		todo.setUsername(username);
		todoRepository.save(todo);
		
		//todoService.addTodo(username, todo.getDescription(), todo.getTargetDate(), todo.isDone());
		return "redirect:list-todos"; //list-todos로 돌아감
		
	}
	
	//리스트 삭제
		@RequestMapping("delete-todo")
		public String deleteTodo(@RequestParam int id) {
			//todo 삭제
			todoRepository.deleteById(id);
	
			//todoService.deleteById(id);
			return "redirect:list-todos";
				
		}
		
		//리스트 업데이트 GET
		@RequestMapping(value= "update-todo" , method =RequestMethod.GET)
		public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
			Todo todo = todoRepository.findById(id).get();
			
			//Todo todo = todoService.findById(id);
			model.addAttribute("todo",todo);
			return "todo";
			
		}
		
		//리스트 업데이트 POST
		@RequestMapping(value="update-todo" , method = RequestMethod.POST)
		public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {	
			if(result.hasErrors()) {
				return "todo";
			}
			
			String username= getLoggedInUsername(model);
			todo.setUsername(username);
			todoRepository.save(todo);
			
			//todoService.updateTodo(todo);
			return "redirect:list-todos"; //list-todos로 돌아감
			
		}
	
	
	
}
