package com.lec.spring.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override   // 실행하기전 session 은 이미 날라가 있음
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("### 로그아웃 성공 : CustomLogoutSuccessHandler 동작 ###");

        // 로그아웃 시간 남기기
        LocalDateTime logoutTime = LocalDateTime.now();
        System.out.println("로그아웃시간: " + logoutTime);

        // 사용시간 계산해보기
        LocalDateTime loginTime = (LocalDateTime)request.getSession().getAttribute("loginTime");
        if(loginTime != null) {
            long seconds = loginTime.until( logoutTime, ChronoUnit.SECONDS );
            System.out.println("사용시간: " + seconds + " 초");
        }
        request.getSession().invalidate();   // 그리고 세션을 수동으로 invalidate (session 날리기)

        // 로그아웃 직후에 갈 수 있는 url
        String redirectUrl = "/home";

        // ret_url  이 있는 경우 logout 하고 해당  url 로 redirect
        if(request.getParameter("ret_url") != null) {
            redirectUrl = request.getParameter("ret_url");
        }

        response.sendRedirect(redirectUrl);
    }
}
