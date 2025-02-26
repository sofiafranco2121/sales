
package com.sofiafranco.sales.service;

import com.sofiafranco.sales.model.Product;
import java.util.List;


public interface IProductService {
    
    public List<Product> getProducts();

    
    public void saveProduct(Product product);

    
    public void deleteProduct(Long productCode);

    
    public Product findProduct(Long productCode);

    
    public void editProduct(Long originalCode, Long newProductCode,
                            String name,
                            String brand,
                            Double cost,
                            Double availableQuantity);
    
    public List<Product> getOutOfStockProducts();
    
}
