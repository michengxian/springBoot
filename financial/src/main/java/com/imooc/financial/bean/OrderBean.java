package com.imooc.financial.bean;

import com.imooc.financial.tools.MethodNote;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name = "imooc_order_t")
public class OrderBean {

    @Id
    @MethodNote(note = "订单编号",nullable = false,length = 50)
    private String orderId;

    @MethodNote(note = "渠道编号",nullable = false,length = 50)
    private  String chanId;

    @MethodNote(note = "产品编号",nullable = false,length = 50)
    private  String productId;

    @MethodNote(note = "渠道用户编号",nullable = false,length = 50)
    private String chanUserId;

    @MethodNote(note = "类型，APPLY:申购，REDEEM:赎回",nullable = false,length = 50)
    private String orderType;

    @MethodNote(note = "状态，INIT：初始化，PROCESS：处理中，SUCCESS：成功，FAIL：失败",nullable = false,length = 50)
    private String orderStatus;

    @MethodNote(note = "外部订单编号",nullable = false,length = 50)
    private String orderOrderId;

    @MethodNote(note = "金额",nullable = false,length = 15,precision = 3)
    private BigDecimal amount;

    @MethodNote(note = "备注",length = 200)
    private String memo;

    @MethodNote(note = "创建时间")
    private Date createAt;

    @MethodNote(note = "更新时间")
    private Date updateAt;

}
