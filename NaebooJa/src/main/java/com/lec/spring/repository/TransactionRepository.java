package com.lec.spring.repository;

import com.lec.spring.domain.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository {

    // 특정 id (PK)의 거래 보기
    Transaction findById(Long id, Long p_id);
}