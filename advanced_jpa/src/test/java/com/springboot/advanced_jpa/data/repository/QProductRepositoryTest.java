package com.springboot.advanced_jpa.data.repository;

import com.querydsl.core.types.Predicate;
import com.springboot.advanced_jpa.data.entity.Product;
import com.springboot.advanced_jpa.data.entity.QProduct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class QProductRepositoryTest {
    @Autowired
    QProductRepository qProductRepository;

    @Test
    public void queryDSLTest1(){
        insertData();
        Predicate predicate = QProduct.product.name.containsIgnoreCase("펜")
                .and(QProduct.product.price.between(1000,2500));

        Optional<Product> foundProduct = qProductRepository.findOne(predicate);

        if(foundProduct.isPresent()){
            Product product = foundProduct.get();

            System.out.println("number: "+product.getNumber());
            System.out.println("name: "+product.getName());
            System.out.println("price: "+product.getPrice());
            System.out.println("stock: "+product.getStock());
        }
    }
    @Test
    public void queryDSLTest2(){
        QProduct qProduct = QProduct.product;
        Iterable<Product> productList = qProductRepository.findAll(
                qProduct.name.contains("펜")
        );

        for (Product product : productList){
            System.out.println("number: "+ product.getNumber());
            System.out.println("name: "+product.getName());
            System.out.println("price: "+product.getPrice());
            System.out.println("stock: "+product.getStock());
            System.out.println("------------------");
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
