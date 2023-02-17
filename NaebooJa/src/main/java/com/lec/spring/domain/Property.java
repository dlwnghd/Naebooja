package com.lec.spring.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Property {
    private Long id;          // 자산의 고유번호
    private String group;     // 자산의 그룹명(카드, 은행)
    private String name;      // 자산의 이름(사용자가 지정한 자산의 이름)
    private Long rest_money;  // 자산의 잔액

    private User user;        // 자산의 사용자 (FK)
}
