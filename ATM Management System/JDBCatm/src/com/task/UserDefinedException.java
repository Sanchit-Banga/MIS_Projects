package com.task;

public class UserDefinedException extends Exception {
	public UserDefinedException(String message) {
		super(message);
		System.out.println(message);
	}

}
