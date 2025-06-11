package com.ecommerce.app.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ecommerce.app.entity.Roles;

public class RolesRowMapper implements RowMapper<Roles> {

	@Override
	public Roles mapRow(ResultSet rs, int rowNum) throws SQLException {
		Roles role = new Roles();
		role.setId(rs.getLong("id"));
		role.setName(rs.getString("name"));
		return role;
	}

}
