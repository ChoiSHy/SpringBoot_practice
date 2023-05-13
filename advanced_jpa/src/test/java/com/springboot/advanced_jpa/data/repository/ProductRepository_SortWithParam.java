package com.springboot.advanced_jpa.data.repository;

import com.springboot.advanced_jpa.data.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;

@SpringBootTest
public class ProductRepository_SortWithParam {
    @Autowired
    ProductRepository productRepository;

    @Test
    void sortingAndPagingTest(){
        Product product1 = Product.builder()
                .name("펜")
                .price(1000)
                .stock(100)
                .build();
        Product product2 = Product.builder()
                .name("펜")
                .price(1000)
                .stock(300)
                .build();
        Product product3 = Product.builder()
                .name("펜")
                .price(500)
                .stock(50)
                .build();

        Product savedProduct1 = productRepository.save(product1);
        Product savedProduct2 = productRepository.save(product2);
        Product savedProduct3 = productRepository.save(product3);

        for (Product p : productRepository.findByName("펜", getSort()))
            System.out.println(p.toString());

        Page<Product> productPage = productRepository.findByName("펜", PageRequest.of(0,2));
        for(Product p : productPage.getContent())
            System.out.println(p);
    }
    private Sort getSort(){
        return Sort.by(
                Sort.Order.asc("price"),
                Sort.Order.desc("stock")
        );
    }
}
