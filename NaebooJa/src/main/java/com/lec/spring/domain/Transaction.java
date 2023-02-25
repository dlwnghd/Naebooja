package com.lec.spring.domain;

import lombok.*;

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

    private Property property_id;               // 거래의 기본 자산 번호(FK)

    @ToString.Exclude
    private Property in_property_id;           // 이체의 자산의 번호 (FK)
    private User user_id;                   // User id (FK)
}
