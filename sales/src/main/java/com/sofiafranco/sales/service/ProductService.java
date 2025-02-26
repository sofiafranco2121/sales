
package com.sofiafranco.sales.service;

import com.sofiafranco.sales.model.Product;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sofiafranco.sales.repository.IProductRepository;

@Service
public class ProductService implements IProductService {
    
    @Autowired
    private IProductRepository repoProduct;

    @Override
    public List<Product> getProducts() {
        
    List<Product> productList = repoProduct.findAll();
    return productList;
    }

    @Override
    public void saveProduct(Product product) {
        repoProduct.save(product);
    }

    @Override
    public void deleteProduct(Long productCode) {
        repoProduct.deleteById(productCode);
    }

    @Override
    public Product findProduct(Long productCode) {
        return repoProduct.findById(productCode).orElse(null);
    }

    @Override
    public void editProduct(Long originalCode, Long newProductCode, String newName, 
            String newBrand, Double newCost, Double newavailableQuantity) {
        
        
            Product product = this.findProduct(originalCode);
            
            
            product.setProductCode(newProductCode);
            product.setName(newName);
            product.setBrand(newBrand);
            product.setCost(newCost);
            product.setAvailableQuantity(newavailableQuantity);
            
            
            this.saveProduct(product);
    }
    
    
      @Override
    public List<Product> getOutOfStockProducts() {
        return repoProduct.findByAvailableQuantityLessThan(5.0);
    }
    
    
    
}
