package com.ecommerce.app.daoImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.ecommerce.app.apiresponse.CommonResponse;
import com.ecommerce.app.apiresponse.LoginResponse;
import com.ecommerce.app.config.JwtTokenProvider;
import com.ecommerce.app.dao.AuthDao;
import com.ecommerce.app.entity.Roles;
import com.ecommerce.app.entity.User;
import com.ecommerce.app.request.LoginUser;
import com.ecommerce.app.request.RegisterUser;
import com.ecommerce.app.rowmapper.RolesRowMapper;
import com.ecommerce.app.rowmapper.UserLoginRowMapper;
import com.ecommerce.app.rowmapper.UserRowMapper;

@Repository
public class AuthDaoImpl implements AuthDao {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	NamedParameterJdbcTemplate namedJdbcTemplate;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@Override
	public Integer createUser(RegisterUser user) {
		try {
			String getUserQuery = "SELECT email FROM users where email=:email";
			Map<String, Object> getUserParams = new HashMap<>();

			getUserParams.put("email", user.getEmail());

			String presentUser = null;

			try {
				presentUser = namedJdbcTemplate.queryForObject(getUserQuery, getUserParams, String.class);
			} catch (EmptyResultDataAccessException e) {
				// No user found, proceed with registration
			}

			if (presentUser != null && !presentUser.isEmpty()) {
				return 1; // Email already exists
			}

			String query = "INSERT INTO users (email, username, mobile, password) VALUES(:email, :username, :mobile, :password)";
			KeyHolder keyHolder = new GeneratedKeyHolder();
			SqlParameterSource params = new MapSqlParameterSource().addValue("email", user.getEmail())
					.addValue("username", user.getUsername()).addValue("mobile", user.getMobile())
					.addValue("password", passwordEncoder.encode(user.getPassword()));
			namedJdbcTemplate.update(query, params, keyHolder, new String[] { "id" });

			Long userId = keyHolder.getKey().longValue();

			String getRoleIdQuery = "SELECT id FROM roles WHERE name=:name";

			for (Roles role : user.getRoles()) {
				SqlParameterSource roleParam = new MapSqlParameterSource().addValue("name", role.getName());

				Long roleId = namedJdbcTemplate.queryForObject(getRoleIdQuery, roleParam, Long.class);

				String roleQuery = "INSERT INTO user_roles (user_id, role_id) VALUES(:user_id, :role_id)";
				SqlParameterSource userRoleParams = new MapSqlParameterSource().addValue("user_id", userId)
						.addValue("role_id", roleId);
				namedJdbcTemplate.update(roleQuery, userRoleParams);
			}
			return 2;
		} catch (Exception e) {
			return 0;
		}

	}

	@Override
	public CommonResponse<LoginResponse> loginUser(String email, String password) {
		CommonResponse<LoginResponse> res = new CommonResponse<>();
		try {
			if (email.length() == 0 || email == null || password == null || password.length() == 0) {
				res.setSuccess(false);
				res.setMessage("Email and password are required");
				return res;
			}
			String query = "SELECT * from users where email=:email";
			Map<String, String> params = new HashMap<>();

			params.put("email", email);

			User user = null;
			try {
			    user = namedJdbcTemplate.queryForObject(query, params, new UserRowMapper());
			} catch (EmptyResultDataAccessException e) {
			    res.setSuccess(false);
			    res.setMessage("User not found");
			    return res;
			}
			
			
			if (!email.equals(user.getEmail())) {
				res.setSuccess(false);
				res.setMessage("Email does not match");
				return res;
			}

			if (!passwordEncoder.matches(password, user.getPassword())) {
				res.setSuccess(false);
				res.setMessage("Password does not match");
				return res;
			}

			List<String> roleNames = new ArrayList<>();
			if (user != null) {
				String roleQuery = "select r.id, r.name from roles r " + 
									"join user_roles ur on ur.role_id=r.id "
						+ "where ur.user_id = :user_id";

				Map<String, Object> roleParams = new HashMap<>();
				roleParams.put("user_id", user.getId());

				List<Roles> roles = namedJdbcTemplate.query(roleQuery, roleParams, new RolesRowMapper());
				user.setRoles(roles);
				roleNames = roles.stream().map(Roles::getName).toList();
			}
			String token = jwtTokenProvider.generateToken(email);
			
			LoginResponse loginResponse = new LoginResponse(token, roleNames);

			res.setSuccess(true);
			res.setMessage("User login successfully");
			res.setData(loginResponse);
			return res;

		} catch (Exception e) {
			res.setSuccess(false);
			res.setMessage(e.getMessage());
			return res;
		}

	}

}
