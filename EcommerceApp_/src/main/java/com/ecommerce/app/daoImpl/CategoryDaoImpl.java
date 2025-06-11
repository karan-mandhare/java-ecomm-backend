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
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.app.dao.CategoryDao;
import com.ecommerce.app.entity.Category;
import com.ecommerce.app.rowmapper.CategoryRowMapper;

@Repository
public class CategoryDaoImpl implements CategoryDao {

	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public Integer createCategory(String name, MultipartFile imageFile) {
		try {
			byte[] imageArray = imageFile.getBytes();

			String query = "INSERT INTO category (name, image) values (:name, :image)";
			KeyHolder keyHolder = new GeneratedKeyHolder();
			MapSqlParameterSource paramSource = new MapSqlParameterSource();
			paramSource.addValue("name", name.toLowerCase());
			paramSource.addValue("image", imageArray);

			namedParameterJdbcTemplate.update(query, paramSource, keyHolder, new String[] { "id" });
			return keyHolder.getKey().intValue();
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public List<Category> getListOfCategory() {
		
		String query = "SELECT * from category";
		return jdbcTemplate.query(query, new CategoryRowMapper());
		
	}

	@Override
	public Integer deleteCategory(Long id) {
		try {
			String query = "DELETE FROM category where id=:id";
			String productQuery = "DELETE FROM products where category_id=:id";
			Map<String, Long> params = new HashMap<>();
			params.put("id", id);
			namedParameterJdbcTemplate.update(productQuery, params);
			namedParameterJdbcTemplate.update(query, params);
			return 1;
			
		} catch (Exception e) {
			return 0;
		}
	}

}
