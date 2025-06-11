package com.ecommerce.app.daoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ecommerce.app.dao.UserDao;
import com.ecommerce.app.entity.Roles;
import com.ecommerce.app.entity.User;
import com.ecommerce.app.rowmapper.RolesRowMapper;
import com.ecommerce.app.rowmapper.UserDetailRowMapper;
import com.ecommerce.app.rowmapper.UserRowMapper;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public User getUserByEmail(String email) {
		try {

			String query = "select * from users where email=:email";

			Map<String, Object> params = new HashMap<>();
			params.put("email", email);

			try {
				User user = namedParameterJdbcTemplate.queryForObject(query, params, new UserRowMapper());
				return user;
			} catch (Exception e) {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Roles> getRoles(String email) {
		String query = "SELECT r.* from roles r " + 
				       "join user_roles ur on ur.role_id = r.id "+ 
				       "join users u on u.id=ur.user_id " + 
				       "where u.email=:email";

		Map<String, String> params = new HashMap<>();
		params.put("email", email);
		return namedParameterJdbcTemplate.query(query, params, new RolesRowMapper());

	}

	@Override
	public User getUserById(Long id) {
		try {
			String query = "select * from users where id=:id";
			Map<String, Long> params = new HashMap<>();
			params.put("id", id);

			return namedParameterJdbcTemplate.queryForObject(query, params, new UserDetailRowMapper());
		} catch (Exception e) {
			return null;
		}
	}

}
