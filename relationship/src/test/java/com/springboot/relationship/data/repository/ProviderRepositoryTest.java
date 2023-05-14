package com.springboot.relationship.data.repository;

import com.springboot.relationship.data.entity.Product;
import com.springboot.relationship.data.entity.Provider;
import com.springboot.relationship.data.repository.support.ProductRepository;
import com.springboot.relationship.data.repository.support.ProviderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProviderRepositoryTest {
    @Autowired
    ProviderRepository providerRepository;
    @Autowired
    ProductRepository productRepository;

    @Test
    void relationshipTest1(){
        // 테스트 데이터 생성
        Provider provider = new Provider();
        provider.setName("삼성물산");

        providerRepository.save(provider);

        Product product = new Product();
        product.setName("Galaxy S 21");
        product.setPrice(5000);
        product.setStock(500);
        product.setProvider(provider);

        productRepository.save(product);

        // 테스트
        System.out.println(
                "product : "+productRepository.findById(1L)
                        .orElseThrow(RuntimeException::new)
        );

        System.out.println("provider : "+ productRepository.findById(1L)
                .orElseThrow(RuntimeException::new).getProvider());
    }
}
