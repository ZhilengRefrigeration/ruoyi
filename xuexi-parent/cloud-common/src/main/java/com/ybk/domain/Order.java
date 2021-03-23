package com.ybk.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "shop_order")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oid;

    private Integer uid;
    private String username;


    private Integer pid;
    private String pname;
    private Double pprice;//价格
    private String stock; //库存

    private Integer number;//购买数量
}
