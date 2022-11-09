package com.example.mystock.documents;

import java.math.BigDecimal;
import java.util.List;

public class Moving {
    private BigDecimal num;
    private BigDecimal stockIdFrom;
    private BigDecimal stockIdTo;
    private List<DocumentRow> documentRows;

    public BigDecimal getNum() {
        return num;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }

    public BigDecimal getStockIdFrom() {
        return stockIdFrom;
    }

    public void setStockIdFrom(BigDecimal stockIdFrom) {
        this.stockIdFrom = stockIdFrom;
    }

    public BigDecimal getStockIdTo() {
        return stockIdTo;
    }

    public void setStockIdTo(BigDecimal stockIdTo) {
        this.stockIdTo = stockIdTo;
    }

    public List<DocumentRow> getDocumentRows() {
        return documentRows;
    }

    public void setDocumentRows(List<DocumentRow> documentRows) {
        this.documentRows = documentRows;
    }
}
