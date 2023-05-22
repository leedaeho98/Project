package com.example.dateweb.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

// 회원가입 DTO
@Getter
@Setter
public class MemberDto {

    private Long id;

    @NotBlank(message = "이름은 필수 입력 값입니다")
    private String name;

    @NotBlank(message = "이메일은 필수 입력 값입니다")
    @Email(message = "이메일 형식으로 입력해주세요")
    private String email;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String password;

    @NotBlank(message = "전화번호는 필수 입력 값입니다")
    private String phone;

    @Min(value = 0, message = "적절한 연령을 입력해주세요") // 나이 0 부터
    @Max(value = 150, message = "적절한 연령을 입력해주세요") // 나이 150까지
    private int age;

    @NotEmpty(message = "성별은 필수 입력 값입니다")
    private String gender;

    @NotBlank(message = "닉네임은 필수 입력 값입니다")
    @Pattern(regexp = "[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "닉네임은 특수문자를 제외한 2~10자리여야 합니다")
    private String nickname;

    @NotEmpty(message = "주소는 필수 입력 값입니다")
    private String address;
}
