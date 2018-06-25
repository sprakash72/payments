package dbservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableEurekaClient //Spring framework takes care of discovery through this
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"dbservice.repositories"})
@SpringBootApplication
public class DbserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbserviceApplication.class, args);
	}
}
