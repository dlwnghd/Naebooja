package com.lec.spring.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Model 객체 (domain, DTO: Data Transfer Object)

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Write {
    private Long id;                    // 글의 고유번호
    private String subject;             // 글의 제목
    private String content;             // 글의 내용
    private LocalDateTime regDate;      // 글의 작성일
    private long viewCnt;               // 글의 조회수

    private User user;  // 글 작성자 (FK)

    // 첨부파일, 댓글
    @ToString.Exclude
    @Builder.Default    // 아래와 같이 초깃값 있으면 @Builder.Default 처리(builder 제공 ❌)
    private List<FileDTO> fileList = new ArrayList<>();

    // 웹개발시...
    // 가능한, 다음 3가지는 이름을 일치시켜주는게 좋습니다.
    // 클래스 필드명 = DB 필드명 = form의 name명
}
