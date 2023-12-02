package com.in28minutes.learnspringframework.examples.c1;

import org.springframework.stereotype.Component;

@Component
public class MySqlDataService2 implements DataService{

	@Override
	public int[] retrieveData() {
		
		return new int[] {1,2,3,4,5};
	}

}
