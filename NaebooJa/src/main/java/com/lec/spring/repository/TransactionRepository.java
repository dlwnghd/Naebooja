package com.lec.spring.repository;

import com.lec.spring.domain.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository {

    // 자산 입력


    // 자산 수정

    // 자산 삭제

    // 특정 id (PK)의 자산 보기 (transaction id)
    Transaction findById(Long id);

    // 특정 user(FK) 의 자산 일괄 보기(user_id)

    // 특정 user 의 특정 거래 내역 보기 (user_id, transaction_type)

    // 특정 user 의 특정 기간 내 거래 전체 보기 (user_id, regDate)

    // 특정 user 의 특정 일자의 특정 거래내역 보기 (user_id, transaction_type, regDate)

}