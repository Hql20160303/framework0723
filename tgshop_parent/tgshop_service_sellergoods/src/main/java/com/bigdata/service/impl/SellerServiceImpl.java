package com.bigdata.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.bigdata.mapper.SellerMapper;
import com.bigdata.mypojo.PageResult;
import com.bigdata.pojo.Seller;
import com.bigdata.pojo.SellerExample;
import com.bigdata.service.SellerService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;


/**
 * @author Administrator
 * @date 2018/10/23 21:47
 * @description
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerMapper sellerMapper;

    @Override
    public void add(Seller seller) {
        seller.setStatus("0");
        seller.setCreateTime(new Date());
        sellerMapper.insert(seller);
    }

    @Override
    public PageResult search(Seller seller, int page, int rows) {
        PageHelper.startPage(page,rows);
        SellerExample example = new SellerExample();
        SellerExample.Criteria criteria = example.createCriteria();
        if (seller != null){
            if(seller.getNickName() != null && !"".equals(seller.getNickName())){
                criteria.andNickNameLike("%"+seller.getNickName()+"%");
            }
        }
        Page<Seller> list = (Page<Seller>) sellerMapper.selectByExample(example);
        PageResult pageResult = new PageResult(list.getTotal(), list.getResult());
        return pageResult;
    }

    @Override
    public Seller findOne(String id) {
        Seller seller = sellerMapper.selectByPrimaryKey(id);
        return seller;
    }

    @Override
    public void updateStatus(String sellerId, String status) {
        Seller seller = new Seller();
        seller.setStatus(status);
        seller.setSellerId(sellerId);
        sellerMapper.updateByPrimaryKeySelective(seller);
    }
}
