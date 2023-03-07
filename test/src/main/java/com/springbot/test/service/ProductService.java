package com.springbot.test.service;

import com.springbot.test.data.dto.ProductDto;
import com.springbot.test.data.dto.ProductResponseDto;

public interface ProductService {
    ProductResponseDto getProduct(Long number);
    ProductResponseDto saveProduct(ProductDto productDto);
    ProductResponseDto changeProductName(Long number, String name) throws Exception;
    void deleteProduct(Long number) throws Exception;

}
