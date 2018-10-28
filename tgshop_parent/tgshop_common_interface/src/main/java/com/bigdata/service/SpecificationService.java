package com.bigdata.service;

import com.bigdata.mypojo.MySpecification;
import com.bigdata.mypojo.PageResult;
import com.bigdata.pojo.Specification;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @date 2018/10/22 22:38
 * @description
 */
public interface SpecificationService {
    PageResult findPage(Specification specification, int page, int rows);

    void add(MySpecification mySpecification);

    MySpecification findOne(Long id);

    void update(MySpecification mySpecification);

    void delete(Long[] ids);

    List<Map> selectOptionList();
}
