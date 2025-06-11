package com.ecommerce.app.config;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ecommerce.app.dao.UserDao;
import com.ecommerce.app.entity.Roles;
import com.ecommerce.app.entity.User;

@Component
public class CustomUserDetailsService  implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userDao.getUserByEmail(email);

		List<Roles> roles = userDao.getRoles(email);

		user.setRoles(roles);

//		List<SimpleGrantedAuthority> authorities = roles.stream()
//                .map(SimpleGrantedAuthority::new)
//                .toList();

		List<GrantedAuthority> authorities = roles.stream()
				.map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName())) // Assuming role names are like
																					// 'USER', 'ADMIN'
				.collect(Collectors.toList());

		return new org.springframework.security.core.userdetails.User(email, user.getPassword(), authorities);
	}
}
