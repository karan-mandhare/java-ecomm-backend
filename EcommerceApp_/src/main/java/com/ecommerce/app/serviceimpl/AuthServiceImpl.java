package com.ecommerce.app.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.app.apiresponse.CommonResponse;
import com.ecommerce.app.apiresponse.LoginResponse;
import com.ecommerce.app.dao.AuthDao;
import com.ecommerce.app.request.LoginUser;
import com.ecommerce.app.request.RegisterUser;
import com.ecommerce.app.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private AuthDao authDao;

	@Override
	public CommonResponse<String> createUser(RegisterUser user) {
		CommonResponse<String> res = new CommonResponse<>();
		try {
			int count = authDao.createUser(user);
			if(count == 0) {
				res.setMessage("Error occur while creating user");
				res.setSuccess(false);
				return res;
			}
			else if(count == 1) {
			res.setSuccess(false);
			res.setMessage("User already exists.");
			return res;
			}
			
			res.setSuccess(true);
			res.setMessage("User created successfully.");
			return res;
			
		} catch (Exception e) {
			res.setMessage(e.getMessage());
			res.setSuccess(false);
			return res;
		}
	}

	@Override
	public CommonResponse<LoginResponse> loginUser(LoginUser user) {
		CommonResponse<LoginResponse> response = new CommonResponse<>();
		
		try {
			 return authDao.loginUser(user.getEmail(), user.getPassword());
		} catch (Exception e) {
			response.setSuccess(false);
			response.setMessage(e.getMessage());
			return response;
		}
		
		
	}

}
