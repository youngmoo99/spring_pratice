package com.in28minutes.mockito.mockitodemo.business;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SomeBusinessImplMockTest {
//mock을 이용한 단위테스트
	@Mock
	private DataService dataServiceMock;
	
	@InjectMocks
	private SomeBusinessImpl businessImpl;
	
	@Test
	void findTheGreatestFromAllData_vasicScenario() {

		//dataServiceMock.retrieveAllData() => new int[] {25,15,5};
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {25,15,5});
		assertEquals(25,  businessImpl.findTheGreatestFromAllData());
	}
	
	@Test
	void findTheGreatestFromAllData_OneValue() {
		
		//dataServiceMock.retrieveAllData() => new int[] {25,15,5};
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {35});	
		assertEquals(35,  businessImpl.findTheGreatestFromAllData());
	}
	
	@Test
	void findTheGreatestFromAllData_EmptyArray() {
		
		//dataServiceMock.retrieveAllData() => new int[] {25,15,5};
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {});	
		assertEquals(Integer.MIN_VALUE,  businessImpl.findTheGreatestFromAllData());
	}
}

