package com.market.projetovendas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.market.projetovendas.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
