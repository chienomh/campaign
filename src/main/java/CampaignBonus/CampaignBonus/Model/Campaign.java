package CampaignBonus.CampaignBonus.Model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

@Entity
public class Campaign {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Campaign must have a name")
  private String name;
  private String description;

  @NotBlank(message = "Campaign must have a name")
  private String awarded_to;

  @NotBlank(message = "Campaign must have a name")
  private Date payment_date;

  @NotBlank(message = "Campaign must have a name")
  private Double bonus_amount;

  @NotBlank(message = "Campaign must have a name")
  @Enumerated(EnumType.ORDINAL)
  private CampaignType campaign_type;

  @Enumerated(EnumType.ORDINAL)
  private CampaignStatus campaign_status = CampaignStatus.CREATED;
  private Long user_id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getAwarded_to() {
    return awarded_to;
  }

  public void setAwarded_to(String awarded_to) {
    this.awarded_to = awarded_to;
  }

  public Date getPayment_date() {
    return payment_date;
  }

  public void setPayment_date(Date payment_date) {
    this.payment_date = payment_date;
  }

  public Double getBonus_amount() {
    return bonus_amount;
  }

  public void setBonus_amount(Double bonus_amount) {
    this.bonus_amount = bonus_amount;
  }

  public CampaignType getCampaign_type() {
    return campaign_type;
  }

  public void setCampaign_type(CampaignType campaign_type) {
    this.campaign_type = campaign_type;
  }

  public CampaignStatus getCampaign_status() {
    return campaign_status;
  }

  public void setCampaign_status(CampaignStatus campaign_status) {
    this.campaign_status = campaign_status;
  }

  public Long getUser_id() {
    return user_id;
  }

  public void setUser_id(Long user_id) {
    this.user_id = user_id;
  }
}
