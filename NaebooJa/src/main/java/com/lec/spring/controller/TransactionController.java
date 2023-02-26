package com.lec.spring.controller;

import com.lec.spring.service.TransactionService;
import com.lec.spring.util.U;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/transaction")
public class TransactionController {
    private TransactionService transactionService;

    @Autowired
    public void setTransactionService(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    public TransactionController() {
        System.out.println("TransactionController() 생성");
    }

    @RequestMapping("/home")
    public void home() {}

    @GetMapping("/daily")
    public void daily(Model model){
//        model.addAttribute("list", transactionService.list(model));
    }

    @GetMapping("/monthly")
    public void monthly(Model model){

    }

    @GetMapping("/calendar")
    public void calendar(Model model){

    }

}
