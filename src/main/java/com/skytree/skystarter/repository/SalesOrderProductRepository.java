package com.skytree.skystarter.repository;

import com.skytree.skystarter.entity.SalesOrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesOrderProductRepository extends JpaRepository<SalesOrderProduct,Long> {
}
