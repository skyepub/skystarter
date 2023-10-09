package com.skytree.skystarter.repository;

import com.skytree.skystarter.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
