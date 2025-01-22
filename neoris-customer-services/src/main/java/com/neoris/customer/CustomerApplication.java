package com.neoris.customer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.neoris.customer.config.CustomerConfiguration;
import com.neoris.customer.client.seeders.ClientSeeder;
import com.neoris.customer.person.seeders.PersonSeeder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.TimeZone;

@Import({CustomerConfiguration.class})
@SpringBootApplication(scanBasePackages = {"com.neoris.customer"})
@EnableFeignClients
public class CustomerApplication implements CommandLineRunner {

    @Autowired
    PersonSeeder personSeeder;

    @Autowired
    ClientSeeder clientSeeder;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(CustomerApplication.class);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
            }
        };
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                .setTimeZone(TimeZone.getTimeZone("America/Guayaquil"));
    }

    @Override
    public void run(String... args) throws Exception {
        //Creo personas
        personSeeder.fillStartPersons();
        //Creo a los clientes
        clientSeeder.fillStartClients();
    }
}
