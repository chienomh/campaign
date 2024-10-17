package CampaignBonus.CampaignBonus.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
  private final String secret = "secret_key";
  private final long expirationTime = 86400000; // 1 day

  public String generateToken(String username) {

    Key key = Keys.hmacShaKeyFor(secret.getBytes());
    return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
  }

  public boolean validateToken(String token) {
    return getClaims(token).getExpiration().after(new Date());
  }

  public String extractUsername(String token) {
    return getClaims(token).getSubject();
  }

  private Claims getClaims(String token) {
    return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
  }
}

