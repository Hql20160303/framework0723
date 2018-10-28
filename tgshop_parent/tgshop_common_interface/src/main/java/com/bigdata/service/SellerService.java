package com.bigdata.service;

import com.bigdata.mypojo.PageResult;
import com.bigdata.pojo.Seller;

/**
 * @author Administrator
 * @date 2018/10/23 21:46
 * @description
 */
public interface SellerService {

    void add(Seller seller);

    PageResult search(Seller seller, int page, int rows);

    Seller findOne(String id);

    void updateStatus(String sellerId, String status);
}
