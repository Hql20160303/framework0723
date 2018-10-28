package com.bigdata.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.bigdata.mapper.TypeTemplateMapper;
import com.bigdata.mypojo.PageResult;
import com.bigdata.pojo.TypeTemplate;
import com.bigdata.pojo.TypeTemplateExample;
import com.bigdata.service.TypeTemplateService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Administrator
 * @date 2018/10/23 11:39
 * @description
 */
@Service
public class TypeTemplateServiceImpl implements TypeTemplateService {
    @Autowired
    private TypeTemplateMapper typeTemplateMapper;

    @Override
    public PageResult findPage(TypeTemplate typeTemplate, int page, int rows) {
        PageHelper.startPage(page, rows);
        TypeTemplateExample example = new TypeTemplateExample();
        TypeTemplateExample.Criteria criteria = example.createCriteria();
        if (typeTemplate != null) {
            if (typeTemplate.getName() != null && !"".equals(typeTemplate.getName())) {
                criteria.andNameLike("%" + typeTemplate.getName() + "%");
            }
        }
        Page<TypeTemplate> list = (Page<TypeTemplate>) typeTemplateMapper.selectByExample(example);
        PageResult pageResult = new PageResult(list.getTotal(), list);
        return pageResult;
    }

    @Override
    public void add(TypeTemplate typeTemplate) {
        typeTemplateMapper.insertSelective(typeTemplate);
    }

    @Override
    public void update(TypeTemplate typeTemplate) {
        typeTemplateMapper.updateByPrimaryKeySelective(typeTemplate);
    }

    @Override
    public TypeTemplate findOne(Long id) {
        return typeTemplateMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            typeTemplateMapper.deleteByPrimaryKey(id);
        }
    }
}
