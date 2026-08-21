package com.yonsai.Day61_20260819.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.yonsai.Day61_20260819.dto.Product;

// 아직은 데이터는 Service쪽에서 저장
// 자바에 저장을 하면 메모리에 저장이 된다.
// 우리가 서버를 끄면 사라진다.
// 상품을 추가하는 페이지를 만든다.
@Service
public class ProductService {

  // 객체가 생성할때 자동으로 데이터 초기화하기
  private ArrayList<Product> 상품리스트 = new ArrayList<>();

  // 서버가 켜지는 순간 스프링이 어노테이션 찾아서
  // 자동으로 객체를 만든다.
  // 그 객체들을 모아놓는 곳 - Spring Container
  // 스프링컨테이너에서 이미 만들어진 객체를 사용하려면
  // @Autowired 이용해서 주소를 저장한다.
  public ProductService() {
    상품리스트.add(new Product(1, "무선 마우스", "전자기기", 15000, 42, "https://picsum.photos/seed/p1/400/300"));
    상품리스트.add(new Product(2, "블루투스 이어폰", "전자기기", 39000, 18, "https://picsum.photos/seed/p2/400/300"));
    상품리스트.add(new Product(3, "기계식 키보드", "전자기기", 89000, 7, "https://picsum.photos/seed/p3/400/300"));
    상품리스트.add(new Product(4, "노트북 파우치", "생활용품", 22000, 25, "https://picsum.photos/seed/p4/400/300"));
    상품리스트.add(new Product(5, "보조배터리", "전자기기", 32000, 60, "https://picsum.photos/seed/p5/400/300"));
    상품리스트.add(new Product(6, "USB 허브", "전자기기", 18000, 33, "https://picsum.photos/seed/p6/400/300"));
    상품리스트.add(new Product(7, "텀블러", "생활용품", 12000, 90, "https://picsum.photos/seed/p7/400/300"));
    상품리스트.add(new Product(8, "백팩", "패션잡화", 55000, 14, "https://picsum.photos/seed/p8/400/300"));
    상품리스트.add(new Product(9, "운동화", "패션잡화", 78000, 22, "https://picsum.photos/seed/p9/400/300"));
    상품리스트.add(new Product(10, "손목시계", "패션잡화", 120000, 9, "https://picsum.photos/seed/p10/400/300"));
    상품리스트.add(new Product(11, "선글라스", "패션잡화", 45000, 17, "https://picsum.photos/seed/p11/400/300"));
    상품리스트.add(new Product(12, "캠핑의자", "레저용품", 35000, 11, "https://picsum.photos/seed/p12/400/300"));
    상품리스트.add(new Product(13, "텐트", "레저용품", 189000, 5, "https://picsum.photos/seed/p13/400/300"));
    상품리스트.add(new Product(14, "등산스틱", "레저용품", 27000, 20, "https://picsum.photos/seed/p14/400/300"));
    상품리스트.add(new Product(15, "요가매트", "레저용품", 19000, 48, "https://picsum.photos/seed/p15/400/300"));
    상품리스트.add(new Product(16, "아령세트", "레저용품", 42000, 15, "https://picsum.photos/seed/p16/400/300"));
    상품리스트.add(new Product(17, "커피머신", "가전제품", 145000, 8, "https://picsum.photos/seed/p17/400/300"));
    상품리스트.add(new Product(18, "전기포트", "가전제품", 28000, 30, "https://picsum.photos/seed/p18/400/300"));
    상품리스트.add(new Product(19, "공기청정기", "가전제품", 165000, 6, "https://picsum.photos/seed/p19/400/300"));
    상품리스트.add(new Product(20, "가습기", "가전제품", 39000, 24, "https://picsum.photos/seed/p20/400/300"));
    상품리스트.add(new Product(21, "선풍기", "가전제품", 32000, 19, "https://picsum.photos/seed/p21/400/300"));
    상품리스트.add(new Product(22, "책상 스탠드", "가구/조명", 24000, 35, "https://picsum.photos/seed/p22/400/300"));
    상품리스트.add(new Product(23, "1인용 소파", "가구/조명", 210000, 4, "https://picsum.photos/seed/p23/400/300"));
    상품리스트.add(new Product(24, "책장", "가구/조명", 88000, 10, "https://picsum.photos/seed/p24/400/300"));
    상품리스트.add(new Product(25, "책상매트", "가구/조명", 16000, 40, "https://picsum.photos/seed/p25/400/300"));
    상품리스트.add(new Product(26, "무드등", "가구/조명", 21000, 27, "https://picsum.photos/seed/p26/400/300"));
    상품리스트.add(new Product(27, "노트북 거치대", "전자기기", 26000, 29, "https://picsum.photos/seed/p27/400/300"));
    상품리스트.add(new Product(28, "웹캠", "전자기기", 47000, 13, "https://picsum.photos/seed/p28/400/300"));
    상품리스트.add(new Product(29, "게이밍 헤드셋", "전자기기", 65000, 16, "https://picsum.photos/seed/p29/400/300"));
    상품리스트.add(new Product(30, "그래픽 태블릿", "전자기기", 98000, 6, "https://picsum.photos/seed/p30/400/300"));

  }

  // 4. 전체 상품을 꺼내갈 수있는 함수
  public ArrayList<Product> all() {
    // 5. 컨트롤러에게 상품리스트 보내기!
    return 상품리스트;
  }

  // 실제 상품리스트에서 반복하면서 키워드 변수에 있는 값이 있는지
  // 확인해서 ArrayList타입으로 컨트롤러에게 돌려준다.
  // 왜? 화면에 결과 보여줘!
  // 결과가 있을 수도 있고 하나있을수 있고 여러개일 수도 있어요!
  // 4. 함수실행
  public ArrayList<Product> productSearch(String type, String keyword) {

    // 5. 검색한 결과를 저장하는 ArrayList를 만든다.
    ArrayList<Product> 검색한결과리스트 = new ArrayList<>();

    for(Product 상품한개 : 상품리스트){
      boolean 결과 = switch (type) {
        case "상품명" -> 상품한개.getName().contains(keyword);      
        case "카테고리"-> 상품한개.getCategory().contains(keyword);       
        default -> false;    //type이 없으면 false를 돌려준다.
      };

      if(결과){ 
        검색한결과리스트.add(상품한개);
      }
    }

    // 7. 반복이 다 되면 검색한결과리스트 컨트롤러에게 보내기!
    return 검색한결과리스트;
  }

}