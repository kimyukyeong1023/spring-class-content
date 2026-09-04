package com.yonsai.Day66_20260903.service;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// @어노테이션 
//  - 스프링한테 요청 처리하는 객체야! 알려주는것!
// IOC컨테이너 (스프링 핵심!)
//  - 스프링이 객체들을 만들어서 보관하는 관리하는 공간!
// @Autowired
//  DI주입( Dependency Injection )
//  내가 필요한 객체를 직접 만들지 않고 스프링한테 넣어줘!
//  자바클래스 파일들 메모리에 생성하는것! new AiServie()
//  pom.xml - 프로젝트가 실행되기 전에 외부라이브러리를 다운로드해라!
import org.springframework.web.multipart.MultipartFile;

// 어떻게 주입을 시켜주지?
// 1. 클래스타입 
// 2. 중복되는 파일명은 패키지(경로 전체를 기준)
// 3. @Qualifier(클래스파일명) 직접 지정!(너야!)
// 4. @Primary  여러개 중에 기본 담당자를 미리 정해둠
@Service
public class AiService {

    // AI한테 질문을 보내기 위해서 준비하는 공간
    // 서비스 작성을 할 때는 여기서 필요한 도구들 !
    // 1. 모델정보를 등록하는 클래스파일(ChatModel)
    @Autowired
    ChatModel chatModel;
    // 2. 채팅창을 만드는 클래스파일(ChatClient)
    ChatClient chatClient;
    // 3. 문서를 읽어드리는 클래스파일 (PagePdfDocumentReader)
    // 내가 원하는데로 문서를 자를 수있는 가위 TokenTextSplitter
    // 4. 임베딩해서 벡터DB저장 (EmbeddingModel ,SimpleVectorStore)
    @Autowired
    EmbeddingModel embeddingModel;
    SimpleVectorStore vector;

    String context = "";

    // 함수명 : 파일읽기()
    // AI프롬프트.pdf 파일을 읽어서 토큰 사이즈 200 맞춰서
    // Document 문서로 만들어서 출력!

    public boolean pdfReader() {

        // 1. 파일읽기
        PagePdfDocumentReader file = new PagePdfDocumentReader("classpath:/스타트업.pdf");

        // 2. 페이지별로 나누기
        List<Document> originFile = file.get();
        // 3. 토큰 나누기
        TokenTextSplitter tokenSplitter = TokenTextSplitter.builder()
                .withChunkSize(200)
                .build();
        List<Document> docs = tokenSplitter.apply(originFile);

        // 4. 확인
        for (Document chunk : docs) {
            System.out.println(chunk.getText());
            System.out.println();
            System.out.println("-------------------------------");
        }

        // 5. 벡터 저장
        saveToVectorStore(docs);

        return true;
    }

    // 벡터 저장
    public void saveToVectorStore(List<Document> docs) {
        System.out.println("AiService - saveToVectorStore()");
        // 1. 벡터 생성
        vector = SimpleVectorStore
                .builder(embeddingModel)
                .build();

        // 2. 저장
        vector.add(docs);

        System.out.println("OK!  - " + docs.size());

    }

    // AI질문 보내기!
    // LLM - 엄청나게 많은 글을 학습해서 사람의 말을 이해하고 다음에 올 말을
    // 만들어내는 AI모델
    // 실제 질문을 이해하고 답변을 만드는 인공지능들! (gpt , 재미니,클로드)
    // 인공지능 (Artificial Intelligence)
    // - 사람이 하던 일들을(판단,예측) 컴퓨터가 대신 하는 기술!

    public String askAi(String question) {
        // 1. 질문로그 확인
        System.out.println("question : " + question);

        // 2. 문서들 확인 (벡터DB)
        // relevant - 관련있는
        List<Document> relevantChunks = vector.similaritySearch(question);
        System.out.println("검색된 관련 청크들 개수: " + relevantChunks.size());

        // 3. List<Document> -> 문자로 변경
        // 한줄의 문자로 연결해라!
        for (Document chunk : relevantChunks) {
            context += chunk.getText() + "\n\n";
        }
        System.out.println(context);

        // 4. 채팅창 생성
        chatClient = ChatClient.builder(chatModel).build();

        // 5. 채팅창에 질문과 문맥(context) 합쳐서 AI 보내기
        String answer = chatClient.prompt() // 채팅창에 질문입력할께!
                .system(
                        "당신은 문서 내용을 바탕으로 답변하는 도우미입니다." +
                                "아래 제공된 문맥 안에서만 답변하고 문맥에 없으면 모른다고 답하세요!")
                .user(u -> u.text("""
                        문맥 : {context}

                        질문 : {question}""")
                        .param("context", context)
                        .param("question", question))
                .call()
                .content();

        return answer;
    }

}