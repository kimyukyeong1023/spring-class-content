package com.yousai.pgvector.service;

import java.util.List;
import java.util.Map;

import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yousai.pgvector.entity.Student;
import com.yousai.pgvector.repository.StudentRepository;

//웹에서 들어오는 일을 처리할 수있다.
// mysql은 똑같은 단어만 찾아준다. 
// 럭셔리하고 조영한 바다가 있는 호텔 
// 조영한  %조용한%
//  바다   %바다%
//  호텔   %호텔%

// postgreSql(저장소) + pgvetor(유사도 검색할수있게) 플러그인
//  임베딩-> 숫자배열로 바꾼다. 

@Service
public class StudentService {
  // 준비하는 공간(service)

  @Autowired
  private EmbeddingModel 숫자배열변경;

  @Autowired
  private StudentRepository 디비저장소;

  @Autowired
  private VectorStore 벡터저장소;

   
  // 학생 이름 검색(유사도 검색!)
  public String search(String 검색어) {
    System.out.println("StudentService- search()");

    SearchRequest 요청객체 = SearchRequest.builder()
        .query(검색어)
        .topK(2) // 유사도 검색을 하면 상위 몇개 가져올지
        .build();
    List<Document> 결과 = 벡터저장소.similaritySearch(요청객체);

    System.out.println(결과);

    System.out.println("===============================");

    List<Student> 디비결과 = 디비저장소.findByName(검색어);
    System.out.println(디비결과);

    return "";
  }
  // SearchRequest
  // - 벡터DB에서 어떻게 검색할지 조건을 담는 객체!
  // - 벡터 검색용 조건 상자!

  // 학생 추가
  public void add(String stuName, int stuAge, String stuAddress) {
    System.out.println("StudentService- add()");
    System.out.println("이름:" + stuName);
    System.out.println("나이:" + stuAge);
    System.out.println("주소:" + stuAddress);

    // 1. 3개를 entity로 묶어야된다.
    Student temp = new Student();
    temp.setName(stuName);
    temp.setAge(stuAge);
    temp.setAddress(stuAddress);

    // 2. 디비에 추가해라!
    디비저장소.save(temp);

    // 3. ai들이 읽을 수있게 Document 문서로 변경!
    // pgvector 테이블에 들어가려면 Document 변경을 해서 추가만 하면
    // 자동으로 테이블에서 임베딩을 하고 저장을 한다.

    Document doc = new Document(
        stuName, // content 컬럼 <- 임베딩 대상
        Map.of("age", stuAge, "address", stuAddress));
    // 벡터테이블에 저장
    // 벡터저장소는 여러개의 숫자배열들이 들어올 수있다.
    // 그래서 기본적으로 매개변수 타입이 여러개를 받는 list형태로 되어있다.
    벡터저장소.add(List.of(doc));

  }

}
// 현재 데이터베이스들은 명확하게 맞지 않으면 결과를 반환하지 않는다.
// 나는 이선희 내 노연 정보 가져다줘
// 문자를 쪼개서 각각 sql like 문장에 이 단어가 포함되어있니?
// 심지어 오타도 다 like로 작성해야된다.
// 유사도 검색을 할 수있도록 임베딩! 임베딩 테이블에 저장한다.