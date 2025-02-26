
package com.sofiafranco.sales.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Product {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long productCode;
    
    private String name;
    private String brand;
    private Double cost;
    
    
    @Column(name = "available_quantity")
    private Double availableQuantity;
    
    

    public Product() {
    }

    public Product(Long productCode, String name, String brand, Double cost, Double availableQuantity) {
        this.productCode = productCode;
        this.name = name;
        this.brand = brand;
        this.cost = cost;
        this.availableQuantity = availableQuantity;
    }

  
    
 
    
    
    
}
