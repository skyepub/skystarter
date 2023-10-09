package com.skytree.skystarter.repository;

import com.skytree.skystarter.entity.MemberAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberAddressRepository extends JpaRepository<MemberAddress,Long> {
}
