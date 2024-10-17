package CampaignBonus.CampaignBonus;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.security.Key;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class CampaignBonusApplication {

	public static void main(String[] args) {
		SpringApplication.run(CampaignBonusApplication.class, args);
		// gen key, lấy được key xong thì xóa
		Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);  // Tạo một khóa bí mật ngẫu nhiên cho HS256
		String base64Key = Encoders.BASE64.encode(key.getEncoded());
		System.out.println("Your secret key: " + base64Key);
	}

}
