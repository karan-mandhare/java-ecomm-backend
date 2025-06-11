package com.ecommerce.app.daoImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ecommerce.app.dao.WishListDao;
import com.ecommerce.app.entity.Product;
import com.ecommerce.app.rowmapper.ProductRowMapper;

@Repository
public class WishListDaoImpl implements WishListDao {

	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public Integer addToWishlist(Long userId, Long productId) {
		try {
			String query = "INSERT INTO wishlist (user_id, product_id) values(:userId, :productId)";
			Map<String, Object> params = new HashMap<>();
			params.put("userId", userId);
			params.put("productId", productId);

			namedParameterJdbcTemplate.update(query, params);

			return 1;
		} catch (Exception e) {
			return 0;
		}

	}

	@Override
	public List<Product> getWishlistByUserId(Long userId) {
		List<Product> list = new ArrayList<>();
		try {
			String query = "SELECT p.* from wishlist w " + "join products p on p.id=w.product_id "
					+ "where w.user_id = :userId";
			Map<String, Object> params = new HashMap<>();
			params.put("userId", userId);
			list = namedParameterJdbcTemplate.query(query, params, new ProductRowMapper());
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			return list;
		}
	}

	@Override
	public Integer removeFromWishlist(Long userId, Long productId) {
		try {
			String query = "delete from wishlist where user_id=:userId and product_id=:productId";
			Map<String, Object> params = new HashMap<>();
			params.put("userId", userId);
			params.put("productId", productId);
			namedParameterJdbcTemplate.update(query, params);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}

	}

}
