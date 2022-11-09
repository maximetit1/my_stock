package com.example.mystock.service;

import com.example.mystock.model.ProdOnStock;
import com.example.mystock.model.Product;
import com.example.mystock.repository.ProdOnStockRepository;
import com.example.mystock.repository.ProductRepository;
import com.example.mystock.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService{
    private StockRepository stockRepo;
    private ProductRepository productRepo;
    private ProdOnStockRepository prodOnStockRepo;

    public ReportServiceImpl(StockRepository stockRepo, ProductRepository productRepo, ProdOnStockRepository prodOnStockRepo) {
        this.stockRepo = stockRepo;
        this.productRepo = productRepo;
        this.prodOnStockRepo = prodOnStockRepo;
    }

    @Override
    public List<Product> productReport(String mask) {
        return productRepo.getAllByNameContains(mask);
    }

    @Override
    public List<ProdOnStock> stockReport(String mask) {
        return prodOnStockRepo.getAllByStockContains(mask);
    }
}
