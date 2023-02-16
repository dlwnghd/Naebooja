package com.lec.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/statistic")
public class StatisticController {

    public StatisticController() {
        System.out.println("StatisticController() 생성");
    }

    @GetMapping("/test")
    public void statistic(){}

}