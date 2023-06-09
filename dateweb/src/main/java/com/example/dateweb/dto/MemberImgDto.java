package com.example.dateweb.dto;

import com.example.dateweb.entity.MemberImg;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter @Setter
public class MemberImgDto {

    private Long id;

    private String imgName;

    private String oriImgName;

    private String imgUrl;

    private String repImg;

    private static ModelMapper modelMapper = new ModelMapper(); // 멤버변수가 많다면 엔티티를 dto로 바꾸기엔 복잡하기 떄문에 ,이에 엔티티를 dto로 손쉽게 바꿔주는 라이브러리이다

    public static MemberImgDto of(MemberImg memberImg){ // static 메소드로 선언 dto객체를 생성하지 않아도 호출
        return modelMapper.map(memberImg,MemberImgDto.class); // MemberImg와 dto의 객체의 자료형과 멤버변수 이름이 같을 때 dto로 값을 복사해서 반환
    }
}
