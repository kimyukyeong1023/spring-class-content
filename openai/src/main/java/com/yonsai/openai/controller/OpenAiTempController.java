package com.yonsai.openai.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class OpenAiTempController {

    // 1. gpt 정보
    @Autowired
    ChatModel gpt정보;

    // 2. AI 채팅창
    ChatClient Ai채팅창;

    // 3. 이전대화기억!
    List<Message> 이전대화기록 = new ArrayList<>();

    @GetMapping("/")
    public String index() {
        System.out.println("메인페이지 보여줘!");
        return "index";
    }

    // 이미지로 질문 받았을 때 PostMapping 받기
    // 브라우저 -> 데이터를 보낸다 post
    // 파일 업로드!
    // 문자 데이터 + 파일 데이터
    // 서버에서 받아주는 타입 multipart
    // 데이터를 여러 부분으로 나눠서 전송하는 방식!

    // @RequestParam() HTML과 컨트롤러를 연결시킨다.
    // HTML에서 name속성 이름과 자바파일 변수명하고 연결해서
    // 저장해줘!
    @PostMapping("/img")
    public String img(@RequestParam("img") MultipartFile img,
            Model model) {

        // 이미지 전송 잘 됬는지 확인 (파일이름)
        System.out.println(img.getOriginalFilename());

        // 1.gpt 정보가져오기
        // 채팅창을 처음 한 번만 만들기
        if (Ai채팅창 == null) {
            Ai채팅창 = ChatClient
                    .builder(gpt정보)
                    .build();
        }

        String 이미지타입=img.getContentType();
        System.out.println("이미지 타입확인: "+이미지타입);
        // MultipartFile로 가져온 파일을 byte배열로 변경
        // AI가 이해할 수있는 형태로!
        try {

            byte[] 이미지데이터 = img.getBytes();

            // 2. AI채팅 보내고 받기
            String 답변 = Ai채팅창
                    .prompt()
                    .user(u -> u
                            .text("이 사진에서 뭐가 보여?")
                            .media(MimeTypeUtils.IMAGE_JPEG,
                                    new ByteArrayResource(이미지데이터)))
                    .call()
                    .content();

            // 3. HTML로 보내기
            model.addAttribute("question", 답변);

        } catch (Exception e) {
        }

        return "index";
    }

    // 질문을 서버로 보낼때는 PostMapping
    // 질문이 길어질 수있다.(글,이미지,동영상,Zip,등등)
    @PostMapping("/history")
    public String history(String question, Model model) {

        // 2. AI 대화하는 도구!
        // 채팅창을 처음 한 번만 만들기
        if (Ai채팅창 == null) {
            Ai채팅창 = ChatClient
                    .builder(gpt정보)
                    .build();
        }

        // 3. 질문 기록!(ArrayList 추가)
        이전대화기록.add(new UserMessage(question));

        // 4. AI 보내기 -> 답변 받기
        String 답변 = Ai채팅창
                .prompt()
                .messages(이전대화기록)
                .call()
                .content();

        // 7.답변 기록 추가하기
        이전대화기록.add(new AssistantMessage(답변));

        // 5. HTML로 보내기
        model.addAttribute("question", 답변);

        // 6. 페이지이동!
        return "index";
    }

}
/*
 * Error resolving template [history]
 * templates 폴더 안에 history HTML파일이 없다!
 * 첫번쨰 확인
 * - 컨트롤러의 return 파일명확인
 * 두번째 확인
 * - HTML 코드에 URL들 확인!
 * 
 */