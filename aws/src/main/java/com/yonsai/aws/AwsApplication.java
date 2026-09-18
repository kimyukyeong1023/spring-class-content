package com.yonsai.aws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AwsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwsApplication.class, args);
	}

}
/*
 * AWS S3 저장소
 * 클라우드(Cloud)
 * - 인터넷으로 다른 곳에 서버,저장 공간을 이용하는것
 * - 필요한 만큼 빌려 쓰는 창고!
 * 
 * S3
 * - AWS에서 제공하는 파일 저장 서비스
 * - 파일(동영상,이미지,문서)
 * 
 * 버킷(Bucket)
 * - S3에서 파일을 담아두는 공간
 * 
 * 범용
 * - 다용도 상자
 * - 여러 용도로 두루 사용할 수있다.
 * 
 * S3 범용 버킷
 * - 다양한 파일을 담는 보관함
 * - 용량 걱정은 안해도됨
 * - 서버가 여러대 있을 경우에 하나의 저장소를 사용하기 때문에
 * 서버를 돌아가면서 찾는 문제가 없다!
 * - 비용효율적
 * - CDN : 정적파일을 빠르게 전세계에 제공할 수있다.
 * 
 * 리전
 * - AWS 서버중 어느 서버를 사용할 지 결정
 * - 아시아 - 서울
 * 
 * 버킷정책?
 * - S3 버킷 전체에 적용되는 규칙!
 * 누가 어떤 행동을 어떤 대상에 하는지 할수있는지 없는지
 * 정의한 문서다!
 * 
 * 
 * 
 * 해당 버킷 -> 권한 -> 버킷 정책 작성
 * 
 * {
 * "Version": "2012-10-17",
 * "Statement": [
 * {
 * "Sid": "PublicReadGetObject",
 * "Effect": "Allow",
 * "Principal": "*",
 * "Action": "s3:GetObject",
 * "Resource": "arn:aws:s3:::fullstack-seohee-8069/*"
 * }
 * ]
 * }
 * "Version": "2012-10-17" (AWS 정책문법 고정값!)
 * "Statement": [] 배열로 여러개의 권한 작성한다.
 * "Sid": 규칙의 이름표(아무 이름이나 붙여도 된다.) 필수아님
 * "Effect": 허용 / 거부 (Deny)
 * "Principal": 누구에게 적용할 건지
 * "Action": 무엇을 허용할지? 조회? 삭제? 수정? 어떤거할래?
 * "Resource": 어디에 적용하니? 어느 버킷에 적용할껀데?
 * 우리가 한 정책은 fullstack-seohee-8069 저장소를 전세계 누구나
 * 읽기(다운로드/조회만) 할수있따!
 * 
 * IAM
 * - AWS에서 누가 무엇을 할 수있는 정하는 권한 기능!
 * 누구? 어디에? 무엇을 할수 있나?
 * 
 * 사용자
 * - 권한을 받을 사용자 계정
 * 정책(Policy)
 * - 사진 읽기 허용 같은 권한 규칙
 * 역할(Role)
 * - 사람이나 프로그램이 맡아서 사용하는 권한 묶음
 * 
 * 
 */