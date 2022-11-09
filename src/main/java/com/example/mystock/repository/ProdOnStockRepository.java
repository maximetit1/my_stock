package com.example.mystock.repository;

import com.example.mystock.model.ProdOnStock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProdOnStockRepository extends CrudRepository<ProdOnStock, BigDecimal> {
    @Query("SELECT a FROM ProdOnStock a WHERE a.stock.id=:stockId and a.prod.id = :prodId")
    List<ProdOnStock> getAllByStockAndProd(@Param("stockId") BigDecimal stockId, @Param("prodId") BigDecimal prodId);

    @Query("SELECT a FROM ProdOnStock a WHERE a.stock.name like %:mask%")
    List<ProdOnStock> getAllByStockContains(@Param("mask") String mask);
}
