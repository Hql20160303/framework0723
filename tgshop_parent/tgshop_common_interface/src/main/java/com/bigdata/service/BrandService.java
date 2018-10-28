package com.bigdata.service;

import com.bigdata.pojo.Brand;
import com.bigdata.mypojo.PageResult;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @date 2018/10/20 22:22
 * @description
 */
public interface BrandService {
    List<Brand> findAll();

    void delete(Long[] ids);

    void update(Brand brand);

    Brand selectOne(Long id);

    PageResult findPage(int page, int rows);

    void add(Brand brand);

    PageResult findPage(Brand brand, int page, int rows);

    List<Map> selectOptionList();
}
