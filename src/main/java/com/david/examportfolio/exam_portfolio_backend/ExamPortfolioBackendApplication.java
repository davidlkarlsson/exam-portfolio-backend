package com.david.examportfolio.exam_portfolio_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // Enables Spring to automatically set date
public class ExamPortfolioBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamPortfolioBackendApplication.class, args);
	}

}
