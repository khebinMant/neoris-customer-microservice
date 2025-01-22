package com.neoris.customer;

import com.neoris.customer.config.CustomerConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


@Import({CustomerConfiguration.class})
@SpringBootApplication(scanBasePackages = {"com.neoris.customer"})
public class CustomerApplicationTest {

}
