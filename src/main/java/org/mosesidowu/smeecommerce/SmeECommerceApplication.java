package org.mosesidowu.smeecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class SmeECommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmeECommerceApplication.class, args);
    }
}
