package com.example.mystock.service;

import com.example.mystock.documents.DocumentRow;
import com.example.mystock.documents.Sale;
import com.example.mystock.model.ProdOnStock;
import com.example.mystock.model.Product;
import com.example.mystock.model.Stock;
import com.example.mystock.repository.ProdOnStockRepository;
import com.example.mystock.repository.ProductRepository;
import com.example.mystock.repository.StockRepository;
import com.example.mystock.сontroller.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleServiceImpl implements SaleService{
    private StockRepository stockRepo;
    private ProductRepository productRepo;
    private ProdOnStockRepository prodOnStockRepo;

    @Autowired
    public SaleServiceImpl(StockRepository stockRepo,ProductRepository productRepo,ProdOnStockRepository prodOnStockRepo) {
        this.stockRepo = stockRepo;
        this.productRepo = productRepo;
        this.prodOnStockRepo = prodOnStockRepo;
    }

    @Override
    public Response posting(Sale sale) {
        try {
            Optional<Stock> stock_opt = stockRepo.findById(sale.getStockId());
            Stock stock = null;
            if (stock_opt.isPresent()) {
                stock = stock_opt.get();
            } else {
                throw new Exception("Склад не найден (id = " + sale.getStockId() + ")");
            }

            List<DocumentRow> documentRows = sale.getDocumentRows();
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
                List<ProdOnStock> prodOnStocks = prodOnStockRepo.getAllByStockAndProd(sale.getStockId(),row.getProdId());
                if (prodOnStocks.isEmpty())
                    throw new Exception("Товар " + product.getName() +" не найден на складе " + stock.getName());
                else
                    prodOnStock = prodOnStocks.get(0);

                //проверки запаса на складе
                if (prodOnStock.getCount().compareTo(row.getCount()) == -1)
                    throw new Exception("Запрашиваемое количество " + row.getCount() + " товара " + product.getName() + " недостаточно. На складе " + stock.getName() + " остаток " + prodOnStock.getCount());
                else if (prodOnStock.getCount().compareTo(row.getCount())  == 0) {
                    prodOnStockRepo.delete(prodOnStock);
                } else {
                    prodOnStock.setCount(prodOnStock.getCount().subtract(row.getCount()));
                    prodOnStockRepo.save(prodOnStock);
                }

                //Обновление цены продажи у товара
                product.setLastSale(row.getPrice());
                productRepo.save(product);
            }
            return new Response("OK", "Списание проведено");
        } catch (Exception ex) {
            return new Response("ERROR", ex.getMessage());
        }
    }
}
