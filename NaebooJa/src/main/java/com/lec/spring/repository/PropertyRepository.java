package com.lec.spring.repository;

import com.lec.spring.domain.Property;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository {

    // 특정 id (PK)의 자산 보기
    Property findById(Long id);
}