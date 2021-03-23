package com.ybk.dao;

import com.ybk.domain.Product;
import com.ybk.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository<Product, Integer> {
}
