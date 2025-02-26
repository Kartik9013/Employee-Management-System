package com.ems.model;

import java.time.LocalDate;

public class Employee {
	private int id;
	private String name;
	private String fathersName;
	private LocalDate dob;
	private Double salary;
	private String designation;
	private String address;
	private String phone;
	private String email;
	private String highestEducation;
	private String password;
	
	public Employee() {
		
	}
	
	public Employee(int id, String name, String fathersName, LocalDate dob, Double salary, 
			String designation, String address, String phone, String email,
			String highestEducation, String password) {
		this.id = id;
		this.name = name;
		this.fathersName = fathersName;
		this.dob = dob;
		this.salary = salary;
		this.designation = designation;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.highestEducation = highestEducation;
		this.setPassword(password);
	}
	
	// To Add new Employee
	public Employee(String name, String fathersName, LocalDate dob, Double salary, 
			String designation, String address, String phone, String email,
			String highestEducation,String password) {
		this.name = name;
		this.fathersName = fathersName;
		this.dob = dob;
		this.salary = salary;
		this.designation = designation;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.highestEducation = highestEducation;
		this.setPassword(password);
	}
	
	public Employee(int id, String name, String fathersName, LocalDate dob, Double salary, 
			String designation, String address, String phone, String email,
			String highestEducation) {
		this.id = id;
		this.name = name;
		this.fathersName = fathersName;
		this.dob = dob;
		this.salary = salary;
		this.designation = designation;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.highestEducation = highestEducation;
	}

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

	public String getFathersName() {
		return fathersName;
	}

	public void setFathersName(String fathersName) {
		this.fathersName = fathersName;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHighestEducation() {
		return highestEducation;
	}

	public void setHighestEducation(String highestEducation) {
		this.highestEducation = highestEducation;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
