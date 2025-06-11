package com.ecommerce.app.serviceimpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.app.apiresponse.CommonResponse;
import com.ecommerce.app.dao.UserDao;
import com.ecommerce.app.entity.User;
import com.ecommerce.app.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Override
	public CommonResponse<User> getUserById(Long id) {
		CommonResponse<User> res = new CommonResponse<>();
		User user = userDao.getUserById(id);
		if(user == null) {
			res.setSuccess(false);
			res.setMessage("Error while getting user by id");
			return res;
		}
		res.setSuccess(true);
		res.setData(user);
		return res;
	}

	@Override
	public CommonResponse<User> getUserByEmail(String email) {
		CommonResponse<User> res = new CommonResponse<>();
		User user = userDao.getUserByEmail(email);
		if(user == null) {
			res.setSuccess(false);
			res.setMessage("Error while getting user by email");
			return res;
		}
		res.setSuccess(true);
		res.setData(user);
		return res;
	}

}
