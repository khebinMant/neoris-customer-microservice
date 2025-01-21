package com.neoris.customer.management;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


@Import({com.neoris.customer.config.CustomerConfiguration.class})
@SpringBootApplication(scanBasePackages = {"com.neoris.customer"})
public class CustomerApplicationTest {

}
