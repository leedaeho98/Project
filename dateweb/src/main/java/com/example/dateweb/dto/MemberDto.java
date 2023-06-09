package com.example.dateweb.dto;

import com.example.dateweb.entity.Member;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

// 회원가입 DTO
@Getter
@Setter
public class MemberDto {

    private Long id;

    @NotBlank(message = "이름은 필수 입력 값입니다")
    private String name;

    @Email(message = "이메일 형식으로 입력해주세요")
    private String email;

    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    @NotEmpty(message = "")
    private String password;

    @Length(min = 11, max = 11 , message = "휴대전화는 10자이하로 적어주세요")
    @NotBlank(message = "")
    private String phone;

    @Min(value = 0, message = "적절한 연령을 입력해주세요") // 나이 0 부터
    @Max(value = 150, message = "적절한 연령을 입력해주세요") // 나이 150까지
    private int age;

    @NotEmpty(message = "성별은 필수 입력 값입니다")
    private String gender;

    private List<MemberImgDto> memberImgDtoList = new ArrayList<>(); // 멤버 저장 후 수정할 때 멤버 이미지 정보를 저장하는 리스트

    private List<Long> itemImgIds = new ArrayList<>(); // 멤버의 이미지 아이디를 저장하는 리스트, 수정 시에 이미지 아이디를 담아둘 용도

    @Pattern(regexp = "[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "닉네임은 특수문자를 제외한 2~10자리여야 합니다")
    @NotBlank(message = "")
    private String nickname;

    @NotEmpty(message = "주소는 필수 입력 값입니다")
    private String address;

    private static ModelMapper modelMapper = new ModelMapper();

    public Member createMember(){
        return modelMapper.map(this, Member.class);
    }

    public static MemberDto of(Member member){
        return modelMapper.map(member,MemberDto.class);
    }

}
