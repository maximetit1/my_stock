package com.example.mystock.documents;

import java.math.BigDecimal;
import java.util.List;

public class Sale {
    private BigDecimal num;
    private BigDecimal stockId;
    private List<DocumentRow> documentRows;

    public BigDecimal getNum() {
        return num;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }

    public BigDecimal getStockId() {
        return stockId;
    }

    public void setStockId(BigDecimal stockId) {
        this.stockId = stockId;
    }

    public List<DocumentRow> getDocumentRows() {
        return documentRows;
    }

    public void setDocumentRows(List<DocumentRow> documentRows) {
        this.documentRows = documentRows;
    }
}
