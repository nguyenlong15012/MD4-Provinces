package com.codegym.controller;

import com.codegym.model.Province;
import com.codegym.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/provinces")
public class ProvinceController {
    @Autowired
    IProvinceService provinceService;

    @GetMapping
    public ModelAndView show(){
        ModelAndView modelAndView = new ModelAndView("/province/list");
        modelAndView.addObject("province", provinceService.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/province/create");
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView save(Province province){
        provinceService.save(province);
        ModelAndView modelAndView = new ModelAndView("redirect:/provinces");
        return modelAndView;
    }
}
