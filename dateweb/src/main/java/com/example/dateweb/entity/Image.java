package com.example.dateweb.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "image")
@Builder
@Getter @Setter
public class Image extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // 식별값

    private String caption; // 사진 설명

    private String postImageUrl; // 사진 경로

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;






}
