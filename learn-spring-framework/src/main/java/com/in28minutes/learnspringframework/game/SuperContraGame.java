package com.in28minutes.learnspringframework.game;

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
