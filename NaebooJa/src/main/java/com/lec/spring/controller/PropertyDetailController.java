package com.lec.spring.controller;

import com.lec.spring.domain.QryPropertyList;
import com.lec.spring.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController  // data 를 response 한다  ('View' 를 리턴하는게 아니다!)
@RequestMapping("/propertyDetail")
public class PropertyDetailController {
    @Autowired
    private PropertyService propertyService;

    public PropertyDetailController() {
        System.out.println(getClass().getName() + "() 생성");
    }

    @GetMapping("/propList")
    public QryPropertyList list(Long id, String date){

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date ChangeDate = null;
        try {
            ChangeDate = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return propertyService.propDetail(id, ChangeDate);}
}


























