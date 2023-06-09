package com.example.dateweb.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="member_img")
@Getter @Setter
public class MemberImg {
    @Id
    @Column(name = "member_img_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // 식별값

    private String imgName; // 이미지 파일명

    private String oriImgName; // 원본 이미지 파일명

    private String imgUrl; // 이미지 조회 경로

    private String repImg; // 대표 이미지 여부

    @ManyToOne(fetch = FetchType.LAZY) // 지연로딩 => 멤버 엔티티가 필요할 경우 데이터 조회
    @JoinColumn(name = "member_id")
    private Member member;

    public void updateMemberImg(String oriImgName, String imgName, String imgUrl){ // 원본 이미지, 업데이트 이미지, 이미지 경로를 파라미터로 입력받아 이미지 정보를 업데이트하는 메서드
        this.oriImgName = oriImgName;
        this.imgName = imgName;
        this.imgUrl = imgUrl;
    }
}
