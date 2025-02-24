package com.simco.simplecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaAuditing
@EnableTransactionManagement
@EnableJpaRepositories("com.simco.simplecommerce.repository")
public class SimpleCommerceApplication {
	public static void main(String[] args) {
		SpringApplication.run(SimpleCommerceApplication.class, args);
	}
}

