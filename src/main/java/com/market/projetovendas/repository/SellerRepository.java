package com.market.projetovendas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.market.projetovendas.model.Seller;

public interface SellerRepository extends JpaRepository<Seller, Long>{
    
}
