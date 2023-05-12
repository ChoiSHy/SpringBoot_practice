package com.springboot.advanced_jpa.data.repository;

import com.springboot.advanced_jpa.data.entity.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductRepositoryTest3 {
    @Autowired
    ProductRepository productRepository;

    @Test
    @DisplayName("@Query testing")
    void usingQueryAnnotation() {
        Product product1 = Product.builder().name("노트").price(1000).stock(500).build();
        Product product2 = Product.builder().name("노트").price(900).stock(500).build();
        Product product3 = Product.builder().name("노트").price(800).stock(100).build();
        Product product4 = Product.builder().name("지우개").price(700).stock(250).build();
        Product product5 = Product.builder().name("지우개").price(50).stock(99).build();
        Product product6 = Product.builder().name("연필").price(55).stock(250).build();
        Product product7 = Product.builder().name("볼펜").price(100).stock(199).build();

        Product savedProduct1 = productRepository.save(product1);
        assertion(product1, savedProduct1);
        Product savedProduct2 = productRepository.save(product2);
        assertion(product2, savedProduct2);
        Product savedProduct3 = productRepository.save(product3);
        assertion(product3, savedProduct3);
        Product savedProduct4 = productRepository.save(product4);
        assertion(product4, savedProduct4);
        Product savedProduct5 = productRepository.save(product5);
        assertion(product5, savedProduct5);
        Product savedProduct6 = productRepository.save(product6);
        assertion(product6, savedProduct6);
        Product savedProduct7 = productRepository.save(product7);
        assertion(product7, savedProduct7);

        System.out.println("just @Query");
        for(Product p : productRepository.findByName__("노트"))
            System.out.println(p.toString());
        System.out.println("@Query with @Param");
        for (Product p : productRepository.findByNameParam__("노트"))
            System.out.println(p.toString());
        System.out.println("@Query로 특정 column 뽑기");
        for (Object[] p : productRepository.findByNameParam2__("노트"))
            System.out.println(p[0] +" " + p[1] + " " + p[2]);
    }
    void assertion(Product saved, Product given){
        Assertions.assertThat(saved.getNumber()).isEqualTo(given.getNumber());
        Assertions.assertThat(saved.getName()).isEqualTo(given.getName());
        Assertions.assertThat(saved.getPrice()).isEqualTo(given.getPrice());
        Assertions.assertThat(saved.getStock()).isEqualTo(given.getStock());

    }
}
