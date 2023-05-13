package com.springboot.relationship.data.repository;

import com.springboot.relationship.data.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
    List<Product> findByUpdatedAtIsNotNull();

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

    // 매개변수를 활용한 쿼리 정렬
    List<Product> findByName(String name, Sort sort);

    // 페이징 처리를 위한 쿼리 메서드
    Page<Product> findByName(String name, Pageable pageable);

    // @Query 어노테이션 사용 메서드
    @Query("SELECT p  FROM Product as p where p.name =?1")      // ?1 : 1번째 파라미터 인자
    List<Product> findByName__(String name);

    // @Query 어노테이션과 @Param 어노테이션을 사용한 메서드
    @Query("select p from Product as p where p.name = :name")
    List<Product> findByNameParam__(@Param("name") String name);

    // 특정 칼럼만 추출하는 쿼리
    @Query("select p.name, p.price, p.stock from Product p where p.name= :name")
    List<Object[]> findByNameParam2__(@Param("name") String name);

}
