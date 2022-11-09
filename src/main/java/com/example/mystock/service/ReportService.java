package com.example.mystock.service;

import com.example.mystock.model.ProdOnStock;
import com.example.mystock.model.Product;

import java.util.List;

public interface ReportService {
    List<Product> productReport(String mask);
    List<ProdOnStock> stockReport(String mask);
}