
package com.sofiafranco.sales.controller;

import com.sofiafranco.sales.dto.SaleDTO;
import com.sofiafranco.sales.model.Client;
import com.sofiafranco.sales.model.Product;
import com.sofiafranco.sales.model.Sale;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.sofiafranco.sales.service.ISaleService;

@RestController
public class SaleController {
    
    @Autowired
    private ISaleService saleServ;
    
    @GetMapping ("/sales")
    public List<Sale> getSales() {
        
        return saleServ.getSales();
    }
    
    @PostMapping("/sales/create")
    public String saveSale(@RequestBody Sale sale) {
        saleServ.saveSale(sale);
        return "The sale was created successfully.";
    }
    
    @DeleteMapping("/sales/delete/{saleCode}")
    public String deleteSale(@PathVariable Long saleCode) {
        saleServ.deleteSale(saleCode);
        return "The sale was deleted successfully.";
    }
    
    @GetMapping("/sales/find/{saleCode}")
    public Sale findSale(@PathVariable Long saleCode) {
        return saleServ.findSale(saleCode);
    }
    
    @PutMapping("/sales/edit/{saleCode}")
    public Sale editSale(@PathVariable Long saleCode,
                         @RequestParam(required = false, name = "saleCode") Long newSaleCode,
                         @RequestParam(required = false, name = "saleDate") LocalDate newSaleDate,
                         @RequestParam(required = false, name = "total") Double newTotal,
                         @RequestParam(required = false, name = "client") Client newClient) {
            
        saleServ.editSale(saleCode, newSaleCode, newSaleDate, newTotal, newClient);
        
        return saleServ.findSale(newSaleCode);
    }
    
    @PutMapping("/sales/edit")
    public Sale editSale(@RequestBody Sale sale) {
        saleServ.editSale(sale);
        return saleServ.findSale(sale.getSaleCode());
    }
    
    @GetMapping("/sales/products/{saleCode}")
    public List<Product> getSaleProducts(@PathVariable Long saleCode) {
        Sale sale = saleServ.findSale(saleCode);
        return saleServ.getSaleProducts(sale);
    }
    
    @GetMapping("/sales/sales-by-date/{saleDate}")
    public ResponseEntity<Map<String, Object>> getSalesByDate(@PathVariable String saleDate) {
        LocalDate date = LocalDate.parse(saleDate);
        Map<String, Object> result = saleServ.getSalesByDate(date);
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/sales/highest-sale")
    public SaleDTO getSaleWithHighestAmount() {
        return saleServ.getSaleWithHighestAmount()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No sales found."));
    }
    
    @DeleteMapping("/sales/remove-product/{saleCode}/{productCode}")
    public ResponseEntity<String> removeProductFromSale(@PathVariable Long saleCode, 
                                                        @PathVariable Long productCode) {
        saleServ.removeProductFromSale(saleCode, productCode);
        return ResponseEntity.ok("Product successfully removed from the sale.");
    }
    
    @PostMapping("/sales/add-product/{saleCode}/{productCode}")
    public ResponseEntity<String> addProductToSale(@PathVariable Long saleCode, 
                                                   @PathVariable Long productCode) {
        try {
            saleServ.addProductToSale(saleCode, productCode);
            return ResponseEntity.ok("Product successfully added.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    

    
}
