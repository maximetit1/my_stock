package com.example.mystock.repository;

import com.example.mystock.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, BigDecimal> {
    @Query("SELECT a FROM Product a WHERE a.name like %:mask%")
    List<Product> getAllByNameContains(@Param("mask") String mask);
}
