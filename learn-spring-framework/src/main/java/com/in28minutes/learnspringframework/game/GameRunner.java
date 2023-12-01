package com.in28minutes.learnspringframework.game;

public class GameRunner {
	//강한결합 
	// SuperContraGame을 하고싶은경우 코드 수정을 해줘야함 
	//MarioGame game;
//	public GameRunner(MarioGame game) {
//		this.game = game;
//	}
	
	//느슨한 결합을 위해 인터페이스를 생성해야함
	private GamingConsole game;
	
	public GameRunner(GamingConsole game) {
		this.game = game;
	}
	
		
	public void run() {
		System.out.println("Running game: "+ game);
		
		game.up();
		game.down();
		game.left();
		game.right();
	}
}
