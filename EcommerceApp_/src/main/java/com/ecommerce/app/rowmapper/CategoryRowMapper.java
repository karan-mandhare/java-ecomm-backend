package com.ecommerce.app.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ecommerce.app.entity.Category;

public class CategoryRowMapper implements RowMapper<Category> {

	@Override
	public Category mapRow(ResultSet rs, int rowNum) throws SQLException {

		Category category = new Category();
		category.setId(rs.getLong("id"));
		category.setName(rs.getString("name"));
		category.setImage(rs.getBytes("image"));
		return category;

	}

}
