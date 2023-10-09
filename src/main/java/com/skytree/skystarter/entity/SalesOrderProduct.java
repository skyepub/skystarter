package com.skytree.skystarter.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "salesorder_product")
public class SalesOrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="salesorder_product_id")
    private long salesorderProductId;

    @Column(name="salesorder_id")
    private long salesorderId;

    @Column(name="product_id")
    private long productId;

    @Column(name = "quantity" )
    private long quantity;
}

