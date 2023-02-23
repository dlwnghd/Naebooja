package com.lec.spring.repository;

// Repository layer
// DataSource (DB) 등에 대한 직접적인 접근

import com.lec.spring.domain.Property;
import com.lec.spring.domain.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository {

    // 새 자산 생성 <- Property
    int save(Property property);

    // 특정 자산 보기
    Property findById(Long id);

    // 전체 자산 보기 : 최신순
    List<Property> findAll(Long id);

    // 특정 자산의 거래보기 : 최신순
    List<Transaction> findPropTransAll(Long id, Long p_id);

    // 특정 id 자산 삭제하기
    int delete(Property property);

    // 전체 자산의 개수
    int countAll();
}