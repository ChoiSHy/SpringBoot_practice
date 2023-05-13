package com.springboot.advanced_jpa.data.repository;

import com.springboot.advanced_jpa.data.entity.Product;
import com.springboot.advanced_jpa.data.repository.support.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JPA_AuditingTest {
    @Autowired
    ProductRepository productRepository;
    @Test
    public void auditingTest(){
        Product product = new Product();
        product.setName("pen");
        product.setPrice(1000);
        product.setStock(100);

        Product savedProduct = productRepository.save(product);

        System.out.println("productName : "+ savedProduct.getName());
        System.out.println("createdAt : "+savedProduct.getCreatedAt());
    }
}
