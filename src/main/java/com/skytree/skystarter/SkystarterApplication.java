package com.skytree.skystarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SkystarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkystarterApplication.class, args);
    }

}
