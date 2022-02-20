package com.newsletter.user.config;

import com.newsletter.user.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * The type Jwt token util.
 */
@Component
public class JwtTokenUtil implements Serializable {
  private static final long serialVersionUID = -2550185165626007488L;

  /**
   * The constant JWT_TOKEN_VALIDITY.
   */
  public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

  @Value("${jwt.secret}")
  private String secret;

  /**
   * Gets email from token.
   *
   * @param token the token
   * @return the email from token
   */
//retrieve email from jwt token
  public String getEmailFromToken(String token) {
    return getClaimFromToken(token, Claims::getSubject);
  }

  /**
   * Gets expiration date from token.
   *
   * @param token the token
   * @return the expiration date from token
   */
//retrieve expiration date from jwt token
  public Date getExpirationDateFromToken(String token) {
    return getClaimFromToken(token, Claims::getExpiration);
  }

  /**
   * Gets claim from token.
   *
   * @param <T>            the type parameter
   * @param token          the token
   * @param claimsResolver the claims resolver
   * @return the claim from token
   */
  public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = getAllClaimsFromToken(token);
    return claimsResolver.apply(claims);
  }
  //for retrieving any information from token we will need the secret key
  private Claims getAllClaimsFromToken(String token) {
    return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
  }

  //check if the token has expired
  private Boolean isTokenExpired(String token) {
    final Date expiration = getExpirationDateFromToken(token);
    return expiration.before(new Date());
  }

  /**
   * Generate token string.
   *
   * @param user the user
   * @return the string
   */
//generate token for user
  public String generateToken(User user) {
    Map<String, Object> claims = new HashMap<>();
    return doGenerateToken(claims, user.getEmail());
  }

  // compaction of the JWT to a URL-safe string
  private String doGenerateToken(Map<String, Object> claims, String subject) {

    return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
        .signWith(SignatureAlgorithm.HS512, secret).compact();
  }

  /**
   * Validate token boolean.
   *
   * @param token the token
   * @param user  the user
   * @return the boolean
   */
//validate token
  public Boolean validateToken(String token, User user) {
    final String email = getEmailFromToken(token);
    return (email.equals(user.getEmail()) && !isTokenExpired(token));
  }

}
