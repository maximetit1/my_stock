package com.example.mystock.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "prod_on_stock")
public class ProdOnStock {
    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "prodOnStockIdSeq", sequenceName = "prod_on_stock_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prodOnStockIdSeq")
    private BigDecimal id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "prod_id", nullable = false)
    private Product prod;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock;

    @Column(name = "count", nullable = false, precision = 4)
    private BigDecimal count;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Product getProd() {
        return prod;
    }

    public void setProd(Product prod) {
        this.prod = prod;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

}