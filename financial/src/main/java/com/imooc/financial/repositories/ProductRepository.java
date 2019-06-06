package com.imooc.financial.repositories;

import com.imooc.financial.bean.ProductBean;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductBean,String> {

}
