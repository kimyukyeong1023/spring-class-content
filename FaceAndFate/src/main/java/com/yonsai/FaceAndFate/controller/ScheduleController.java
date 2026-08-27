package com.yonsai.FaceAndFate.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

// 웹 요청이 아니라 정해진 시간마다 메서드를 자동으로 실행시키는
// 기능

// 나는 오전 9시에 알림 울려줘!
// 나는 오전 7시에 알림 울려줘!
// 업데이터,오늘매출 통계정리 새벽 5시 진행해줘!
@Component // 스프링아 이 클래스 니가 알아서 관리해! 그래서 5초에 한번씩
           // 스케쥴러 실행해!
public class ScheduleController {

    // 5초 마다 알람을 실행해줘! 스케쥴을 등록!
    // @Scheduled 스케쥴등록
    // fixedRate 시간 ms단위 5000ms -> 5초
    @Scheduled(fixedRate = 5000)
    public void 자동실행() {
        System.out.println("⏰ 실행됨!");
    }

    // cron = 특정 시간마다 자동으로 실행하는 시간표!
    // 6자리를 이용해서 특정 시간을 설정한다.
    // 초 분 시 일 월 요일
    // 0 0 0 0 0 0

    @Scheduled(cron = "0 0 9 * * *",zone = "Asia/Seoul")
    public void 특정오전9시실행() {

    }

}
/*
 * fixedRate
 * - 서버가 켜지는 순간부터 카운트
 * - 서버상태 체그 , 헬스체크 , 실시간 모니터링
 * 
 * 
 * 
 */