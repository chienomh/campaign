package CampaignBonus.CampaignBonus.Controller;

import CampaignBonus.CampaignBonus.Model.LoginRequest;
import CampaignBonus.CampaignBonus.Model.Users;
import CampaignBonus.CampaignBonus.Service.UserService;
import CampaignBonus.CampaignBonus.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

  @Autowired
  private UserService userService;

  @Autowired
  private JwtUtil jwtUtil;

  @PostMapping("/signup")
  public ResponseEntity<String> signup(@RequestBody Users user) {
    userService.signup(user);
    return ResponseEntity.ok("User registered successfully!");
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
    Users user = userService.login(loginRequest.getUsername(), loginRequest.getPassword());

    if (user != null) {
      String token = jwtUtil.generateToken(loginRequest.getUsername());
      token = "Bearer " + token;
      return ResponseEntity.ok("Bearer " + token);
    }
    return ResponseEntity.status(401).body("Invalid username or password");
  }
}

