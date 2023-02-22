package com.lec.spring.repository;

import com.lec.spring.domain.Transaction;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository {

    // 자산 입력
    int save(Transaction transaction);

    // 자산 수정
    int update(Transaction transaction);

    // 자산 삭제
    int delete(Transaction transaction);

    // 특정 id (PK)의 자산 전체 보기 (transaction id)
    List<Transaction> findById(Long id);

    // 특정 user(FK) 의 자산 일괄 보기(user_id)
    List<Transaction> findByUserId(Long id);

    // 특정 user 의 특정 거래 내역 보기 (user_id, transaction_type)
    List<Transaction> findByType(Long id, String type);

    // 특정 user 의 특정 기간 내 거래 전체 보기 (user_id, regDate)
    List<Transaction> findByDate(Long id, LocalDateTime regDate);
}