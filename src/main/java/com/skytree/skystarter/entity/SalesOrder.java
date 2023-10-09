package com.skytree.skystarter.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "salesorder")
public class SalesOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="salesorder_id")
    private long salesorderId;

    @Column(name="member_id")
    private long memberId;

    @Column(name = "create_date" )
    private LocalDateTime createDate;
}
