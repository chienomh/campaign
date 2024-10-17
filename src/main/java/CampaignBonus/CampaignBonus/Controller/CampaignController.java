package CampaignBonus.CampaignBonus.Controller;

import CampaignBonus.CampaignBonus.Model.Campaign;
import CampaignBonus.CampaignBonus.Model.CampaignStatus;
import CampaignBonus.CampaignBonus.Service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CampaignController {

  @Autowired
  private CampaignService campaignService;

  @GetMapping("/campaign")
  public List<Campaign> getAllCampaign() {
    return campaignService.findAllCampaign();
  }

  @GetMapping("/campaign/{id}")
  public Optional<Campaign> getCampaignById(@PathVariable Long id) {
    return Optional.ofNullable(campaignService.findCampaignById(id));
  }

  @PostMapping("/campaign")
  public Campaign createCampaign(@Valid @RequestBody Campaign campaign) {
    return campaignService.createCampaign(campaign);
  }

  @PutMapping("/campaign/{id}")
  public Optional<Campaign> updateCampaign(@Valid @RequestBody Campaign campaign, @PathVariable Long id) {
    return campaignService.updateCampaign(id, campaign);
  }

  @PutMapping("/campaign/status/{id}")
  public Optional<Campaign> updateCampaignStatus(@RequestBody CampaignStatus status, @PathVariable Long id) {
    return campaignService.updateStatusOfCampaign(id, status);
  }

  @DeleteMapping("/campaign/{id}")
  public void deleteCampaign(@PathVariable Long id) {
    campaignService.deleteCampaign(id);
  }
}
