package com.bigdata.service;

import com.bigdata.mypojo.PageResult;
import com.bigdata.pojo.TypeTemplate;

/**
 * @author Administrator
 * @date 2018/10/23 11:39
 * @description
 */
public interface TypeTemplateService {
    PageResult findPage(TypeTemplate typeTemplate, int page, int rows);

    void add(TypeTemplate typeTemplate);

    void update(TypeTemplate typeTemplate);

    TypeTemplate findOne(Long id);

    void delete(Long[] ids);
}
