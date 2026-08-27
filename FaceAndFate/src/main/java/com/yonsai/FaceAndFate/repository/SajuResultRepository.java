package com.yonsai.FaceAndFate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yonsai.FaceAndFate.entity.SajuResult;

// Entity를 어떻게 저장하고 꺼내올지 담당하는 직원!
// 그 직원이 업무메뉴얼을 교육받기(JpaRepository )
@Repository
public interface SajuResultRepository extends JpaRepository<SajuResult, Long> {
}

// entity 데이터를 저장하는 곳!
// Repository 데이터들을 관리하는 창고!+담당직원배치!