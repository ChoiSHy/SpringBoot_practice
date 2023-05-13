package com.springboot.advanced_jpa.data.repository;

import com.springboot.advanced_jpa.data.entity.Product;
import com.springboot.advanced_jpa.data.repository.support.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProductRepositorySupportTest {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    QProductRepository qProductRepository;

    @Test
    void findByNameTest(){
        insertData();
        List<Product> productList = productRepository.findByName("펜");

        for(Product product : productList){
            System.out.println("number: "+product.getNumber());
            System.out.println("name: " +product.getName());
            System.out.println("price: "+product.getPrice());
            System.out.println("stock: "+product.getStock());
        }
    }
    private void insertData(){
        qProductRepository.save(Product.builder().name("노트").price(1000).stock(500).build());
        qProductRepository.save(Product.builder().name("펜").price(1000).stock(500).build());
        qProductRepository.save(Product.builder().name("펜").price(5000).stock(1500).build());
        qProductRepository.save(Product.builder().name("노트").price(1500).stock(5200).build());
        qProductRepository.save(Product.builder().name("지우개").price(500).stock(500).build());
        qProductRepository.save(Product.builder().name("샤프심").price(800).stock(1500).build());
        qProductRepository.save(Product.builder().name("펜").price(6200).stock(1000).build());

    }
}
