package com.itany.zshop.service;

import com.itany.zshop.common.exception.ProductTypeExistException;
import com.itany.zshop.pojo.ProductType;

import java.util.List;

/**
 * Author：汤小洋
 * Date：2018-04-26 11:51
 * Description：<描述>
 */
public interface ProductTypeService {

    /**
     * 查找所有商品类型信息
     * @return
     */
    public List<ProductType> findAll();

    /**
     * 添加商品类型
     * 判断商品类型名称是否已存在
     *      如果已存在，抛出异常
     *      如果不存在，则保持
     * @param name
     */
    public void add(String name) throws ProductTypeExistException;

    /**
     * 根据id查找商品类型
     * @param id
     * @return
     */
    ProductType findById(int id);

    /**
     * 修改商品类型的名称
     * @param id
     * @param name
     */
    void modifyName(int id, String name) throws ProductTypeExistException;

    /**
     * 根据id删除商品类型
     *      判断是否存在该商品类型的商品，如果存在，则抛出异常
     * @param id
     */
    void removeById(int id);

    /**
     * 修改商品类型的状态
     * @param id
     */
    void modifyStatus(int id);

    /**
     * 查找有效的商品类型信息
     * @return
     */
    List<ProductType> findEnable();

}
