package com.lec.spring.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
    private Long id;                        // 거래의 고유번호
    private String transaction_type;        // 거래의 타입 (수입, 지출, 이체)
    private LocalDateTime regDate;          // 거래의 생성일
    private Long money;                     // 거래액
    private String category;                // 거래의 분류(이체,월급,용돈,식비,교통비,쇼핑,기타)
    private String content;                 // 거래의 내용

    private Property property;              // 거래의 자산의 번호 (FK)
    private Property in_property;           // 이체의 자산의 번호 (FK)
}
