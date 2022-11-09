package com.example.mystock.сontroller;

import com.example.mystock.documents.GoodsReceipt;
import com.example.mystock.documents.Moving;
import com.example.mystock.documents.Sale;
import com.example.mystock.model.ProdOnStock;
import com.example.mystock.model.Product;
import com.example.mystock.service.GoodsReceiptService;
import com.example.mystock.service.MoveService;
import com.example.mystock.service.ReportService;
import com.example.mystock.service.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@org.springframework.web.bind.annotation.RestController
public class RestController {

    private final GoodsReceiptService goodsReceiptService;
    private final SaleService saleService;
    private final MoveService moveService;
    private final ReportService reportService;


    public RestController(GoodsReceiptService goodsReceiptService, SaleService saleService, MoveService moveService, ReportService reportService) {
        this.goodsReceiptService = goodsReceiptService;
        this.saleService = saleService;
        this.moveService = moveService;
        this.reportService = reportService;
    }


    @PostMapping(value = "/goodsReceipt")
    public ResponseEntity<?> doGoodsReceipt(@RequestBody GoodsReceipt goodsReceipt) {
        Response response = goodsReceiptService.posting(goodsReceipt);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/sale")
    public ResponseEntity<?> doSale(@RequestBody Sale sale) {
        Response response = saleService.posting(sale);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/moving")
    public ResponseEntity<?> doMoving(@RequestBody Moving moving) {
        Response response = moveService.posting(moving);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/productReport")
    public ResponseEntity<?> productReport() {
        List<Product> response = reportService.productReport("");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/productReport/{mask}")
    public ResponseEntity<?> productReport(@PathVariable(name = "mask") String mask) {
        List<Product> response = reportService.productReport(mask);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/productReport")
    public ResponseEntity<?> productReportPost(@RequestBody Filter filter) {
        List<Product> response = reportService.productReport(filter.getMask());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/stockReport")
    public ResponseEntity<?> stockReport() {
        try {
            List<ProdOnStock> response = reportService.stockReport("");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/stockReport/{mask}")
    public ResponseEntity<?> stockReport(@PathVariable(name = "mask") String mask) {
        List<ProdOnStock> response = reportService.stockReport(mask);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/stockReport")
    public ResponseEntity<?> stockReportPost(@RequestBody Filter filter) {
        List<ProdOnStock> response = reportService.stockReport(filter.getMask());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
