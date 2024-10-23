package CampaignBonus.CampaignBonus.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
  private final String secret = "Z1J4HwgUZ5KCwt2qd4WQUBflqPn3ctHM6hTzdWGPJhY";
  private final long expirationTime = 86400000; // 1 day

//  private static final String SECRET_KEY = "Z1J4HwgUZ5KCwt2qd4WQUBflqPn3ctHM6hTzdWGPJhY=";
//  private static final Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));

  public String generateToken(String username) {

    Key key = Keys.hmacShaKeyFor(secret.getBytes());
    return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
  }

  public boolean validateToken(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  public String extractUsername(String token) {
    return extractAllClaims(token).getSubject();
  }

  private Date extractExpiration(String token) {
    return extractAllClaims(token).getExpiration();
  }

  private Claims extractAllClaims(String token) {
    Key key = Keys.hmacShaKeyFor(secret.getBytes()); // Decode secret key

    // Use the new `parserBuilder` instead of deprecated `parser()`
    return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody();
  }
}

