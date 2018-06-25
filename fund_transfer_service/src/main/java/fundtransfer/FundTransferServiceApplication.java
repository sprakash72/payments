package fundtransfer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
//@EnableEurekaClient //Spring framework takes care of discovery through this
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"fundtransfer.repositories"})
@SpringBootApplication
public class FundTransferServiceApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(FundTransferServiceApplication.class, args);
	}
	
	@LoadBalanced
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
}

//@Configuration
//class Config {
//	
//	@LoadBalanced
//	@Bean
//	public RestTemplate restTemplate(){
//		return new RestTemplate();
//	}
//	
//}
