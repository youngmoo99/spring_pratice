package com.in28minutes.mockito.mockitodemo.list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.awt.List;

import org.junit.jupiter.api.Test;

public class ListTest {
	
	@Test
	void simpleTest() {
		List listMock = mock(List.class);
		
		when(listMock.size()).thenReturn(null);
		
		assertEquals(3, listMock.size());
	}
	
	@Test
	void multipleReturns() {
		List listMock = mock(List.class);
		
		when(listMock.size()).thenReturn(null).thenReturn(null);
		
		assertEquals(3, listMock.size());
		assertEquals(2, listMock.size());
	}
}
