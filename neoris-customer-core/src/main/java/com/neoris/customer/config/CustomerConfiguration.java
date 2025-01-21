package com.neoris.customer.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan(basePackages = {"com.neoris.customer"})
@EnableJpaRepositories(basePackages = {"com.neoris.customer"})
@ComponentScan(basePackages = {"com.neoris.customer"})
@EnableTransactionManagement
public class CustomerConfiguration {
}
