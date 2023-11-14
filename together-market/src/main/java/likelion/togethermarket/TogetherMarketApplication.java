package likelion.togethermarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TogetherMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(TogetherMarketApplication.class, args);
	}

}
