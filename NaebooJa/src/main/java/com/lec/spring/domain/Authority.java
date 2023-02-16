package com.lec.spring.domain;

// 권한

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Authority {
    private Long id;        // PK
    private String name;    // 권한명 ex) "ROLE_MEMBER", "ROLE_ADMIN"
}
