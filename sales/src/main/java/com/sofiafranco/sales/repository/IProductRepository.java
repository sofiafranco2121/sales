
package com.sofiafranco.sales.repository;

import com.sofiafranco.sales.model.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository <Product, Long> {
    List<Product> findByAvailableQuantityLessThan(Double quantity);
}
