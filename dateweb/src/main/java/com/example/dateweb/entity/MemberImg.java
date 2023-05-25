package com.example.dateweb.entity;

import javax.persistence.*;

@Entity // 데이터베이스 테이블 생성
@Table(name = "member_img") // 테이블 이름
public class MemberImg {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "memberImg_id")
    private Long id; // 식별값

    private String memberImgName; // 이미지 이름

    private String memberImgUrl; // 이미지 경로

    @ManyToOne(fetch = FetchType.LAZY) // 지연로딩
    @JoinColumn(name = "member_id")
    private Member member;


}
