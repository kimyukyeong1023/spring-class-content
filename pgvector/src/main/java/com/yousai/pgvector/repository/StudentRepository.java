package com.yousai.pgvector.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yousai.pgvector.entity.Student;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
  // 자동으로 CRUD를 생성한다
  // save(), findAll(), findById()

  List<Student> findByName(String name);
}
