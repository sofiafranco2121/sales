
package com.sofiafranco.sales.service;

import com.sofiafranco.sales.dto.SaleDTO;
import com.sofiafranco.sales.model.Client;
import com.sofiafranco.sales.model.Product;
import com.sofiafranco.sales.model.Sale;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sofiafranco.sales.repository.IProductRepository;
import com.sofiafranco.sales.repository.ISaleRepository;

@Service
public class SaleService implements ISaleService {
    
    @Autowired
    private ISaleRepository saleRepo;
    
    @Autowired
    private IProductRepository productRepo;

    @Override
    public List<Sale> getSales() {
        return saleRepo.findAll();
    }

    @Override
    public void saveSale(Sale sale) {
        if (sale.getProductList() == null || sale.getProductList().isEmpty()) {
            throw new IllegalArgumentException("The sale must include at least one product.");
        }

        for (Product product : sale.getProductList()) {
            Product prod = productRepo.findById(product.getProductCode())
                    .orElseThrow(() -> new IllegalArgumentException("Product with ID " + product.getProductCode() + " does not exist."));

            if (prod.getAvailableQuantity() == null || prod.getAvailableQuantity() < 1) {
                throw new IllegalArgumentException("The product '" + prod.getName() + "' is out of stock.");
            }

            prod.setAvailableQuantity(prod.getAvailableQuantity() - 1);
            productRepo.save(prod);
        }

        saleRepo.save(sale);
    }

    @Override
    public void deleteSale(Long saleId) {
        saleRepo.deleteById(saleId);
    }

    @Override
    public Sale findSale(Long saleId) {
        return saleRepo.findById(saleId).orElse(null);
    }

    @Override
    public void editSale(Long originalCode,
                         Long newCode,
                         LocalDate newDate,
                         Double newTotal,
                         Client newClient) {
        Sale sale = this.findSale(originalCode);

        sale.setSaleCode(newCode);
        sale.setSaleDate(newDate);
        sale.setTotal(newTotal);
        sale.setClient(newClient);

        saleRepo.save(sale);
    }

    @Override
    public void editSale(Sale sale) {
        saleRepo.save(sale);
    }
    
    @Override
    public List<Product> getSaleProducts(Sale sale) {
        return sale.getProductList();
    }
    
    @Override
    public Map<String, Object> getSalesByDate(LocalDate saleDate) {
        List<Sale> sales = saleRepo.findBySaleDate(saleDate);

        double totalAmount = sales.stream().mapToDouble(Sale::getTotal).sum();
        int saleCount = sales.size();

        Map<String, Object> result = new HashMap<>();
        result.put("saleCount", saleCount);
        result.put("totalAmount", totalAmount);
        
        return result;
    }
    
    @Override
    public Optional<SaleDTO> getSaleWithHighestAmount() {
        return Optional.ofNullable(saleRepo.findSaleWithHighestTotal());
    }
    
    @Override
    @Transactional
    public void removeProductFromSale(Long saleId, Long productId) {
        Sale sale = saleRepo.findById(saleId)
                .orElseThrow(() -> new IllegalArgumentException("Sale with ID " + saleId + " does not exist."));

        Product productToRemove = sale.getProductList().stream()
                .filter(p -> p.getProductCode().equals(productId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Product with ID " + productId + " is not in this sale."));

        sale.getProductList().remove(productToRemove);

        productToRemove.setAvailableQuantity(productToRemove.getAvailableQuantity() + 1);
        productRepo.save(productToRemove);

        saleRepo.save(sale);
    }
    
    @Override
    @Transactional
    public void addProductToSale(Long saleId, Long productId) {
        Sale sale = saleRepo.findById(saleId)
                .orElseThrow(() -> new IllegalArgumentException("Sale not found"));

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        if (sale.getProductList().contains(product)) {
            throw new IllegalArgumentException("The product is already in the sale.");
        }

        if (product.getAvailableQuantity() <= 0) {
            throw new IllegalArgumentException("Not enough stock of the product.");
        }

        sale.getProductList().add(product);

        product.setAvailableQuantity(product.getAvailableQuantity() - 1);
        productRepo.save(product);

        saleRepo.save(sale);
    }
}
