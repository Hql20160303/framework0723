package com.bigdata.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bigdata.pojo.Brand;
import com.bigdata.mypojo.PageResult;
import com.bigdata.mypojo.Result;
import com.bigdata.service.BrandService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @date 2018/10/20 22:25
 * @description
 */
@RestController
@RequestMapping("brand")
public class BrandController {
    @Reference
    private BrandService service;

    @RequestMapping("findAll")
    public List<Brand> findAll(){
        return service.findAll();
    }
    @RequestMapping("findPage")
    public PageResult findPage(int page , int rows){
        PageResult pageResult = service.findPage(page, rows);
        return pageResult;
    }

    @RequestMapping("add")
    public Result add(@RequestBody Brand brand){
        try {
            service.add(brand);
            return new Result(true,"添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"添加失败");
        }
    }

    @RequestMapping("/update")
    public Result update(@RequestBody Brand brand) {
        try {
            service.update(brand);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }

    @RequestMapping("delete")
    public Result delete(Long[] ids){
        try {
            service.delete(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }

    @RequestMapping("findOne")
    public Brand findOne(Long id){
        Brand brand = service.selectOne(id);
        return brand;
    }
    @RequestMapping("search")
    public PageResult search(@RequestBody Brand brand,int page,int rows){
        return service.findPage(brand, page, rows);
    }

    @RequestMapping("selectOptionList")
    public List<Map> selectOptionList(){
        return service.selectOptionList();
    }
}
