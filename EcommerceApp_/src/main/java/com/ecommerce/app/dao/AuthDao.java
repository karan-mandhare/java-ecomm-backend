package com.ecommerce.app.dao;

import com.ecommerce.app.apiresponse.CommonResponse;
import com.ecommerce.app.apiresponse.LoginResponse;
import com.ecommerce.app.request.RegisterUser;

public interface AuthDao {

	public Integer createUser(RegisterUser user);
	public CommonResponse<LoginResponse> loginUser(String email, String password);
}
