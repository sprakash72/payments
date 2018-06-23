package balanceinquiry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"balanceinquiry.repositories"})
@SpringBootApplication
public class BalancedinquiryserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BalancedinquiryserviceApplication.class, args);
	}
}
