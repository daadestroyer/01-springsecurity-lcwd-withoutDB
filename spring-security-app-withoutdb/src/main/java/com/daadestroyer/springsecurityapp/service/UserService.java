package com.daadestroyer.springsecurityapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.daadestroyer.springsecurityapp.entity.User;

@Service
public class UserService {

	List<User> userList = new ArrayList<>();

	public UserService() {
		this.userList.add(new User("pulkit", "1234", "pulkit@gmail.com"));
		this.userList.add(new User("ansh", "123", "ansh@gmail.com"));
		this.userList.add(new User("shubhanshu", "12345", "shubhanshu@gmail.com"));
	}
	
	// get all user
	public List<User> getAllUser(){
		return this.userList;
	}
	
	// get single user by email
	public User getSingleUserByEmail(String email) {
		return this.userList.stream().filter(user->user.getEmail().equals(email)).findAny().orElse(null);
	}
	
	// add new user
	public String addUser(User user) {
		this.userList.add(user);
		return "User Added";
	}

}
