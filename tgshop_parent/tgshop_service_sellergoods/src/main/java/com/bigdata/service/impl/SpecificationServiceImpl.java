package com.bigdata.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.bigdata.mapper.SpecificationMapper;
import com.bigdata.mapper.SpecificationOptionMapper;
import com.bigdata.mypojo.MySpecification;
import com.bigdata.mypojo.PageResult;
import com.bigdata.pojo.Specification;
import com.bigdata.pojo.SpecificationExample;
import com.bigdata.pojo.SpecificationOption;
import com.bigdata.pojo.SpecificationOptionExample;
import com.bigdata.service.SpecificationService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @date 2018/10/22 22:39
 * @description
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {
    @Autowired
    private SpecificationMapper specificationMapper;
    @Autowired
    private SpecificationOptionMapper specificationOptionMapper;

    @Override
    public PageResult findPage(Specification specification, int page, int rows) {
        PageHelper.startPage(page,rows);
        SpecificationExample example = new SpecificationExample();
        if (specification != null){
            SpecificationExample.Criteria criteria = example.createCriteria();
            if (specification.getSpecName() != null && "".equals(specification.getSpecName())){
                criteria.andSpecNameLike("%"+specification.getSpecName()+"%");
            }
        }
        Page<Specification> pageList = (Page<Specification>) specificationMapper.selectByExample(example);

        return new PageResult(pageList.getTotal(),pageList.getResult());
    }

    @Override
    public void add(MySpecification mySpecification) {
        //先插入规格表,需要读取主键的值,因为规格选项需要依赖主键
        specificationMapper.insert(mySpecification.getSpecification());

        for (SpecificationOption specificationOption : mySpecification.getSpecificationOptionList()) {
            specificationOption.setSpecId(mySpecification.getSpecification().getId());
            specificationOptionMapper.insert(specificationOption);
        }
    }

    @Override
    public MySpecification findOne(Long id) {
        Specification specification = specificationMapper.selectByPrimaryKey(id);
        SpecificationOptionExample example = new SpecificationOptionExample();
        example.createCriteria().andSpecIdEqualTo(id);
        List<SpecificationOption> specificationOptions = specificationOptionMapper.selectByExample(example);
        MySpecification mySpecification = new MySpecification(specification, specificationOptions);
        return mySpecification;
    }

    @Override
    public void update(MySpecification mySpecification) {
        //1.修改规格
        specificationMapper.updateByPrimaryKey(mySpecification.getSpecification());
        //2.更新规格选项表(无法直接更新,只能删了重新插)
        //2.1把指定specId的规格选项数据先删除
        SpecificationOptionExample example = new SpecificationOptionExample();
        example.createCriteria().andSpecIdEqualTo(mySpecification.getSpecification().getId());
        specificationOptionMapper.deleteByExample(example);
        //2.2重写插入规格选项数据
        for (SpecificationOption specificationOption : mySpecification.getSpecificationOptionList()) {
            specificationOption.setSpecId(mySpecification.getSpecification().getId());
            specificationOptionMapper.insert(specificationOption);
        }
}

    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            specificationMapper.deleteByPrimaryKey(id);
            SpecificationOptionExample example = new SpecificationOptionExample();
            example.createCriteria().andSpecIdEqualTo(id);
            specificationOptionMapper.deleteByExample(example);
        }
    }

    @Override
    public List<Map> selectOptionList() {
        return specificationMapper.selectOptionList();
    }
}
