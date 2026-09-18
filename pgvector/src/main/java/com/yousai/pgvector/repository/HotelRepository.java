package com.yousai.pgvector.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yousai.pgvector.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
