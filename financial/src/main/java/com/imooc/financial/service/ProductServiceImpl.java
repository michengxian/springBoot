package com.imooc.financial.service;

import com.imooc.financial.bean.ProductBean;
import com.imooc.financial.constant.ProductStatus;
import com.imooc.financial.controller.ProductController;
import com.imooc.financial.repositories.ProductRepository;
import com.sun.xml.internal.bind.v2.TODO;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import sun.jvm.hotspot.code.Location;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;


@Service
public class ProductServiceImpl implements ProductService {


    private static Logger LOG = Logger.getLogger(ProductService.class.getName());


    @Autowired
    private ProductRepository productRepository;


    public ProductBean addProduct(ProductBean bean){
        LOG.fine("创建产品，参数：{"+bean+"}");
        // 数据校验
        checkProduct(bean);

        // 设置默认值
        setDefault(bean);
        ProductBean result = productRepository.save(bean);
        LOG.fine("创建产品，结果：{"+result+"}");
        return result;
    }

    /**
     * 设置默认值
     * 创建时间、更新时间
     * 投资步长、锁定期、状态
     */
    private void setDefault(ProductBean bean){
        if (bean.getCreateAt()==null){
            bean.setCreateAt(new Date());
        }
        if (bean.getUpdateAt()==null){
            bean.setUpdateAt(new Date());
        }
        if (bean.getStepAmount()==null){
            bean.setStepAmount(BigDecimal.ZERO);
        }
        if (bean.getLockTerm()==null){
            bean.setLockTerm(0);
        }
        if (bean.getSTATUS()==null){
            bean.setSTATUS(ProductStatus.AUDINTING.name());
        }
    }


    /**
     * 校验非空数据
     * 收益率0-30以内
     * 投资步长必须为整数
     * @param bean
     */
    public void checkProduct(ProductBean bean){
        Assert.notNull(bean.getId(),"产品编号不能为空");
        Assert.notNull(bean.getName(),"产品名称不能为空");
        Assert.notNull(bean.getThresholdAmount(),"起投金额不能为空");
        Assert.notNull(bean.getLockTerm(),"锁定期不能为空");
        Assert.notNull(bean.getSTATUS(),"状态不能为空");
        Assert.isTrue(BigDecimal.ZERO.compareTo(bean.getRewardRate())<0,"收益率不能为0");
        Assert.isTrue(BigDecimal.valueOf(30).compareTo(bean.getRewardRate())>=0,"收益率不能大于30%");
        Assert.isTrue(BigDecimal.valueOf(bean.getStepAmount().longValue()).compareTo(bean.getStepAmount())==0,"投资步长需为整数");


    }


    /**
     * 根据id查询单个产品
     * @param id
     * @return
     */
    public ProductBean searchProductById(String id){
        Assert.notNull(id,"产品编号不能为空");
        LOG.fine("根据id查询单个产品，入参id：{"+id+"}");

        ProductBean bean = new ProductBean();
        bean.setId(id);
        Example<ProductBean> productBeanExample = Example.of(bean);
        ProductBean res = productRepository.findOne(productBeanExample).orElse(null);
        LOG.fine("findOne-根据id查询单个产品，结果：{"+res+"}");

        ProductBean result = productRepository.findById(id).get();
        LOG.fine("findById-根据id查询单个产品，结果：{"+result+"}");
        return result;
    }


    /**
     * 分页查询产品
     * @param idList
     * @param minRewardRate
     * @param maxRewardRate
     * @param statusList
     * @param pageable
     * @return
     */
    public Page<ProductBean> query(List<String> idList,
                                   BigDecimal minRewardRate,
                                   BigDecimal maxRewardRate,
                                   List<String> statusList,
                                   Pageable pageable){
        LOG.fine("查询产品,idList={"+idList+"},minRewardRate:{"+minRewardRate+"},maxRewardRate:{"+maxRewardRate+"},statusList:{"+statusList+"},pageable:{"+pageable+"}");

        TODO 貌似这个版本不支持Specification;

//        Specification<ProductBean> specification = new Specification<ProductBean>() {
//            @Nullable
//            @Override
//            public Predicate toPredicate(Root<ProductBean> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
//                Expression<String> idcol = root.get("id");
//                Expression<BigDecimal> rewardRateCol = root.get("rewRate");
//                Expression<String> statusCol = root.get("status");
//                List<Predicate> predicates = new ArrayList<>();
//                if (idList!=null && idList.size()>0){
//                    predicates.add(idcol.in(idList));
//                }
//
//                if (BigDecimal.ZERO.compareTo(minRewardRate)<0){
//                    predicates.add(criteriaBuilder.ge(rewardRateCol,minRewardRate));
//                }
//                if (BigDecimal.ZERO.compareTo(maxRewardRate)<0){
//                    predicates.add(criteriaBuilder.le(rewardRateCol,maxRewardRate));
//                }
//                if (statusList!=null && statusList.size()>0){
//                    predicates.add(statusCol.in(statusList));
//                }
//                criteriaQuery.where(predicates.toArray(new Predicate[0]));
//
//                return null;
//            }
//
//        };


        ProductBean bean = new ProductBean();
        ExampleMatcher matcher = ExampleMatcher.matching();
        Example<ProductBean> example = Example.of(bean,matcher);


        Page<ProductBean> page = productRepository.findAll(pageable);

        return page;

    }

    /**
     * 根据状态查询
     * @param status
     * @return
     */
    public Page<ProductBean> queryByStatus(String status,Pageable pageable){
        ProductBean bean = new ProductBean();
        bean.setSTATUS(status);
        ExampleMatcher matcher = ExampleMatcher.matching();
        Example<ProductBean> example = Example.of(bean,matcher);

        Page<ProductBean> page = productRepository.findAll(example,pageable);

        return page;
    }

}
