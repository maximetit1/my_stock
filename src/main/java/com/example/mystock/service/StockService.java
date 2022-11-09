package com.example.mystock.service;

import com.example.mystock.repository.StockRepository;
import org.springframework.stereotype.Service;

@Service
public class StockService {
    public StockService(StockRepository repo) {}
}
