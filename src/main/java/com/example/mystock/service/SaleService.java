package com.example.mystock.service;

import com.example.mystock.documents.Sale;
import com.example.mystock.сontroller.Response;

public interface SaleService {
    /**
     * Прордажа
     * @param sale - список товаров, их количество и цены продажи
     */
    Response posting(Sale sale);
}
