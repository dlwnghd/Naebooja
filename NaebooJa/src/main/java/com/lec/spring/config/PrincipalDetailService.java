package com.lec.spring.config;

import com.lec.spring.domain.User;
import com.lec.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// UserDetailsService
// 컨테이너에 등록한다.
// 시큐리티 설정에서 loginProcessingUrl(url) 을 설정해 놓았기에
// 로그인시 위 url 로 요청이 오면 자동으로 UserDetailsService 타입으로 IoC 되어 있는
// loadUserByUsername() 가 실행되고
// 인증성공하면 결과를 UserDetails 로 리턴

@Service    // UserDetailsService 🫘Bean 객체 등록
public class PrincipalDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;    // DB 조회🔍를 위해 사용됨

    @Override// ⬇️Spring Security 객체              ⬇️ login.html 에서 name이 username 인 값
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {   // 우리 회원이라면 UserDetails 객체 return

        System.out.println("loadUserByUsername(" + username + ")");

        // ⭐DB 조회 🔍 -> 받아온 String 타입의 username 으로 찾음 (Mybatis)
        User user = userService.findByUsername(username);

        // ⭐해당 username 의 user 가 DB에 있다면 👍
        // UserDetails 생성해서 리턴
        if(user != null){
            PrincipalDetails userDetails = new PrincipalDetails(user);
            userDetails.setUserService(userService);
            return userDetails;
        }


        // ⭐해당 username 의 user 가 없다면? ❌
        // UsernameNotFoundException 을 throw 해주어야 한다.
        throw new UsernameNotFoundException(username);

        // ⚠️주의! 여기서 null 리턴하면 예외 발생
    }
}
