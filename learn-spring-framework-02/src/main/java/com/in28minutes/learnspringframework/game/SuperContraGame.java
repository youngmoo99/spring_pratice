package com.in28minutes.learnspringframework.game;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Qualifier("SuperContraGameQualifier") // 런처에서 해당 Qualifier를 특정지정하면 해당 빈 실행가능
//GameRunner에서 설정
public class SuperContraGame implements GamingConsole{
	public void up() {
		System.out.println("up");
	}
	public void down() {
		System.out.println("sit down");
	}
	public void left() {
		System.out.println("GO back");
	}
	public void right() {
		System.out.println("Shoot a bullet");
	}
}
