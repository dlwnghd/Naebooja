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

    // 특정 id (PK)의 거래 보기
    Transaction findById(Long id, Long p_id);

    // 특정 user(FK) 의 거래 내역 일괄 보기(user_id)
    List<Transaction> findAll(Long id);

    // 특정 user 의 특정 거래 내역 보기 (user_id, transaction_type)
    List<Transaction> findByType(Long id, String type);

    // 특정 user 의 하루동안 거래내역 전체 보기 (user_id, regDate)
    List<Transaction> findAllByDaily(Long id, int day, int month, int year);

    // 특정 user 의 한달동안 거래내역 전체 보기 (user_id, regDate)
    List<Transaction> findAllByMonthly(Long id, int month, int year);

    // 특정 user 의 하룻동안 특정 type 의 거래내역 전체 보기
    List<Transaction> findByDayType(Long id, String type, int day, int month, int year);

    // 특정 user 의 한달동안 특정 type 의 거래내역 전체 보기
    List<Transaction> findByMonthType(Long id, String type, int month, int year);


}