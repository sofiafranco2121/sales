
package com.sofiafranco.sales.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Sale {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long saleCode;
    
    @Column(name = "sale_date") 
    private LocalDate saleDate;
    private Double total;
    
    @ManyToMany
    @JoinTable(
        name = "sale_product",
        joinColumns = @JoinColumn(name = "sale_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> productList;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Sale() {
    }

    public Sale(Long saleCode, LocalDate saleDate, Double total, List<Product> productList, Client client) {
        this.saleCode = saleCode;
        this.saleDate = saleDate;
        this.total = total;
        this.productList = productList;
        this.client = client;
    }

  

  

   
    
    
    
}
