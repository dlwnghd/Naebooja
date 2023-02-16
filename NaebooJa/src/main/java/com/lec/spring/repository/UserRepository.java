package com.lec.spring.repository;

import com.lec.spring.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    // 특정 id (PK) 의 user 리턴
    User findById(Long id);

    // 특정 username 의 user 리턴
    User findByUsername(String username);

    // 새로운 User 등록
    int save(User user);

    int update(User user);

}
