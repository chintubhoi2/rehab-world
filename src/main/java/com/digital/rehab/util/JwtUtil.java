package com.digital.rehab.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	@Value("${app.secret}")
	private String secret;
	
	public boolean validateToken(String token,String username) {
		String tokenUserName = getUserName(token);
		return(username.equals(tokenUserName) && !isTokenExp(tokenUserName));
	}
	
	public boolean isTokenExp(String token) {
		Date expDate = getExpiryDate(token);
		return expDate.before(new Date(System.currentTimeMillis()));
	}
	
	public String getUserName(String token) {
		return getClaims(token).getSubject();
	}
	
	public Date getExpiryDate(String token) {
		return getClaims(token).getExpiration();
	}
	
	public Claims getClaims(String token) {
		
		return Jwts
				.parserBuilder()
				.setSigningKey(getSignKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	public String generateToken(String subject) {
		
		
		return Jwts.builder()
				.setSubject(subject)
				.setIssuer("ved")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+TimeUnit.MINUTES.toMillis(15)))
				.signWith(getSignKey(),SignatureAlgorithm.HS256)
				.compact();
	}
	private java.security.Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
