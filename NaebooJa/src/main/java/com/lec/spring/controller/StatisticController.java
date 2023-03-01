package com.lec.spring.controller;
;
import com.lec.spring.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/statistic")
public class StatisticController {

    private StatisticService statisticService;

    @Autowired
    public void setStatisticService(StatisticService statisticService) {this.statisticService = statisticService;}

    public StatisticController() {
        System.out.println("StatisticController() 생성");
    }

    @GetMapping("/stat")
    public void statistic(Model model){
    }

}