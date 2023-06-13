package com.example.dateweb.service;

import com.example.dateweb.dto.ImageUploadDto;
import com.example.dateweb.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ImageService {

    @Value("${uploadPath}") // application.properties에서 가져옴
    private String uploadFolder;

    private final ImageRepository imageRepository;


    @Transactional
    public void upload(ImageUploadDto imageUploadDto){


    }

}
