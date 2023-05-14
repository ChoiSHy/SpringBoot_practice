package com.springboot.relationship.data.repository;

import com.springboot.relationship.data.entity.Category;
import com.springboot.relationship.data.entity.Product;
import com.springboot.relationship.data.repository.support.ProductRepository;
import com.springboot.relationship.data.repository.support.ProviderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CategoryRepositoryTest {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void relationshipTest(){
        // generate test data
        Product product=Product.builder()
                .name("pen")
                .price(2000)
                .stock(100)
                .build();
        productRepository.save(product);

        Category category = new Category();
        category.setCode("S1");
        category.setName("Book");
        category.getProducts().add(product);

        categoryRepository.save(category);

        // Test code
        List<Product> products = categoryRepository.findById(1L).get().getProducts();

        for(Product foundProduct : products)
            System.out.println(product);
    }

}
