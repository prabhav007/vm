package in.novopay.ws.util;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2350647080756579383L;

	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.token.validity}")
	private Integer tokenValidity;

	// generate token for user
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return generateToken(claims, userDetails.getUsername());
	}

	// validate token
	public Boolean validateToken(String token, UserDetails userDetails) {
		Claims claims = getClaimsFromToken(token);
		String username = claims.getSubject();
		Date expirationDate = claims.getExpiration();
		return (username.equals(userDetails.getUsername()) && expirationDate.after(new Date()));
	}
	
	public Boolean validateClaims(Claims claims, UserDetails userDetails) {
		String username = claims.getSubject();
		Date expirationDate = claims.getExpiration();
		return (username.equals(userDetails.getUsername()) && expirationDate.after(new Date()));
	}
	
	public String getUsernameFromToken(String token) {
		return getClaimsFromToken(token).getSubject();
	}
	
	public String getUsernameFromClaims(Claims claims) {
		return claims.getSubject();
	}
	
	public Claims getClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	
	private String generateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + tokenValidity * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}
}
