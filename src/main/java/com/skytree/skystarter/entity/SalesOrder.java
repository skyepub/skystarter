package com.skytree.skystarter.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
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
