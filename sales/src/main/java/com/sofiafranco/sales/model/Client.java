
package com.sofiafranco.sales.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
@Entity
public class Client {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id_client;
    
    private String fname;
    private String lname;
    private String dni;
    
    

    public Client() {
    }

    public Client(Long id_client, String fname, String lname, String dni) {
        this.id_client = id_client;
        this.fname = fname;
        this.lname = lname;
        this.dni = dni;
    }

   
    
    
    
}
