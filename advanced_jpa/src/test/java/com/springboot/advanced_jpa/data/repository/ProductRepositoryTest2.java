package com.springboot.advanced_jpa.data.repository;

import com.springboot.advanced_jpa.data.entity.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductRepositoryTest2 {
    @Autowired
    ProductRepository productRepository;
    @Test
    public void basicCRUDTest(){
        /* create */
        // given
        Product givenProduct=Product.builder()
                .name("노트")
                .price(1000)
                .stock(1000).build();
        // when
        Product savedProduct = productRepository.save(givenProduct);

        // then
        Assertions.assertThat(savedProduct.getNumber()).isEqualTo(givenProduct.getNumber());
    }
}
