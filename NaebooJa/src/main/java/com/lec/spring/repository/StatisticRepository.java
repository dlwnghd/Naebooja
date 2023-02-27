package com.lec.spring.repository;

// Repository layer
// DataSource (DB) 등에 대한 직접적인 접근

import com.lec.spring.domain.Transaction;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface StatisticRepository {

    // 특정 유저의 전체 지출보기 : 최신순
    List<Transaction> findUserOutcomeAll(Long id);

//    // 특정 유저의 전체 수입보기 : 최신순
//    List<Transaction> findUserIncomeAll(Long id);

    // 특정 유저의 특정월 지출보기 : 최신순
    List<Transaction> findUserOutcomeAll_Monthly(Long id, Date date);

//    // 특정 유저의 특정월 수입보기 : 최신순
//    List<Transaction> findUserIncomeAll_Monthly(Long id, Date date);

    // 전체 자산의 개수
    int countAll();
}