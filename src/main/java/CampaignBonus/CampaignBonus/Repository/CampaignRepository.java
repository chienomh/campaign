package CampaignBonus.CampaignBonus.Repository;

import CampaignBonus.CampaignBonus.Model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {
}
