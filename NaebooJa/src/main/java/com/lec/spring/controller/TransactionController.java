package com.lec.spring.controller;

import com.lec.spring.domain.Transaction;
import com.lec.spring.domain.Write;
import com.lec.spring.service.TransactionService;
import com.lec.spring.util.U;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

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
    public void home(Model model) {
        model.addAttribute("list", transactionService.list(model));
    }

    @GetMapping("/daily")
    public void daily(Model model, String date){
        LocalDate localdate = LocalDate.now();
//      date 값이 있다면 ,
        if(date != null){
            localdate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
        }
        model.addAttribute("income", transactionService.listByTypeinDay("수입", localdate));
        model.addAttribute("outcome",transactionService.listByTypeinDay("지출", localdate));
        model.addAttribute("transfer",transactionService.listByTypeinDay("이체", localdate));
        model.addAttribute("list", transactionService.listByDay(localdate));
        model.addAttribute("date", localdate);
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
        LocalDate date = LocalDate.parse("2023-02-16");
        model.addAttribute("income", transactionService.listByTypeinMonth("수입", date));
        model.addAttribute("outcome",transactionService.listByTypeinMonth("지출", date));
        model.addAttribute("transfer",transactionService.listByTypeinMonth("이체", date));
        model.addAttribute("list",transactionService.listByMonth(date));
    }

    @GetMapping("/insert")
    public void insert(){}

    @PostMapping("/insert")
    public String insertOk(@ModelAttribute("dto")Transaction transaction
            , Model model
    ){
        model.addAttribute("result", transactionService.insert(transaction));
        return "transaction/insertOk";
    }
}
