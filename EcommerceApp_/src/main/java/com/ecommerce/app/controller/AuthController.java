package com.ecommerce.app.controller;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.app.apiresponse.CommonResponse;
import com.ecommerce.app.apiresponse.LoginResponse;
import com.ecommerce.app.request.LoginUser;
import com.ecommerce.app.request.RegisterUser;
import com.ecommerce.app.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	AuthService authService;

	@PostMapping("/create-user")
	public ResponseEntity<CommonResponse<String>> createUser(@RequestBody RegisterUser user) {
		return ResponseEntity.ok(authService.createUser(user));
	}
	
	@PostMapping("/login")
	public ResponseEntity<CommonResponse<LoginResponse>> loginUser(@RequestBody LoginUser user){
		return ResponseEntity.ok(authService.loginUser(user));
	}

	@GetMapping("/check")
	public ResponseEntity<String> checkApp(){
		return ResponseEntity.ok("Applicaiton working");
	}
}