package com.itany.zshop.backend.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.zshop.backend.vo.ProductVo;
import com.itany.zshop.common.constant.PaginationConstant;
import com.itany.zshop.common.util.ResponseResult;
import com.itany.zshop.dto.ProductDto;
import com.itany.zshop.pojo.Product;
import com.itany.zshop.pojo.ProductType;
import com.itany.zshop.service.ProductService;
import com.itany.zshop.service.ProductTypeService;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/backend/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductTypeService productTypeService;

    @ModelAttribute("productTypes")
    public List<ProductType> loadProductTypes() {
        List<ProductType> productTypes = productTypeService.findEnable();
        return productTypes;
    }

    @RequestMapping("/findAll")
    public String findAll(Integer pageNum, Model model) {
        if (ObjectUtils.isEmpty(pageNum)) {
            pageNum = PaginationConstant.PAGE_NUM;
        }

        PageHelper.startPage(pageNum, PaginationConstant.PAGE_SIZE);
        List<Product> products = productService.findAll();
        PageInfo<Product> pageInfo = new PageInfo<Product>(products);
        model.addAttribute("pageInfo", pageInfo);

        return "productManager";
    }

    @RequestMapping("/add")
    public String add(ProductVo productVo, Integer pageNum, HttpSession session, Model model) {
        String uploadPath = session.getServletContext().getRealPath("/WEB-INF/upload");

        try {
            //将VO转换为DTO
            ProductDto productDto = new ProductDto();
            PropertyUtils.copyProperties(productDto, productVo); //对象间属性的拷贝
            productDto.setInputStream(productVo.getFile().getInputStream());
            productDto.setFileName(productVo.getFile().getOriginalFilename());
            productDto.setUploadPath(uploadPath);

            productService.add(productDto);
            model.addAttribute("successMsg", "添加成功");
        } catch (Exception e) {
            //e.printStackTrace();
            model.addAttribute("errorMsg", e.getMessage());
        }

        return "forward:findAll?pageNum=" + pageNum;
    }

    @RequestMapping("/checkName")
    @ResponseBody
    public Map<String, Object> checkName(String name) {
        Map<String, Object> map = new HashMap<>();
        if (productService.checkName(name)) { //不存在，可用
            map.put("valid", true);
        } else {
            map.put("valid", false);
            map.put("message", "商品（" + name + "）已存在");
        }
        return map;
    }

    @RequestMapping("/findById")
    @ResponseBody
    public ResponseResult findById(int id) {
        Product product = productService.findById(id);
        return ResponseResult.success(product);
    }

    //上传图片的路径
    @RequestMapping("/getImage")
    public void getImage(String path, OutputStream outputStream) {
        productService.getImage(path, outputStream);
    }

    @RequestMapping("/modify")
    public String modify(ProductVo productVo, Integer pageNum, HttpSession session, Model model) {
        String uploadPath = session.getServletContext().getRealPath("/WEB-INF/upload");

        try {
            //将VO转换为DTO
            ProductDto productDto = new ProductDto();
            PropertyUtils.copyProperties(productDto, productVo); //对象间属性的拷贝
            productDto.setInputStream(productVo.getFile().getInputStream());
            productDto.setFileName(productVo.getFile().getOriginalFilename());
            productDto.setUploadPath(uploadPath);

            productService.modify(productDto);
            model.addAttribute("successMsg", "修改成功");
        } catch (Exception e) {
            //e.printStackTrace();
            model.addAttribute("errorMsg", e.getMessage());
        }

        return "forward:findAll?pageNum=" + pageNum;//刷新的时候在当前页
    }

    @RequestMapping("/removeById")
    @ResponseBody
    public ResponseResult removeById(int id) {
        productService.removeById(id);
        return ResponseResult.success();
    }
}