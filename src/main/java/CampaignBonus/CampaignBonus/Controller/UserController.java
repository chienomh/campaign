package CampaignBonus.CampaignBonus.Controller;

import CampaignBonus.CampaignBonus.Model.LoginRequest;
import CampaignBonus.CampaignBonus.Model.Users;
import CampaignBonus.CampaignBonus.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
  @Autowired
  private UserService userService;

  @GetMapping("/users")
  public List<Users> getAllUsers() {
    return userService.findAllUsers();
  }

  @PutMapping("/users/{id}")
  public Optional<Users> updateUser(@PathVariable Long id, @RequestBody Users user) {
    return userService.updateUser(id, user);
  }

  @DeleteMapping("/users/{id}")
  public void deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
  }
}


