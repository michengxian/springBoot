package com.imooc.financial.controller;


import com.imooc.financial.bean.ProductBean;
import com.imooc.financial.service.ProductService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/product")
public class ProductController {

    private static Logger LOG = Logger.getLogger(ProductController.class.getName());
//    private static Logger LOG = (Logger) LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "",method = RequestMethod.POST)
    public ProductBean addProduct(@RequestBody ProductBean bean){
        LOG.info("创建产品，参数：{"+bean+"}");
        ProductBean result = productService.addProduct(bean);

        LOG.info("创建产品，结果：{"+result+"}");
        return result;
    }


    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ProductBean searchProductById (@PathVariable String id){
        LOG.info("根据id查询，参数：{"+id+"}");
        ProductBean result = productService.searchProductById(id);
        LOG.info("根据id查询，结果：{"+result+"}");
        return result;
    }

    @RequestMapping(value = "",method = RequestMethod.GET)
    public Page<ProductBean> queryByStatus(String status,
                                           @RequestParam(defaultValue = "0") int pageNum,
                                           @RequestParam(defaultValue = "20") int pageSize){
        LOG.info("根据状态查询，参数：{"+status+"}");

        Pageable pageable = new PageRequest(pageNum,pageSize);

        Page<ProductBean> page = productService.queryByStatus(status,pageable);

        return page;
    }


}
