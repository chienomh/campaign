package CampaignBonus.CampaignBonus.Service;

import CampaignBonus.CampaignBonus.Model.Users;
import CampaignBonus.CampaignBonus.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public List<Users> findAllUsers() {
    return userRepository.findAll();
  }

  public Users createUser(Users user) {
    return userRepository.save(user);
  }

  public Optional<Users> updateUser(Long id, Users userDetail) {
    return Optional.ofNullable(userRepository.findById(id).map(user -> {
      user.setFirstName(userDetail.getFirstName());
      user.setLastName(userDetail.getLastName());
      user.setEmail(userDetail.getEmail());
      return userRepository.save(user);
    }).orElseThrow(() -> new RuntimeException("error")));
  }

  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }

  public Users signup(Users user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  public Users login(String username, String password) {
    return userRepository.findByUsername(username)
            .filter(item -> passwordEncoder.matches(password, item.getPassword()))
            .orElse(null);
  }
}
