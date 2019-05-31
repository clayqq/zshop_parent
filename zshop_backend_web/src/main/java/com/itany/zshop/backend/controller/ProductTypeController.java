package com.itany.zshop.backend.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.zshop.common.constant.PaginationConstant;
import com.itany.zshop.common.constant.ResponseStatusConstant;
import com.itany.zshop.common.exception.ProductTypeExistException;
import com.itany.zshop.common.util.ResponseResult;
import com.itany.zshop.pojo.ProductType;
import com.itany.zshop.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Author：汤小洋
 * Date：2018-04-26 11:06
 * Description：<描述>
 */
@Controller
@RequestMapping("/backend/productType")
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;

    @RequestMapping("/findAll")
    public String findAll(Integer pageNum,Model model){
        if(ObjectUtils.isEmpty(pageNum)){
            pageNum= PaginationConstant.PAGE_NUM;
        }

        //设置分页
        PageHelper.startPage(pageNum,PaginationConstant.PAGE_SIZE);

        //查找数据
        List<ProductType> productTypes = productTypeService.findAll();

        //将查找出的结果封装到PageInfo对象中
        PageInfo<ProductType> pageInfo=new PageInfo<>(productTypes);
        //pageInfo.getPageNum();
        //pageInfo.getPages();
        //pageInfo.getNextPage();
        //pageInfo.getPrePage();
        //pageInfo.getList();

        model.addAttribute("pageInfo",pageInfo);

        return "productTypeManager";
    }

    @RequestMapping("/add")
    @ResponseBody
    public ResponseResult add(String name){
        ResponseResult result = new ResponseResult();
        try {
            productTypeService.add(name);
            result.setStatus(ResponseStatusConstant.RESPONSE_STATUS_SUCCESS);
            result.setMessage("添加成功");
        } catch (ProductTypeExistException e) {
            //e.printStackTrace();
            result.setStatus(ResponseStatusConstant.RESPONSE_STATUS_FAIL);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @RequestMapping("/findById")
    @ResponseBody
    public ResponseResult findById(int id){
        ProductType productType=productTypeService.findById(id);
        return ResponseResult.success(productType);
    }

    @RequestMapping("/modifyName")
    @ResponseBody
    public ResponseResult modifyName(int id,String name){
        try {
            productTypeService.modifyName(id,name);
            return ResponseResult.success("修改成功");
        } catch (ProductTypeExistException e) {
            //e.printStackTrace();
            return ResponseResult.fail(e.getMessage());
        }
    }

    @RequestMapping("/removeById")
    @ResponseBody
    public ResponseResult removeById(int id){
        productTypeService.removeById(id);
        return ResponseResult.success();
    }

    @RequestMapping("/modifyStatus")
    @ResponseBody
    public ResponseResult modifyStatus(int id){
        productTypeService.modifyStatus(id);
        return ResponseResult.success();
    }

}
