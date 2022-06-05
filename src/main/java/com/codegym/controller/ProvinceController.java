package com.codegym.controller;

import com.codegym.model.Province;
import com.codegym.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("/province/edit");
        //Vì hàm findById là kiểu dữ liệu Optinal( nên phải dùng get() để lấy ra
        Province province = provinceService.findById(id).get();
        modelAndView.addObject("pro", province);
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView update(@PathVariable Long id, Province province){
        ModelAndView modelAndView = new ModelAndView("redirect:/provinces");
        Province oldProvince = provinceService.findById(id).get();
        oldProvince.setName(province.getName());
        oldProvince.setDescription(province.getDescription());
        provinceService.save(oldProvince);
        return modelAndView;
    }
}
