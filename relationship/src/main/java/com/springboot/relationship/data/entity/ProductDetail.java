package com.springboot.relationship.data.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="product_detail")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ProductDetail extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // 상품번호로 매핑하기 위한 어노테이션
    private Long id;    // 상품 번호

    private String description; // 제품 설명

    @OneToOne       // 일대일 연관관계
    @JoinColumn(name = "product_number")    // Join할 외래키를 설정
    /* @JoinColumn 어노테이션에서 사용하는 속성
    * name: 매핑할 외래키의 이름 설정
    * referencedColumnName: 외래키가 참조할 상대 테이블의 칼럼명 지정
    * foreignKey: 외래키를 생성하면서 지정할 제약조건을 설정 ( unique, nullable, insertable, updatable 등 )
    */
    private Product product;
}
