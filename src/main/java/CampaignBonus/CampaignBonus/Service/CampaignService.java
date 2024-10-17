package CampaignBonus.CampaignBonus.Service;

import CampaignBonus.CampaignBonus.Model.Campaign;
import CampaignBonus.CampaignBonus.Model.CampaignStatus;
import CampaignBonus.CampaignBonus.Repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CampaignService {
  @Autowired
  private CampaignRepository campaignRepository;

  public List<Campaign> findAllCampaign() {
    return campaignRepository.findAll();
  }

  public Campaign findCampaignById(Long id) {
    return campaignRepository.findById(id).orElse(null);
  }

  public Campaign createCampaign(Campaign campaign) {
    campaign.setCampaign_status(CampaignStatus.CREATED);
    return campaignRepository.save(campaign);
  }

  public Optional<Campaign> updateCampaign(Long id, Campaign campaignDetail) {
    return campaignRepository.findById(id).map(campaign -> {
      campaign.setName(campaignDetail.getName());
      campaign.setDescription(campaignDetail.getDescription());
      campaign.setAwarded_to(campaignDetail.getAwarded_to());
      campaign.setCampaign_type(campaignDetail.getCampaign_type());
      campaign.setBonus_amount(campaignDetail.getBonus_amount());
      campaign.setPayment_date(campaignDetail.getPayment_date());
      campaign.setUser_id(campaignDetail.getUser_id());
      return campaignRepository.save(campaign);
    });
  }

  public void deleteCampaign(Long id) {
    campaignRepository.deleteById(id);
  }

  public Optional<Campaign> updateStatusOfCampaign(Long id, CampaignStatus status) {
    return campaignRepository.findById(id).map(campaign -> {
      campaign.setCampaign_status(status);
      return campaignRepository.save(campaign);
    });
  }
}
