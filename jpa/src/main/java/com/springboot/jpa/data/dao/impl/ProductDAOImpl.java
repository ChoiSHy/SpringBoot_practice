package com.springboot.jpa.data.dao.impl;

import com.springboot.jpa.data.dao.ProductDAO;
import com.springboot.jpa.data.entity.Product;
import com.springboot.jpa.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component  // spring이 관리하는 Bean으로 등록하는 어노테이션
public class ProductDAOImpl implements ProductDAO {

    private final ProductRepository productRepository;

    @Autowired
    public ProductDAOImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public Product insertProduct(Product product) {
        Product savedProduct=productRepository.save(product);
        return savedProduct;
    }

    @Override
    public Product selectProduct(Long number) {
        Product selectedProduct =productRepository.getById(number);
        /*
        * getById(ID id)
        * : 데이터베이스 우선 접근해 데이터 조회
        * r-type : 프락시 객체
        *
        * findById(ID id)
        * : 캐시에서 값을 조회 후, 값이 존재하지 않으면 데이터베이스에 접근
        * r-type : Optional
        * */
        return selectedProduct;
    }

    @Override
    public Product updateProductName(Long number, String name) throws Exception {
        Optional<Product> selectedProduct = productRepository.findById(number); // number에 해당하는 레코드를 불러와 Optional에 저장

        Product updatedProduct;
        if(selectedProduct.isPresent()){    // 데이터가 존재한다면
            Product product = selectedProduct.get();    // Optional의 정보를 프락시 객체에 전달

            product.setName(name);  //이름 정보 수정
            product.setUpdatedAt(LocalDateTime.now());  //update시간 수정

            updatedProduct = productRepository.save(product);   // 해당 객체를 DB에 저장
        }else{
            throw new Exception();
        }
        return updatedProduct;
    }

    @Override
    public void deleteProduct(Long number) throws Exception {
        Optional<Product> selectedProduct = productRepository.findById(number); // 삭제하고자 하는 레코드를 불러오기

        if(selectedProduct.isPresent()){    // 데이터가 존재한다면
            Product product = selectedProduct.get();    // 해당 데이터에 대한 객체 생성

            productRepository.delete(product);  // DB에서 해당 객체에 대한 레코드 삭제
        }else{
            throw new Exception();
        }
    }
}
