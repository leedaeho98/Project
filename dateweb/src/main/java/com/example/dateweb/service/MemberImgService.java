package com.example.dateweb.service;

import com.example.dateweb.entity.MemberImg;
import com.example.dateweb.repository.MemberImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

@Service // 멤버 이미지의 서비스를 담당하는 클래스를 지정
@RequiredArgsConstructor // 인텔리제이에선 @AutoWired보다 이 어노테이션을 선호
@Transactional // 스프링에서는 기능은 메서드가 포함하고 있는 작업중에 하나라도 실패하면 전체 작업 취소
public class MemberImgService {
    @Value("${memberImgLocation}") // @Value 어노테이션을 통해 application에 파일에 등록한 memberimglocation을 들고와서 변수에 대입
    private String memberImgLocation;

    private final MemberImgRepository memberImgRepository;

    private final FileService fileService;

    public void saveMemberImg(MemberImg memberImg, MultipartFile memberImgFile) throws Exception{
       String oriImgName = memberImgFile.getOriginalFilename();
       String imgName = "";
       String imgUrl = "";

       // 파일 업로드
        if(!StringUtils.isEmpty(oriImgName)){
            imgName = fileService.uploadFile(memberImgLocation, oriImgName, memberImgFile.getBytes()); //  upLoadFile 메서드를 호출해서 호출결과 로컬에 저장된 파일의 이름을 imgName에 저장
            imgUrl = "/images/member/" + imgName; // 저장한 상품 이미지를 불러올 경로를 설정
        }

        // 회원 이미지 저장 ( 입력받은 상품 이미지 정보를 저장)
        memberImg.updateMemberImg(oriImgName, imgName, imgUrl);
        memberImgRepository.save(memberImg);

    }


}
