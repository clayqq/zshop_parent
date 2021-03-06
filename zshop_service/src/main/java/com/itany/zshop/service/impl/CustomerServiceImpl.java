package com.itany.zshop.service.impl;

import com.itany.zshop.common.constant.CustomerConstant;
import com.itany.zshop.common.exception.LoginErrorException;
import com.itany.zshop.dao.CustomerDao;
import com.itany.zshop.pojo.Customer;
import com.itany.zshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Customer login(String loginName, String password) throws LoginErrorException {
        Customer customer = customerDao.selectByLoginNameAndPassword(loginName,password, CustomerConstant.CUSTOMER_VALID);
        if(customer==null){
            throw new LoginErrorException("登录失败,用户名或密码错误");
        }
        return customer;
    }
}
