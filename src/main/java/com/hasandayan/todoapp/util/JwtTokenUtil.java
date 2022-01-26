package com.hasandayan.todoapp.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {

	private static final long JWT_TOKEN_VALIDITY = 2 * 60 * 60 * 1000L;

	@Value("jwt.secret.key")
	private String secretKey;

	private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
	}

	public String retrieveUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date retrieveExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	private boolean isTokenExpired(String token) {
		final Date expiration = retrieveExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public String generateTokenForUserDetails(String username) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, username);
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {

		return Jwts.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
				.signWith(SignatureAlgorithm.HS512, secretKey)
				.compact();
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		final String username = retrieveUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

}
