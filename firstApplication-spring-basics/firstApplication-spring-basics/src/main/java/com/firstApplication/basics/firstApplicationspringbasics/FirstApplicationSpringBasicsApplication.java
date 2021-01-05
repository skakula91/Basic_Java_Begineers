package com.firstApplication.basics.firstApplicationspringbasics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class FirstApplicationSpringBasicsApplication {

	public static void main(String[] args) {
		//BinarySearchImplement binaryImpl = new BinarySearchImplement(new QuickSort());
		//int result = binaryImpl.BinarySearch(new int[]{1,2,3,4,5,6}, 4);

		// Application context has all the information related to beans
		ApplicationContext context = SpringApplication.run(FirstApplicationSpringBasicsApplication.class, args);
		BinarySearchImplement binarySearch = context.getBean(BinarySearchImplement.class);
		int result = binarySearch.BinarySearch(new int[]{1,2,3,4,5,6}, 4);
		System.out.println(result);
	}
}
