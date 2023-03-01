package com.lec.spring.controller;

import com.lec.spring.domain.QryStatisticList;
import com.lec.spring.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController  // data 를 response 한다  ('View' 를 리턴하는게 아니다!)
@RequestMapping("/statisticDetail")
public class StatisticDetailController {
    @Autowired
    private StatisticService statisticService;

    public StatisticDetailController() {
        System.out.println(getClass().getName() + "() 생성");
    }

    @GetMapping("/statisticList")
    public QryStatisticList list(Long id, String date){

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date ChangeDate = null;
        try {
            ChangeDate = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return statisticService.statisticDetail(id, ChangeDate);}

    @GetMapping("/statList")
    public QryStatisticList list(){

        return statisticService.Qrylist();}

    @GetMapping("/statList_income")
    public QryStatisticList QrylistIncome(){

        return statisticService.QrylistIncome();}
}


























