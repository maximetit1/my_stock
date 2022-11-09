package com.example.mystock.service;

import com.example.mystock.documents.GoodsReceipt;
import com.example.mystock.documents.DocumentRow;
import com.example.mystock.model.*;
import com.example.mystock.repository.*;
import com.example.mystock.сontroller.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class GoodsReceiptServiceImpl implements GoodsReceiptService{

    private StockRepository stockRepo;
    private ProductRepository productRepo;
    private ProdOnStockRepository prodOnStockRepo;

    @Autowired
    public GoodsReceiptServiceImpl(StockRepository stockRepo,ProductRepository productRepo,ProdOnStockRepository prodOnStockRepo) {
        this.stockRepo = stockRepo;
        this.productRepo = productRepo;
        this.prodOnStockRepo = prodOnStockRepo;
    }

    @Override
    public Response posting(GoodsReceipt goodsReceipt) {
        try {
            Optional<Stock> stock_opt = stockRepo.findById(goodsReceipt.getStockId());
            Stock stock = null;
            if (stock_opt.isPresent()) {
                stock = stock_opt.get();
            } else {
                throw new Exception("Склад не найден (id = " + goodsReceipt.getStockId() + ")");
            }

            List<DocumentRow> documentRows = goodsReceipt.getDocumentRows();
            for (DocumentRow row : documentRows) {
                Optional<Product> prod_opt = productRepo.findById(row.getProdId());
                Product product = null;
                if (prod_opt.isPresent()) {
                    product = prod_opt.get();
                } else {
                    throw new Exception("Товар не найден (id = " + row.getProdId() + ")");
                }


                ProdOnStock prodOnStock = null;
                //Поиск существующей записи
                List<ProdOnStock> prodOnStocks = prodOnStockRepo.getAllByStockAndProd(goodsReceipt.getStockId(),row.getProdId());

                if (prodOnStocks.isEmpty()) {
                    //Создание новой записи по размещению товара на складе
                    prodOnStock = new ProdOnStock();
                    prodOnStock.setProd(product);
                    prodOnStock.setStock(stock);
                    prodOnStock.setCount(row.getCount());
                } else {
                    //Изменение записи по размещению товара на складе
                    prodOnStock = prodOnStocks.get(0);
                    prodOnStock.setCount(prodOnStock.getCount().add(row.getCount()));
                }
                prodOnStockRepo.save(prodOnStock);

                //Обновление закупочной цены у товара
                product.setLastPurch(row.getPrice());
                productRepo.save(product);
            }
            return new Response("OK", "Поступление проведено");
        } catch (Exception ex) {
            return new Response("ERROR", ex.getMessage());
        }
    }
}
