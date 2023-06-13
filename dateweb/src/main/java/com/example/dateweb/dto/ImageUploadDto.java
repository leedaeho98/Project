package com.example.dateweb.dto;

import com.example.dateweb.entity.Image;
import com.example.dateweb.entity.Member;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

// 파일을 받는 DTO 클래스 => file과 caption을 받을 수 있도록 한다
@Getter @Setter
public class ImageUploadDto {

    // MultipartFile에는 @NotBlack가 지원안됌
    private MultipartFile file;

    private String caption;

    // toEntity 메소드를 통해 Image객체를 형변환 할 수 있도록 한다
    public Image toEntity(String postImageUrl, Member member){
        return Image.builder()
                .caption(caption)
                .postImageUrl(postImageUrl)
                .member(member)
                .build();


    }
}
