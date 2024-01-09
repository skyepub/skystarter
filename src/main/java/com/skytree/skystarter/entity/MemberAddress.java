package com.skytree.skystarter.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "member_address")
public class MemberAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_address_id")
    long memberAddressId;

    @Column(name = "member_id")
    long memberId;

    String zipCode;
    String address1;
    String address2;
}
