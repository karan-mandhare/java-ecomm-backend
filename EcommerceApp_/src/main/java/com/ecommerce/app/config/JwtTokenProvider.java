package com.ecommerce.app.config;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ecommerce.app.dao.UserDao;
import com.ecommerce.app.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

	private String jwtSecret = "KARANMANDHAREECOMMERCESPRINGBOOTWEBAPPLICATION";
	private long jwtExpirationDate = 18000000; // 1h = 3600s and 3600*1000 = 3600000 milliseconds

	@Autowired
	private UserDao userDao;
	
	public String generateToken(String email) {

		User user = userDao.getUserByEmail(email);
		
		Date currentDate = new Date();

		Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);

		return Jwts.builder()
				.setSubject(String.valueOf(user.getId())) 
				.claim("email", email)                         // Add custom claim for email
			    .claim("role", user.getRoles()) 
				.setIssuedAt(new Date())
				.setExpiration(expireDate)
				.signWith(key(), SignatureAlgorithm.HS256) // ✅					// Correct
				.compact();

	}

	private Key key() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
	}
	
	public String getUsername(String token){

        return Jwts.parserBuilder()
        		.setSigningKey(key()) 
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("email", String.class); 
    }
	
	public boolean validateToken(String token){
    	try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key()) 
                    .build()
                    .parseClaimsJws(token) 
                    .getBody();
            return true; // Token is valid
        } catch (Exception e) {
            return false; // Invalid token
        }

    }
	
}
