package CampaignBonus.CampaignBonus.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
  private final String secret = "ThangCaoChimDaiDu30PhutNhieuTuTheBuLiemDayDu";
  private final long expirationTime = 86400000; // 1 day

//  private static final String SECRET_KEY = "Z1J4HwgUZ5KCwt2qd4WQUBflqPn3ctHM6hTzdWGPJhY=";
//  private static final Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));

  public String generateToken(String username) {

    Key key = Keys.hmacShaKeyFor(secret.getBytes());
    return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
            .signWith(key, SignatureAlgorithm.HS256) // Use the Key instance here
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

