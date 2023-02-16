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
public class User {
    private Long id;
    
    private String username;   // 회원 아이디
    @JsonIgnore
    private String password;   // 회원 비밀번호

    @ToString.Exclude
    @JsonIgnore
    private String re_password;  // 비밀번호 확인 입력
    
    private String name;   // 회원 이름
    @JsonIgnore
    private LocalDateTime regDate;

    @ToString.Exclude
    @Builder.Default
    @JsonIgnore
    private List<Authority> authorities = new ArrayList<>();

    public void addAuthority(Authority.../*⬅️가변매개변수 Authority 객체가 몇개가 들어오든지 다 받아줌*/ authorities/*배열*/){     // 이 배열을 위에 있는 리스트(authorities)에 담을 거임
        if(authorities != null){
            Collections.addAll(this.authorities, authorities);
        }
    }
}








