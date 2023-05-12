package com.springboot.advanced_jpa.data.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springboot.advanced_jpa.data.entity.QProduct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


/*
*  JPAQueryFactory 객체를 @Bean 객체로 등록해 활용한 예제
* */
@SpringBootTest
public class ProductRepositoryTest5 {
    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @Test
    void queryDslTest4(){
        QProduct qProduct = QProduct.product;

        List<String> productList = jpaQueryFactory
                .select(qProduct.name)
                .from(qProduct)
                .where(qProduct.name.eq("노트"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for(String product : productList){
            System.out.println("------------------------");
            System.out.println("Product Name : "+product);
            System.out.println("------------------------");
        }
    }
}
