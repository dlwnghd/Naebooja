package com.lec.spring.repository;

import com.lec.spring.domain.Transaction;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository {

    // 자산 입력 (Create)
    int save(Transaction transaction);

    // 자산 수정 (Update)
    int update(Transaction transaction);

    // 자산 삭제 (Delete)
    int delete(Transaction transaction);

//    Read
    // 특정 id (PK)의 거래 보기 -> transaction 에서는 사용하지 않긴 하는데, 이거 왜 Param 이 2개나 필요한건지 모르겠습니다..!
    Transaction findById(Long u_id, Long p_id);

    // 특정 user(FK) 의 거래 내역 일괄 보기(user_id)
    List<Transaction> findAll(Long id);

    // 특정 user 의 특정 거래 내역 보기 (user_id, transaction_type)
    List<Transaction> findByType(Long id, String type);

    // 특정 user 의 하루동안 거래내역 전체 보기 (user_id, regDate)
    List<Transaction> findAllByDaily(Long id, Date date);

    // 특정 user 의 한달동안 거래내역 전체 보기 (user_id, regDate)
    List<Transaction> findAllByMonthly(Long id, Date date);

    // 특정 user 의 하룻동안 특정 type 의 거래내역 전체 보기
    List<Transaction> findByDayType(Long id, String type, Date date);

    // 특정 user 의 한달동안 특정 type 의 거래내역 전체 보기
    List<Transaction> findByMonthType(Long id, String type, Date date);


}