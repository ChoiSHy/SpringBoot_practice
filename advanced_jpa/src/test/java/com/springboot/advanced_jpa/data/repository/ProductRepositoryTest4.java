package com.springboot.advanced_jpa.data.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springboot.advanced_jpa.data.entity.Product;
import com.springboot.advanced_jpa.data.entity.QProduct;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@SpringBootTest
public class ProductRepositoryTest4 {
    @PersistenceContext
    EntityManager entityManager;        // JPAQuery 객체

    @Test
    void queryDslTest(){
        JPAQuery<Product> query = new JPAQuery(entityManager);
        QProduct qProduct = QProduct.product;

        List<Product> productList = query
                .from(qProduct)                         // 쿼리 from문
                .where(qProduct.name.eq("펜"))     // 쿼리 where문
                .orderBy(qProduct.price.asc())          // 쿼리 정렬 여부
                .fetch();       // 작성된 쿼리를 통해 받아내어 리스트로 반환받음

                // T fetchOne                      조회 결과 1건만 반환
                // T fetchFirst()                  여러 건의 조회 결과 중 1건을 반환 ( == .limit(1).fetchOne() )
                // Long fetchCount()               조회 결과의 개수 반환
                // QueryResult<T> fetchResults()   조회 결과 리스트와 개수를 포함한 QueryResults 반환

        for (Product product : productList){
            System.out.println("---------------------");
            System.out.println();
            System.out.println("Product Number: "+product.getNumber());
            System.out.println("Product Name: "+product.getName());
            System.out.println("Product Price: "+product.getPrice());
            System.out.println("Product Stock: "+product.getStock());
            System.out.println();
            System.out.println("---------------------");
        }
    }

    @Test
    void queryDslTest2(){
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QProduct qProduct = QProduct.product;

        List<Product> productList = jpaQueryFactory.selectFrom(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for( Product product : productList){
            System.out.println("---------------------");
            System.out.println();
            System.out.println("Product Number: "+product.getNumber());
            System.out.println("Product Name: "+product.getName());
            System.out.println("Product Price: "+product.getPrice());
            System.out.println("Product Stock: "+product.getStock());
            System.out.println();
            System.out.println("---------------------");
        }
    }

    @Test
    void queryDslTest3(){
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QProduct qProduct=QProduct.product;

        List<String> productList = jpaQueryFactory
                .select(qProduct.name)
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for (String product : productList){
            System.out.println("---------------------");
            System.out.println("Product Name : "+product);
            System.out.println("---------------------");
        }

        List<com.querydsl.core.Tuple> tupleList = jpaQueryFactory
                .select(qProduct.name, qProduct.price)
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for(Tuple product : tupleList){
            System.out.println("-------------------");
            System.out.println("Product Name : "+product.get(qProduct.name));
            System.out.println("Product Name : "+product.get(qProduct.price));
            System.out.println("-------------------");
        }
    }

}
