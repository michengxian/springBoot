package com.imooc.financial.bean;


import com.imooc.financial.tools.MethodNote;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


@Entity
@Data
@Table(name = "imooc_product")
public class ProductBean {

    @Id
    @MethodNote(note = "产品id", nullable = false, length = 50)
    private String id;

    @MethodNote(note = "产品名称", nullable = false, length = 50)
    private String name;

    @MethodNote(note = "起投金额", nullable = false, length = 15 ,precision = 3)
    private BigDecimal thresholdAmount;

    @MethodNote(note = "投资步长", nullable = false, length = 15 ,precision = 3)
    private BigDecimal stepAmount;

    @MethodNote(note = "锁定期", nullable = false)
    private Integer lockTerm;

    @MethodNote(note = "收益率，0-100百分比", nullable = false)
    private BigDecimal rewardRate;

    @MethodNote(note = "状态，AUDINTING-审核中，IN_SELL-销售中,LOCKED-暂停销售，FINISHED-已结束",nullable = false,length = 20)
    private String STATUS;

    @MethodNote(note = "备注", length = 50)
    private String memo;

    @MethodNote(note = "创建时间")
    private Date createAt;

    @MethodNote(note = "创建人", length = 20)
    private String createUser;

    @MethodNote(note = "更新时间")
    private Date updateAt;

    @MethodNote(note = "创建者", length = 20)
    private String updateUser;

    @Override
    public String toString() {
        return "ProductBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", thresholdAmount=" + thresholdAmount +
                ", stepAmount=" + stepAmount +
                ", lockTerm=" + lockTerm +
                ", rewardRate=" + rewardRate +
                ", STATUS='" + STATUS + '\'' +
                ", memo='" + memo + '\'' +
                ", createAt=" + createAt +
                ", createUser='" + createUser + '\'' +
                ", updateAt=" + updateAt +
                ", updateUser='" + updateUser + '\'' +
                '}';
    }
}
