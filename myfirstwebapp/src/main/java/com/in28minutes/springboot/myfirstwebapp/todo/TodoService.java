package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class TodoService {
	private static List<Todo> todos = new ArrayList<>();
	
	private static int todosCount = 0;
	static {
		todos.add(new Todo(++todosCount,"in28minutes","Get AWS certified 1", LocalDate.now().plusYears(1), false));
		todos.add(new Todo(++todosCount,"in28minutes","Learn Devops 1", LocalDate.now().plusYears(2), false));
		todos.add(new Todo(++todosCount,"in28minutes","Learn Full stack 1", LocalDate.now().plusYears(3), false));
		
		
	}
	public List<Todo> findByUsername(String username) {
		Predicate<? super Todo> predicate = todo ->todo.getUsername().equalsIgnoreCase(username);
		return todos.stream().filter(predicate).toList();
	}
	
	public void addTodo(String username, String description, LocalDate targetDate, boolean done) {
		Todo todo = new Todo(++todosCount, username,description, targetDate, done);
		todos.add(todo);
		
	}
	
	public void deleteById(int id) {
		//todo.getId() == id 
		//람다함수 사용
		// todo -> todo.getId() == id;
		Predicate<? super Todo> predicate = todo ->todo.getId() == id;
		todos.removeIf(predicate);
	}
	
	//리스트 업데이트 아이디찾기
	public Todo findById(int id) {
		Predicate<? super Todo> predicate = todo ->todo.getId() == id;
		Todo todo = todos.stream().filter(predicate).findFirst().get();
		return todo;
	}
	
	// 리스트 업데이트 출력
	public void updateTodo(@Valid Todo todo) {
		deleteById(todo.getId()); //기존값을 삭제하고 새로운값추가
		todos.add(todo);
		
	}
}
