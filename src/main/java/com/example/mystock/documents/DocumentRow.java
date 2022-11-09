package com.example.mystock.documents;

import java.math.BigDecimal;

public class DocumentRow {
    BigDecimal prodId;
    BigDecimal count;
    BigDecimal price;

    public BigDecimal getProdId() {
        return prodId;
    }

    public void setProdId(BigDecimal prodId) {
        this.prodId = prodId;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
