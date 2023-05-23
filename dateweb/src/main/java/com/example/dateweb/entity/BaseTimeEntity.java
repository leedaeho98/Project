package com.example.dateweb.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@EntityListeners(value = {AuditingEntityListener.class}) // Auditing을 적용하기 위한 어노테이션
@MappedSuperclass // 공통 매핑 정보가 필요할 때 사용하는 어노테이션 , 부모클래스를 상속 받는 자식클래스에 매핑 정보 제공
@Getter @Setter
public abstract class BaseTimeEntity {

    @CreatedDate // 엔티티가 생성되어 저장될 때 시간을 자동으로 저장
    @Column(updatable = false) // 수정 안됌 ( 수정 가능여부)
    private LocalDateTime regtime; // 엔티티 생성 시간

    @LastModifiedDate // 엔티티가 수정될 때 시간을 자동으로 저장
    private LocalDateTime updateTime;
}
