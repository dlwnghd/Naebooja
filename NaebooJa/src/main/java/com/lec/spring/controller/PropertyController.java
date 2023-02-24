package com.lec.spring.controller;

import com.lec.spring.domain.Property;
import com.lec.spring.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/property")
public class PropertyController {

    private PropertyService propertyService;

    @Autowired
    public void setPropertyService(PropertyService propertyService) {this.propertyService = propertyService;}

    public PropertyController() {
        System.out.println("PropertyController() 생성");
    }

    @GetMapping("/prop")
    public void property(Model model){

        // 자산 리스트
//        System.out.println("🔴🔴🔴🔴🔴🔴🔴🔴🔴🔴🔴🔴");
        propertyService.list(model);
    }

    @GetMapping("/write")
    public void write(){}

    @PostMapping("/write")
    public String writeOk(
               @ModelAttribute("prop_dto") Property property
            , Model model
    ){
        model.addAttribute("result", propertyService.write(property));
        return "property/writeOk";
    }
    @PostMapping("/delete")
    public String deleteOk(long id, Model model){
        model.addAttribute("result", propertyService.deleteById(id));
        return "property/deleteOk";
    }

}