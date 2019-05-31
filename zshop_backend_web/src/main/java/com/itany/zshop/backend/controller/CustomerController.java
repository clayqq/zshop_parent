package com.itany.zshop.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/backend/customer")
public class CustomerController {

    @RequestMapping("/findAll")
    public String findAll(){
        return "customerManager";
    }

}
