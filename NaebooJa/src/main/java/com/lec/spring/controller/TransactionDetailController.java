package com.lec.spring.controller;

import com.lec.spring.domain.QryTransactionList;
import com.lec.spring.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController  // data 를 response 한다  ('View' 를 리턴하는게 아니다!)
@RequestMapping("/transactionDetail")
public class TransactionDetailController {
    @Autowired
    private TransactionService transactionService;


    public TransactionDetailController() {
        System.out.println(getClass().getName() + "() 생성");
    }

    @GetMapping("/transacList")
    public QryTransactionList list(String date){

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date ChangeDate = new Date();
        try {
            ChangeDate = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return transactionService.transacDetail(ChangeDate);
    }

    @GetMapping("/transacListbyMonth")
    public QryTransactionList listbyMonth(String date){

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date ChangeDate = new Date();
        try {
            ChangeDate = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return transactionService.transacDetailbyMonth(ChangeDate);
    }
}