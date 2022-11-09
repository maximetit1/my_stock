package com.example.mystock.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "stock")
public class Stock {
    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "stockIdSeq", sequenceName = "stock_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stockIdSeq")
    private BigDecimal id;

   // @Lob
    @Column(name = "name", nullable = false)
    private String name;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}