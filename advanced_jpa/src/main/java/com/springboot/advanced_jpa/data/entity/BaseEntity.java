package com.springboot.advanced_jpa.data.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@MappedSuperclass       // JPA의 엔티티가 상속받을 경우 자식 클래스에게 매핑 정보 전달
@EntityListeners(AuditingEntityListener.class)  // 엔티티를 데이터베이스에 적용하기 전후로 콜백 요청을 가능하게 함
                                                // 엔티티의 Auditing 정보를 주입하는 JPA 엔티티 리스너 클래스
public class BaseEntity {
    @CreatedDate                        // 생성 날짜를 자동으로 주입하는 어노테이션
    @Column(updatable = false)          
    private LocalDateTime createdAt;

    @LastModifiedDate                   // 수정 날짜를 자동으로 주입하는 어노테이션
    private LocalDateTime updatedAt;
}
