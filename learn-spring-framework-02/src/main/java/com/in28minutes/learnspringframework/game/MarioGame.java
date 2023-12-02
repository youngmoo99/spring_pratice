package com.in28minutes.learnspringframework.game;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary//컴포넌트 여러개일때 사용 해당클래스에 빈 우선권
public class MarioGame implements GamingConsole{
	public void up() {
		System.out.println("Mario Jump");
	}
	public void down() {
		System.out.println("Mario Go into a hole");
	}
	public void left() {
		System.out.println("Mario Go back");
	}
	public void right() {
		System.out.println("Mario Accelerate");
	}
}
