
package com.sofiafranco.sales.controller;

import com.sofiafranco.sales.model.Product;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sofiafranco.sales.service.IProductService;

@RestController
public class ProductController {
    
    @Autowired
    private IProductService productServ;
    
    @GetMapping ("/products")
    public List<Product> getProducts() {
        
        return productServ.getProducts();
    }
    
    @PostMapping ("/products/create")
    public String saveProducto (@RequestBody Product product) {
        productServ.saveProduct(product);
        
        return "The product was created successfully";
    }
    
    @DeleteMapping ("/products/delete/{productCode}")
    public String deleteProducto(@PathVariable Long productCode) {
        productServ.deleteProduct(productCode);
        return "The product was successfully removed";
    }
    
    @GetMapping ("/products/{productCode}")
    public Product findProducto(@PathVariable Long productCode) {
        
        return productServ.findProduct(productCode);
    }
    
    
    @PutMapping ("/products/edit/{productCode}")
    public Product editProducto (@PathVariable Long productCode,
            @RequestParam(required = false, name = "productCode") Long newProductCode,
            @RequestParam(required = false, name = "name") String newName,
            @RequestParam(required = false, name = "brand") String newBrand,
            @RequestParam(required = false, name = "cost") Double newCost,
            @RequestParam(required = false, name = "availableQuantity") Double newavailableQuantity
    ) {
            
        productServ.editProduct(productCode, newProductCode, newName, newBrand, newCost, newavailableQuantity);
        
        Product product = productServ.findProduct(newProductCode);
        
        return product;

    }
    
  @GetMapping("products/low_stock")
public List<Product> getOutOfStockProducts() {
    return productServ.getOutOfStockProducts();
}
    
    
}
