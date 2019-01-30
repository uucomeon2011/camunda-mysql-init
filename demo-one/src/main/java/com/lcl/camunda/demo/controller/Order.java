package com.lcl.camunda.demo.controller;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Auther: liuchunli3
 * @Date: 2019/1/25 14:25
 * @Description:
 */
public class Order implements Serializable {
    private Long id;
    private Double money;
    private Integer productType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    @Override
    public String toString() {
        return this.id+"-"+this.money+"-"+this.productType;
    }
}
