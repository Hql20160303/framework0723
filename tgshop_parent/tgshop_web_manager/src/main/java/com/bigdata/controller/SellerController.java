package com.bigdata.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bigdata.mypojo.PageResult;
import com.bigdata.mypojo.Result;
import com.bigdata.pojo.Seller;
import com.bigdata.service.SellerService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @date 2018/10/23 22:35
 * @description
 */
@RestController
@RequestMapping("seller")
public class SellerController {

    @Reference
    private SellerService sellerService;

    @RequestMapping("search")
    public PageResult search(@RequestBody Seller seller , int page , int rows){
       return sellerService.search(seller,page,rows);
    }
    @RequestMapping("findOne")
    public Seller findOne(String id){
        return sellerService.findOne(id);
    }
    @RequestMapping("updateStatus")
    public Result updateStatus(String sellerId, String status){
        try {
            sellerService.updateStatus(sellerId,status);
            return new Result(true, "修改成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败!");
        }
    }
}
