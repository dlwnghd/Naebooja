package com.lec.spring.controller;

import com.lec.spring.service.BoardService;
import com.lec.spring.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/property")
public class PropertyController {

    private BoardService boardService;
    private PropertyService propertyService;

    @Autowired
    public void setPropertyService(PropertyService propertyService) {this.propertyService = propertyService;}

    public PropertyController() {
        System.out.println("PropertyController() 생성");
    }

    @GetMapping("/list")
    public void property(Integer page, Model model){
        boardService.list(page, model);
    }

}