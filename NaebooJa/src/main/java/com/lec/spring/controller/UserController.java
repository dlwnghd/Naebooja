package com.lec.spring.controller;

import com.lec.spring.domain.User;
import com.lec.spring.domain.UserValidator;
import com.lec.spring.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public UserController() {
        System.out.println(getClass().getName() + "() 생성");
    }

    // 로그인 페이지로 이동
    @GetMapping("/login")
    public void login() {
    }

    // 로그인에러 페이지로 이동
    // onAuthenticationFailure 에서 로그인 실패시 forwarding 용
    // request 에 담겨진 attribute 는 Thymeleaf 에서 그대로 표현 가능.
    @PostMapping("/loginError")
    public String loginError() {
        return "user/login";
    }

    @RequestMapping("/rejectAuth")
    public String rejectAuth(){
        return "common/rejectAuth";
    }

    // 회원가입 페이지로 이동
    @GetMapping("/register")
    public void register() {
    }

    // 회원가입 확인
    @PostMapping("/register")
    public String registerOk(@Valid User user
            , BindingResult result  // UserValidator 가 유효성 검증한 결과가 담긴 객체
            , Model model
            , RedirectAttributes redirectAttrs
    ){
        // 이미 등록된 중복된 아이디(username) 이 들어오면
        if(!result.hasFieldErrors("username") && userService.isExist(user.getUsername())){
            result.rejectValue("username", "이미 존재하는 아이디(username) 입니다.");
        }

        // 검증 에러가 있었다면 redirect 한다
        if(result.hasErrors()){
            redirectAttrs.addFlashAttribute("username", user.getUsername());
            redirectAttrs.addFlashAttribute("name", user.getName());

            List<FieldError> errList = result.getFieldErrors();
            for(FieldError err : errList) {
                redirectAttrs.addFlashAttribute("error", err.getCode());  // 가장 처음에 발견된 에러를 담아ㅣ 보낸다
                break;
            }

            return "redirect:/user/register";
        }

        // 에러 없었으면 회원 등록 진행
        String page = "/user/registerOk";
        int cnt = userService.register(user);
        model.addAttribute("result", cnt);
        return page;
    }

    // 아이디찾기
    @GetMapping("/findId")
    public String findId(Model model,
                         User user) {
        return "/user/findId";
    }

    // 아이디찾기 확인
    @PostMapping("/findId")
    public String findIdOk(User user, Model model,
                           @RequestParam("name") String name
    ) {

        String page = "user/findIdOk";
        String result = userService.findByName(name);
        model.addAttribute("result", result);
        return page;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.setValidator(new UserValidator());
    }
}
