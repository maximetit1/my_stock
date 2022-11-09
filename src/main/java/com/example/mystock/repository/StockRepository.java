package com.example.mystock.repository;

import com.example.mystock.model.Stock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface StockRepository extends CrudRepository<Stock, BigDecimal> {
}
