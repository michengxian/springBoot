package com.imooc.financial.service;

import com.imooc.financial.bean.ProductBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    public ProductBean addProduct(ProductBean bean);


    public ProductBean searchProductById(String id);


    public Page<ProductBean> query(List<String> idList,
                                   BigDecimal minRewardRate,
                                   BigDecimal maxRewardRate,
                                   List<String> statusList,
                                   Pageable pageable);

    public Page<ProductBean> queryByStatus(String status,Pageable pageable);
}
