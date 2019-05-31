package com.itany.zshop.front.controller;

import com.itany.zshop.common.exception.LoginErrorException;
import com.itany.zshop.pojo.Customer;
import com.itany.zshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/front/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/loginByAccount")
    public String loginByAccount(String loginName,String password){
        try {
            Customer customer = customerService.login(loginName, password,HttpSession session);
            session.attribute("customer",customer):
        } catch (LoginErrorException e) {
            e.printStackTrace();
        }
    }
}
