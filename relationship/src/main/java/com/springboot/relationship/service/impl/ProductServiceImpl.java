package com.springboot.relationship.service.impl;

import com.springboot.relationship.data.dto.ProductDto;
import com.springboot.relationship.data.dto.ProductResponseDto;
import com.springboot.relationship.data.entity.Product;
import com.springboot.relationship.data.repository.ProductRepository;
import com.springboot.relationship.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 예제 6.22
@Service
public class ProductServiceImpl implements ProductService {
    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository=productRepository;
    }


    @Override
    public ProductResponseDto getProduct(Long number) {
        logger.info("[getProduct] input number : {}", number);
        Product product = productRepository.findById(number).get();

        logger.info("[getProduct] product number : {}, name : {}", product.getNumber(), product.getName());

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber(product.getNumber());
        productResponseDto.setName(product.getName());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setStock(product.getStock());

        return productResponseDto;
    }


    @Override
    public ProductResponseDto saveProduct(ProductDto productDto) {
        logger.info("[saveProduct] product DTO : {}", productDto.toString());
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());

        Product savedProduct = productRepository.save(product);
        logger.info("[saveProduct] savedProduct : {}", savedProduct);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber(savedProduct.getNumber());
        productResponseDto.setName(savedProduct.getName());
        productResponseDto.setPrice(savedProduct.getPrice());
        productResponseDto.setStock(savedProduct.getStock());

        return productResponseDto;
    }


    @Override
    public ProductResponseDto changeProductName(Long number, String name){
        Product foundProduct = productRepository.findById(number).get();
        foundProduct.setName(name);
        Product changedProduct = productRepository.save(foundProduct);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber(changedProduct.getNumber());
        productResponseDto.setName(changedProduct.getName());
        productResponseDto.setPrice(changedProduct.getPrice());
        productResponseDto.setStock(changedProduct.getStock());

        return productResponseDto;
    }

    // 예제 6.26
    @Override
    public void deleteProduct(Long number){
        productRepository.deleteById(number);
    }
}
