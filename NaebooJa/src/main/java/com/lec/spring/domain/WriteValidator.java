package com.lec.spring.domain;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

// Validator 는 Controller 에 등록되어 동작하게 해야 한다
public class WriteValidator implements Validator {


    // 이 Validator가 제공된 Class의 인스턴스(clazz)를 유효성검사할 수 있는가?
    @Override
    public boolean supports(Class<?> clazz) {
        System.out.println("supports(" + clazz.getName() + ")");

        // ↓ 검증할 객체의 클래스 타입인지 확인 : Write = clazz; 가능 여부
        boolean result = Write.class.isAssignableFrom(clazz);
        System.out.println(result);
        return result;
    }

    // 주어진 객체(target)에 유효성검사를 하고
    // 유효성검사에 오류가 있는 경우 주어진 객체에 이 오류들을 errors 에 등록 한다.
    // 아래 validate() 는 Spring 이 기본적인 binding이 수행한 이후에 호출된다.
    // 따라서, errors 에는 Spring이 수행한 기본적인 binding 에러 들이 이미 담겨 있고
    // target 에는 binding 이 완료한 커맨드 객체가 전달된다.
    @Override
    public void validate(Object target, Errors errors) {
        Write write = (Write)target;

        System.out.println("validate() 호출 : " + write);

//        // 바인딩한 객체에 대한 다양한 검증 수행.
//        String user = write.getUser();
//        if(user == null || user.trim().isEmpty()){
//            errors.rejectValue("user", "user 사용자는 필수입니다");
//        }
        
//        String subject = write.getSubject();
//        if(subject == null || subject.trim().isEmpty()){
//            errors.rejectValue("subject", "subject 글 제목은 필수입니다");
//        }

        // ValidationUtils 사용
        // 단순히 빈(empty) 폼 데이터를 처리할때는 아래 와 같이 사용 가능
        // 두번째 매개변수 "subject" 은 반드시 target 클래스의 필드명 이어야 함
        // 게다가 Errors 에 등록될때도 동일한 field 명으로 등록된다.
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "subject", "subject 글 제목은 필수입니다");

    }
}
