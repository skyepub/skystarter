package com.skytree.skystarter.repository;

import com.skytree.skystarter.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Member findByEmail(String username);
}
