package com.in28minutes.learnspringframework;

import com.in28minutes.learnspringframework.game.GameRunner;
import com.in28minutes.learnspringframework.game.MarioGame;
import com.in28minutes.learnspringframework.game.PacmanGame;
import com.in28minutes.learnspringframework.game.SuperContraGame;

public class App01GamingBasicJava {

	public static void main(String[] args) {
		//강한 결합
//		var superContraGame = new SuperContraGame();	
//		var marioGame = new MarioGame();
		//var gameRunner = new GameRunner(marioGame);
		
		//var gameRunner = new GameRunner(superContraGame); //game runner 클래스를 보면 마리오 게임과 강하게 결합되어 있어 오류가뜸 
		//gameRunner.run();
		
		//느슨한 결합 활용 인터페이스
		
		//var game = new MarioGame();	
		//var game = new SuperContraGame();		
		var game = new PacmanGame(); // 1.오브젝트 생성 
		var gameRunner = new GameRunner(game); // 2. 종속성 연결 , game에 의존성 주입
		gameRunner.run(); // 실행
		
		

	}

}
