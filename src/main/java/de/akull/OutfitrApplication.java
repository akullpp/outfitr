package de.akull;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableCaching
@EnableFeignClients
@SpringBootApplication
public class OutfitrApplication {

    public static void main(String[] args) {
        SpringApplication.run(OutfitrApplication.class, args);
    }
}
