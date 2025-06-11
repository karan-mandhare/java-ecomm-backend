package com.ecommerce.app.service;

import com.ecommerce.app.apiresponse.CommonResponse;
import com.ecommerce.app.entity.User;

public interface UserService {

	public CommonResponse<User> getUserById(Long id);
	public CommonResponse<User> getUserByEmail(String email);
	
}
