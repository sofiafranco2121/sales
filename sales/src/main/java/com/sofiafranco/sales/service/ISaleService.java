
package com.sofiafranco.sales.service;

import com.sofiafranco.sales.dto.SaleDTO;
import com.sofiafranco.sales.model.Client;
import com.sofiafranco.sales.model.Product;
import com.sofiafranco.sales.model.Sale;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface ISaleService {
    
    public List<Sale> getSales();

    //alta
    public void saveSale(Sale sale);

    //baja
    public void deleteSale(Long saleCode);

    //lectura de un solo objeto
    public Sale findSale(Long SaleCode);

    //edición/modificación
    public void editSale(Long originalSaleCode, Long newSaleCode,
                            LocalDate newSaleDate,
                            Double total,
                            Client newClient);

    public void editSale(Sale sale);
    
    public List<Product> getSaleProducts(Sale sale);

public Map<String, Object> getSalesByDate(LocalDate saleDate);

public Optional<SaleDTO> getSaleWithHighestAmount();

public void removeProductFromSale(Long saleId, Long productId);

public void addProductToSale(Long saleId, Long productId);
    
}
