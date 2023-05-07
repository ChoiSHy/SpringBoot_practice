package com.springboot.advanced_jpa.data.repository;

import com.springboot.advanced_jpa.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// 예제 6.7
public interface ProductRepository extends JpaRepository<Product, Long> {

    // find...By
    Optional<Product> findByNumber(Long number);
    List<Product> findAllByName(String name);
    Product queryByNumber(Long number);

    // exists...By
    boolean existsByNumber(Long number);

    // count...By
    long countByName(String name);

    // delete...By, remove...By
    void deleteByNumber(Long number);
    long removeByNumber(String name);

    // ...First<number>... , ...Top<number>...
    List<Product> findFirst5ByName(String name);
    List<Product> findTop10ByName(String name);

    // Is : findByNumber 메서드와 동일하게 동작
    Product findByNumberIs(Long number);
    Product findByNumberEquals(Long number);

    // Not
    Product findByNumberIsNot(Long number);
    Product findByNumberNot(Long number);

    // Null, NotNull
    List<Product> findByUpdatedAtNull();
    List<Product> findByUpdatedAtIsNull();
    List<Product> findByUpdatedAtNotNull();
    List<Product> findByUpdatedAtIsnotNull();

    // True, False
    //Product findByIsActiveTrue();
    //Product findByisActiveIsTrue();
    //Product findByisActiveFalse();
    //Product findByisActiveIsFalse();

    // And, Or
    Product findByNumberAndName(Long number, String name);
    Product findByNumberOrName(Long number, String name);

    // GreaterThan, LessThan, Between
    List<Product> findByPriceGreaterThan(Long price);
    List<Product> findByPriceLessThan(Long Price);
    List<Product> findByPriceBetween(Long lowPrice, Long highPrice);

    // StringWith, EndingWith(== ~sWith), Containing(== Contains)
    List<Product> findByNameLike(String name);
    List<Product> findByNameContains(String name);
    List<Product> findByNameContaining(String name);

    List<Product> findByNameStartsWith(String name);
    List<Product> findByNameStartingWith(String name);

    List<Product> findByNameEndsWith(String name);
    List<Product> findByNameEndingWith(String name);

    // Asc, Desc : 오름, 내림차순
    List<Product> findByNameOrderByNumberAsc(String name);
    List<Product> findByNameOrderByNumberDesc(String name);

    // And 붙이지 않음
    List<Product> findByNameOrderByPriceAscStockDesc(String name);

}
