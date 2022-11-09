package com.example.mystock.service;

import com.example.mystock.documents.GoodsReceipt;
import com.example.mystock.сontroller.Response;

public interface GoodsReceiptService {
    /**
     * Поступление
     * @param goodsReceipt - список товаров, их количество и закупочные цены
     */
    Response posting(GoodsReceipt goodsReceipt);
}
