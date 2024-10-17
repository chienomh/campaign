package CampaignBonus.CampaignBonus.Repository;

import CampaignBonus.CampaignBonus.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
  Optional<Users> findByUsername(String username);
}
