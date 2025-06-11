package com.ecommerce.app.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ecommerce.app.request.LoginUser;

public class UserLoginRowMapper  implements RowMapper<LoginUser> {

	@Override
	public LoginUser mapRow(ResultSet rs, int rowNum) throws SQLException {
		LoginUser loginUser = new LoginUser();
		loginUser.setEmail(rs.getString("email"));
		loginUser.setPassword(rs.getString("password"));
		return loginUser;
	}

}
