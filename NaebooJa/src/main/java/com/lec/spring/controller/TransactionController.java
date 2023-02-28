package com.lec.spring.controller;

import com.lec.spring.service.TransactionService;
import com.lec.spring.util.U;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.Date;

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
        LocalDate date = LocalDate.of(2023,2,26);
        model.addAttribute("income", transactionService.listByTypeinDay("수입", date));
        model.addAttribute("outcome",transactionService.listByTypeinDay("지출", date));
        model.addAttribute("transfer",transactionService.listByTypeinDay("이체", date));
        model.addAttribute("list", transactionService.listByDay(date));
        model.addAttribute("date", date);
    }

    @GetMapping("/monthly")
    public void monthly(Model model){
        LocalDate date = LocalDate.now();
        model.addAttribute("income", transactionService.listByTypeinMonth("수입", date));
        model.addAttribute("outcome",transactionService.listByTypeinMonth("지출", date));
        model.addAttribute("transfer",transactionService.listByTypeinMonth("이체", date));
        model.addAttribute("list", transactionService.listByMonth(date));
        model.addAttribute("date", date);
    }

    @GetMapping("/calendar")
    public void calendar(Model model){
        model.addAttribute("list",transactionService.list(model));
    }

}
