package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

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
public class TodoController {
	public TodoController(TodoService todoService) {
		super();
		this.todoService = todoService;
	}
	
	private TodoService todoService;
	
	
	//리스트 출력 
	@RequestMapping("list-todos")
	public String listAllTodos(ModelMap model) {
		List<Todo> todos = todoService.findByUsername("in28minutes");
		model.addAttribute("todos",todos);
		return "listTodos";
		
	}
	
	//리스트 추가 GET
	@RequestMapping(value="add-todo", method = RequestMethod.GET) // url주소이름 add-todo
	public String showNewTodoPage(ModelMap model) {
		String username= (String)model.get("name");
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
		String username= (String)model.get("name");
		todoService.addTodo(username, todo.getDescription(), todo.getTargetDate(), false);
		return "redirect:list-todos"; //list-todos로 돌아감
		
	}
	
	//리스트 삭제
		@RequestMapping("delete-todo")
		public String deleteTodo(@RequestParam int id) {
			//todo 삭제
			todoService.deleteById(id);
			return "redirect:list-todos";
				
		}
		
		//리스트 업데이트 GET
		@RequestMapping(value= "update-todo" , method =RequestMethod.GET)
		public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
			Todo todo = todoService.findById(id);
			model.addAttribute("todo",todo);
			return "todo";
			
		}
		
		//리스트 업데이트 POST
		@RequestMapping(value="update-todo" , method = RequestMethod.POST)
		public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {	
			if(result.hasErrors()) {
				return "todo";
			}
			
			String username= (String)model.get("name");
			todo.setUsername(username);
			todoService.updateTodo(todo);
			return "redirect:list-todos"; //list-todos로 돌아감
			
		}
	
	
	
}
