package com.mapsa.marketplace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class MarketplaceServiceApplication {
    // TODO: 11/27/2020  @ResponseStatus/Database Tables & relations/spring validations

    public static void main(String[] args) {
        SpringApplication.run(MarketplaceServiceApplication.class, args);
    }

}
