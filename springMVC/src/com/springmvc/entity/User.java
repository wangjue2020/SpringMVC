package com.springmvc.entity;

public class User {
	private String username;
	private String password;
	private String gender;
	private int age;
	private Address address;
	public User() {
		super();
	}
	public User(String username, String password, String gender, int age, Address address) {
		super();
		this.username = username;
		this.password = password;
		this.gender = gender;
		this.age = age;
		this.address = address;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", gender=" + gender + ", age=" + age
				+ ", address=" + address + "]";
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
}
