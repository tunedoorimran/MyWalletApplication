package com.tunedoor.microservice;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * @author Mohamed
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
public class MyWalletApplication {

	private static Logger logger = Logger.getLogger(MyWalletApplication.class.getName());

	public static void main(String[] args) {
		System.setProperty("spring.config.name", "mywallet-server");
		logger.info("Change Application Configuration to use mywallet-server.yml");
		SpringApplication.run(MyWalletApplication.class, args);
	}
	
	/**
	 * 
	 * @return restTemplate
	 */
	@LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
