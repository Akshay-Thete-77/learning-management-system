package com.te.lms.utils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {

	private String secret = "some secret key";

	public String generateToken(String subject) {
		JwtBuilder builder = Jwts.builder();
		builder.setSubject(subject);
		builder.setIssuer("VIJENDRA SINGH");
		builder.setIssuedAt(new Date(System.currentTimeMillis()));
		builder.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(15)));
		builder.signWith(SignatureAlgorithm.HS256, secret.getBytes());
		return builder.compact();
	}

	public Claims getClaims(String token) {
		JwtParser parser = Jwts.parser();
		parser.setSigningKey(secret.getBytes());
		Jws<Claims> parseClaimsJws = parser.parseClaimsJws(token);
		return parseClaimsJws.getBody();
	}

	public Date getExpirationDate(String token) {
		return getClaims(token).getExpiration();
	}

	public String getUsername(String token) {
		return getClaims(token).getSubject();
	}

	public boolean isTokenExpired(String token) {
		Date expirationDate = getExpirationDate(token);
		return expirationDate.before(new Date(System.currentTimeMillis()));
	}

	public boolean validateToken(String token, String username) {
		String usernameFromToken = getUsername(token);
		return username.equals(usernameFromToken) && !isTokenExpired(token);
	}
}
