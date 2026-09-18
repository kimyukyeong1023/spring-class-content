package com.yonsai.aws.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Controller
public class AwsController {

  // AWS 액세스키랑 비밀번호를 저장
  private String accessKey = "key";

  private String secretKey = "key";

  @GetMapping("/index")
  public String index() {
    return "index";
  }

  @PostMapping("/upload")
  public String upload(@RequestParam("file") MultipartFile file) {
    System.out.println("AwsController - upload");
    System.out.println(file.getOriginalFilename());

    // 인증키 (액세스,비밀번호) 하나로 묶어서 전달하기 (출입증)
    AwsBasicCredentials 인증정보 = AwsBasicCredentials
        .create(accessKey, secretKey);
    // 접속할 때 출입증 인증 담당자한테 줘야된다.
    StaticCredentialsProvider 인증담당자 = StaticCredentialsProvider
        .create(인증정보);

    try {
      // 1. AWS에 접속할 도구를 만든다.
      // S3에 파일을 보내고 가져오는 도구
      S3Client s3Client = S3Client.builder() // 도구만들기시작!
          .region(Region.of("ap-southeast-2")) // 접속할 AWS 지역
          .credentialsProvider(인증담당자)
          .build();

      // 어떤 버킷에 , 어떤 이름으로 저장할지 정하기 (택배송장)
      PutObjectRequest 저장요청 = PutObjectRequest.builder() // 버킷설정시작!
          .bucket("fullstack-seohee-8069")
          .key(file.getOriginalFilename())
          .build();

      // 실제로 보낼 파일 내용 준비하기
      RequestBody 파일내용 = RequestBody.fromInputStream(
          file.getInputStream(), // 파일 내용을 읽을 통로
          file.getSize());

      // 2. 보내기
      s3Client.putObject(저장요청, 파일내용);

      System.out.println("업로드 완료!");
    } catch (Exception e) {
      e.printStackTrace();
    }

    return "index";
  }

}