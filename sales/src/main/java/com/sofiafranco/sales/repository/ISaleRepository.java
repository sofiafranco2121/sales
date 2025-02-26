
package com.sofiafranco.sales.repository;

import com.sofiafranco.sales.dto.SaleDTO;
import com.sofiafranco.sales.model.Sale;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ISaleRepository extends JpaRepository<Sale, Long> {
    List<Sale> findBySaleDate(LocalDate saleDate);

    @Query("SELECT new com.sofiafranco.sales.dto.SaleDTO(s.saleCode, s.total, SIZE(s.productList), c.fname, c.lname) " +
           "FROM Sale s JOIN s.client c " +
           "ORDER BY s.total DESC")
    SaleDTO findSaleWithHighestTotal();
}
