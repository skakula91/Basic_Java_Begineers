package com.test.web.application.first.springbootfirstwebapplication;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SpringBootFirstWebApplicationTests {

	@Test
	void contextLoads() {
		int[] arr1 = new int[]{1,2,3};
		int[] arr2 = new int[]{1,2,3};
		assertArrayEquals(arr1,arr2);
	}

}
