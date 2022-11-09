package com.example.mystock.service;

import com.example.mystock.documents.DocumentRow;
import com.example.mystock.documents.Moving;
import com.example.mystock.model.ProdOnStock;
import com.example.mystock.model.Product;
import com.example.mystock.model.Stock;
import com.example.mystock.repository.ProdOnStockRepository;
import com.example.mystock.repository.ProductRepository;
import com.example.mystock.repository.StockRepository;
import com.example.mystock.сontroller.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class MoveServiceImpl implements MoveService{
    private StockRepository stockRepo;
    private ProductRepository productRepo;
    private ProdOnStockRepository prodOnStockRepo;

    @Autowired
    public MoveServiceImpl(StockRepository stockRepo, ProductRepository productRepo, ProdOnStockRepository prodOnStockRepo) {
        this.stockRepo = stockRepo;
        this.productRepo = productRepo;
        this.prodOnStockRepo = prodOnStockRepo;
    }

    /**
     * Перемещение товаров со скалада на склад
     * В дальнейшем можно использовать методы SaleService и GoodsReceiptService
     * На данный момент код вынесен отдельно для сторнирования списания при ошибке поступления
     * К тому же нет номеров докумета послупления и продажи.
     * @param moving - список товаров, их количество и склады
     * @return
     */
    @Override
    public Response posting(Moving moving) {
        try {
            Optional<Stock> stock_opt = stockRepo.findById(moving.getStockIdFrom());
            Stock stockFrom = null;
            if (stock_opt.isPresent()) {
                stockFrom = stock_opt.get();
            } else {
                throw new Exception("Склад не найден (id = " + moving.getStockIdFrom() + ")");
            }

            stock_opt = stockRepo.findById(moving.getStockIdTo());
            Stock stockTo = null;
            if (stock_opt.isPresent()) {
                stockTo = stock_opt.get();
            } else {
                throw new Exception("Склад не найден (id = " + moving.getStockIdTo() + ")");
            }

            List<DocumentRow> documentRows = moving.getDocumentRows();
            for (DocumentRow row : documentRows) {
                Optional<Product> prod_opt = productRepo.findById(row.getProdId());
                Product product = null;
                if (prod_opt.isPresent()) {
                    product = prod_opt.get();
                } else {
                    throw new Exception("Товар не найден (id = " + row.getProdId() + ")");
                }

                ProdOnStock prodOnStockFrom = null, prodOnStockTo = null;

                //Списание со склада
                List<ProdOnStock> prodOnStocksFrom = prodOnStockRepo.getAllByStockAndProd(moving.getStockIdFrom(),row.getProdId());
                if (prodOnStocksFrom.isEmpty())
                    throw new Exception("Товар " + product.getName() +" не найден на складе " + stockFrom.getName());
                else
                    prodOnStockFrom = prodOnStocksFrom.get(0);
                //проверки запаса на складе
                if (prodOnStockFrom.getCount().compareTo(row.getCount()) == -1)
                    throw new Exception("Запрашиваемое количество " + row.getCount() + " товара " + product.getName() + " недостаточно. На складе " + stockFrom.getName() + " остаток " + prodOnStockFrom.getCount());
                else if (prodOnStockFrom.getCount().compareTo(row.getCount())  == 0) {
                    prodOnStockFrom.setCount(BigDecimal.ZERO);//На случай сторнирования
                    prodOnStockRepo.delete(prodOnStockFrom);
                } else {
                    prodOnStockFrom.setCount(prodOnStockFrom.getCount().subtract(row.getCount()));
                    prodOnStockRepo.save(prodOnStockFrom);
                }


                //Поступление на склад
                try {
                    List<ProdOnStock> prodOnStocksTo = prodOnStockRepo.getAllByStockAndProd(moving.getStockIdTo(), row.getProdId());
                    if (prodOnStocksTo.isEmpty()) {
                        //Создание новой записи по размещению товара на складе
                        prodOnStockTo = new ProdOnStock();
                        prodOnStockTo.setProd(product);
                        prodOnStockTo.setStock(stockTo);
                        prodOnStockTo.setCount(row.getCount());
                    } else {
                        //Изменение записи по размещению товара на складе
                        prodOnStockTo = prodOnStocksTo.get(0);
                        prodOnStockTo.setCount(prodOnStockTo.getCount().add(row.getCount()));
                    }
                    prodOnStockRepo.save(prodOnStockTo);
                } catch (Exception ex) {//сторнируем списание
                    prodOnStockFrom.setCount(prodOnStockFrom.getCount().add(row.getCount()));
                    prodOnStockRepo.save(prodOnStockFrom);
                    return new Response("ERROR", "Перемещение сторнировано. Причина: " + ex.getMessage());
                }
            } //for

            return new Response("OK", "Перемещение проведено");
        } catch (Exception ex) {
            return new Response("ERROR", ex.getMessage());
        }
    }
}
