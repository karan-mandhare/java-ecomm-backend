package com.ecommerce.app.service;

import com.ecommerce.app.apiresponse.CommonResponse;
import com.ecommerce.app.apiresponse.LoginResponse;
import com.ecommerce.app.request.LoginUser;
import com.ecommerce.app.request.RegisterUser;

public interface AuthService {
	public CommonResponse<String> createUser(RegisterUser user);
	public CommonResponse<LoginResponse> loginUser(LoginUser user);
}
