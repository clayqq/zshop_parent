package com.itany.zshop.dto;

import java.io.InputStream;

/**
 * Author：汤小洋
 * Date：2018-04-27 11:31
 * Description：<描述>
 */
public class ProductDto {
    private Integer id;
    private String name;
    private Double price;
    private Integer productTypeId;
    private InputStream inputStream; //文件的输入流
    private String fileName; //文件的名称
    private String uploadPath; //文件的上传路径


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }
}
