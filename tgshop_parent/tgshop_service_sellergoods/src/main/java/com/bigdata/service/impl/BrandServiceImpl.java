package com.bigdata.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.bigdata.mapper.BrandMapper;
import com.bigdata.pojo.Brand;
import com.bigdata.pojo.BrandExample;
import com.bigdata.mypojo.PageResult;
import com.bigdata.service.BrandService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @date 2018/10/20 22:23
 * @description
 */

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandMapper mapper;

    @Override
    public List<Brand> findAll() {
        return mapper.selectByExample(null);
    }

    @Override
    public void add(Brand brand) {
        mapper.insert(brand);
    }

    @Override
    public PageResult findPage(Brand brand, int page, int rows) {
        PageHelper.startPage(page,rows);
        BrandExample example = new BrandExample();
        BrandExample.Criteria criteria = example.createCriteria();
        if (brand.getName() != null && brand.getName().length() > 0){
            criteria.andNameLike("%"+brand.getName()+"%");
        }
        if (brand.getFirstChar() != null && brand.getFirstChar().length() == 1){
            criteria.andFirstCharEqualTo(brand.getFirstChar());
        }
        Page<Brand> list = (Page<Brand>) mapper.selectByExample(example);
        PageResult pageResult = new PageResult(list.getTotal(), list);
        return pageResult;
    }

    @Override
    public List<Map> selectOptionList() {
        return mapper.selectOptionList();
    }

    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            mapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public void update(Brand brand) {
        mapper.updateByPrimaryKey(brand);
    }

    @Override
    public Brand selectOne(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(int page, int rows) {
        PageHelper.startPage(page,rows);

        List<Brand> brands = mapper.selectByExample(null);
        PageInfo<Brand> pageInfo = new PageInfo<>(brands);
        PageResult result = new PageResult(pageInfo.getTotal(), pageInfo.getList());

        return result;
    }
}
