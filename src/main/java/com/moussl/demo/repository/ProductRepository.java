package com.moussl.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.moussl.demo.entities.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {

}
