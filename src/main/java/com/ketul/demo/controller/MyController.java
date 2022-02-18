package com.ketul.demo.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ketul.demo.module.User;

@RestController
public class MyController {

	@Autowired
	private ServiceImp serviceImp;

	@GetMapping("/users")
	public Set<User> getUsers() {
		
		return serviceImp.getUsers();
	}
	
	@GetMapping("/usersHttpClient")
	public Set<User> postUsers() {
		return serviceImp.getUsersHttpClient();
	}
}
