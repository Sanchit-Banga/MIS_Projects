package com.task;

import java.net.Socket;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.PseudoColumnUsage;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Scanner;


public class Bank {
	
	private int id;
	private String name;
	private int pwd;
	private int money;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPwd() {
		return pwd;
	}

	public void setPwd(int pwd) {
		this.pwd = pwd;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	
	
	@Override
	public String toString() {
		return "Bank [id=" + id + ", name=" + name + ", pwd=" + pwd + ", money=" + money + "]";
	}

	public void assignValues(int id, int pswd,String name, int money) {
		setId(id);
		setMoney(money);
		setName(name);
		setPwd(pswd);
	}
	
	public void makeObject(int token)throws UserDefinedException {
		Scanner sc = new Scanner(System.in);
		if(token == 1) {
			System.out.println("Enter new user name = ");
			String str = sc.next();
			System.out.println("Enter new user id = ");
			int id = sc.nextInt();
			System.out.println("Enter new user password = ");
			int pwd = sc.nextInt();
			System.out.println("Enter how many money u want to deposit");
			int m = sc.nextInt();
			while(m<0) {
				System.out.println("Enter the correct amount > 0");
				m=sc.nextInt();
			}
			assignValues(id, pwd,str,m);
			newUserAdd();
		}
		else {
			System.out.println("Welcome back again");
			System.out.println("Enter the Account id = ");
			int id = sc.nextInt();
			System.out.println("Enter the pwd = ");
			int pwd = sc.nextInt();
			int i=3;
			boolean check = oldUserVerify(id, pwd);
			while(i>0 && !check) {
				System.out.println("Please re-enter correct id and password = ");
				System.out.println("Enter the Account id = ");
				id = sc.nextInt();
				System.out.println("Enter the pwd = ");
				pwd = sc.nextInt();
				check = oldUserVerify(id, pwd);
				i--;
			}
			if(check) {
				allFuncs();
			}
			else {
				System.out.println(":) pls try again later");
				throw new UserDefinedException("Your given password is incorrect");
			}
		}
		
		
		sc.close();
	}

	public void newUserAdd() {
		try {
			Connection c = JDBCConnection.getConnection();
			PreparedStatement ps = c.prepareStatement("select * from userinfo where id=? or password=?");
			ps.setInt(1, getId());
			ps.setInt(2, getPwd());
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				ps.close();
				c.close();
				throw new UserDefinedException("User with this id and passoword already exits please select another one");
			}
			else {
				ps = c.prepareStatement("insert into userinfo values(?,?,?,?,?)");
				ps.setInt(1, getId());
				ps.setString(2, getName());
				ps.setInt(3, getMoney());
				ps.setInt(4, getPwd());
				ps.setString(5, "");
				int i = ps.executeUpdate();
				if(i==1) {
					System.out.println("New User added");
				}
				else {
					System.out.println("User Registration denied pls try again !!");
				}
				ps.close();
				c.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	public boolean oldUserVerify(int id, int pwd) {
		try {
			Connection c = JDBCConnection.getConnection();
			PreparedStatement ps = c.prepareStatement("select * from userinfo where id=? and password=?");
			ps.setInt(1, id);
			ps.setInt(2,pwd);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				setMoney(rs.getInt("money"));
				setPwd(pwd);
				setId(id);
				setName(rs.getString("name"));
				return true;
			}
			else {
				return false;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;	
	}
	
	public void printDetails() {
		try {
			Connection c = JDBCConnection.getConnection();
			PreparedStatement ps = c.prepareStatement("select * from userinfo where id=? and password=?");
			ps.setInt(1, getId());
			ps.setInt(2,getPwd());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println("id = " + rs.getInt("id"));
				System.out.println("name = " + rs.getString("name"));
				break;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void withdraw() {
		Scanner sc = new Scanner(System.in);
		Date myDate = new Date(System.currentTimeMillis());
		SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");  
		try {
			Connection c = JDBCConnection.getConnection();
			PreparedStatement ps = c.prepareStatement("select * from userinfo where id=? and password=?");
			ps.setInt(1, getId());
			ps.setInt(2,getPwd());
			ResultSet rs = ps.executeQuery();
			System.out.println("Enter the amount to be withdrawn = ");
			int wmny = sc.nextInt();
			int mny=0;
			String str = "";
			while(rs.next()) {
				mny = rs.getInt("money");
				str = rs.getString("statement");
				break;
			}
			if(mny-wmny<0) {
				System.out.println("Insuficient balance");
			}
			else {
				int x = mny-wmny;
				str = str + "\nAmount withdrawn of rupees " + wmny + " at " + formatter.format(myDate);
				ps = c.prepareStatement("update userinfo set money=?,statement=? where id=? and password=?");
				ps.setInt(1, x);
				ps.setString(2, str);
				ps.setInt(3, getId());
				ps.setInt(4, getPwd());
				ps.executeUpdate();
				System.out.println("Amount withdrawn of rupees = " + wmny);
			}
			ps.close();
			c.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void insert() {
		Scanner sc = new Scanner(System.in);
		Date myDate = new Date(System.currentTimeMillis());
		SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss"); 
		try {
			Connection c = JDBCConnection.getConnection();
			PreparedStatement ps = c.prepareStatement("select * from userinfo where id=? and password=?");
			ps.setInt(1, getId());
			ps.setInt(2,getPwd());
			ResultSet rs = ps.executeQuery();
			System.out.println("Enter the amount to be inserted = ");
			int imny = sc.nextInt();
			int mny=0;
			String str = "";
			while(rs.next()) {
				mny = rs.getInt("money");
				str = rs.getString("statement");
				break;
			}
			int x = mny+imny;
			str = str + "\nAmount inserted of rupees " + imny + " at " + formatter.format(myDate);
			ps = c.prepareStatement("update userinfo set money=?,statement=? where id=? and password=?");
			ps.setInt(1, x);
			ps.setString(2, str);
			ps.setInt(3, getId());
			ps.setInt(4, getPwd());
			ps.executeUpdate();
			System.out.println("Amount inserted of rupees = " + imny);
			ps.close();
			c.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
//		sc.close();
	}
	public void showAllTransactions() {
		Scanner sc = new Scanner(System.in);
		try {
			Connection c = JDBCConnection.getConnection();
			PreparedStatement ps = c.prepareStatement("select * from userinfo where id=? and password=?");
			ps.setInt(1, getId());
			ps.setInt(2,getPwd());
			ResultSet rs = ps.executeQuery();
			System.out.print("Your Transaction history is = ");
			while(rs.next()) {
				System.out.println(rs.getString("statement"));
				System.out.println("Current balance = " + rs.getString("money"));
				break;
			}
			ps.close();
			c.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void allFuncs() {
		Scanner sc = new Scanner(System.in);
		int x;
		do {
			System.out.println("Select 1 for show details of your account");
			System.out.println("Select 2 to add money to your account");
			System.out.println("Select 3 to withdraw money from your account");
			System.out.println("Select 4 to show transaction history");
			System.out.println("Select 5 to quit");
			x = sc.nextInt();
			sc.nextLine();
//			if (x==5) {
//				break;
//			}
			switch (x) {
			case 1:{
				printDetails();
				break;
			}
			case 2:{
				insert();
				break;
			}
			case 3:{
				withdraw();
				break;
			}
			case 4:{
				showAllTransactions();
				break;
			}
			case 5:{
				System.out.println("Thank u visit us again");
				break;
			}
			default:
				System.out.println("Pls select valid option");
			}
		}
		while(x!=5);
	}
	
	
}
