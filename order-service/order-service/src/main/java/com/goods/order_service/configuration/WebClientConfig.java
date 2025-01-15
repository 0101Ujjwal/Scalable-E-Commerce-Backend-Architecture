package com.goods.order_service.configuration;


import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
	
//	@Bean
//	public org.springframework.web.reactive.function.client.WebClient webclient(){
//		return org.springframework.web.reactive.function.client.WebClient.builder().build();
//	}
	
	@Bean
	@LoadBalanced
	public WebClient.Builder webclient() {
		
		return WebClient.builder();
	}

}
