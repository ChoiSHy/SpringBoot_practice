package com.springboot.jpa.data.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/*
 * @Transient : 엔티티 클래스 내에선 선언된 필드지만 데이터베이스에서는 필요 없을 경우 이 어노테이션을 사용해
 * 데이터베이스에서 사용하지 않게 함
 */

@Entity     // Entity 명시 어노테이션
@Table(name = "product")  // 클래스의 이름과 테이블의 이름이 다르게 지정해야 할 때 필요한 어노테이션, name은 테이블 이름 명시
//* Lombok 관련 Annotation *//
@Getter     // Getter method 생성 어노테이션
@Setter     // Setter method 생성 어노테이션
@NoArgsConstructor  // 매개변수 없는 생성자 어노테이션
@AllArgsConstructor // 모든 매개변수 있는 생성자 어노테이션
// @RequiredArgsConstructor // final 혹은 @NotNull이 설정된 변수를 매개변수로 갖는 생성자를 자동 생성
@ToString()   //toString에 해당하는 메서드 생성. exclude 속성을 통해 특정 필드값 제외 가능
// @EqualsAndHashCode   // 객체의 동등성(Equality)과 동일성(Identity)을 비교하는 메서드 생성
                        // 동등성(Equality): 두 객체의 내용이 같은지 비교 
                        // 동일성(Identity): 두 객체가 같은 객체인지 비교
// @Data    // 앞서 언급한 모든 lombok annotation 포괄

public class Product {

    // 상품 번호
    @Id //모든 엔티티에 필요한 어노테이션, 테이블의 기본값 역할로 사용됨
    @GeneratedValue(strategy = GenerationType.IDENTITY) //해당 필드의 값을 어떤 방식으로 자동 생성할 지 결정
    /*
     * 사용x : 직접 할당
     * AUTO : 해당 어노테이션의 기본 설정값.
     * IDENTITY : 기본값 생성을 데이터베이스에 위임하는 방식. DB의 AUTO_INCREMENT를 사용해 기본값 생성
     * SEQUENCE : @SequenceGenerator 으로 식별자 생성기 설정하고 이를 통해 값을 자동 주입받는다.
     * TABLE : 어떤 DBMS를 사용하더라도 동일하게 동작하기를 원할 때 사용. 별도의 보관 테이블을 생성해 엔티티 생성할 때마다 갱신.
     * */
    private Long number;

    // 상품 이름
    @Column(nullable = false)     // 칼럼 매핑 어노테이션. 일반적으론 자동으로 매핑되어 생략 가능.
    // nullable: null처리 가능한지 명시 속성, default = true
    // name : 칼럼 명 설정 속성, 설정하지 않으면 필드명으로 지정
    // length : 데이터베이스에 저장하는 데이터의 최대 길이
    // unique : 해당 칼럼을 유니크로 설정
    private String name;

    // 상품 가격
    @Column(nullable = false)
    private Integer price;

    // 상품 재고
    @Column(nullable = false)
    private Integer stock;

    // 상품 생성 일자
    private LocalDateTime createdAt;
    // 상품 정보 변경 일자
    private LocalDateTime updatedAt;

    /*
    // Lombok 미적용시 사용할 getter/setter 메서드
    public Product(Long number, String name, Integer price, Integer stock, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.number = number;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    public Product(){
        
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    */
}
