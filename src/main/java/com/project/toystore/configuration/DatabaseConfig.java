package com.project.toystore.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@Configuration
@EnableJpaRepositories(basePackages = {"com.project.toystore.reposities"})
public class DatabaseConfig {

}
