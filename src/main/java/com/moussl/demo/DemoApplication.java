package com.moussl.demo;

import com.moussl.demo.entities.Product;
import com.moussl.demo.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.security.autoconfigure.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner start(ProductRepository productRepository) {
        return args -> {
            productRepository.save(Product.builder().name("computer").price(5800).quantity(450).build());
            productRepository.save(Product.builder().name("smart").price(2).quantity(3).build());
            productRepository.save(Product.builder().name("test").price(3).quantity(9).build());
            productRepository.findAll().forEach(e->System.out.println(e.toString()));
        };
    }
}
