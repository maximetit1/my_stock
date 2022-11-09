package com.example.mystock.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "productIdSeq", sequenceName = "product_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productIdSeq")
    private BigDecimal id;

    @Column(name = "art", nullable = false, precision = 8)
    private BigDecimal art;

//    @Lob
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "last_purch", precision = 8, scale = 2)
    private BigDecimal lastPurch;

    @Column(name = "last_sale", precision = 8, scale = 2)
    private BigDecimal lastSale;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getArt() {
        return art;
    }

    public void setArt(BigDecimal art) {
        this.art = art;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getLastPurch() {
        return lastPurch;
    }

    public void setLastPurch(BigDecimal lastPurch) {
        this.lastPurch = lastPurch;
    }

    public BigDecimal getLastSale() {
        return lastSale;
    }

    public void setLastSale(BigDecimal lastSale) {
        this.lastSale = lastSale;
    }
}