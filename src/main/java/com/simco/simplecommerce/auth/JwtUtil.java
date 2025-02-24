package com.simco.simplecommerce.auth;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    private static final String SECRET_KEY_BASE64 = "my_base64_encoded_secret_key_here";

    private final SecretKey secretKey = Keys.hmacShaKeyFor(SECRET_KEY_BASE64.getBytes());
    private Log logger= LogFactory.getFactory().getInstance(JwtUtil.class);

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", userDetails.getAuthorities().iterator().next().getAuthority());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour expiry
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        try {
                final String username = extractUsername(token);
                return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
            } catch (ExpiredJwtException e) {
                logger.error("Token expired: "+ e.getMessage());
                return false;
            } catch (MalformedJwtException e) {
                logger.error("Invalid token format: "+ e.getMessage());
                return false;
            } catch (Exception e) {
                logger.error("Token validation failed: "+ e.getMessage());
                return false;
            }
    }


    private boolean isTokenExpired(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }
}
