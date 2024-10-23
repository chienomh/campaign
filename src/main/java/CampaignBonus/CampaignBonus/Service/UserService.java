package CampaignBonus.CampaignBonus.Service;

import CampaignBonus.CampaignBonus.Model.Users;
import CampaignBonus.CampaignBonus.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public List<Users> findAllUsers() {
    return userRepository.findAll();
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


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<Users> user = userRepository.findByUsername(username);

    if (user == null) {
      throw new UsernameNotFoundException("User not found with username: " + username);
    }

    // Return a UserDetails object for Spring Security
    return new org.springframework.security.core.userdetails.User(user.get().getUsername(),
            user.get().getPassword(), user.get().getAuthorities());
  }
}
