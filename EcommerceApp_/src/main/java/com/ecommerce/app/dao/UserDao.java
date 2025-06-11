package com.ecommerce.app.dao;

import java.util.List;

import com.ecommerce.app.entity.Roles;
import com.ecommerce.app.entity.User;

public interface UserDao {
	public User getUserByEmail(String email);
	public List<Roles> getRoles(String email);
	public User getUserById(Long id);
}
