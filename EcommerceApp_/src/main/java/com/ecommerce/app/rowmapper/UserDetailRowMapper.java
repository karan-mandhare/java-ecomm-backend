package com.ecommerce.app.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ecommerce.app.entity.User;

public class UserDetailRowMapper implements RowMapper<User>  {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setEmail(rs.getString("email"));
		user.setId(rs.getLong("id"));
		user.setMobile(rs.getString("mobile"));
//		user.setPassword(rs.getString("password"));
		user.setUsername(rs.getString("username"));
		user.setProfilePhoto(rs.getBytes("profile_photo"));
		return user;
	}

}
