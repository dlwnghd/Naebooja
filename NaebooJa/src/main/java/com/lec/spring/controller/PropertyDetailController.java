package com.lec.spring.controller;

import com.lec.spring.domain.QryCommentList;
import com.lec.spring.domain.QryPropertyList;
import com.lec.spring.domain.QryResult;
import com.lec.spring.service.CommentService;
import com.lec.spring.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController  // data 를 response 한다  ('View' 를 리턴하는게 아니다!)
@RequestMapping("/propertyDetail")
public class PropertyDetailController {
    @Autowired
    private PropertyService propertyService;

    public PropertyDetailController() {
        System.out.println(getClass().getName() + "() 생성");
    }

    @GetMapping("/propList")
    public QryPropertyList list(Long id){return propertyService.propDetail(id);}
}


























