package com.ecommerce.app.daoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.ecommerce.app.apiresponse.CommonResponse;
import com.ecommerce.app.dao.ProductDao;
import com.ecommerce.app.entity.Product;
import com.ecommerce.app.request.ProductRequest;
import com.ecommerce.app.rowmapper.ProductRowMapper;

@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public Integer addProduct(Product request, Long categoryId) {

		try {
			String query = "INSERT INTO products (name, brandName, description, price, discount, category_id, image, rating, available) "
					+ "VALUES (:name, :brandName, :description, :price, :discount, :category_id, :image, :rating, :available)";

			MapSqlParameterSource params = new MapSqlParameterSource().addValue("name", request.getName())
					.addValue("brandName", request.getBrandName()).addValue("description", request.getDesc())
					.addValue("price", request.getPrice()).addValue("discount", request.getDiscount())
					.addValue("category_id", categoryId).addValue("image", request.getImage())
					.addValue("rating", request.getRating()).addValue("available", request.getAvailable());

			KeyHolder keyHolder = new GeneratedKeyHolder();

			namedParameterJdbcTemplate.update(query, params, keyHolder, new String[] { "id" });
			return keyHolder.getKey().intValue();
		} catch (Exception e) {
			return 0;
		}

	}

	@Override
	public List<Product> getProducts() {
		try {
			String query = "SELECT p.id, p.name, p.brandName, p.description, p.price, p.discount, p.image, p.rating, p.available,"+
						   " c.id, c.name, c.image from products p left join category c on c.id = p.id";
			return jdbcTemplate.query(query, new ProductRowMapper());
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Product> getProductsByCategory(Long id) {
		
		try {
			String query = "SELECT * FROM products where category_id=:category_id";
			Map<String, Long> params = new HashMap<>();
			params.put("category_id", id);
			return namedParameterJdbcTemplate.query(query, params, new ProductRowMapper());
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Integer deleteProductById(Long id) {
		try {
			String query = "DELETE FROM products where id=:id";
			Map<String, Long> params = new HashMap<>();
			params.put("id", id);
			namedParameterJdbcTemplate.update(query, params);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

}
