package com.task;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws UserDefinedException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to MIS Bank");
		System.out.println("Select 1 for new user registration");
		System.out.println("Select 2 for old user login");
		int x = sc.nextInt();
		Bank obj = new Bank();
		obj.makeObject(x);
		sc.close();
	}

}
