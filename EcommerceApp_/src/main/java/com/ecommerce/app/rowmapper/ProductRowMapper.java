package com.ecommerce.app.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ecommerce.app.entity.Category;
import com.ecommerce.app.entity.Product;

public class ProductRowMapper implements RowMapper<Product> {

	@Override
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
		Product product = new Product();
		product.setId(rs.getLong("id"));
		product.setName(rs.getString("name"));
		product.setBrandName(rs.getString("brandName"));
		product.setDesc(rs.getString("description"));
		product.setPrice(rs.getDouble("price"));
		product.setDiscount(rs.getFloat("discount"));
		product.setImage(rs.getBytes("image"));
		product.setRating(rs.getInt("rating"));
		product.setAvailable(rs.getBoolean("available"));

		Category category = new Category();
		category.setId(rs.getLong("id"));
		category.setName(rs.getString("name"));
		category.setImage(rs.getBytes("image"));

		product.setCategory(category);

		return product;
	}

}
