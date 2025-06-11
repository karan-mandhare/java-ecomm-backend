package com.ecommerce.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.app.apiresponse.CommonResponse;
import com.ecommerce.app.entity.User;
import com.ecommerce.app.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/get-user/{id}")
	public ResponseEntity<CommonResponse<User>> getUserById(@PathVariable Long id){
		return ResponseEntity.ok(userService.getUserById(id));
	}
	
	@GetMapping("/get-user/{email}")
	public ResponseEntity<CommonResponse<User>> getUserByEmail(@PathVariable String email){
		return ResponseEntity.ok(userService.getUserByEmail(email));
	}

}
