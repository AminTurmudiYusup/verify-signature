package com.example.signature.repository;

import com.example.signature.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepsitory extends JpaRepository<Product, Long> {
}
